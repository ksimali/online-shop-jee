package com.onlineshop.controleur;

import com.onlineshop.modele.Panier;
import com.onlineshop.modele.Produit;
import com.onlineshop.modele.ProduitPanier;
import com.onlineshop.service.CategorieDbService;
import com.onlineshop.service.PanierDbService;
import com.onlineshop.service.ProduitDbService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.io.IOException;
import java.util.List;

@WebServlet("/panier")
public class PanierServletControleur extends HttpServlet {

	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;
		
	// Declarations de références de ProduitDbService et CategorieDbService
	private ProduitDbService produitDbService;
    private CategorieDbService categorieDbService;
    private PanierDbService panierDbService;
	    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session et le panier de l'utilisateur
        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");

        // Si le panier n'existe pas, en créer un vide
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        // Récupérer la liste des produits dans le panier
        List<ProduitPanier> produitsDansPanier = panier.getProduits();

        // Calculer le total du panier (si nécessaire)
        double totalPanier = panier.calculerTotal();

        // Ajouter les informations du panier à la requête
        request.setAttribute("panier", panier);
        request.setAttribute("produitsDansPanier", produitsDansPanier);
        request.setAttribute("totalPanier", totalPanier);

        // Rediriger vers la page panier.jsp
        request.getRequestDispatcher("/pages/panier.jsp").forward(request, response);
    }
	    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session et le panier de l'utilisateur
        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");

        // Si le panier n'existe pas encore, en créer un
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier); 
        }

        // Identifier l'action à effectuer (ajouter, modifier, supprimer, acheter)
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendRedirect("generalError.jsp");
            return;
        }
        
        try {
        	
        	switch (action) {
	            case "ajouter":
	                ajouterProduitAuPanier(request, response, panier);
	                break;
	
	            case "modifier":
	                modifierQuantiteProduit(request, panier);
	                break;
	
	            case "supprimer":
	                supprimerProduitDuPanier(request, panier);
	                break;
	
	            case "acheter":
	                enregistrerPanierDansBd(request, response, panier);
	                break;
	
	            default:
	                response.sendRedirect("generalError.jsp");
	                return;
        	}
        	
        }catch(Exception ex) {
        	ex.printStackTrace();
            // Afficher une page d'erreur générique si une exception survient
            request.setAttribute("erreur", "Une erreur est survenue lors du traitement de votre demande.");
            request.getRequestDispatcher("generalError.jsp").forward(request, response);
            return;
        }
        

        // Redirection vers la page du panier après toute action
        //response.sendRedirect("panier.jsp");
        response.sendRedirect(request.getContextPath() + "/pages/panier.jsp");

    }

    // Methode pour ajouter un produit au panier
    private void ajouterProduitAuPanier(HttpServletRequest request, HttpServletResponse response, Panier panier) throws ServletException, IOException {
        try {
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            int quantite = 1; // La quantité est fixée à 1 par défaut
            String nomProduit = request.getParameter("nomProduit");
            double prixProduit = Double.parseDouble(request.getParameter("prixProduit"));

            // Créer le produit
            Produit produit = new Produit(produitId, nomProduit, "", prixProduit, "", 0);

            // Vérifier si le produit existe déjà dans le panier
            ProduitPanier produitPanierExist = panier.getProduitParId(produitId);

            if (produitPanierExist != null) {
                // Si le produit existe déjà, augmenter sa quantité de 1
                panier.modifierQuantite(produitId, produitPanierExist.getQuantite() + 1);
            } else {
                // Si le produit n'existe pas, l'ajouter avec une quantité de 1
                panier.ajouterProduit(produit, quantite);
            }

            System.out.println("Contenu du panier après ajout : " + panier.getProduits());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erreur", "Erreur lors de l'ajout du produit.");
            forwardToErrorPage(request, response);
        }
    }


    // Methode qui modifie la quantité d'un produit dans le panier
    private void modifierQuantiteProduit(HttpServletRequest request, Panier panier) {
        try {
            // Récupérer les paramètres
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            System.out.println("Produit ID : " + produitId);

            int nouvelleQuantite = Integer.parseInt(request.getParameter("quantite_" + produitId));
            System.out.println("Nouvelle quantité : " + nouvelleQuantite);

            // Vérification de la quantité et modification
            if (nouvelleQuantite <= 0) {
                panier.supprimerProduit(produitId);
            } else {
                panier.modifierQuantite(produitId, nouvelleQuantite);
            }

            System.out.println("Quantité modifiée pour le produit ID " + produitId);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format pour la quantité : " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Methode qui supprime un produit du panier
    private void supprimerProduitDuPanier(HttpServletRequest request, Panier panier) {
        try {
            // Récupérer l'ID du produit
            int produitId = Integer.parseInt(request.getParameter("produitId"));

            // Vérifier que le produit existe dans le panier
            ProduitPanier produitPanier = panier.getProduitParId(produitId);
            if (produitPanier != null) {
                // Supprimer le produit
                panier.supprimerProduit(produitId);
                System.out.println("Produit supprimé du panier : " + produitPanier.getProduit().getNom());
            } else {
                System.out.println("Tentative de suppression d'un produit introuvable : ID " + produitId);
            }
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format lors de la suppression d'un produit : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Ensuite on enregistre le panier dans la base de donnée au moment de l'achat
    private void enregistrerPanierDansBd(HttpServletRequest request, HttpServletResponse response, Panier panier) throws IOException {
        int clientId = Integer.parseInt(request.getParameter("clientId")); // Récupérer l'ID du client
        PanierDbService panierDbService = new PanierDbService((DataSource) getServletContext().getAttribute("dataSource"));

        // Tente d'enregistrer le panier en base de données
        int panierId = panierDbService.enregistrerPanier(panier, clientId);

        if (panierId != -1) {
            // Enregistre les produits du panier
            panierDbService.enregistrerProduitsDuPanier(panierId, panier.getProduits());
            
            // Panier enregistré avec succès, vider le panier de la session
            panier.getProduits().clear();
            panier.calculerTotal();
            response.sendRedirect("confirmation.jsp");
        } else {
            response.sendRedirect("erreur.jsp");
        }
    }
    
    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("generalError.jsp").forward(request, response);
    }
    
    

} 
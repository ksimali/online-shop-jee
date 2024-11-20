package com.onlineshop.controleur;

import com.onlineshop.modele.Panier;
import com.onlineshop.modele.Produit;
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

@WebServlet("/panier")
public class PanierServletControleur extends HttpServlet {

	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
		@Resource(name="jdbc/onlineshop_bd")
		private DataSource dataSource;
			
		// Declarations de références de ProduitDbService et CategorieDbService
		private ProduitDbService produitDbService;
	    private CategorieDbService categorieDbService;
	    
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

        switch (action) {
            case "ajouter":
                ajouterProduitAuPanier(request, panier);
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
                response.sendRedirect("erreur.jsp");
                return;
        }

        // Redirection vers la page du panier après toute action
        response.sendRedirect("panier.jsp");
    }

    private void ajouterProduitAuPanier(HttpServletRequest request, Panier panier) {
        int produitId = Integer.parseInt(request.getParameter("produitId"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        String nomProduit = request.getParameter("nomProduit");
        double prixProduit = Double.parseDouble(request.getParameter("prixProduit"));

        Produit produit = new Produit(produitId, nomProduit, "", prixProduit, "", 0);
        panier.ajouterProduit(produit, quantite);
    }

    private void modifierQuantiteProduit(HttpServletRequest request, Panier panier) {
        int produitId = Integer.parseInt(request.getParameter("produitId"));
        int nouvelleQuantite = Integer.parseInt(request.getParameter("quantite"));
        panier.modifierQuantite(produitId, nouvelleQuantite);
    }

    private void supprimerProduitDuPanier(HttpServletRequest request, Panier panier) {
        int produitId = Integer.parseInt(request.getParameter("produitId"));
        panier.supprimerProduit(produitId);
    }

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

} 
package com.onlineshop.controleur;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.onlineshop.modele.Categorie;
import com.onlineshop.modele.Commande;
import com.onlineshop.modele.Panier;
import com.onlineshop.service.CategorieDbService;
import com.onlineshop.service.CommandeService;

/**
 * Servlet implementation class EnregistrerCommandeServletControleur
 */
@WebServlet("/enregistrer")
public class EnregistrerCommandeServletControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;
    
	private CommandeService commandeService;
	private CategorieDbService categorieDbService;

	@Override
	public void init() throws ServletException {
	    try {
	        // Utilisation de JNDI pour récupérer la DataSource
	        InitialContext context = new InitialContext();
	        this.dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/onlineshop_bd");

	        if (this.dataSource == null) {
	            throw new IllegalStateException("La DataSource n'a pas pu être initialisée.");
	        }

	        this.commandeService = new CommandeService(dataSource);
	    } catch (NamingException e) {
	        throw new ServletException("Erreur lors de l'initialisation de la DataSource", e);
	    }
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerCommandeServletControleur() {
        super();
        categorieDbService = new CategorieDbService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Récupérer les catégories via les services
        List<Categorie> categories;
		try {
			categories = categorieDbService.getAllCategories();
			
			System.out.println("Catégories: " + categories);
	        
	        // Ajouter les produits et les catégories dans l'objet requête
	        request.setAttribute("categories", categories);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// Vérifier si l'utilisateur est connecté en utilisant la session
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        // Si l'utilisateur n'est pas connecté, rediriger vers la page de connexion
        if (userEmail == null) {
        	// Ajouter un message d'erreur à la requête
            request.setAttribute("loginToValidate", "Vous devez être connecté pour valider votre panier.");
            
            // Rediriger vers la page de login
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            return;
        }

        // Vérifier si le panier est vide
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null || panier.getProduits().isEmpty()) {
            // Si le panier est vide, ajouter un message d'information
            request.setAttribute("panierVide", "Votre panier est vide. Vous ne pouvez pas passer de commande.");
            
            // Rediriger vers la page panier pour que l'utilisateur puisse ajouter des produits
            request.getRequestDispatcher("/pages/panier.jsp").forward(request, response);
            return;
        }

        // Si tout va bien (utilisateur connecté et panier non vide), afficher la page de validation de commande
        request.getRequestDispatcher("/pages/validationCommande.jsp").forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Récupérer la date actuelle comme date de commande
	        Date dateCommande = new Date(System.currentTimeMillis());

	        // Récupérer et traiter la dateLivraison
	        String dateLivraisonParam = request.getParameter("dateLivraison");
	        Date dateLivraison = null;
	        
	        if (dateLivraisonParam != null && !dateLivraisonParam.isEmpty()) {
	            try {
	                dateLivraison = Date.valueOf(dateLivraisonParam);
	            } catch (IllegalArgumentException e) {
	                // Si la date est invalide, rediriger vers la page d'erreur
	                response.sendRedirect(request.getContextPath() + "/pages/erreurCommande.jsp");
	                return; // Sortir de la méthode pour ne pas continuer
	            }
	        } else {
	            // Si la dateLivraison est vide, rediriger vers la page d'erreur
	            response.sendRedirect(request.getContextPath() + "/pages/erreurCommande.jsp");
	            return;
	        }

	        // Récupération des autres paramètres du formulaire
	        String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String telephone = request.getParameter("telephone");
	        String courriel = request.getParameter("courriel");
	        String adresse = request.getParameter("adresse");
	        String ville = request.getParameter("ville");
	        String province = request.getParameter("province");
	        String pays = request.getParameter("pays");
	        String codePostal = request.getParameter("codePostal");
	        String adresseLivraison = request.getParameter("adresseLivraison");
	        String villeLivraison = request.getParameter("villeLivraison");
	        String provinceLivraison = request.getParameter("provinceLivraison");
	        String paysLivraison = request.getParameter("paysLivraison");
	        String codePostalLivraison = request.getParameter("codePostalLivraison");
	        String numeroCarteCredit = request.getParameter("numeroCarteCredit");
	        String dateExpirationParam = request.getParameter("dateExpiration");
	        
	        // Traitement de la date d'expiration (au format MM/AA)
	        Date dateExpiration = null;
	        if (dateExpirationParam != null && !dateExpirationParam.isEmpty()) {
	            try {
	                String[] expirationParts = dateExpirationParam.split("/");
	                String formattedExpirationDate = "20" + expirationParts[1] + "-" + expirationParts[0] + "-01"; // "20" pour le siècle
	                dateExpiration = Date.valueOf(formattedExpirationDate);
	            } catch (IllegalArgumentException e) {
	                // Si la date d'expiration est invalide, rediriger vers la page d'erreur
	                response.sendRedirect(request.getContextPath() + "/pages/erreurCommande.jsp");
	                return; // Sortir de la méthode pour ne pas continuer
	            }
	        }

	        String ccv = request.getParameter("ccv");

	        // Créer un objet Commande
	        Commande commande = new Commande(dateCommande, dateLivraison, nom, prenom, telephone, 
	                                          courriel, adresse, ville, province, pays, codePostal, 
	                                          adresseLivraison, villeLivraison, provinceLivraison, 
	                                          paysLivraison, codePostalLivraison, numeroCarteCredit, 
	                                          dateExpiration, ccv);
	        
	        // Enregistrer la commande via le service
	        boolean isCommandeEnregistree = commandeService.enregistrerCommande(commande);
	        
	        // Rediriger ou afficher un message en fonction du résultat
	        if (isCommandeEnregistree) {
	        	// Vider le panier dans la session
	            HttpSession session = request.getSession();
	            session.removeAttribute("panier");
	            
	            // rediriger vers la page de confirmation
	            // Ajouter les informations de la commande comme attributs de la requête
	            request.setAttribute("commande", commande);

	            // Rediriger vers la page de confirmation (en mode forward)
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/commandeConfirmation.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            response.sendRedirect(request.getContextPath() + "/pages/erreurCommande.jsp"); // page d'erreur
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect(request.getContextPath() + "/pages/erreurCommande.jsp");
	    }
	}

	


}

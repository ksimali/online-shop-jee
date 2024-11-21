package com.onlineshop.controleur;

import java.io.IOException;
import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.onlineshop.modele.Commande;
import com.onlineshop.modele.Panier;
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

    @Override
    public void init() throws ServletException {
        // Initialisation de la DataSource (injection par le conteneur servlet)
        DataSource dataSource = (DataSource) getServletContext().getAttribute("jdbc/onlineshop_bd");
        commandeService = new CommandeService(dataSource);
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerCommandeServletControleur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si l'utilisateur est connecté en utilisant la session
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("clientId");

        // Si l'utilisateur n'est pas connecté, rediriger vers la page de connexion
        if (clientId == null) {
        	// Ajouter un message d'erreur à la requête
            request.setAttribute("message", "Vous devez être connecté pour valider votre panier.");
            
            // Rediriger vers la page de login
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            return;
        }

        // Vérifier si le panier est vide
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null || panier.getProduits().isEmpty()) {
            // Si le panier est vide, ajouter un message d'information
            request.setAttribute("message", "Votre panier est vide. Vous ne pouvez pas passer de commande.");
            
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
            // Récupérer les données du formulaire
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            Date dateCommande = Date.valueOf(request.getParameter("dateCommande"));
            Date dateLivraison = Date.valueOf(request.getParameter("dateLivraison"));
            double total = Double.parseDouble(request.getParameter("total"));
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
            Date dateExpiration = Date.valueOf(request.getParameter("dateExpiration"));
            String ccv = request.getParameter("ccv");
            // Créer un objet Commande
            Commande commande = new Commande(clientId, dateCommande, dateLivraison, total, nom, prenom, telephone, 
                                              courriel, adresse, ville, province, pays, codePostal, 
                                              adresseLivraison, villeLivraison, provinceLivraison, 
                                              paysLivraison, codePostalLivraison, numeroCarteCredit, 
                                              dateExpiration, ccv);
            
            // Enregistrer la commande via le service
            boolean isCommandeEnregistree = commandeService.enregistrerCommande(commande);
            
            // Rediriger ou afficher un message en fonction du résultat
            if (isCommandeEnregistree) {
                response.sendRedirect("/pages/commandeConfirmation.jsp"); // page de confirmation
            } else {
                response.sendRedirect("/pages/erreurCommande.jsp"); // page d'erreur
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/pages/erreurCommande.jsp");
        }
    }

}
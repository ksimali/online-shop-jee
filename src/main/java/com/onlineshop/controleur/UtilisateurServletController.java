 package com.onlineshop.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlineshop.modele.Categorie;
import com.onlineshop.modele.Client;
import com.onlineshop.service.CategorieDbService;
import com.onlineshop.service.UtilisateurDbService;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Servlet implementation class UtilisateurServletController
 */
@WebServlet("/auth")
public class UtilisateurServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtilisateurDbService userService;
    private CategorieDbService categorieDbService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurServletController() {
        super();
        userService = new UtilisateurDbService();
        categorieDbService = new CategorieDbService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
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

        if ("login".equals(action)) {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        } else if ("register".equals(action)) {
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        } else if ("gcompte".equals(action)) {
            handleGcompte(request, response);
        } else if ("logout".equals(action)) {
            handleLogout(request, response);
        } else {
            response.sendRedirect("/pages/home.jsp");  // Redirection vers la page d'accueil par défaut
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        
        if ("login".equals(action)) {
            handleLogin(request, response);
        } else if ("register".equals(action)) {
            handleRegister(request, response);
        } else if ("update".equals(action)) {
            handleUpdate(request, response);
        }
	}
	
	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer la session de l'utilisateur (sans la créer si elle n'existe pas)
	    HttpSession session = request.getSession(false); // false signifie que la session n'est pas créée si elle n'existe pas

	    if (session != null) {
	        // Invalider la session pour supprimer toutes les données associées
	        session.invalidate();
	    }

	    // Rediriger l'utilisateur vers la page d'accueil ou la page de connexion
	    response.sendRedirect(request.getContextPath() + "/home");
	}
	
	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pwd");
        String type = request.getParameter("type");
        
        if (userService.loginUser(email, password, type)) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
            session.setAttribute("userType", type);
            if(type.equals("admin")) {
            	request.getRequestDispatcher("/pages/accueilAdmin.jsp").forward(request, response); // Page après une connexion réussie
            } else if (type.equals("client")) {
            	Client client = userService.getClientByEmail(email);
            	session.setAttribute("userName", client.getPrenom() + ", " + client.getNom());

            	//request.setAttribute("userEmail", email);
            	response.sendRedirect(request.getContextPath() + "/home");
            	//request.getRequestDispatcher("/pages/home.jsp").forward(request, response); // Page après une connexion réussie
            } else {
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }

	private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupération des paramètres du formulaire
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String email = request.getParameter("email");
	    String confirmEmail = request.getParameter("confirm_email");
	    String password = request.getParameter("pwd");
	    String confirmPassword = request.getParameter("confirm_pwd");
	    String dateNaissance = request.getParameter("date_naissance");
	    String telephone = request.getParameter("telephone");
	    String adresseDomicile = request.getParameter("adresse_domicile");
	    String villeDomicile = request.getParameter("ville_domicile");
	    String provinceDomicile = request.getParameter("province_domicile");
	    String paysDomicile = request.getParameter("pays_domicile");
	    String codePostalDomicile = request.getParameter("codepostal_domicile");
	    String adresseLivraison = request.getParameter("adresse_livraison");
	    String villeLivraison = request.getParameter("ville_livraison");
	    String provinceLivraison = request.getParameter("province_livraison");
	    String paysLivraison = request.getParameter("pays_livraison");
	    String codePostalLivraison = request.getParameter("codepostal_livraison");
	    
	    if(email==null ||  email.isEmpty() || (email!=null && !email.equalsIgnoreCase(confirmEmail))) {
	    	request.setAttribute("errorMessage", "L'email n'est pas valide.");
	        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
	    } else if(password==null || password.isEmpty() || (password!=null && !password.equals(confirmPassword))) {
	    	request.setAttribute("errorMessage", "Le mot de passe n'est pas valide.");
	        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
	    }

	    // Vérification si l'utilisateur existe déjà
	    boolean userExists = userService.userExists(email);

	    if (userExists) {
	        // Si l'utilisateur existe déjà, on renvoie un message d'erreur
	        request.setAttribute("errorMessage", "Un utilisateur avec ce courriel existe déjà.");
	        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
	    } else {
	    	// Convertir la chaîne dateNaissanceStr en java.sql.Date
	        Date dateNaissanceConverti = null;
	        if (dateNaissance != null && !dateNaissance.isEmpty()) {
	            try {
	                // Le format de la date dans le formulaire (ajustez si nécessaire)
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                java.util.Date parsedDate = dateFormat.parse(dateNaissance);
	                dateNaissanceConverti = new Date(parsedDate.getTime());  // Convertir en java.sql.Date
	            } catch (ParseException e) {
	            	request.setAttribute("errorMessage", "La date entree ne respecte pas le format: yyyy-MM-dd");
	    	        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
	            }
	        }
	        
	        // Si l'utilisateur n'existe pas, on hache le mot de passe et on enregistre l'utilisateur
	        // Enregistrer les informations de l'utilisateur
	    	boolean isCreate = userService.registerClient(firstName, lastName, email, password, dateNaissanceConverti, telephone, 
	                adresseDomicile, villeDomicile, provinceDomicile, paysDomicile, codePostalDomicile, 
	                adresseLivraison, villeLivraison, provinceLivraison, paysLivraison, codePostalLivraison);
	        if(isCreate) {
	        	HttpSession session = request.getSession();
	        	session.setAttribute("userName", firstName + ", " + lastName);
	        	session.setAttribute("userEmail", email);
	            session.setAttribute("userType", "client");
	        	response.sendRedirect(request.getContextPath()+"/home?newUser=1");
	        	//request.getRequestDispatcher("home").forward(request, response);
	        } else {
            	request.setAttribute("errorMessage", "Une erreur s'est produite lors de la creation de l'utilisateur.");
	        	request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
	        }
	        // Redirection vers la page de connexion
	        //response.sendRedirect("/auth?action=login");
	    }
	}
	
	private void handleGcompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur de la session
        HttpSession session = request.getSession();
        
        String email = (String) session.getAttribute("userEmail");
        Client client = null;
        
        if(email!=null && !email.isEmpty()) {
        	client = userService.getClientByEmail(email);
        }

        if (client != null) {
            request.setAttribute("client", client);  // Passer les informations du client au JSP
            request.getRequestDispatcher("/pages/gCompte.jsp").forward(request, response); // Afficher le formulaire de gestion de compte
        } else {
            response.sendRedirect(request.getContextPath()+ "/auth?action=gcompte");  // Rediriger vers la page de login si l'utilisateur n'est pas connecté
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les informations du formulaire de modification
        Client client = new Client();
        	int clientId = Integer.parseInt(request.getParameter("clientId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
    	    String confirmEmail = request.getParameter("confirm_email");
            String telephone = request.getParameter("telephone");
    	    String password = request.getParameter("pwd");
    	    String confirmPassword = request.getParameter("confirm_pwd");
    	    String dateNaissance = request.getParameter("date_naissance");
            String adresseDomicile = request.getParameter("adresse_domicile");
            String villeDomicile = request.getParameter("ville_domicile");
            String provinceDomicile = request.getParameter("province_domicile");
            String paysDomicile = request.getParameter("pays_domicile");
            String codePostalDomicile = request.getParameter("codepostal_domicile");
            String adresseLivraison = request.getParameter("adresse_livraison");
            String villeLivraison = request.getParameter("ville_livraison");
            String provinceLivraison = request.getParameter("province_livraison");
            String paysLivraison = request.getParameter("pays_livraison");
            String codePostalLivraison = request.getParameter("codepostal_livraison");
            
            if(email==null ||  email.isEmpty() || (email!=null && !email.equalsIgnoreCase(confirmEmail))) {
    	    	request.setAttribute("errorMessage", "L'email n'est pas valide.");
    	        request.getRequestDispatcher("auth?action=gcompte").forward(request, response);
    	    } else if(password==null || password.isEmpty() || (password!=null && !password.equals(confirmPassword))) {
    	    	request.setAttribute("errorMessage", "Le mot de passe n'est pas valide.");
    	        request.getRequestDispatcher("auth?action=gcompte").forward(request, response);
    	    }
            
         // Convertir la chaîne dateNaissanceStr en java.sql.Date
	        Date dateNaissanceConverti = null;
	        if (dateNaissance != null && !dateNaissance.isEmpty()) {
	            try {
	                // Le format de la date dans le formulaire (ajustez si nécessaire)
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                java.util.Date parsedDate = dateFormat.parse(dateNaissance);
	                dateNaissanceConverti = new Date(parsedDate.getTime());  // Convertir en java.sql.Date
	            } catch (ParseException e) {
	            	request.setAttribute("errorMessage", "La date entree ne respecte pas le format: yyyy-MM-dd");
	    	        response.sendRedirect(request.getContextPath() + "/auth?action=gcompte");
	            }
	        }
	        

            // Mettre à jour les informations dans la base de données
            boolean isUpdated = userService.updateClient(clientId, firstName, lastName, email, password, dateNaissanceConverti, telephone,
                    adresseDomicile, villeDomicile, provinceDomicile, paysDomicile, codePostalDomicile,
                    adresseLivraison, villeLivraison, provinceLivraison, paysLivraison, codePostalLivraison);

            if (isUpdated) {
                client.setPrenom(firstName);  // Mettre à jour l'objet client dans la session
                client.setNom(lastName);
                client.setEmail(email);
                client.setTelephone(telephone);
                client.setAdresseDomicile(adresseDomicile);
                client.setVilleDomicile(villeDomicile);
                client.setProvinceDomicile(provinceDomicile);
                client.setPaysDomicile(paysDomicile);
                client.setCodepostalDomicile(codePostalDomicile);
                client.setAdresseLivraison(adresseLivraison);
                client.setVilleLivraison(villeLivraison);
                client.setProvinceLivraison(provinceLivraison);
                client.setPaysLivraison(paysLivraison);
                client.setCodepostalLivraison(codePostalLivraison);
                client.setMotDePasse(password);
                
                request.setAttribute("client", client);  // Mettre à jour l'objet client dans la session
                request.setAttribute("successMessage", "Vos informations ont été mises à jour avec succès.");
                
                HttpSession session = request.getSession();
	        	session.setAttribute("userName", firstName + ", " + lastName);
                
	        	response.sendRedirect(request.getContextPath()+"/home?newUpdate=1");
	        } else {
                request.setAttribute("errorMessage", "Une erreur s'est produite lors de la mise à jour des informations.");
    	        response.sendRedirect(request.getContextPath() + "/auth?action=gcompte");
            }
    }
}

package com.onlineshop.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.onlineshop.service.UtilisateurDbService;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class UtilisateurServletController
 */
@WebServlet("/auth")
public class UtilisateurServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtilisateurDbService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurServletController() {
        super();
        userService = new UtilisateurDbService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

        if ("login".equals(action)) {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        } else if ("register".equals(action)) {
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");  // Redirection vers la page d'accueil par défaut
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
        }
	}
	
	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pwd");
        String type = request.getParameter("type");
        
        if (userService.loginUser(email, password, type)) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
            if(type.equals("admin")) {
            	request.getRequestDispatcher("/pages/accueilAdmin.jsp").forward(request, response); // Page après une connexion réussie
            } else if (type.equals("client")) {
            	request.getRequestDispatcher("/pages/produits.jsp").forward(request, response); // Page après une connexion réussie
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
	        	request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	        } else {
            	request.setAttribute("errorMessage", "Une erreur s'est produite lors de la creation de l'utilisateur.");
	        	request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
	        }
	        // Redirection vers la page de connexion
	        //response.sendRedirect("/auth?action=login");
	    }
	}
}

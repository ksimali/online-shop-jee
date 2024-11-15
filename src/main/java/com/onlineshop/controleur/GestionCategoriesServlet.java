package com.onlineshop.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineshop.modele.Categorie;
import com.onlineshop.service.GestionCategoriesDBService;

/**
 * Servlet implementation class GestionCategoriesServlet
 */
@WebServlet("/gcategories")
public class GestionCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private GestionCategoriesDBService gestionCategoriesDBService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCategoriesServlet() {
        super();
        gestionCategoriesDBService = new GestionCategoriesDBService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // Récupérer la liste des catégories depuis la base de données
            List<Categorie> categories = gestionCategoriesDBService.getAllCategories();

            // Placer la liste des catégories dans l'attribut de la requête
            request.setAttribute("categories", categories);
            request.setCharacterEncoding("UTF-8");

            // Rediriger vers la page JSP pour afficher les catégories
            request.getRequestDispatcher("/pages/gcategories.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur de récupération des catégories");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String nom, description; int id;
		
        try {
        	if(action.equals("create")) {
    			nom = request.getParameter("nom");
    	        description = request.getParameter("description");
    	        
    	     // Ajouter la nouvelle catégorie dans la base de données
                gestionCategoriesDBService.addCategorie(nom, description);
    		} else if(action.equals("update")) {
    			id = Integer.parseInt(request.getParameter("id"));
    			nom = request.getParameter("nom");
    	        description = request.getParameter("description");
    	        
    	     // Mettre a jour une catégorie dans la base de données
                gestionCategoriesDBService.updateCategorie(id, nom, description);
    		} else if(action.equals("delete")) {
    			
    		} else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Action inconnue!");
    		}
            
            // Rediriger vers la même page pour afficher la liste mise à jour
        	response.sendRedirect(request.getContextPath() + "/gcategories");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur d'ajout de la catégorie");
        }
	}

}

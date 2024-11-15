package com.onlineshop.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.onlineshop.modele.Categorie;
import com.onlineshop.modele.Produit;
import com.onlineshop.service.CategorieDbService;
import com.onlineshop.service.ProduitDbService;

/**
 * Servlet implementation class HomeServletControleur
 */
@WebServlet("/Home")
public class HomeServletControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;
		
	// Declarations de références de ProduitDbService et CategorieDbService
	private ProduitDbService produitDbService;
    private CategorieDbService categorieDbService;
    
    public HomeServletControleur() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
        
       try {
    	// Initialisation des services avec l'injection de la DataSource
    	// Injection explicite de la DataSource dans les services
           produitDbService = new ProduitDbService(dataSource);
           categorieDbService = new CategorieDbService(dataSource);
           
       } catch(Exception ex) {
    	   throw new ServletException("Erreur lors de l'initialisation des services", ex);
       }
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            // Récupérer les produits et les catégories via les services
            List<Produit> produits = produitDbService.getAllProduits();
            List<Categorie> categories = categorieDbService.getAllCategories();
            
            System.out.println("Produits: " + produits);
            System.out.println("Catégories: " + categories);
            
            // Ajouter les produits et les catégories dans l'objet requête
            request.setAttribute("produits", produits);
            request.setAttribute("categories", categories);
            
            // Rediriger vers la page d'accueil (home.jsp)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Gestion des erreurs
            request.setAttribute("error", "Erreur survenue lors de la récupération des données.");
            request.getRequestDispatcher("/databaseError.jsp").forward(request, response);
        } catch (Exception ex) {
			ex.printStackTrace();
			request.setAttribute("error", "Une erreur inconnue est survenue.");
            request.getRequestDispatcher("/generalError.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

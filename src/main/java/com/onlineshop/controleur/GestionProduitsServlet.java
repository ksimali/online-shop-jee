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
import com.onlineshop.modele.Produit;
import com.onlineshop.service.ProduitDbService;
import com.onlineshop.service.CategorieDbService;

@WebServlet("/gproduits")
public class GestionProduitsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProduitDbService produitDbService;
    private CategorieDbService categorieDbService;

    public GestionProduitsServlet() {
        super();
        produitDbService = new ProduitDbService();
        categorieDbService = new CategorieDbService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer la liste des produits
            List<Produit> produits = produitDbService.getAllProduitsWithCategorieNom();

            // Passer les produits à la JSP
            request.setAttribute("produits", produits);

            // Passer la liste des catégories (avec leur id et nom)
            List<Categorie> categories = categorieDbService.getAllCategories();
            request.setAttribute("categories", categorieDbService.getAllCategories());

            // Afficher la page JSP
            request.getRequestDispatcher("/pages/gproduits.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des produits");
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite!");
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            // Traitement selon l'action
            if (action == null) {
                response.sendRedirect(request.getContextPath() + "/gproduits"); // Rediriger vers la page de gestion
                return;
            }

            switch (action) {
                case "create":
                    createProduit(request);
                    break;
                case "update":
                    updateProduit(request);
                    break;
                case "delete":
                    deleteProduit(request);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/gproduits");
                    return;
            }

            // Rediriger vers la page de gestion des produits après l'action
            response.sendRedirect(request.getContextPath() + "/gproduits");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la gestion du produit");
        }
    }

    private void createProduit(HttpServletRequest request) throws SQLException {
        String nom = request.getParameter("nom");
        double prix = Double.parseDouble(request.getParameter("prix"));
        String image = request.getParameter("image");
        int categorieId = Integer.parseInt(request.getParameter("categorieId")); // Utilisation de l'ID de catégorie

        Produit produit = new Produit(nom, prix, image, categorieId);
        produitDbService.addProduit(produit);
    }

    private void updateProduit(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        double prix = Double.parseDouble(request.getParameter("prix"));
        String image = request.getParameter("image");
        int categorieId = Integer.parseInt(request.getParameter("categorieId")); // Utilisation de l'ID de catégorie

        Produit produit = new Produit(id, nom, prix, image, categorieId);
        produitDbService.updateProduit(produit);
    }

    private void deleteProduit(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        produitDbService.deleteProduit(id);
    }
}

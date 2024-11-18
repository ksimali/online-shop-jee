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

import com.onlineshop.modele.Produit;
import com.onlineshop.modele.ProduitPanier;
import com.onlineshop.service.PanierDbService;
import com.onlineshop.service.ProduitDbService;

@WebServlet("/Panier")
public class PanierServletControleur extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/onlineshop_bd")
    private DataSource dataSource;

    private PanierDbService panierDbService;
    private ProduitDbService produitDbService;

    public PanierServletControleur() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            panierDbService = new PanierDbService(dataSource);
            produitDbService = new ProduitDbService(dataSource);
        } catch (Exception ex) {
            throw new ServletException("Erreur lors de l'initialisation des services", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les produits du panier via la méthode getProduitsDansPanier
            List<ProduitPanier> produitsDansPanier = panierDbService.getProduitsDansPanier();
            request.setAttribute("produits", produitsDansPanier);

            if (produitsDansPanier.isEmpty()) {
                request.setAttribute("message", "Votre panier est vide.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/panier.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Erreur lors de la récupération des produits dans le panier.");
            request.getRequestDispatcher("/databaseError.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Une erreur inconnue est survenue.");
            request.getRequestDispatcher("/generalError.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer l'action du formulaire
            String action = request.getParameter("action");
            int produitId = request.getParameter("produitId") != null ? Integer.parseInt(request.getParameter("produitId")) : -1;
            int quantite = request.getParameter("quantite") != null ? Integer.parseInt(request.getParameter("quantite")) : 1;

            if ("ajouter".equals(action) && produitId != -1) {
                // Ajouter ou mettre à jour le produit dans le panier
                panierDbService.ajouterOuMettreAJourProduit(produitId, quantite);
            } else if ("supprimer".equals(action) && produitId != -1) {
                // Supprimer le produit du panier
                panierDbService.supprimerProduitDuPanier(produitId);
            } else if ("mettreAJourQuantite".equals(action) && produitId != -1 && quantite > 0) {
                // Mettre à jour la quantité du produit dans le panier
                panierDbService.modifierQuantiteProduit(produitId, quantite);
            }

            // Rediriger pour éviter le double-submit
            response.sendRedirect("Panier");

        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Erreur lors de la mise à jour du panier.");
            request.getRequestDispatcher("/databaseError.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Une erreur inconnue est survenue.");
            request.getRequestDispatcher("/generalError.jsp").forward(request, response);
        }
    }
}

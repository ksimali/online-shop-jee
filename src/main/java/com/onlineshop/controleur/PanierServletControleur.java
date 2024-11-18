package com.onlineshop.controleur;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import com.onlineshop.modele.ProduitPanier;
import com.onlineshop.service.PanierDbService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/panier")
public class PanierServletControleur extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PanierDbService panierDbService;

    public PanierServletControleur() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        panierDbService = new PanierDbService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            // Récupérer les produits du panier depuis la session
            List<ProduitPanier> produitsDansPanier = panierDbService.getProduitsDansPanier(session);
            request.setAttribute("produits", produitsDansPanier);

            if (produitsDansPanier.isEmpty()) {
                request.setAttribute("message", "Votre panier est vide.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/panier.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Une erreur inconnue est survenue.");
            request.getRequestDispatcher("/generalError.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            // Récupérer l'action du formulaire
            String action = request.getParameter("action");
            int produitId = request.getParameter("produitId") != null ? Integer.parseInt(request.getParameter("produitId")) : -1;
            int quantite = request.getParameter("quantite") != null ? Integer.parseInt(request.getParameter("quantite")) : 1;

            // Validation des paramètres
            if (produitId == -1 || quantite < 1) {
                request.setAttribute("error", "Produit ID ou quantité invalide.");
                request.getRequestDispatcher("/generalError.jsp").forward(request, response);
                return;
            }

            // Vérifier et effectuer l'action demandée
            switch (action) {
                case "ajouter":
                    panierDbService.ajouterOuMettreAJourProduit(session, produitId, quantite);
                    break;
                case "supprimer":
                    panierDbService.supprimerProduitDuPanier(session, produitId);
                    break;
                case "mettreAJourQuantite":
                    if (quantite > 0) {
                        panierDbService.modifierQuantiteProduit(session, produitId, quantite);
                    } else {
                        request.setAttribute("error", "La quantité doit être positive.");
                        request.getRequestDispatcher("/generalError.jsp").forward(request, response);
                        return;
                    }
                    break;
                default:
                    request.setAttribute("error", "Action invalide.");
                    request.getRequestDispatcher("/generalError.jsp").forward(request, response);
                    return;
            }

            // Rediriger après l'action
            response.sendRedirect("panier");

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Une erreur inconnue est survenue.");
            request.getRequestDispatcher("/generalError.jsp").forward(request, response);
        }
    }
}

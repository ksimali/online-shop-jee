package com.onlineshop.service;

import javax.servlet.http.HttpSession;
import com.onlineshop.modele.Produit;
import com.onlineshop.modele.ProduitPanier;
import java.util.List;
import java.util.ArrayList;

public class PanierDbService {

    // Méthode pour ajouter ou mettre à jour un produit dans le panier (en session)
    public void ajouterOuMettreAJourProduit(HttpSession session, int produitId, int quantite) {
        // Récupérer le panier actuel (une liste d'objets ProduitPanier) depuis la session
        List<ProduitPanier> panier = (List<ProduitPanier>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
            session.setAttribute("panier", panier);
        }

        // Vérifier si le produit est déjà dans le panier
        boolean produitExistant = false;
        for (ProduitPanier produitPanier : panier) {
            if (produitPanier.getProduit().getId() == produitId) {
                // Si le produit existe, on met à jour sa quantité
                produitPanier.setQuantite(produitPanier.getQuantite() + quantite);
                produitExistant = true;
                break;
            }
        }

        // Si le produit n'est pas encore dans le panier, on l'ajoute
        if (!produitExistant) {
            // Supposons qu'on récupère le produit à partir de la base de données (par exemple via ProduitDbService)
            // On ajoute le produit à la liste
            Produit produit = new Produit(produitId, "NomProduit", "DescriptionProduit", 100.0, "image.jpg", 1);
            panier.add(new ProduitPanier(produit, quantite));
        }
    }

    // Méthode pour supprimer un produit du panier (en session)
    public void supprimerProduitDuPanier(HttpSession session, int produitId) {
        List<ProduitPanier> panier = (List<ProduitPanier>) session.getAttribute("panier");
        if (panier != null) {
            panier.removeIf(produitPanier -> produitPanier.getProduit().getId() == produitId);
        }
    }

    // Méthode pour modifier la quantité d'un produit dans le panier (en session)
    public void modifierQuantiteProduit(HttpSession session, int produitId, int quantite) {
        List<ProduitPanier> panier = (List<ProduitPanier>) session.getAttribute("panier");
        if (panier != null) {
            for (ProduitPanier produitPanier : panier) {
                if (produitPanier.getProduit().getId() == produitId) {
                    produitPanier.setQuantite(quantite);
                    break;
                }
            }
        }
    }

    // Méthode pour récupérer les produits dans le panier (en session)
    public List<ProduitPanier> getProduitsDansPanier(HttpSession session) {
        List<ProduitPanier> panier = (List<ProduitPanier>) session.getAttribute("panier");
        return panier != null ? panier : new ArrayList<>();
    }
}

package com.onlineshop.modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    // Attributs
    private int id;
    private int clientId; // Référence au client
    private List<ProduitPanier> produits; // Liste des produits dans le panier
    private double total; // Montant Total du panier

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public List<ProduitPanier> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitPanier> produits) {
        this.produits = produits;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Constructeur par défaut 
    public Panier() {
        this.produits = new ArrayList<>();
        this.total = 0.0;
    }

    // Constructeur avec paramètres
    public Panier(int id, int clientId, List<ProduitPanier> produits, double total) {
        this.id = id;
        this.clientId = clientId;
        this.produits = produits;
        this.total = total;
    }

    // Méthode Override toString()
    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", produits=" + produits +
                ", total=" + total +
                '}';
    }

    // Méthode de calcul du total, qui retourne le montant total calculé
    public double calculerTotal() {
        total = produits.stream()
                .mapToDouble(p -> p.getProduit().getPrix() * p.getQuantite())
                .sum();
        return total; // Retourner le montant total calculé
    }

    // Ajouter un produit au panier
    public void ajouterProduit(Produit produit, int quantite) {
        for (ProduitPanier produitPanier : produits) {
            if (produitPanier.getProduit().getId() == produit.getId()) {
                // Si le produit existe déjà, augmente la quantité
                produitPanier.setQuantite(produitPanier.getQuantite() + quantite);
                calculerTotal(); // Recalculer le total
                return;
            }
        }
        // Ajouter le produit si non présent dans le panier
        produits.add(new ProduitPanier(produit, quantite));
        calculerTotal(); // Recalculer le total
    }

    // Modifier la quantité d'un produit
    public void modifierQuantite(int produitId, int nouvelleQuantite) {
        for (ProduitPanier produitPanier : produits) {
            if (produitPanier.getProduit().getId() == produitId) {
                produitPanier.setQuantite(nouvelleQuantite);
                calculerTotal(); // Recalculer le total
                return;
            }
        }
        throw new IllegalArgumentException("Produit introuvable dans le panier.");
    }

    // Supprimer un produit du panier
    public void supprimerProduit(int produitId) {
        produits.removeIf(produitPanier -> produitPanier.getProduit().getId() == produitId);
        calculerTotal(); // Recalculer le total
    }

    // Vider complètement le panier
    public void vider() {
        produits.clear();
        total = 0.0;
    }

    public ProduitPanier getProduitParId(int produitId) {
        for (ProduitPanier produitPanier : produits) {
            if (produitPanier.getProduit().getId() == produitId) {
                return produitPanier; // Retourne le produit trouvé dans le panier
            }
        }
        return null; // Retourne null si le produit n'est pas dans le panier
    }

}

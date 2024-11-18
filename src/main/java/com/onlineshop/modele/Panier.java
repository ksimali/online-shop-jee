package com.onlineshop.modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private int id; // ID du panier dans la base de données
    private int utilisateurId; // ID de l'utilisateur, null si non connecté
    private List<ProduitPanier> produits; // Liste des produits dans le panier
    private double total; // Montant Total du panier

    // Getters et setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public Integer getUtilisateurId() {
        return utilisateurId;
    }
    
    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    
    public List<ProduitPanier> getProduits() {
        return produits;
    }
    
    public void setProduits(List<ProduitPanier> produits) {
    	this.produits = produits;
    }
    

    public double getTotal() {
    	double total = 0;
        for (ProduitPanier pp : produits) {
            total += pp.getProduit().getPrix() * pp.getQuantite();
        }
        return total;
    }
    
    public void setTotal(double total) {
    	this.total = total;
    }
    
    // Constructeur
    public Panier() {
        this.produits = new ArrayList<>();
    }
    
    // Constructeur avec paramètres
    public Panier(int id, int utilisateurId) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.produits = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Panier{" +
                "produitsPanier=" + produits +
                ", total=" + total +
                '}';
    }
    
    // Ajouter un produit au panier (ou mettre à jour la quantité si le produit existe déjà)
    public void ajouterProduit(ProduitPanier produitPanier) {
        for (ProduitPanier pp : produits) {
            if (pp.getProduit().getId() == produitPanier.getProduit().getId()) {
                pp.setQuantite(pp.getQuantite() + produitPanier.getQuantite());
                return;
            }
        }
        // Si le produit n'existe pas encore, on l'ajoute
        produits.add(produitPanier);
    }
    
    // Supprimer un produit du panier
    public void supprimerProduit(int produitId) {
        produits.removeIf(pp -> pp.getProduit().getId() == produitId);
    }
    
    // Mettre à jour la quantité d'un produit dans le panier
    public void mettreAJourQuantite(int produitId, int quantite) {
        for (ProduitPanier pp : produits) {
            if (pp.getProduit().getId() == produitId) {
                pp.setQuantite(quantite);
                return;
            }
        }
    }
}


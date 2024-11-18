package com.onlineshop.modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private int id; // ID du panier dans la base de données
    private int utilisateurId; // ID de l'utilisateur, null si non connecté
    private List<ProduitPanier> produitsPanier; // Liste des produits dans le panier
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
    
    public List<ProduitPanier> getProduitsPanier() {
        return produitsPanier;
    }
    

    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
    	this.total = total;
    }
    
    // Constructeur
    public Panier() {
        this.produitsPanier = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Panier{" +
                "produitsPanier=" + produitsPanier +
                ", total=" + total +
                '}';
    }
}


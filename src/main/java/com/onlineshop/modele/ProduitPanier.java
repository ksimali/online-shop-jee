package com.onlineshop.modele;

public class ProduitPanier {
	// Attributes
    private Produit produit;
    private int quantite;
    
    // Getters and Setters 
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    // Constructor
    public ProduitPanier(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }
    
    // Method Override toString
    @Override
    public String toString() {
        return "ProduitPanier{" +
                "produit=" + produit +
                ", quantite=" + quantite +
                '}';
    }

    
}


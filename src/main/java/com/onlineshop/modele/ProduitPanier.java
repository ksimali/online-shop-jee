package com.onlineshop.modele;

public class ProduitPanier {
    private Produit produit;
    private int quantite;

    // Constructeur
    public ProduitPanier(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    // Getters et setters
    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
    	if (quantite > 0) {
            this.quantite = quantite;
        } else {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
    }

    public double getSousTotal() {
        return produit.getPrix() * quantite;
    }

    @Override
    public String toString() {
        return "ProduitPanier{" +
                "produit=" + produit +
                ", quantite=" + quantite +
                ", sousTotal=" + getSousTotal() +
                '}';
    }
}


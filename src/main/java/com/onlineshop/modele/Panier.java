package com.onlineshop.modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	// Attribute
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
    
    // Constructeur avec paramètre
    public Panier(int id, int clientId, List<ProduitPanier> produits, double total) {
        this.id = id;
        this.clientId = clientId;
        this.produits = produits;
        this.total = total;
    }

    // Methode Override toString()
    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", produits=" + produits +
                ", total=" + total +
                '}';
    }
    
    // Method calcul du total
    public void calculerTotal() {
        total = produits.stream()
                .mapToDouble(p -> p.getProduit().getPrix() * p.getQuantite())
                .sum();
    }
}


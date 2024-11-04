package com.onlineshop.modele;

import java.util.List;

public class Panier {
    private int id;
    private int clientId; // Référence au client
    private List<Produit> produits; // Liste des produits dans le panier
    private double total; // Montant Total du panier

    // Constructeur
    public Panier(int id, int clientId, List<Produit> produits, double total) {
        this.id = id;
        this.clientId = clientId;
        this.produits = produits;
        this.total = total;
    }

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

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", produits=" + produits +
                ", total=" + total +
                '}';
    }
}


package com.onlineshop.modele;

import java.math.BigDecimal;
import java.util.List;

public class Panier {
    private int id;
    private int clientId; // Référence au client
    private List<Produit> produits; // Liste des produits dans le panier
    private BigDecimal total; // Total du panier

    // Constructeur
    public Panier(int id, int clientId, List<Produit> produits, BigDecimal total) {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
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


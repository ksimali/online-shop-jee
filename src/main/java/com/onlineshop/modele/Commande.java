package com.onlineshop.modele;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Commande {
    private int id;
    private int clientId; // Référence au client
    private Date dateCommande; // Date de la commande
    private List<Produit> produits; // Liste des produits commandés
    private BigDecimal total; // Montant total de la commande
    private String statut; // Statut de la commande (en cours, expédiée, annulée)

    // Constructeur
    public Commande(int id, int clientId, Date dateCommande, List<Produit> produits, BigDecimal total, String statut) {
        this.id = id;
        this.clientId = clientId;
        this.dateCommande = dateCommande;
        this.produits = produits;
        this.total = total;
        this.statut = statut;
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

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", dateCommande=" + dateCommande +
                ", produits=" + produits +
                ", total=" + total +
                ", statut='" + statut + '\'' +
                '}';
    }
}


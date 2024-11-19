package com.onlineshop.service;

import com.onlineshop.modele.Panier;
import com.onlineshop.modele.ProduitPanier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanierDbService {
    private Connection connection;

    // Constructeur : initialise la connexion à la base de données
    public PanierDbService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Enregistre le contenu du panier dans la base de données lors de l'achat.
     * @param panier L'objet Panier contenant les produits.
     * @param clientId L'identifiant du client qui réalise l'achat.
     * @return true si l'opération réussit, false sinon.
     */
    public boolean enregistrerPanier(Panier panier, int clientId) {
        String insertPanierSQL = "INSERT INTO panier (client_id, total) VALUES (?, ?)";
        String insertProduitSQL = "INSERT INTO produit_panier (panier_id, produit_id, quantite) VALUES (?, ?, ?)";

        try {
            // Enregistrer le panier et récupérer l'ID généré
            PreparedStatement panierStmt = connection.prepareStatement(insertPanierSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            panierStmt.setInt(1, clientId);
            panierStmt.setDouble(2, panier.getTotal());
            panierStmt.executeUpdate();

            ResultSet rs = panierStmt.getGeneratedKeys();
            if (rs.next()) {
                int panierId = rs.getInt(1);

                // Ajouter chaque produit dans la table `produit_panier`
                PreparedStatement produitStmt = connection.prepareStatement(insertProduitSQL);
                for (ProduitPanier produitPanier : panier.getProduits()) {
                    produitStmt.setInt(1, panierId);
                    produitStmt.setInt(2, produitPanier.getProduit().getId());
                    produitStmt.setInt(3, produitPanier.getQuantite());
                    produitStmt.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

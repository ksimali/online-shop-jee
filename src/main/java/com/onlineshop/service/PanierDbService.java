package com.onlineshop.service;

import com.onlineshop.modele.Panier;
import com.onlineshop.modele.ProduitPanier;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PanierDbService {

    @Resource(name = "jdbc/onlineshop_bd")
    private DataSource dataSource;

    public PanierDbService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Enregistre le panier dans la base de données.
     * @param panier Le panier à enregistrer.
     * @param clientId L'ID du client associé.
     * @return L'ID du panier inséré ou -1 en cas d'erreur.
     */
    public int enregistrerPanier(Panier panier, int clientId) {
        String sqlInsertPanier = "INSERT INTO panier (client_id, total) VALUES (?, ?)";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlInsertPanier, PreparedStatement.RETURN_GENERATED_KEYS)) {
             
            // Définir les paramètres de la requête
            ps.setInt(1, clientId);
            ps.setDouble(2, panier.getTotal());

            // Exécuter la requête d'insertion
            int rowsAffected = ps.executeUpdate();

            // Vérifier si une ligne a été insérée
            if (rowsAffected > 0) {
                // Récupérer les clés générées
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Retourner l'ID généré
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Retourner -1 en cas d'erreur
    }


    /**
     * Enregistre les produits associés au panier.
     * @param panierId L'ID du panier auquel les produits sont associés.
     * @param produits La liste des produits du panier.
     */
    public void enregistrerProduitsDuPanier(int panierId, List<ProduitPanier> produits) {
        String sqlInsertProduit = "INSERT INTO produit_panier (panier_id, produit_id, quantite) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlInsertProduit)) {

            for (ProduitPanier produitPanier : produits) {
                ps.setInt(1, panierId);
                ps.setInt(2, produitPanier.getProduit().getId());
                ps.setInt(3, produitPanier.getQuantite());
                ps.addBatch();
            }

            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.onlineshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.onlineshop.modele.Produit;
import com.onlineshop.modele.ProduitPanier;

public class PanierDbService {

    @Resource(name = "jdbc/onlineshop_bd")
    private DataSource dataSource;

    public PanierDbService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void ajouterOuMettreAJourProduit(int produitId, int quantite) throws SQLException {
        String selectSql = "SELECT quantite FROM panier WHERE produit_id = ?";
        String insertSql = "INSERT INTO panier (produit_id, nom, description, prix, image, categorie_id, quantite) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE panier SET quantite = ? WHERE produit_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            selectStmt.setInt(1, produitId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int nouvelleQuantite = rs.getInt("quantite") + quantite;
                updateStmt.setInt(1, nouvelleQuantite);
                updateStmt.setInt(2, produitId);
                updateStmt.executeUpdate();
            } else {
                String produitDetailsSql = "SELECT * FROM produit WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(produitDetailsSql)) {
                    stmt.setInt(1, produitId);
                    ResultSet produitRs = stmt.executeQuery();
                    if (produitRs.next()) {
                        insertStmt.setInt(1, produitId);
                        insertStmt.setString(2, produitRs.getString("nom"));
                        insertStmt.setString(3, produitRs.getString("description"));
                        insertStmt.setDouble(4, produitRs.getDouble("prix"));
                        insertStmt.setString(5, produitRs.getString("image"));
                        insertStmt.setInt(6, produitRs.getInt("categorie_id"));
                        insertStmt.setInt(7, quantite);
                        insertStmt.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout ou mise à jour du produit dans le panier: " + e.getMessage());
            throw e;
        }
    }

    public List<ProduitPanier> getProduitsDansPanier() throws SQLException {
        List<ProduitPanier> produits = new ArrayList<>();
        String sql = "SELECT p.*, p.quantite FROM panier p JOIN produit pr ON p.produit_id = pr.id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produit produit = new Produit(
                        rs.getInt("produit_id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("categorie_id")
                );
                int quantite = rs.getInt("quantite");
                produits.add(new ProduitPanier(produit, quantite));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des produits dans le panier: " + e.getMessage());
            throw e;
        }

        return produits;
    }

    public void supprimerProduitDuPanier(int produitId) throws SQLException {
        String sql = "DELETE FROM panier WHERE produit_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produitId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du produit du panier: " + e.getMessage());
            throw e;
        }
    }

    public void modifierQuantiteProduit(int produitId, int quantite) throws SQLException {
        String sql = "UPDATE panier SET quantite = ? WHERE produit_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantite);
            stmt.setInt(2, produitId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la quantité du produit dans le panier: " + e.getMessage());
            throw e;
        }
    }
}

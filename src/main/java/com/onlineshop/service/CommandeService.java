package com.onlineshop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.onlineshop.modele.Commande;

public class CommandeService {

    // Attribut pour la datasource permettant d'obtenir des connexions à la base de données
    @Resource(name = "jdbc/onlineshop_bd")
    private DataSource dataSource;

    // Constructeur pour initialiser la DataSource via injection
    public CommandeService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Enregistre une commande dans la base de données.
     *
     * @param commande L'objet Commande contenant les informations à enregistrer.
     * @return true si l'insertion réussit, false sinon.
     */
    public boolean enregistrerCommande(Commande commande) {
        // Requête SQL pour insérer une commande
        String insertCommandeSQL = "INSERT INTO commande (date_commande, date_livraison, nom, prenom, "
                + "telephone, courriel, adresse, ville, province, pays, codepostal, adresse_livraison, "
                + "ville_livraison, province_livraison, pays_livraison, codepostal_livraison, numero_carte_credit, "
                + "date_expiration, ccv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Vérification de la DataSource avant utilisation
        if (dataSource == null) {
            throw new IllegalStateException("DataSource non initialisée. Vérifiez la configuration.");
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertCommandeSQL)) {

            // Remplissage des paramètres de la requête
            ps.setDate(1, commande.getDateCommande());
            ps.setDate(2, commande.getDateLivraison());
            ps.setString(3, commande.getNom());
            ps.setString(4, commande.getPrenom());
            ps.setString(5, commande.getTelephone());
            ps.setString(6, commande.getCourriel());
            ps.setString(7, commande.getAdresse());
            ps.setString(8, commande.getVille());
            ps.setString(9, commande.getProvince());
            ps.setString(10, commande.getPays());
            ps.setString(11, commande.getCodePostal());
            ps.setString(12, commande.getAdresseLivraison());
            ps.setString(13, commande.getVilleLivraison());
            ps.setString(14, commande.getProvinceLivraison());
            ps.setString(15, commande.getPaysLivraison());
            ps.setString(16, commande.getCodePostalLivraison());
            ps.setString(17, commande.getNumeroCarteCredit());
            ps.setDate(18, commande.getDateExpiration());
            ps.setString(19, commande.getCcv());

            // Exécution de la requête
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Journaliser l'erreur pour faciliter le débogage
            System.err.println("Erreur lors de l'insertion de la commande : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

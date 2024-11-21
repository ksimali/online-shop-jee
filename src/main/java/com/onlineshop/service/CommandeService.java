package com.onlineshop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.onlineshop.modele.Commande;

public class CommandeService {
    
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;

    // Constructeur pour initialiser la DataSource
    public CommandeService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean enregistrerCommande(Commande commande) {
        String insertCommandeSQL = "INSERT INTO commandes (client_id, date_commande, date_livraison, total, nom, prenom, "
                + "telephone, courriel, adresse, ville, province, pays, code_postal, adresse_livraison, "
                + "ville_livraison, province_livraison, pays_livraison, code_postal_livraison, numero_carte_credit, "
                + "date_expiration, ccv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertCommandeSQL)) {
            
            ps.setInt(1, commande.getClientId());
            ps.setDate(2, commande.getDateCommande());
            ps.setDate(3, commande.getDateLivraison());
            ps.setDouble(4, commande.getTotal());
            ps.setString(5, commande.getNom());
            ps.setString(6, commande.getPrenom());
            ps.setString(7, commande.getTelephone());
            ps.setString(8, commande.getCourriel());
            ps.setString(9, commande.getAdresse());
            ps.setString(10, commande.getVille());
            ps.setString(11, commande.getProvince());
            ps.setString(12, commande.getPays());
            ps.setString(13, commande.getCodePostal());
            ps.setString(14, commande.getAdresseLivraison());
            ps.setString(15, commande.getVilleLivraison());
            ps.setString(16, commande.getProvinceLivraison());
            ps.setString(17, commande.getPaysLivraison());
            ps.setString(18, commande.getCodePostalLivraison());
            ps.setString(19, commande.getNumeroCarteCredit());
            ps.setDate(20, commande.getDateExpiration());
            ps.setString(21, commande.getCcv());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

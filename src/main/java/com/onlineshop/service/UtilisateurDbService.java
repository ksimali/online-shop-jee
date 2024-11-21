package com.onlineshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.onlineshop.modele.Client;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UtilisateurDbService {
	
	private DataSource dataSource;
	
	public UtilisateurDbService () {
		try {
            // Récupérer le DataSource à partir de JNDI
            InitialContext context = new InitialContext();
            this.dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/onlineshop_bd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
    // Méthode pour obtenir une connexion à partir du DataSource
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
	
	// Authentifier un utilisateur
    public boolean loginUser(String email, String password, String type) {
    	String query = "";
    	if(type.equals("admin")) {
        	query = "SELECT mot_de_passe FROM administrateur WHERE email = ?";
    	} else if(type.equals("client")) {
        	query = "SELECT mot_de_passe FROM client WHERE email = ?";
    	} else {
        	return false;
    	}
    	
        try (Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPasswordHash = rs.getString("mot_de_passe");
            	//System.out.println("Pass: " + BCrypt.withDefaults().hashToString(12, storedPasswordHash.toCharArray()));
                // Vérifier le mot de passe en le comparant avec le hachage stocké
                //return BCrypt.verifyer().verify(password.toCharArray(), storedPasswordHash.toCharArray()).verified; //BCrypt.checkpw(password, storedPasswordHash);
                return storedPasswordHash.equals(password);
            } else {
            	System.out.println("Aucun utilisateur trouvé avec cet email : " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
    // Vérifier si l'utilisateur existe déjà
    public boolean userExists(String email) {
    	String query = "SELECT COUNT(*) FROM client WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Créer un nouvel utilisateur
    public boolean registerClient(String prenom, String nom, String email, String motDePasse, Date dateNaissance, String telephone,
            String adresseDomicile, String villeDomicile, String provinceDomicile, String paysDomicile, String codePostalDomicile,
            String adresseLivraison, String villeLivraison, String provinceLivraison, String paysLivraison, String codePostalLivraison) {

		String query = "INSERT INTO client (prenom, nom, email, mot_de_passe, date_naissance, telephone, adresse_domicile, " +
		    "ville_domicile, province_domicile, pays_domicile, codepostal_domicile, adresse_livraison, ville_livraison, " +
		    "province_livraison, pays_livraison, codepostal_livraison) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(query)) {
			
			// Hacher le mot de passe avant de l'enregistrer
			//String hashedPassword = BCrypt.withDefaults().hashToString(12, motDePasse.toCharArray()); //BCrypt.hashpw(motDePasse, BCrypt.gensalt());
			
			// Préparer les valeurs à insérer dans la base de données
			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			stmt.setString(3, email);
			stmt.setString(4, motDePasse);
			stmt.setDate(5, dateNaissance);
			stmt.setString(6, telephone);
			stmt.setString(7, adresseDomicile);
			stmt.setString(8, villeDomicile);
			stmt.setString(9, provinceDomicile);
			stmt.setString(10, paysDomicile);
			stmt.setString(11, codePostalDomicile);
			stmt.setString(12, adresseLivraison);
			stmt.setString(13, villeLivraison);
			stmt.setString(14, provinceLivraison);
			stmt.setString(15, paysLivraison);
			stmt.setString(16, codePostalLivraison);
			
			// Exécuter l'insertion dans la base de données
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    
    public boolean updateClient(int id, String prenom, String nom, String email, String motDePasse, Date dateNaissance, String telephone,
            String adresseDomicile, String villeDomicile, String provinceDomicile, String paysDomicile, String codePostalDomicile,
            String adresseLivraison, String villeLivraison, String provinceLivraison, String paysLivraison, String codePostalLivraison) {

	    String query = "UPDATE client SET prenom = ?, nom = ?, email = ?, mot_de_passe = ?, date_naissance = ?, telephone = ?, " +
	        "adresse_domicile = ?, ville_domicile = ?, province_domicile = ?, pays_domicile = ?, codepostal_domicile = ?, " +
	        "adresse_livraison = ?, ville_livraison = ?, province_livraison = ?, pays_livraison = ?, codepostal_livraison = ? " +
	        "WHERE id = ?";
	
	    try (Connection connection = getConnection();
	         PreparedStatement stmt = connection.prepareStatement(query)) {
	
	        // Hacher le mot de passe avant de l'enregistrer (si le mot de passe est modifié)
	        //String hashedPassword = BCrypt.withDefaults().hashToString(12, motDePasse.toCharArray()); // Hashing avec BCrypt
	
	        // Préparer les valeurs à insérer dans la base de données
	        stmt.setString(1, prenom);
	        stmt.setString(2, nom);
	        stmt.setString(3, email);
	        stmt.setString(4, motDePasse);
	        stmt.setDate(5, dateNaissance);
	        stmt.setString(6, telephone);
	        stmt.setString(7, adresseDomicile);
	        stmt.setString(8, villeDomicile);
	        stmt.setString(9, provinceDomicile);
	        stmt.setString(10, paysDomicile);
	        stmt.setString(11, codePostalDomicile);
	        stmt.setString(12, adresseLivraison);
	        stmt.setString(13, villeLivraison);
	        stmt.setString(14, provinceLivraison);
	        stmt.setString(15, paysLivraison);
	        stmt.setString(16, codePostalLivraison);
	        stmt.setInt(17, id);  // L'ID du client à mettre à jour
	
	        // Exécuter la mise à jour dans la base de données
	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0; // Retourne true si des lignes ont été affectées par la mise à jour
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
    }
    
    public Client getClientByEmail(String email) {
        String query = "SELECT * FROM client WHERE email = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Créer un objet Client avec les informations récupérées
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setPrenom(rs.getString("prenom"));
                    client.setNom(rs.getString("nom"));
                    client.setEmail(rs.getString("email"));
                    client.setDateNaissance(rs.getDate("date_naissance"));
                    client.setTelephone(rs.getString("telephone"));
                    client.setAdresseDomicile(rs.getString("adresse_domicile"));
                    client.setVilleDomicile(rs.getString("ville_domicile"));
                    client.setProvinceDomicile(rs.getString("province_domicile"));
                    client.setPaysDomicile(rs.getString("pays_domicile"));
                    client.setCodepostalDomicile(rs.getString("codepostal_domicile"));
                    client.setAdresseLivraison(rs.getString("adresse_livraison"));
                    client.setVilleLivraison(rs.getString("ville_livraison"));
                    client.setProvinceLivraison(rs.getString("province_livraison"));
                    client.setPaysLivraison(rs.getString("pays_livraison"));
                    client.setCodepostalLivraison(rs.getString("codepostal_livraison"));
                    client.setDateInscription(rs.getTimestamp("date_inscription"));
                    client.setMotDePasse(rs.getString("mot_de_passe"));
                    return client; // Retourner l'objet Client
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // Retourner null si aucun client n'est trouvé
    }


}

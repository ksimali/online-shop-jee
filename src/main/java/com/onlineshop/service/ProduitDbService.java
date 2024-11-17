package com.onlineshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.onlineshop.modele.Produit;

public class ProduitDbService {
	
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;
	
	// Constructeur qui initialise l'objet dataSource avec la dataSource(la bdd) fournie
	public ProduitDbService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	// Suppression du constructeur qui acceptait un DataSource, car l'injection est gérée par le conteneur
    // Pas besoin de constructeur explicite, l'injection de dépendance se charge de l'initialisation de dataSource
	
	// Méthode de recherche des produis par mot clé
	public List<Produit> searchProduitsByKeyword(String keyword) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		String sql = "SELECT * FROM  `produit` WHERE nom LIKE ? OR description LIKE ?";
		
		// Connexion à la base de données et exection de la requête
		try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchTerm = "%" + keyword + "%"; // Utilisation des jokers pour la recherche partielle
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);

            // Exécution de la requête et récupération des résultats
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produit produit = new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("image"),
                        rs.getInt("categorie_id")
                    );
                    produits.add(produit);
                }
            }
        }catch(SQLException ex) {
        	// Loguer l'exception et la relancer pour être capturée dans le contrôleur
            ex.printStackTrace();
            throw new SQLException("Erreur lors de la recherche des produits", ex);
        }
		
		// Vérifier si la liste est vide et retourner un message
	    if (produits.isEmpty()) {
	        throw new SQLException("Aucun résultat de recherche n'a été trouvé.");
	    }
		
		return produits;
	}
	
	// Methode pour recupérer tous les produits de la bdd
	public List<Produit> getAllProduits() throws Exception {
		List<Produit> produits = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Obtenir une connexion a la bdd
			connection = dataSource.getConnection();
			
			// Définir la requête SQL pour sélectionner tous les étudiants, triés par matricule
            String sql = "SELECT * FROM produit ORDER BY nom";
            
            // Créer une déclaration et exécuter la requête
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            // Parcourir le résultat et créer des objets Etudiant pour chaque enregistrement
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                double prix = resultSet.getDouble("prix");
                String image = resultSet.getString("image");
                int categorieId = resultSet.getInt("categorie_id");

                // Créer un objet Etudiant temporaire et l'ajouter à la liste
                Produit produitTemp = new Produit(id, nom, description, prix, image, categorieId);
                produits.add(produitTemp);
            }

            // Retourner la liste des étudiants
            return produits;
			
		}
		finally {
			close(connection, statement, resultSet);
		}
	}
	
	private void close(Connection connection, Statement statement, ResultSet resultSet) {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}

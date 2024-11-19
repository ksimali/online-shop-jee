package com.onlineshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.onlineshop.modele.Categorie;
import com.onlineshop.modele.Produit;

public class ProduitDbService {
	
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;
	
	// Constructeur qui initialise l'objet dataSource avec la dataSource(la bdd) fournie
	public ProduitDbService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ProduitDbService() {
		try {
            // Récupérer le DataSource à partir de JNDI
            InitialContext context = new InitialContext();
            this.dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/onlineshop_bd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}

	// Suppression du constructeur qui acceptait un DataSource, car l'injection est gérée par le conteneur
    // Pas besoin de constructeur explicite, l'injection de dépendance se charge de l'initialisation de dataSource
	
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
	
	public List<Produit> getAllProduitsWithCategorieNom() throws SQLException {
	    List<Produit> produits = new ArrayList<>();
	    String sql = "SELECT p.id, p.nom, p.prix, p.image, c.id AS categorie_id, c.nom AS categorie_nom "
	    				+ "FROM produit p JOIN categorie c ON p.categorie_id = c.id";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {
	        
	        while (resultSet.next()) {
	            int produitId = resultSet.getInt("id");
	            String produitNom = resultSet.getString("nom");
	            double produitPrix = resultSet.getDouble("prix");
	            String produitImage = resultSet.getString("image");
	            
	            // Récupérer les informations de la catégorie
	            int categorieId = resultSet.getInt("categorie_id");
	            String categorieNom = resultSet.getString("categorie_nom");
	            
	            // Créer un objet Produit avec la catégorie
	            Produit produit = new Produit(produitId, produitNom, produitPrix, produitImage, categorieId, categorieNom);
	            
	            produits.add(produit);
	            System.out.println(produit.toString());
	        }
	    }
	    
	    return produits;
	}

	
	public void addProduit(Produit produit) throws SQLException {
	    String query = "INSERT INTO produit (nom, prix, image, categorie_id) VALUES (?, ?, ?, ?)";

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, produit.getNom());
	        stmt.setDouble(2, produit.getPrix());
	        stmt.setString(3, produit.getImage());
	        stmt.setInt(4, produit.getCategorieId()); // Utilisation de l'ID de catégorie
	        stmt.executeUpdate();
	    }
	}

	public void updateProduit(Produit produit) throws SQLException {
	    String query = "UPDATE produit SET nom = ?, prix = ?, image = ?, categorie_id = ? WHERE id = ?";

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, produit.getNom());
	        stmt.setDouble(2, produit.getPrix());
	        stmt.setString(3, produit.getImage());
	        stmt.setInt(4, produit.getCategorieId()); // Utilisation de l'ID de catégorie
	        stmt.setInt(5, produit.getId()); // L'ID du produit à mettre à jour
	        stmt.executeUpdate();
	    }
	}

	// Supprimer un produit
    public void deleteProduit(int id) throws SQLException {
        String query = "DELETE FROM produit WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
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

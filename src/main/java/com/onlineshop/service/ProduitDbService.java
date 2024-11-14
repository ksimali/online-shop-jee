package com.onlineshop.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.onlineshop.modele.Produit;

public class ProduitDbService {
	
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource datasource;
	
	// Constructeur qui initialise l'objet dataSource avec la dataSource(la bdd) fournie
	public ProduitDbService(DataSource laDataSource) {
		datasource = laDataSource;
	}
	
	// Methode pour recupérer tous les produits de la bdd
	public List<Produit> getAllProduits() throws Exception {
		List<Produit> produits = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Obtenir une connexion a la bdd
			connection = datasource.getConnection();
			
			// Définir la requête SQL pour sélectionner tous les étudiants, triés par matricule
            String sql = "SELECT * FROM etudiant ORDER BY nom";
            
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
                int categorieId = resultSet.getInt("categorieId");

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

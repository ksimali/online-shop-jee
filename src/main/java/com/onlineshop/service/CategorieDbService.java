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

import com.onlineshop.modele.Categorie;

public class CategorieDbService {
	
	// Attribut pour la datasource permettant d'obtenir des connexion à la bd
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource datasource;
		
	// Constructeur qui initialise l'objet dataSource avec la dataSource(la bdd) fournie
	public CategorieDbService(DataSource laDataSource) {
		datasource = laDataSource;
	}
	
	// CREATE - Ajouter une nouvelle catégorie dans la base de données
    public boolean ajouterCategorie(Categorie categorie) throws SQLException {
    	
        String sql = "INSERT INTO Categorie (nom, description) VALUES (?, ?)";
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	
            statement.setString(1, categorie.getNom());
            statement.setString(2, categorie.getDescription());
            
            return statement.executeUpdate() > 0; // Retourne true si l'insertion a réussi
        }
    }
    
	// READ - Récupérer toutes les catégories
    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categorie ORDER BY nom";
        try (Connection connection = datasource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
        	
        	// Parcourir les résultats et créer des objets Categorie pour chaque enregistrement
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                categories.add(new Categorie(id, nom, description)); // Ajout de la catégorie à la liste des catégories
            }
            return categories;
        }
    }
    
 // DELETE - Supprimer une catégorie de la base de données
    public boolean supprimerCategorie(int categorieId) throws SQLException {
    	
        String sql = "DELETE FROM Categorie WHERE id=?";
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	
            statement.setInt(1, categorieId);
            
            return statement.executeUpdate() > 0; // Retourne true si la suppression a réussi
        }
    }
}

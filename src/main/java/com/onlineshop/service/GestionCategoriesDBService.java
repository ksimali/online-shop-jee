package com.onlineshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.onlineshop.modele.Categorie;

public class GestionCategoriesDBService {
	
	private DataSource dataSource;

	// Méthode pour récupérer une connexion à la base de données via DataSource
    public GestionCategoriesDBService() {
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

    // Méthode pour récupérer toutes les catégories depuis la base de données
    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM categorie";

        try (Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                categories.add(new Categorie(id, nom, description));
            }
        }
        return categories;
    }

    // Méthode pour ajouter une nouvelle catégorie dans la base de données
    public void addCategorie(String nom, String description) throws SQLException {
        String query = "INSERT INTO categorie (nom, description) VALUES (?, ?)";

        try (Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, description);
            stmt.executeUpdate();
        }
    }

    // Methode pour mettre a jour une categorie dans la base de donnees
    public void updateCategorie(int id, String nom, String description) throws SQLException {
        String query = "UPDATE categorie SET nom = ?, description = ? WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, description);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

	public void deleteCategorie(int id) throws SQLException {
		String query = "DELETE FROM categorie WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
	}
}

package com.onlineshop.modele;

public class Categorie {
    private int id;
    private String nom;
    private String description;

    // Constructeurs, getters et setters

    public Categorie() {}

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Categorie(int categorieId, String categorieNom) {
    	this.id = categorieId;
        this.nom = categorieNom;
    }

	public Categorie(int categorieId) {
		// TODO Auto-generated constructor stub
		this.id = categorieId;
	}

	// Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    // Methode toString
    @Override
    public String toString() {
    	return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


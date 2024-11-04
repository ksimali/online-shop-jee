package com.onlineshop.modele;

import java.sql.Timestamp;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse; // Stockez le mot de passe sous forme hachée
    private String adresse; // Adresse de livraison
    private Timestamp dateInscription;
    
    // Constructeur par défaut
    public Client() {}
    
    // Constructeur avec paramètres
    public Client(int id, String nom, String prenom, String email, String motDePasse, String adresse, Timestamp dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse; // Il est conseillé de hacher le mot de passe lors de l'enregistrement
        this.adresse = adresse;
        this.dateInscription = dateInscription;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse; // Pensez à hacher le mot de passe avant de le stocker
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Timestamp getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Timestamp dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}

package com.onlineshop.modele;

import java.sql.Date;

public class Commande {
    private int id;
    private int clientId;
    private Date dateCommande;         // Utilisation de java.sql.Date pour DateTime en base
    private Date dateLivraison;        // Utilisation de java.sql.Date pour DateTime en base
    private double total;
    private String nom;
    private String prenom;
    private String telephone;
    private String courriel;
    private String adresse;
    private String ville;
    private String province;
    private String pays;
    private String codePostal;
    private String adresseLivraison;
    private String villeLivraison;
    private String provinceLivraison;
    private String paysLivraison;
    private String codePostalLivraison;
    private String numeroCarteCredit;
    private Date dateExpiration;       // Utilisation de java.sql.Date pour Date en base
    private String ccv;

    // Constructeur sans l'ID (l'ID est généré par la base de données)
    public Commande(Date dateCommande, Date dateLivraison, double total, String nom, String prenom,
                    String telephone, String courriel, String adresse, String ville, String province,
                    String pays, String codePostal, String adresseLivraison, String villeLivraison,
                    String provinceLivraison, String paysLivraison, String codePostalLivraison,
                    String numeroCarteCredit, Date dateExpiration, String ccv) {
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.total = total;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.adresse = adresse;
        this.ville = ville;
        this.province = province;
        this.pays = pays;
        this.codePostal = codePostal;
        this.adresseLivraison = adresseLivraison;
        this.villeLivraison = villeLivraison;
        this.provinceLivraison = provinceLivraison;
        this.paysLivraison = paysLivraison;
        this.codePostalLivraison = codePostalLivraison;
        this.numeroCarteCredit = numeroCarteCredit;
        this.dateExpiration = dateExpiration;
        this.ccv = ccv;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getVilleLivraison() {
        return villeLivraison;
    }

    public void setVilleLivraison(String villeLivraison) {
        this.villeLivraison = villeLivraison;
    }

    public String getProvinceLivraison() {
        return provinceLivraison;
    }

    public void setProvinceLivraison(String provinceLivraison) {
        this.provinceLivraison = provinceLivraison;
    }

    public String getPaysLivraison() {
        return paysLivraison;
    }

    public void setPaysLivraison(String paysLivraison) {
        this.paysLivraison = paysLivraison;
    }

    public String getCodePostalLivraison() {
        return codePostalLivraison;
    }

    public void setCodePostalLivraison(String codePostalLivraison) {
        this.codePostalLivraison = codePostalLivraison;
    }

    public String getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    public void setNumeroCarteCredit(String numeroCarteCredit) {
        this.numeroCarteCredit = numeroCarteCredit;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    @Override
    public String toString() {
        return "Commande{" +
                ", dateCommande=" + dateCommande +
                ", dateLivraison=" + dateLivraison +
                ", total=" + total +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", courriel='" + courriel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", province='" + province + '\'' +
                ", pays='" + pays + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", adresseLivraison='" + adresseLivraison + '\'' +
                ", villeLivraison='" + villeLivraison + '\'' +
                ", provinceLivraison='" + provinceLivraison + '\'' +
                ", paysLivraison='" + paysLivraison + '\'' +
                ", codePostalLivraison='" + codePostalLivraison + '\'' +
                ", numeroCarteCredit='" + numeroCarteCredit + '\'' +
                ", dateExpiration=" + dateExpiration +
                ", ccv='" + ccv + '\'' +
                '}';
    }
}

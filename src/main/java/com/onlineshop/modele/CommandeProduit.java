package com.onlineshop.modele;

public class CommandeProduit {
	// Attributes
	private int id;
	private int commandeId; // Réference à la commande
	private int produitId; // Réference au produit
	private int quantite; // Quantité de chaque produit dans la commande
	private double prixUnitaire; // Prix du produit au moment de la commande
	
	// Getters and Setters
	public int getId() {return id;}
	
	public int getCommandeId() {return commandeId;}
	public int getProduitId() {return produitId;}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
	public double getPrixUnitaire() {return prixUnitaire;}
	
	// Constructors
	public CommandeProduit() {}
	
	public CommandeProduit(int id, int commandeId, int produitId, int quantite, double prixUnitaire) {
        this.id = id;
        this.commandeId = commandeId;
        this.produitId = produitId;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }
	
	
}

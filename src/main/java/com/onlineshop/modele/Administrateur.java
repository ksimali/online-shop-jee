package com.onlineshop.modele;

import java.sql.Timestamp;

public class Administrateur extends Utilisateur {
    // Constructeur
	public Administrateur(int id, String nom, String prenom, String email, String motDePasse,
			Timestamp dateInscription, String telephone) {
		super(id, nom, prenom, email, motDePasse, dateInscription, telephone);
	}
}


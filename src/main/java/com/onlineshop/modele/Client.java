package com.onlineshop.modele;

import java.sql.Timestamp;

public class Client extends Utilisateur {
    private String adresseDomicile;
    private String villeDomicile;
    private String provinceDomicile;
    private String paysDomicile;
    private String codepostalDomicile;
    
    private String adresseLivraison;
    private String villeLivraison;
    private String provinceLivraison;
    private String paysLivraison;
    private String codepostalLivraison;
    
    // Constructeur par défaut
    public Client() {}
    
    // Constructeur avec paramètres
    public Client(int id, String nom, String prenom, String email, String motDePasse, Timestamp dateInscription,
			String adresseDomicile, String villeDomicile, String provinceDomicile, String paysDomicile,
			String codepostalDomicile, String adresseLivraison, String villeLivraison, String provinceLivraison,
			String paysLivraison, String codepostalLivraison) {
		super(id, nom, prenom, email, motDePasse, dateInscription);
		this.adresseDomicile = adresseDomicile;
		this.villeDomicile = villeDomicile;
		this.provinceDomicile = provinceDomicile;
		this.paysDomicile = paysDomicile;
		this.codepostalDomicile = codepostalDomicile;
		this.adresseLivraison = adresseLivraison;
		this.villeLivraison = villeLivraison;
		this.provinceLivraison = provinceLivraison;
		this.paysLivraison = paysLivraison;
		this.codepostalLivraison = codepostalLivraison;
	}

	public String getAdresseDomicile() {
		return adresseDomicile;
	}

	public void setAdresseDomicile(String adresseDomicile) {
		this.adresseDomicile = adresseDomicile;
	}

	public String getVilleDomicile() {
		return villeDomicile;
	}

	public void setVilleDomicile(String villeDomicile) {
		this.villeDomicile = villeDomicile;
	}

	public String getProvinceDomicile() {
		return provinceDomicile;
	}

	public void setProvinceDomicile(String provinceDomicile) {
		this.provinceDomicile = provinceDomicile;
	}

	public String getPaysDomicile() {
		return paysDomicile;
	}

	public void setPaysDomicile(String paysDomicile) {
		this.paysDomicile = paysDomicile;
	}

	public String getCodepostalDomicile() {
		return codepostalDomicile;
	}

	public void setCodepostalDomicile(String codepostalDomicile) {
		this.codepostalDomicile = codepostalDomicile;
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

	public String getCodepostalLivraison() {
		return codepostalLivraison;
	}

	public void setCodepostalLivraison(String codepostalLivraison) {
		this.codepostalLivraison = codepostalLivraison;
	}

	@Override
	public String toString() {
		return "Informations additionnelles: [adresseDomicile=" + adresseDomicile + ", villeDomicile=" + villeDomicile + ", provinceDomicile="
				+ provinceDomicile + ", paysDomicile=" + paysDomicile + ", codepostalDomicile=" + codepostalDomicile
				+ ", adresseLivraison=" + adresseLivraison + ", villeLivraison=" + villeLivraison
				+ ", provinceLivraison=" + provinceLivraison + ", paysLivraison=" + paysLivraison
				+ ", codepostalLivraison=" + codepostalLivraison + "]";
	}
	
	
}

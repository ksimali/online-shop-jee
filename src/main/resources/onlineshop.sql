CREATE DATABASE IF NOT EXISTS onlineshop;

USE onlineshop;

CREATE TABLE IF NOT EXISTS client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    date_naissance DATE,
    telephone VARCHAR(20),
    email VARCHAR(255) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    adresse_domicile VARCHAR(255),
    ville_domicile VARCHAR(100),
    province_domicile VARCHAR(100),
    pays_domicile VARCHAR(100),
    codepostal_domicile VARCHAR(20),
    adresse_livraison VARCHAR(255),
    ville_livraison VARCHAR(100),
    province_livraison VARCHAR(100),
    pays_livraison VARCHAR(100),
    codepostal_livraison VARCHAR(20),
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS administrateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categorie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
);

CREATE TABLE IF NOT EXISTS produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    description TEXT,
    prix DECIMAL(10, 2) NOT NULL,
    image VARCHAR(255),
    categorie_id INT,  -- Clé étrangère pour référencer la catégorie
    FOREIGN KEY (categorie_id) REFERENCES categorie(id)
);

CREATE TABLE IF NOT EXISTS panier (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    produit_id INT,
    quantite INT,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (produit_id) REFERENCES produit(id)
);

CREATE TABLE IF NOT EXISTS commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    date_commande DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_livraison DATETIME,
    total DECIMAL(10, 2) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    telephone VARCHAR(50),
    adresse VARCHAR(255),
    ville VARCHAR(100),
    province VARCHAR(100),
    pays VARCHAR(100),
    codepostal VARCHAR(20),
    adresse_livraison VARCHAR(255),
    ville_livraison VARCHAR(100),
    province_livraison VARCHAR(100),
    pays_livraison VARCHAR(100),
    codepostal_livraison VARCHAR(20),
    numero_carte_credit VARCHAR(20),
    date_expiration DATE,
    ccv VARCHAR(4),
    FOREIGN KEY (client_id) REFERENCES client(id)
);
CREATE TABLE IF NOT EXISTS commande_produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    commande_id INT,         -- Référence à la commande
    produit_id INT,          -- Référence au produit
    quantite INT NOT NULL,   -- Quantité de ce produit dans la commande
    prix_unitaire DECIMAL(10, 2) NOT NULL, -- Prix du produit au moment de la commande
    FOREIGN KEY (commande_id) REFERENCES commande(id),
    FOREIGN KEY (produit_id) REFERENCES produit(id)
);

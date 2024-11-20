CREATE DATABASE IF NOT EXISTS onlineshop_bd;

USE onlineshop_bd;

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
    description TEXT
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

CREATE TABLE panier (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);

CREATE TABLE produit_panier (
    id INT AUTO_INCREMENT PRIMARY KEY,
    panier_id INT NOT NULL,
    produit_id INT NOT NULL,
    quantite INT NOT NULL,
    FOREIGN KEY (panier_id) REFERENCES panier(id) ON DELETE CASCADE,
    FOREIGN KEY (produit_id) REFERENCES produit(id) ON DELETE CASCADE
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

-- Insertion d'un compte client
INSERT INTO client (prenom, nom, date_naissance, telephone, email, mot_de_passe, adresse_domicile, ville_domicile, province_domicile, pays_domicile, codepostal_domicile)
VALUES ('User', 'One', '1990-05-15', '1234567890', 'user@gmail.com', '123456', '800 boulevard René Lesveque', 'Montréal', 'Québec', 'Canada', 'H2L2N2');

-- Insertion d'un compte administrateur
INSERT INTO administrateur (prenom, nom, email, mot_de_passe)
VALUES ('Admin', 'One', 'admin@gmail.com', 'adminpass789');

-- Insertion de 5 catégories d'articles
INSERT INTO categorie (nom, description) VALUES 
('Électronique', 'Appareils électroniques et gadgets'),
('Vêtements', 'Vêtements pour hommes, femmes et enfants'),
('Maison', 'Produits pour la maison et le jardin'),
('Sport', 'Équipements et accessoires de sport'),
('Livres', 'Livres de différents genres');

-- Insertion de 5 produits
INSERT INTO produit (nom, description, prix, image, categorie_id) VALUES 
('Smartphone XYZ', 'Un smartphone moderne avec des fonctionnalités avancées.', 799.99, 'smartphone.jpg', 1),
('T-shirt Coton', 'Un t-shirt en coton bio pour un confort optimal.', 19.99, 'tshirt.jpg', 2),
('Lampe de Bureau', 'Une lampe de bureau design et économique.', 49.99, 'lampe.jpg', 3),
('Raquette de Tennis', 'Raquette de tennis de qualité professionnelle.', 120.00, 'raquette.jpg', 4),
('Roman Policier', 'Un roman captivant plein de suspense.', 12.50, 'roman.jpg', 5);


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- panierVide.jsp -->
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Panier Vide</title>
</head>
<body>
    <h2>Votre panier est vide.</h2>
    <p>Il semble que vous n'ayez ajouté aucun produit à votre panier. Veuillez explorer nos produits et ajouter des articles à votre panier.</p>
    <a href="<%= request.getContextPath() %>/pages/produits.jsp">Voir nos produits</a>
</body>
</html>
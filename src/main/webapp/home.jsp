<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.onlineshop.modele.Categorie" %>
<%@ page import="com.onlineshop.modele.Produit" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produits</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
	<%@ include file="header.jsp" %>

    <div class="container mt-5">
        <div class="row">
            <%@ include file="aside.jsp" %>

            <div class="col-md-9">
                <div class="row">
                    <% 
					    // Récupérer la liste des produits depuis l'attribut de la requête
					    List<Produit> produits = (List<Produit>) request.getAttribute("produits");
					
					    // Vérifier si la liste des produits n'est pas vide ou nulle
					    if (produits != null && !produits.isEmpty()) {
					        // Boucler à travers chaque produit pour afficher ses informations
					        for (Produit produit : produits) {
					%>
					            <div class="col-md-4">
					                <div class="card mb-4 transparent-card">
					               		<img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit ...">
					                    <div class="card-body">
					                        <h5 class="card-title"><%= produit.getNom() %></h5>
					                        <p class="card-text"><%= produit.getDescription() %></p>
					                        <p class="card-text"><%= produit.getPrix() %> $</p>
					                        <a href="#" class="btn-ajouter">Ajouter</a>
					                    </div>
					                </div>
					            </div>
					<%  
					        }
					    } else {
					%>
					        <p>Aucun produit disponible.</p>
					<%
					    }
					%>
                    
                    
                    
                    
                </div>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
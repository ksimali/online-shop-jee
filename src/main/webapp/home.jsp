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
    <!-- Inclure l'en-tête -->
    <header class="p-3">
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="logo">
                    <h1>onlineshop</h1>
                </div>
                <nav>
                    <ul class="nav">
                        <li class="nav-item"><a class="nav-link text-white" href="#"><b>Accueil</b></a></li>
                        <li class="nav-item"><a class="nav-link text-white" href="#"><b>Contacter Nous</b></a></li>
                        <li class="nav-item"><a class="nav-link text-white" href="#"><b>FR</b></a></li>
                    </ul>
                </nav>
                <div class="search-bar">
                    <input type="text" class="form-control" placeholder="Recherche...">
                </div>
                <div class="d-flex">
                    <a href="#" class="btn">Panier</a>
                    <a href="#" class="btn">Se connecter</a>
                </div>
            </div>
            <hr>
        </div>
    </header>

    <div class="container mt-5">
        <div class="row">
            <!-- Inclure le menu latéral -->
            <div id="sidebar" class="col-md-3">
                <aside class="p-3">
                	<h5> Les catégories</h6>
                    <ul class="list-group">
                    	<!-- Utiliser un scriptlet pour boucler sur les catégories pas si simple cette affaire OMG :-(-->
        				<% 
        					List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
	        				if (categories != null && !categories.isEmpty()) {
	        	                for (Categorie categorie : categories) {
        				%>
                        			<li class="list-group-item"><%= categorie.getNom() %></li>
                        <%
	        	                }
	        				} else {
                        %>
                        		 <li class="list-group-item">Aucune catégorie disponible.</li>
                    	<%
	        				}
                    	%>
                    </ul>
                </aside>
            </div>

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

    <footer>
        <hr>
        <div class="footerBox">
            <nav>
                <ul class="nav">
                    <li class="nav-item"><a class="nav-link text-white" href="#"><b>Accueil</b></a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="#"><b>Contacter Nous</b></a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="#"><b>FR</b></a></li>
                </ul>
            </nav>
        </div>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
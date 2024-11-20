<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.onlineshop.modele.Panier" %>
<%@ page import="com.onlineshop.modele.Produit" %>
<%@ page import="com.onlineshop.modele.ProduitPanier" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panier</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <!-- Inclure l'en-tête -->
    <%@ include file="/header.jsp" %>

    <div class="container mt-5">
        <div class="row">
            <!-- Inclure le menu latéral -->
            <%@ include file="/aside.jsp" %>

            <div class="col-md-9">
                <h1 class="text-center">Panier</h1>
                
                <!-- Vérifier si le panier est vide -->
                <%
                    Panier panier = (Panier) session.getAttribute("panier");
                    if (panier == null || panier.getProduits() == null || panier.getProduits().isEmpty()) {
                %>
                    <div class="alert alert-warning text-center">
                        Votre panier est vide.
                    </div>
                <%
                    } else {
                %>
                <!-- Si le panier n'est pas vide, afficher le contenu -->
                <form action="panier" method="post">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="text-center align-middle">Produit</th>
                                <th class="text-center align-middle">Prix</th>
                                <th class="text-center align-middle">Quantité</th>
                                <th class="text-center align-middle" colspan="2">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (ProduitPanier produitPanier : panier.getProduits()) { 
                                Produit produit = produitPanier.getProduit();
                            %>
                            <tr>
                                <td class="align-middle"><%= produit.getNom() %></td>
                                <td class="align-middle"><%= String.format("%.2f", produit.getPrix()) %>€</td>
                                <td class="align-middle">
                                    <form action="<%= request.getContextPath() %>/panier" method="POST" class="d-inline">
									    <input type="hidden" name="action" value="modifier">
									    <input type="hidden" name="produitId" value="<%= produit.getId() %>">
									    <input type="number" class="form-control" name="quantite_<%= produit.getId() %>" value="<%= produitPanier.getQuantite() %>" min="1">
									    <button type="submit" class="btn btn-primary btn-sm ml-2">Modifier</button>
									</form>
                                
                                </td>
                                <td class="text-center align-middle">
                                    <form action="<%= request.getContextPath() %>/panier" method="POST" class="d-inline">
									    <input type="hidden" name="action" value="supprimer">
									    <input type="hidden" name="produitId" value="<%= produit.getId() %>">
									    <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
									</form>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                        <tfoot>
						    <tr>
						        <th colspan="3" class="text-right">Total :</th>
						        <th class="text-center"><%= String.format("%.2f", panier.calculerTotal()) %>€</th>
						    </tr>
						</tfoot>
                    </table>
                </form>
                <%
                    }
                %>
                
                <div class="text-right">
                     <form action="<%= request.getContextPath() %>/" method="get">
                        <button type="submit" class="btn btn-secondary">Continuer à magasiner</button>
                    </form>
                    <form action="enregistrer" method="get">
                        <button type="submit" class="btn btn-success">Acheter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Inclure le footer -->
    <%@ include file="/footer.jsp" %>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panier</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css">
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
                <c:if test="${empty panier}">
                    <div class="alert alert-warning text-center">
                        Votre panier est vide.
                    </div>
                </c:if>

                <!-- Si le panier n'est pas vide, afficher le contenu -->
                <c:if test="${not empty panier}">
                    <form action="modifierPanier.jsp" method="post">
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
                                <!-- Utilisation de JSTL pour afficher les produits du panier -->
                                <c:forEach var="produit" items="${panier}">
                                    <tr>
                                        <td class="align-middle">${produit.nom}</td>
                                        <td class="align-middle">
                                            <fmt:formatNumber value="${produit.prix}" pattern="##.00" />€
                                        </td>
                                        <td class="align-middle">
                                            <input type="number" class="form-control" name="quantite_${produit.id}" value="${produit.quantite}" min="1">
                                        </td>
                                        <td class="align-middle">
                                            <button type="submit" name="action" value="modifier_${produit.id}" class="btn btn-primary">Modifier</button>
                                        </td>
                                        <td class="text-center align-middle">
                                            <button type="submit" name="action" value="supprimer_${produit.id}" class="btn btn-danger">Supprimer</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </c:if>
                
                <div class="text-right">
                    <form action="${pageContext.request.contextPath}/" method="get">
                        <button type="submit" class="btn btn-secondary">Continuer à magasiner</button>
                    </form>
                    <form action="acheter.jsp" method="get">
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

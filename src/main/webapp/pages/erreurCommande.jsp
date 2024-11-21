<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur de Commande</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <h1 class="text-center text-danger">Erreur de Commande</h1>

                <p class="lead">Désolé, une erreur est survenue lors de l'enregistrement de votre commande. Voici les détails de l'erreur :</p>

                <div class="alert alert-danger" role="alert">
                    <strong>Erreur :</strong> ${errorMessage}
                </div>

                <div class="text-center">
                    <a href="panier.jsp" class="btn btn-warning">Retourner au panier</a>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Retour à l'accueil</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
    
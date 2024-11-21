<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmation de la Commande</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <h1 class="text-center text-success">Commande Confirmée</h1>

                <p class="lead">Merci pour votre commande ! Nous avons bien reçu votre commande. Voici les détails de votre commande :</p>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Prénom</th>
                            <th scope="col">Courriel</th>
                            <th scope="col">Adresse de Livraison</th>
                            <th scope="col">Date de Commande</th>
                            <th scope="col">Date de Livraison</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${commande.nom}</td>
                            <td>${commande.prenom}</td>
                             <td>${commande.courriel}</td>
                            <td>${commande.adresseLivraison}</td>
                            <td>${commande.dateCommande}</td>
                            <td>${commande.dateLivraison}</td>
                        </tr>
                    </tbody>
                </table>

                <p>Nous vous enverrons un email de confirmation avec les détails de la livraison.</p>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Retour à l'accueil</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

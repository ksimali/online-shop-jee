<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Votre commande</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <!-- Inclure l'en-tête -->
    <%@ include file="../header.jsp" %>

    <div class="container mt-5">
        <div class="row">
            <!-- Inclure le menu latéral -->
            <%@ include file="../aside.jsp" %>

            <div class="col-md-9">
                <h5 class="text-white">Votre commande</h5>
                <hr>

                <!-- Formulaire de commande -->
                <form action="${pageContext.request.contextPath}/enregistrer" method="post">
                    
                    <!-- Informations personnelles et date livraison -->
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="dateLivraison">Date de Livraison</label>
                            <input type="date" id="dateLivraison" name="dateLivraison" class="form-control" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="prenom">Prénom</label>
                            <input type="text" id="prenom" name="prenom" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="nom">Nom</label>
                            <input type="text" id="nom" name="nom" class="form-control" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="telephone">Téléphone</label>
                            <input type="tel" id="telephone" name="telephone" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="courriel">Courriel</label>
                            <input type="email" id="courriel" name="courriel" class="form-control" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="adresse">Adresse</label>
                            <input type="text" id="adresse" name="adresse" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="ville">Ville</label>
                            <select id="ville" name="ville" class="form-control" required>
                                <option value="">Sélectionner une ville</option>
                                <option value="Montreal">Montréal</option>
                                <option value="Quebec">Québec</option>
                                <option value="Ottawa">Ottawa</option>
                                <option value="Toronto">Toronto</option>
                                <option value="Vancouver">Vancouver</option>
                                <option value="Calgary">Calgary</option>
                                <option value="Edmonton">Edmonton</option>
                                <option value="Winnipeg">Winnipeg</option>
                                <option value="Halifax">Halifax</option>
                                <option value="Victoria">Victoria</option>
                                <option value="Hamilton">Hamilton</option>
                                <option value="Ottawa">Ottawa</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="province">Province</label>
                            <select id="province" name="province" class="form-control" required>
                                <option value="">Sélectionner une province</option>
                                <option value="QC">Québec</option>
                                <option value="ON">Ontario</option>
                                <option value="BC">Colombie-Britannique</option>
                                <option value="AB">Alberta</option>
                                <option value="MB">Manitoba</option>
                                <option value="NB">Nouveau-Brunswick</option>
                                <option value="NL">Terre-Neuve-et-Labrador</option>
                                <option value="NS">Nouvelle-Écosse</option>
                                <option value="PE">Île-du-Prince-Édouard</option>
                                <option value="SK">Saskatchewan</option>
                                <option value="NT">Territoires du Nord-Ouest</option>
                                <option value="NU">Nunavut</option>
                                <option value="YT">Yukon</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="pays">Pays</label>
                            <input type="text" id="pays" name="pays" class="form-control" value="Canada" readonly>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="codePostal">Code Postal</label>
                            <input type="text" id="codePostal" name="codePostal" class="form-control" required>
                        </div>
                    </div>
                    <hr>
                    <!-- Adresse de livraison -->
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="adresseLivraison">Adresse de Livraison</label>
                            <input type="text" id="adresseLivraison" name="adresseLivraison" class="form-control" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="villeLivraison">Ville</label>
                            <select id="villeLivraison" name="villeLivraison" class="form-control" required>
                                <option value="">Sélectionner une ville</option>
                                <option value="Montreal">Montréal</option>
                                <option value="Quebec">Québec</option>
                                <option value="Ottawa">Ottawa</option>
                                <option value="Toronto">Toronto</option>
                                <option value="Vancouver">Vancouver</option>
                                <option value="Calgary">Calgary</option>
                                <option value="Edmonton">Edmonton</option>
                                <option value="Winnipeg">Winnipeg</option>
                                <option value="Halifax">Halifax</option>
                                <option value="Victoria">Victoria</option>
                                <option value="Hamilton">Hamilton</option>
                                <option value="Ottawa">Ottawa</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="provinceLivraison">Province</label>
                            <select id="provinceLivraison" name="provinceLivraison" class="form-control" required>
                                <option value="">Sélectionner une province</option>
                                <option value="QC">Québec</option>
                                <option value="ON">Ontario</option>
                                <option value="BC">Colombie-Britannique</option>
                                <option value="AB">Alberta</option>
                                <option value="MB">Manitoba</option>
                                <option value="NB">Nouveau-Brunswick</option>
                                <option value="NL">Terre-Neuve-et-Labrador</option>
                                <option value="NS">Nouvelle-Écosse</option>
                                <option value="PE">Île-du-Prince-Édouard</option>
                                <option value="SK">Saskatchewan</option>
                                <option value="NT">Territoires du Nord-Ouest</option>
                                <option value="NU">Nunavut</option>
                                <option value="YT">Yukon</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="paysLivraison">Pays</label>
                            <input type="text" id="paysLivraison" name="paysLivraison" class="form-control" value="Canada" readonly>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="codePostalLivraison">Code Postal</label>
                            <input type="text" id="codePostalLivraison" name="codePostalLivraison" class="form-control" required>
                        </div>
                    </div>
                    <hr>
                    <!-- Paiement -->
                    <h6 class="text-white">Paiement</h6>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="numeroCarteCredit">Carte de Crédit</label>
                            <input type="text" id="numeroCarteCredit" name="numeroCarteCredit" class="form-control" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="dateExpiration">Date d'Expiration</label>
                            <input type="text" id="dateExpiration" name="dateExpiration" class="form-control" 
           							placeholder="MM/AA" pattern="^(0[1-9]|1[0-2])\/\d{2}$" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="ccv">CC</label>
                            <input type="text" id="ccv" name="ccv" class="form-control" placeholder="122" required>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Envoyer ma commande</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

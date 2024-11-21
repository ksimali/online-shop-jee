<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.onlineshop.modele.Client" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gérer mon compte</title>
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
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-primary">${successMessage}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/auth?action=update" method="POST">
                    <input type="hidden" name="clientId" value="${client.id}">
                    <h5 class="text-white">Gérer mon compte</h5>
                    <hr>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="firstName">Prénom:</label>
                            <input type="text" class="form-control transparent-input" id="firstName" name="firstName" value="${client.prenom}" placeholder="Entrez votre prénom">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="lastName">Nom:</label>
                            <input type="text" class="form-control transparent-input" id="lastName" name="lastName" value="${client.nom}" placeholder="Entrez votre nom">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="date_naissance">Date de naissance:</label>
                            <input type="date" class="form-control transparent-input" id="date_naissance" name="date_naissance" value="${client.dateNaissance}">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="telephone">Téléphone:</label>
                            <input type="tel" class="form-control transparent-input" id="telephone" name="telephone" value="${client.telephone}" placeholder="Entrez votre téléphone">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="email">Courriel:</label>
                            <input type="email" class="form-control transparent-input" id="email" name="email" value="${client.email}" placeholder="Entrez votre courriel">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="confirm_email">Confirmer courriel:</label>
                            <input type="email" class="form-control transparent-input" id="confirm_email" name="confirm_email" value="${client.email}" placeholder="Confirmez votre courriel">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="pwd">Mot de passe:</label>
                            <input type="password" class="form-control transparent-input" id="pwd" name="pwd" value="${client.motDePasse}" placeholder="Entrez votre mot de passe">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="confirm_pwd">Confirmer mot de passe:</label>
                            <input type="password" class="form-control transparent-input" id="confirm_pwd" name="confirm_pwd" value="${client.motDePasse}" placeholder="Confirmez votre mot de passe">
                        </div>
                    </div>

                    <br>

                    <h5 class="text-white">Adresse</h5>
                    <hr>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="adresse_domicile">Adresse:</label>
                            <input type="text" class="form-control transparent-input" id="adresse_domicile" name="adresse_domicile" value="${client.adresseDomicile}" placeholder="Entrez votre adresse">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="ville_domicile">Ville:</label>
                            <input type="text" class="form-control transparent-input" id="ville_domicile" name="ville_domicile" value="${client.villeDomicile}" placeholder="Entrez votre ville">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="province_domicile">Province:</label>
                            <input type="text" class="form-control transparent-input" id="province_domicile" name="province_domicile" value="${client.provinceDomicile}" placeholder="Entrez votre province">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="pays_domicile">Pays:</label>
                            <input type="text" class="form-control transparent-input" id="pays_domicile" name="pays_domicile" value="${client.paysDomicile}" placeholder="Entrez votre pays">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="codepostal_domicile">Code Postal:</label>
                            <input type="text" class="form-control transparent-input" id="codepostal_domicile" name="codepostal_domicile" value="${client.codepostalDomicile}" placeholder="Entrez votre code postal">
                        </div>
                    </div>

                    <br>

                    <h5 class="text-white">Adresse de livraison</h5>
                    <hr>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="adresse_livraison">Adresse:</label>
                            <input type="text" class="form-control transparent-input" id="adresse_livraison" name="adresse_livraison" value="${client.adresseLivraison}" placeholder="Entrez votre adresse de livraison">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="ville_livraison">Ville:</label>
                            <input type="text" class="form-control transparent-input" id="ville_livraison" name="ville_livraison" value="${client.villeLivraison}" placeholder="Entrez votre ville de livraison">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="province_livraison">Province:</label>
                            <input type="text" class="form-control transparent-input" id="province_livraison" name="province_livraison" value="${client.provinceLivraison}" placeholder="Entrez votre province de livraison">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="pays_livraison">Pays:</label>
                            <input type="text" class="form-control transparent-input" id="pays_livraison" name="pays_livraison" value="${client.paysLivraison}" placeholder="Entrez votre pays de livraison">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="codepostal_livraison">Code Postal:</label>
                            <input type="text" class="form-control transparent-input" id="codepostal_livraison" name="codepostal_livraison" value="${client.codepostalLivraison}" placeholder="Entrez votre code postal de livraison">
                        </div>
                    </div>

                    <div class="btn-register">
                        <button type="submit" id="btn-register-save" class="btn">Enregistrer</button>
                    </div>
                </form>
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
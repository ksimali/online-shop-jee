<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page de Connexion</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <!-- Inclure l'en-tête -->
    <header class="p-3">
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="logo">
                    <h1>Logo</h1>
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
                    <ul class="list-group">
                        <li class="list-group-item">Catégorie 1</li>
                        <li class="list-group-item">Catégorie 2</li>
                        <li class="list-group-item">Catégorie 3</li>
                        <li class="list-group-item">Catégorie 4</li>
                        <li class="list-group-item">Catégorie 5</li>
                        <li class="list-group-item">Catégorie 6</li>
                        <li class="list-group-item">Catégorie 7</li>
                    </ul>
                </aside>
            </div>

            <div class="col-md-9">
                <h2 class="text-white">Connexion</h2><br/>
                <form action="${pageContext.request.contextPath}/auth" method="POST" style="margin-left: 100px;">
                	<input type="hidden" name="action" value="login">
                    <div class="form-group">
                        <label for="email">Courriel:</label>
                        <input type="email" name="email" class="form-control transparent-input w-75" id="email" placeholder="Entrez votre courriel">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Mot de passe:</label>
                        <input type="password" name="pwd" class="form-control transparent-input w-75" id="pwd" placeholder="Entrez votre mot de passe">
                    </div>
                    <div class="form-group">
                        <label for="type">Type utilisateur:</label>
                        <select name="type" id="type" required class="form-control transparent-input w-75">
		                    <option value="">Sélectionner un type...</option>
		                    <option value="admin">Administrateur</option>
		                    <option value="client">Client</option>
		                </select>
                    </div>
                    <div class="btn-login">
                        <button type="submit" id="btn-login-ok" class="btn btn-primary">OK</button>
                        <button type="button" id="btn-login-register" class="btn btn-secondary" onclick="window.location.href='register.jsp'">Créer un compte</button>  
                    </div>
                </form>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
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

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil Administrateur</title>
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
                        <li class="nav-item"><a class="nav-link text-white" href="#"><b>Module Administrateur</b></a></li>
                    </ul>
                </nav>
                <div class="search-bar">
                    <input type="text" class="form-control" placeholder="Recherche...">
                </div>
                <div class="d-flex">
                    <a href="#" class="btn">Admin</a>
                </div>
            </div>
            <hr>
        </div>
    </header>

    <div class="container mt-3 d-flex justify-content-center align-items-center">
        <div class="w-25">
            <div class="mb-3">
                <button onclick="window.location.href='gcategories'" class="btn btn-warning btn-block" style="background-color: #BF9000;">Gestion de Catégorie</button>
            </div>
            <div>
                <button onclick="window.location.href='gproduits'" class="btn btn-warning btn-block" style="background-color: #BF9000;">Gestion de Produit</button>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

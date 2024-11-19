<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produits</title>
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
                <div class="row">
                    <div class="col-md-4">
                        <div class="card mb-4 transparent-card">
                            <img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit 1">
                            <div class="card-body">
                                <h5 class="card-title">Produit 1</h5>
                                <p class="card-text">Description du produit 1.</p>
                                <p class="card-text">Prix: 24.99$</p>
                                <a href="#" class="btn-ajouter">Ajouter</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 transparent-card">
                            <img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit 2">
                            <div class="card-body">
                                <h5 class="card-title">Produit 2</h5>
                                <p class="card-text">Description du produit 2.</p>
                                <p class="card-text">Prix: 29.99$</p>
                                <a href="#" class="btn-ajouter">Ajouter</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 transparent-card">
                            <img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit 3">
                            <div class="card-body">
                                <h5 class="card-title">Produit 3</h5>
                                <p class="card-text">Description du produit 3.</p>
                                <p class="card-text">Prix: 19.99$</p>
                                <a href="#" class="btn-ajouter">Ajouter</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 transparent-card">
                            <img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit 4">
                            <div class="card-body">
                                <h5 class="card-title">Produit 4</h5>
                                <p class="card-text">Description du produit 4.</p>
                                <p class="card-text">Prix: 34.99$</p>
                                <a href="#" class="btn-ajouter">Ajouter</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 transparent-card">
                            <img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit 5">
                            <div class="card-body">
                                <h5 class="card-title">Produit 5</h5>
                                <p class="card-text">Description du produit 5.</p>
                                <p class="card-text">Prix: 49.99$</p>
                                <a href="#" class="btn-ajouter">Ajouter</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 transparent-card">
                            <img src="https://www.pngkey.com/png/detail/233-2332677_image-500580-placeholder-transparent.png" class="card-img-top" alt="Produit 6">
                            <div class="card-body">
                                <h5 class="card-title">Produit 6</h5>
                                <p class="card-text">Description du produit 6.</p>
                                <p class="card-text">Prix: 39.99$</p>
                                <a href="#" class="btn-ajouter">Ajouter</a>
                            </div>
                        </div>
                    </div>
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

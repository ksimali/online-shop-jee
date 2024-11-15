<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Produits</title>
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

    <div class="container mt-5">
        <h5>Gestion des Produits</h5>
        <hr>

        <form class="mb-5  ml-3">
            <div class="form-row d-flex flex-column">
                <div class="form-group col-md-6">
                    <label for="categorie">Catégorie:</label>
                    <select class="form-control transparent-input" id="categorie">
                        <option>Catégorie 1</option>
                        <option>Catégorie 2</option>
                        <option>Catégorie 3</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label for="nom">Nom:</label>
                    <input type="text" class="form-control transparent-input" id="nom" placeholder="Entrez le nom du produit">
                </div>
            </div>
            <div class="form-row  d-flex flex-column">
                <div class="form-group col-md-6">
                    <label for="prix">Prix:</label>
                    <input type="text" class="form-control transparent-input" id="prix" placeholder="Entrez le prix du produit">
                </div>
                <div class="form-group col-md-6">
                    <label for="image">Image:</label>
                    <input type="text" class="form-control transparent-input" id="image" placeholder="Entrez l'URL de l'image du produit">
                </div>
            </div>
            <button type="submit" id="btn-produit-save" class="btn btn-primary">Enregistrer</button>
        </form>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center align-middle">Catégorie</th>
                    <th class="text-center align-middle">Nom</th>
                    <th class="text-center align-middle">Prix</th>
                    <th class="text-center align-middle">Image</th>
                    <th class="text-center align-middle" colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-center align-middle">Catégorie 1</td>
                    <td class="text-center align-middle">Produit 1</td>
                    <td class="text-center align-middle">14.99$</td>
                    <td class="text-center align-middle">D:\images\img1.jpg</td>
                    <td class="text-center align-middle">
                        <button class="btn-action-modif">Modifier</button>
                    </td>
                    <td class="text-center align-middle">
                        <button class="btn-action-sup">Supprimer</button>
                    </td>
                </tr>
                <tr>
                    <td class="text-center align-middle">Catégorie 1</td>
                    <td class="text-center align-middle">Produit 2</td>
                    <td class="text-center align-middle">24.99$</td>
                    <td class="text-center align-middle">D:\images\img2.jpg</td>
                    <td class="text-center align-middle">
                        <button class="btn-action-modif">Modifier</button>
                    </td>
                    <td class="text-center align-middle">
                        <button class="btn-action-sup">Supprimer</button>
                    </td>
                </tr>
                <tr>
                    <td class="text-center align-middle">Catégorie 1</td>
                    <td class="text-center align-middle">Produit 3</td>
                    <td class="text-center align-middle">100.99$</td>
                    <td class="text-center align-middle">D:\images\img3.jpg</td>
                    <td class="text-center align-middle">
                        <button class="btn-action-modif">Modifier</button>
                    </td>
                    <td class="text-center align-middle">
                        <button class="btn-action-sup">Supprimer</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

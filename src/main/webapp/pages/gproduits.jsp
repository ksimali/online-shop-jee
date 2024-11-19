<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Produits</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <script type="text/javascript">
        // Fonction pour afficher le formulaire d'édition avec les bonnes valeurs
        function showEditForm(id, nom, prix, image, categorieId) {
            // Affiche le formulaire d'édition
            document.getElementById('editForm').style.display = 'block';
            document.getElementById('addForm').style.display = 'none';
            
            // Remplir le formulaire avec les valeurs actuelles du produit
            document.getElementById('editId').value = id;
            document.getElementById('editNom').value = nom;
            document.getElementById('editPrix').value = prix;
            document.getElementById('editImage').value = image;
            document.getElementById('editCategorie').value = categorieId;
        }

        // Fonction pour masquer le formulaire d'édition
        function hideEditForm() {
            document.getElementById('editForm').style.display = 'none';
            document.getElementById('addForm').style.display = 'block';
        }
        
        function deleteProduit(id) {
            if (confirm("Voulez-vous vraiment supprimer ce produit ?")) {
                // Effectuer une requête POST avec fetch
                fetch("${pageContext.request.contextPath}/gproduits", {
                    method: "POST", // Méthode HTTP POST
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded" // Type de contenu de la requête
                    },
                    body: new URLSearchParams({
                        action: 'delete', // L'action de suppression
                        id: id // L'ID du produit à supprimer
                    })
                })
                .then(response => {
                    if (response.ok) {
                        // Si la suppression a réussi, on recharge la page ou on met à jour la liste
                        window.location.reload(); // Recharge la page après suppression
                    } else {
                        // Si la suppression a échoué, on affiche un message d'erreur
                        alert('La suppression du produit a échoué.');
                    }
                })
                .catch(error => {
                    console.error('Erreur lors de la suppression:', error);
                    alert('Une erreur est survenue lors de la suppression.');
                });
            }
        }
    </script>
</head>
<body>
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

        <!-- Formulaire d'ajout d'un nouveau produit -->
        <div id="addForm" style="display:block;">
	        <form method="POST" action="${pageContext.request.contextPath}/gproduits">
	            <input type="hidden" name="action" value="create" />
	            <div class="form-row d-flex flex-column">
	                <div class="form-group col-md-6">
	                    <label for="categorieId">Catégorie:</label>
	                    <select class="form-control" id="categorieId" name="categorieId">
	                        <c:forEach var="categorie" items="${categories}">
	                            <option value="${categorie.id}">${categorie.nom}</option>
	                        </c:forEach>
	                    </select>
	                </div>
	                <div class="form-group col-md-6">
	                    <label for="nom">Nom:</label>
	                    <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom du produit" />
	                </div>
	            </div>
	
	            <div class="form-row d-flex flex-column">
	                <div class="form-group col-md-6">
	                    <label for="prix">Prix:</label>
	                    <input type="text" class="form-control" id="prix" name="prix" placeholder="Prix du produit" />
	                </div>
	                <div class="form-group col-md-6">
	                    <label for="image">Image:</label>
	                    <input type="text" class="form-control" id="image" name="image" placeholder="URL de l'image" />
	                </div>
	            </div>
	
	            <button type="submit" class="btn-action-modif">Enregistrer</button>
	        </form>
	        <br/>
		</div>
        <!-- Formulaire d'édition (initialement caché) -->
        <div id="editForm" style="display:none;">
            <form method="POST" action="${pageContext.request.contextPath}/gproduits">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" id="editId" name="id" />
                
                <div class="form-row d-flex flex-column">
                    <div class="form-group col-md-6">
                        <label for="editCategorie">Catégorie:</label>
                        <select class="form-control" id="editCategorie" name="categorieId">
                            <c:forEach var="categorie" items="${categories}">
                                <option value="${categorie.id}">${categorie.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="editNom">Nom:</label>
                        <input type="text" class="form-control" id="editNom" name="nom" />
                    </div>
                </div>

                <div class="form-row d-flex flex-column">
                    <div class="form-group col-md-6">
                        <label for="editPrix">Prix:</label>
                        <input type="text" class="form-control" id="editPrix" name="prix" />
                    </div>
                    <div class="form-group col-md-6">
                        <label for="editImage">Image:</label>
                        <input type="text" class="form-control" id="editImage" name="image" />
                    </div>
                </div>

                <button type="submit" class="btn-action-modif">Enregistrer</button>
                <button type="button" class="btn-action-sup" onclick="hideEditForm()">Annuler</button>
            </form>
            <br/>
        </div>

        <!-- Tableau des produits -->
        <div class="formProd">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center align-middle text-white">Catégorie</th>
                        <th class="text-center align-middle text-white">Nom</th>
                        <th class="text-center align-middle text-white">Prix</th>
                        <th class="text-center align-middle text-white">Image</th>
                        <th class="text-center align-middle text-white" colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produit" items="${produits}">
                        <tr>
                            <td class="text-center align-middle">${produit.categorieNom}</td>
                            <td class="text-center align-middle" id="nom_${produit.id}">${produit.nom}</td>
                            <td class="text-center align-middle" id="prix_${produit.id}">${produit.prix}€</td>
                            <td class="text-center align-middle" id="image_${produit.id}">
                                <img src="${produit.image}" id="img_${produit.id}" alt="${produit.nom}" class="img-thumbnail" />
                            </td>
                            <td class="text-center align-middle">
                                <button type="button" class="btn-action-modif" onclick="showEditForm(${produit.id}, '${produit.nom}', '${produit.prix}', '${produit.image}', ${produit.categorieId})">Modifier</button>
                            </td>
                            <td class="text-center align-middle">
                                <button type="button" class="btn-action-sup" onclick="deleteProduit(${produit.id})">Supprimer</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

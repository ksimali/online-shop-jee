<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Catégories</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    
    <script type="text/javascript">
	    function showEditForm(id) {
	        // Récupère les éléments de la ligne
	        var divId = document.getElementById('id_' + id);
	        var nom = document.getElementById('nom_' + id);
	        var description = document.getElementById('description_' + id);
	        var modifyButton = document.getElementById('modifyBtn_' + id);
	        var saveButton = document.getElementById('saveBtn_' + id);
	        var formCat = document.getElementById('formCat');
	        
	        // Encode les valeurs en UTF-8 pour éviter les problèmes de caractères spéciaux
	        var encodedNom = encodeURIComponent(nom.innerHTML);
	        var encodedDescription = encodeURIComponent(description.innerHTML);
	        
	        // Transforme le texte en champ de saisie
	        divId.innerHTML = "<input type='hidden' name='id' value=" + id + " />";
	        nom.innerHTML = "<input style='width:100%; border-color:transparent;' name='nom' type='text' id='inputNom_" + id + "' value='" + decodeURIComponent(encodedNom) + "' />";
	        description.innerHTML = "<input style='width:100%; border-color:transparent;' name='description' type='text' id='inputDescription_" + id + "' value='" + decodeURIComponent(encodedDescription) + "' />";
	        
	        // Affiche le bouton de sauvegarde et cache le bouton de modification
	        saveButton.style.display = "block";
	        modifyButton.style.display = "none";
	        
	        // Remplace le formulaire avec la bonne action
	        formCat.innerHTML = "<form action='${pageContext.request.contextPath}/gcategories' method='POST'><input type='hidden' name='action' value='update' />" + formCat.innerHTML + "</form>";
	    }
	    
	    function deleteCategorie(id) {
            if (confirm("Voulez-vous vraiment supprimer cette categorie ?")) {
                // Effectuer une requête POST avec fetch
                fetch("${pageContext.request.contextPath}/gcategories", {
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
                        alert('La suppression de la categorie a échoué.');
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
                    <h1>onlineshop</h1>
                </div>
                <nav>
                    <ul class="nav">
                        <li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/pages/accueilAdmin.jsp"><b>Module Administrateur</b></a></li>
                    </ul>
                </nav>
                <div class="search-bar">
                    <input type="text" class="form-control" placeholder="Recherche...">
                </div>
                <div class="d-flex">
                    <a href="${pageContext.request.contextPath}/auth?action=logout" class="btn">Se deconnecter</a>
                    <a href="#" class="btn">Admin</a>
                </div>
            </div>
            <hr>
        </div>
    </header>


    <div class="container mt-5">
        <h5>Gestion de Catégorie</h5>
        <hr>
        <form method="post" action="${pageContext.request.contextPath}/gcategories" class="mb-5 ml-3">
        	<input type='hidden' name='action' value='create' />
            <div class="form-row d-flex flex-column">
                <div class="form-group col-md-6">
                    <label for="nom">Nom:</label>
                    <input type="text" class="form-control transparent-input" id="nom" name="nom" placeholder="Entrez le nom de la catégorie">
                </div>
                <div class="form-group col-md-6">
                    <label for="description">Description:</label>
                    <input type="text" class="form-control transparent-input" id="description" name="description" placeholder="Entrez la description de la catégorie">
                </div>
            </div>
            <button type="submit" class="btn-action-modif">Enregistrer</button>
        </form>

	<div id="formCat">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center align-middle text-white">Nom</th>
                    <th class="text-center align-middle text-white">Description</th>
                    <th class="text-center align-middle text-white" colspan="2">Action</th>
                </tr>
            </thead>
            <tbody class="tabCategorie">
                <!-- Affichage des catégories dynamiquement -->
                <c:forEach var="categorie" items="${categories}">
                    <tr>
                        <td class="text-center align-middle" id="nom_${categorie.id}">${categorie.nom}</td>
                        <td class="text-center align-middle" id="description_${categorie.id}">${categorie.description}</td>
                        <td class="text-center align-middle">
                        	<div id="id_${categorie.id}"></div>
                        	<button  type='submit' class='btn-action-modif' id='saveBtn_${categorie.id}' style='display:none;'>Enregistrer</button>
                            <button class="btn-action-modif" id="modifyBtn_${categorie.id}" style='display:block;' onclick="showEditForm(${categorie.id})">Modifier</button>
                        </td>
                        <td class="text-center align-middle">
                            <button class="btn-action-sup"  id="deleteBtn_${categorie.id}" onclick="deleteCategorie(${categorie.id})">Supprimer</button>
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

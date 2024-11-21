<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Validation de la Commande</title>
    <style>
        .form-container {
            display: flex;
            justify-content: space-between;
        }
        .column {
            width: 48%;
        }
        .form-group {
            margin-bottom: 10px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        button {
            margin-top: 20px;
            padding: 10px 20px;
        }
    </style>
</head>
<body>
    <h1>Validation de la Commande</h1>
    <form action="enregistrer" method="post">
        <div class="form-container">
            <div class="column">
                <h3>Informations personnelles</h3>
                <div class="form-group">
                    <label for="dateLivraison">Date de Livraison</label>
                    <input type="date" id="dateLivraison" name="dateLivraison" required>
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom" required>
                </div>
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom" required>
                </div>
                <div class="form-group">
                    <label for="telephone">Téléphone</label>
                    <input type="tel" id="telephone" name="telephone" required>
                </div>
                <div class="form-group">
                    <label for="courriel">Courriel</label>
                    <input type="email" id="courriel" name="courriel" required>
                </div>
                <div class="form-group">
                    <label for="adresse">Adresse</label>
                    <input type="text" id="adresse" name="adresse" required>
                </div>
                <div class="form-group">
                    <label for="ville">Ville</label>
                    <input type="text" id="ville" name="ville" required>
                </div>
                <div class="form-group">
                    <label for="province">Province</label>
                    <input type="text" id="province" name="province" required>
                </div>
                <div class="form-group">
                    <label for="pays">Pays</label>
                    <input type="text" id="pays" name="pays" required>
                </div>
                <div class="form-group">
                    <label for="codePostal">Code Postal</label>
                    <input type="text" id="codePostal" name="codePostal" required>
                </div>
            </div>

            <div class="column">
                <h3>Adresse de livraison</h3>
                <div class="form-group">
                    <label for="adresseLivraison">Adresse de Livraison</label>
                    <input type="text" id="adresseLivraison" name="adresseLivraison" required>
                </div>
                <div class="form-group">
                    <label for="villeLivraison">Ville</label>
                    <input type="text" id="villeLivraison" name="villeLivraison" required>
                </div>
                <div class="form-group">
                    <label for="provinceLivraison">Province</label>
                    <input type="text" id="provinceLivraison" name="provinceLivraison" required>
                </div>
                <div class="form-group">
                    <label for="paysLivraison">Pays</label>
                    <input type="text" id="paysLivraison" name="paysLivraison" required>
                </div>
                <div class="form-group">
                    <label for="codePostalLivraison">Code Postal</label>
                    <input type="text" id="codePostalLivraison" name="codePostalLivraison" required>
                </div>

                <h3>Paiement</h3>
                <div class="form-group">
                    <label for="numeroCarteCredit">Carte de Crédit</label>
                    <input type="text" id="numeroCarteCredit" name="numeroCarteCredit" required>
                </div>
                <div class="form-group">
                    <label for="dateExpiration">Date d'Expiration</label>
                    <input type="month" id="dateExpiration" name="dateExpiration" required>
                </div>
                <div class="form-group">
                    <label for="ccv">CCV</label>
                    <input type="text" id="ccv" name="ccv" required>
                </div>

                <button type="submit">Envoyer ma commande</button>
            </div>
        </div>
    </form>
</body>
</html>
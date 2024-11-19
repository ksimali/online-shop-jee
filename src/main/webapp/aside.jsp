<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.onlineshop.modele.Categorie" %>
<%@ page import="com.onlineshop.modele.Produit" %>

<!-- Inclure le menu latéral -->
       <div id="sidebar" class="col-md-3">
       		<aside class="p-3">
            	<h5> Les catégories</h5>
                <ul class="list-group list-unstyled">
                	<!-- Utiliser un code JSTL plutot pour boucler sur les catégories, heureusement car pas si simple cette affaire de scriplet OMG :-(-->
    				<!-- Vérifier d'abord si la liste des catégories existe et n'est pas vide -->
					<c:if test="${not empty categories}">
					    <!-- Boucler sur la liste des catégories -->
					    <c:forEach var="categorie" items="${categories}">
					        <li><a class="list-group-item" href="?categorieId=${categorie.id}">${categorie.nom}</a></li>
					    </c:forEach>
					</c:if>
					
					<!-- Message à afficher si aucune catégorie n'est disponible -->
					<c:if test="${empty categories}">
					    <li class="list-group-item">Aucune catégorie disponible.</li>
					</c:if>
                </ul>
            </aside>
        </div>
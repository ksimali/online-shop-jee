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
                	<!-- Utiliser un scriptlet pour boucler sur les catégories pas si simple cette affaire OMG :-(-->
    				<% 
    					List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
     					if (categories != null && !categories.isEmpty()) {
     	                	for (Categorie categorie : categories) {
    				%>
                   				<li><a class="list-group-item" href="?categorieId=<%= categorie.getId() %>"><%= categorie.getNom() %></a></li>
                    <%
     	                	}
     					} else {
                    %>
                    		 <li class="list-group-item">Aucune catégorie disponible.</li>
                	<%
     				}
                	%>
                </ul>
            </aside>
        </div>
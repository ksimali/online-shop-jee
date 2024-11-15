<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur génerale</title>
</head>
<body>
	<h1>Une erreur est survenue</h1>
    <p>${errorMessage}</p>
    <p>Erreur inconnue : <%= request.getAttribute("errorMessage") %></p>
    
</body>
</html>
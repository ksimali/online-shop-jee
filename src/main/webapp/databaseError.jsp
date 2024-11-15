<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur de base de données</title>
</head>
<body>
	<h1>Erreur de base de données</h1>
    <p>${errorMessage}</p>
    <p>Erreur de base de données : <%= request.getAttribute("error") %></p>
</body>
</html>
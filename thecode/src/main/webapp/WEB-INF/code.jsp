<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter Code</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/code.css" />

</head>
<body>
	<p class="flashcenter"><c:out value="${flash}"/></p>
	<p>What is the code?</p>
	<form action="/submitcode" method="post">
		<input class="codeinput" type="text" name="code"/>
		<input class="btn btn-primary btn-center" type="submit" value="Submit"/>
	</form>
</body>
</html>
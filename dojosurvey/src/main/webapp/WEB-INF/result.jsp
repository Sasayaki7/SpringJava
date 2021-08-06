<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/form.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<div class="borderBox">
		<h2>Submitted Info</h2>
		<div class="inputrow">
			<p>Name: </p>
			<p><c:out value="${name}"/></p>
		</div>
		<div class="inputrow">
			<p>Dojo Location: </p>
			<p><c:out value="${location}"/></p>
		</div>
		<div class="inputrow">
			<p>Favorite Language: </p>
			<p><c:out value="${language}"/></p>
		</div>
		<div class="inputrow">
			<p>Comment: </p>
			<p><c:out value="${comment}"/></p>
		</div>
	</div>
</body>
</html>
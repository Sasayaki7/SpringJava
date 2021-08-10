<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<a href="/dashboard">Dashboard</a>
	<div class="input-row">
		<p class="p1">Title</p>
		<p class="p2">${song.title }</p>
	</div>
	<div class="input-row">
		<p class="p1">Artist</p>
		<p class="p2">${song.artist}</p>
	</div>	
	<div class="input-row">
		<p class="p1">Rating (0-10)</p>
		<p class="p2">${song.rating}</p>
	</div>
	<form action="/songs/${song.id}" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit" class="btn btn-danger" value="Delete"/>
	</form>
</body>
</html>
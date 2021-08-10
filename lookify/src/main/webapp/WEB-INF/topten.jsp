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
<div class="header">
	<p>Top Ten Songs:</p>
	<a href="/dashboard">Dashboard</a>
</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Rating</th>
				<th>Name</th>
				<th>Artist</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="song" items="${songs}">
				<tr>
					<td>${song.rating}</td>
					<td><a href="/songs/${song.id}">${song.title}</a></td>
					<td>${song.artist}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
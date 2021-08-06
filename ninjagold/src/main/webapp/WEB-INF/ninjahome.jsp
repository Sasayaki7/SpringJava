<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>Your Gold: <c:out value="${gold}"/></h2>
	<div class="locations">
		<c:forEach var="location" items="${locationArray}">
			<div class="location-info">
				<h3><c:out value="${location.getName()}"/></h3>
				<p>(earns <c:out value="${location.getMin()}"/>-<c:out value="${location.getMax()}"/> gold)</p>
				<form action="/goldsubmission" method="post">
					<input type="hidden" name="place" value=<c:out value="${location.getName()}"/> />
					<input type="hidden" name="min" value=<c:out value="${location.getMin()}"/> />
					<input type="hidden" name="max" value=<c:out value="${location.getMax()}"/> />
					
					<input class="btn btn-primary"  type="submit" value="Find Gold!"/>
				</form>
			</div>
		</c:forEach>
	</div>
	<a class="btn btn-secondary" href="/?reset=true">Reset</a>
	<h2>Activities:</h2>
	<div class="log">
		<c:forEach var="log" items="${logs}">
			<p class="loghistory"><c:out value="${log}" /></p>
		</c:forEach>
	</div>
</body>
</html>
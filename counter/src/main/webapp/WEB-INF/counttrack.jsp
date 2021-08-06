<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>You have visited "http://localhost:8080/" <c:out value="${count}"/> times.</p>
	<a href="/">Test visit?</a>
	<br/>
	<a href="/counter2x">Or if you're generous...?</a>
	<a href="/counter?reset=true">Reset</a>
</body>
</html>
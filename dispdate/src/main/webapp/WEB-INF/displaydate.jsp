<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/datetemplate.css">
<title>Insert title here</title>
</head>
<body>
	<p>The date is <span class="date"><c:out value="${date}"/></span></p>
	<script type="text/javascript" src="js/alertdate.js"></script>
	
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/timetemplate.css">

</head>
<body>
	<p>The time is <span class="time"><c:out value="${time}"/></span></p>
    <script type="text/javascript" src="js/alerttime.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1>New Student</h1>
   	<form action="/students/create">
        <div class="input-row">
        	<label for="firstName">First Name:</label>
        	<input type="text" name="firstName"/>
        </div>
        <div class="input-row">
        	<label for="lastName">Last Name:</label>
        	<input type="text" name="lastName"/>
        </div>
        <div class="input-row">
        	<label for="age">Age:</label>
        	<input type="number" name="age"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Create"/>
   </form>
</body>
</html>


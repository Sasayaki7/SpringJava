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
	<h1>New Contact</h1>
     <form action="/contacts/create">
   		<div class="input-row">
   			<label for="studentId">Student</label>
        	<select name="studentId">
        		<c:forEach var="human" items="${students}">
        			<option value="${human.id }">${human.firstName} ${human.lastName}</option>
        		</c:forEach>
        	</select>	
        </div>
        <div class="input-row">
        	<label for="address">Address:</label>
        	<input type="text" name="address"/>
        </div>
        <div class="input-row">
        	<label for="city">City:</label>
        	<input type="text" name="city"/>
        </div>
        <div class="input-row">
        	<label for="state">State:</label>
        	<input type="text" name="state"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Create"/>
   </form>
</body>
<html>


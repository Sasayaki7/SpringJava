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
    <title>New Person</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   	<h2>New Person</h2>
   	<form:form action="/persons/new" method="POST" modelAttribute="person">
   	    <form:errors path="firstName"/>
   		<div class="input-row">
   			<form:label path="firstName">First Name:</form:label>
        	<form:input path="firstName"/>
        </div>
        <form:errors path="lastName"/>
        <div class="input-row">
        	<form:label path="lastName">Last Name:</form:label>
        	<form:input path="lastName"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Create"/>
   </form:form>
</body>
</html>


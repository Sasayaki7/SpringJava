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
    <title>New Product</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <h1>New Product</h1>
    <form:form method="post" action="/products/new" modelAttribute="product">
    
      	<form:errors path="name"/>
    	<div class="input-row">
    		<form:label path="name">Name:</form:label>
    		<form:input path="name"/>
    	</div>
    	<form:errors path="description"/>	
    	<div class="input-row">
    		<form:label path="description">Description:</form:label>
    		<form:textarea path="description"/>
    	</div>
    	<form:errors path="price"/>	
    	<div class="input-row">
    		<form:label path="price">Price:</form:label>
    		<form:input path="price"/>
    	</div>
    	<button class="btn btn-primary">Create</button>
    </form:form>
</body>
</html>


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
    <title>Product Page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <h1>${product.name }</h1>
   <div class="cols-setup">
	   <div class="left-col">
	   		<h2>Categories</h2>
	   		<ul>
	   			<c:forEach var="category" items="${product.categories}">
	   				<li>${category.name }</li>
	   			</c:forEach>
			</ul>
   		</div>
   		<div class="right-col">
   			<form method="post" action="/products/${product.id}/addCategory">
   				<label for="category">Add Category:</label>
   				<select name="category">
   					<c:forEach var="ctg" items="${categoriesNotInProduct}">
   						<option value=${ctg.id}>${ctg.name}</option>
   					</c:forEach>
   				</select>
   				<button class="btn btn-primary">Add</button>
   			</form>
   		</div>
   </div>
</body>
</html>


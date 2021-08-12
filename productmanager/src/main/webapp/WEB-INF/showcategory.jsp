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
    <title>Show Category</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
      <h1>${category.name }</h1>
   <div class="cols-setup">
	   <div class="left-col">
	   		<h2>Products</h2>
	   		<ul>
	   			<c:forEach var="product" items="${category.products}">
	   				<li>${product.name }</li>
	   			</c:forEach>
			</ul>
   		</div>
   		<div class="right-col">
   			<form method="post" action="/categories/${category.id}/addProduct">
   				<label for="product">Add Product:</label>
   				<select name="product">
   					<c:forEach var="prod" items="${productsNotInCategory}">
   						<option value=${prod.id}>${prod.name}</option>
   					</c:forEach>
   				</select>
   				<button class="btn btn-primary">Add</button>
   			</form>
   		</div>
   </div>
</body>
</html>


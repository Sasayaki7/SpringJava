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
    <title>New Question</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   	<h1>What is your question?</h1>
   	<form method="post" action="/questions/new">
   		<p><c:out value="${textError}"/></p>
   		<div class="input-row">
   			<label for="text">Question:</label>
   			<textarea name="text"><c:out value="${textvalue}"/></textarea>
   		</div>
   		<p><c:out value="${tagsError}"/></p>
   		<div class="input-row">
   			<label for="tags">Tags:</label>
   			<input name="tags" value="<c:out value="${tagvalue}"/>"/>
   		</div>
   		<button class="btn btn-primary">Submit</button>
   	</form>
</body>
</html>


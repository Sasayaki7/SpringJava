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
   	<h1>${student.firstName} ${student.lastName }</h1>
   	<form method="post" action="/students/${student.id}/addClass/">
   		<label for="classes">Classes:</label>
   		<select name="classes">
   			<c:forEach var="cls" items="${unenrolledclasses}">
   				<option value="${cls.id}">${cls.name}</option>
   			</c:forEach>
   		</select>
   		<button class="btn btn-primary">Add</button>
   	</form>
   	<h3>Your Schedule</h3>
   	<table class="table table-primary table-striped">
   		<thead>
   			<tr>
   				<th>Class Name</th>
   				<th>Action</th>
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="cls" items="${student.classes}">
   				<tr>
   					<td>${cls.name} </td>
   					<td> <form method="post" action="/students/${student.id }/removeClass/">
   							<input type="hidden" name="classes" value="${cls.id }"/>
   							<button class="btn btn-danger">Drop</button>
   						</form>
   					</td>
   				</tr>
   			</c:forEach>
   		</tbody>
   	</table>
</body>
</html>


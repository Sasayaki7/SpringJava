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
    <title>Show Dormitory</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>${dorm.name} Dormitory</h1>
	<form action="/dorms/${dorm.id}/add">
	<select name="student">
		<c:forEach var="student" items="${studentsWithoutDorms}">
			<c:if test="${student.dorm == null}">
				<option value="${student.id}">${student.firstName} ${student.lastName}</option>
			</c:if>
		</c:forEach>
	</select>
	<button class="btn btn-primary">Add</button>
	</form>
	
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${dorm.students}">
				<tr>
					<td>${student.firstName} ${student.lastName }</td>
					<td>
						<form action="/dorms/${dorm.id}/remove">
							<input type="hidden" name="student" value="${student.id }"/>
							<button class="btn btn-danger">Remove</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>


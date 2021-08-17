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
    <title>Events</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1><c:out value="${event.name}"/></h1>
	
	<h3>Edit Event</h3>
   	<form:form post="method" action="/events/${event.id}/edit" modelAttribute="event">
   		<input type="hidden" name="_method" value="put"/>
		<h1>Create an Event</h1>
		<form:errors path="name"/>
		<div class="input-row">
			<form:label path="name">Name:</form:label>
			<form:input path="name"/>
		</div>
		<form:errors path="eventDate"/>
		<div class="input-row">
			<form:label path="eventDate">Date:</form:label>
			<form:input type="date" path="eventDate"/>
		</div>
		<form:errors path="city"/>
		<form:errors path="state"/>
		<div class="input-row">
			<form:label path="city">Location:</form:label>
			<form:input path="city"/>
   			<form:select path="state">
   				<form:option value="" disabled="true"></form:option>
   				<c:forEach  var = "st" items = "${state}">
   					<form:option value="${st}"><c:out value="${st}"/></form:option>
   				</c:forEach>
   			</form:select>
		</div>
		<button class="btn btn-primary">Edit</button>
	</form:form>
</body>
</html>


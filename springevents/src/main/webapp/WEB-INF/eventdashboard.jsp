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
   	<h1>Welcome, Ninja</h1>
   	<a class="btn btn-danger" href="/logout">Logout</a>
   	<p>Here are some of the events in your state:</p>
   	<table class="table table-striped">
   		<thead>
   			<tr>
   				<th>Name</th>
   				<th>Date</th>		
   				<th>Location</th>
   				<th>Host</th>
   				<th>Action/Status</th>  				
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="event" items="${eventsInState}">
   				<tr>
   					<td><a href="/events/${event[0].id}"><c:out value="${event[0].name}"/></a></td>
   					<td> <fmt:formatDate pattern="MMMM dd, yyyy"  value="${event[0].eventDate}"/></td>
   					<td> <c:out value="${event[0].city}"/></td>
   					<td> <c:out value="${event[0].host.firstName} ${event[0].host.lastName }"/></td>
   					<td>
	   					<c:choose>
		   					 <c:when test="${event[0].host.id eq uuid}">
		   					 	<a href="/events/${event[0].id}/edit" class="btn btn-primary">Edit</a> 
		   					 	<form action="/events/${event[0].id}" method="post">
		   					 		<input type="hidden" name="_method" value="delete"/>
		   					 		<button class="btn btn-danger">Delete</button> 
		   					 	</form>
		   					 </c:when>
		   					 <c:when test="${event[1]}">
		   					 	<p>Joining
		   					 		<a href="/events/${event[0].id }/cancel" class="btn btn-primary">Cancel</a>
		   					 	</p>
		   					 </c:when>
		   					 <c:otherwise>
		   					 		<a href="/events/${event[0].id }/join" class="btn btn-primary">Join</a>
		   					 </c:otherwise>
   						</c:choose>
   					</td>
   				</tr>
   			</c:forEach>
   		</tbody>
   	</table>
   	<p>Here are some events not in the state</p>
 	<table class="table table-striped">
   		<thead>
   			<tr>
   				<th>Name</th>
   				<th>Date</th>		
   				<th>Location</th>
   				<th>State</th>
   				<th>Host</th>
   				<th>Action/Status</th>  				
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="event" items="${eventsOutOfState}">
   				<tr>
   					<td><a href="/events/${event[0].id}"><c:out value="${event[0].name}"/></a></td>
   					<td> <fmt:formatDate pattern="MMMM dd, yyyy"  value="${event[0].eventDate}"/></td>
   					<td> <c:out value="${event[0].city}"/></td>
   					<td> <c:out value="${event[0].state}"/></td>
   					<td> <c:out value="${event[0].host.firstName} ${event[0].host.lastName }"/></td>
   					<td>
	   					<c:choose>
		   					 <c:when test="${event[0].host.id eq uuid}">
		   					 	<a href="/events/${event[0].id}/edit" class="btn btn-primary">Edit</a> 
		   					 	<form action="/events/${event[0].id}" method="post">
		   					 		<input type="hidden" name="_method" value="delete"/>
		   					 		<button class="btn btn-danger">Delete</button> 
		   					 	</form>
		   					 </c:when>
		   					 <c:when test="${event[1]}">
		   					 	<p>Joining
		   					 		<a href="/events/${event[0].id }/cancel" class="btn btn-primary">Cancel</a>
		   					 	</p>
		   					 </c:when>
		   					 <c:otherwise>
		   					 		<a href="/events/${event[0].id }/join" class="btn btn-primary">Join</a>
		   					 </c:otherwise>
   						</c:choose>
   					</td>
   				</tr>
   			</c:forEach>
   		</tbody>
   	</table>
	
	<form:form post="method" action="/events" modelAttribute="event">
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
		<button class="btn btn-primary">Create</button>
	</form:form>


</body>
</html>


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
    <title><c:out value="${event.name }"/></title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   	<div class="input-row">
   		<div class="left-col">
   			<h2><c:out value="${event.name}"/></h2>
   			<p>Host: <c:out value="${event.host.firstName} ${event.host.lastName}"/></p>
   			<p>Date: <fmt:formatDate pattern="MMMM dd, yyyy"  value="${event.eventDate}"/></p>
   			<p>Location: <c:out value="${event.city}, ${event.state}"/></p>
   			<p>People who are attending this event: <c:out value="${event.attendees.size()}"/></p>
   			<table>
   				<thead>
   					<tr>
   						<th>Name:</th>
   						<th>Location:</th>
   					</tr>
   				</thead>
   				<tbody>
   					<c:forEach var="attendee" items="${event.attendees}">
	   					<tr>
	   						<td><c:out value="${attendee.firstName } ${attendee.lastName }"/></td>
	   						<td><c:out value="${attendee.city}"/></td>
	   					</tr>
	   				</c:forEach>
   				</tbody>
   			</table>
   		</div>
   		<div class="right-col">
   			<h2>Message Wall</h2>
   			<div class="message-box">
   				<c:forEach var="comm" items="${event.comments }" varStatus="loop">
   					<c:out value="${comm.poster.firstName} ${comm.poster.lastName}: ${comm.comment}"/>
   					<c:if test="${!loop.last}">
   						<br/>
   						<c:out value="--------------------------------"/>
   						<br/>					
   					</c:if>
   				</c:forEach>
   			</div>
   			<c:if test="${not empty uuid}">
	   			<p>Add Comment:</p>
	   			<form:form modelAttribute="comm" action="/events/${event.id}/addComment" method="post">
	   				<form:errors path="comment"/>
	   				<form:textarea path="comment" row="4" cols="80"/>
	   				<button class="btn btn-primary">Submit</button>
	   			</form:form>
   			</c:if>
   		</div>
   	</div>
</body>
</html>


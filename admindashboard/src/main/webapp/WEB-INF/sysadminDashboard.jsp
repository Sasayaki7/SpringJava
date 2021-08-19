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
   	<a class="btn btn-danger" href="/logout">Logout</a>
   	<h1>Welcome <c:out value="${currentUser.firstName}"/></h1>
   	<table class="table table-striped">
   		<thead>
   			<tr>
   				<th>Name</th>
   				<th>Email</th>
   				<th>Action</th>   				
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="user" items="${users}">
   				<c:if test="${!user.isSuperAdmin() }">
   				<tr>
   					<td><c:out value="${user.firstName} ${user.lastName}"/></td>
   					<td><c:out value="${user.email}"/></td>
   					<td><c:choose>
   						<c:when test="${user.isAdmin()}">
   							<p><form method="post" action="/delete/sysadmin/${user.id}">
   								<input type="hidden" name="_method" value="delete"/>
   								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
   								<button class="btn btn-danger">Delete</button>
   							</form>
   							<form method="post" action="/sysadmin/unadmin/${user.id}">
   								<input type="hidden" name="_method" value="put"/>
   								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
   								<button class="btn btn-warning">Un-Admin</button>
   							</form> </p>
   						</c:when>
   						<c:otherwise>
   							<form method="post" action="/delete/admin/${user.id}">
   								<input type="hidden" name="_method" value="delete"/>
   								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
   								<button class="btn btn-danger">Delete</button>
   							</form>
   							<form method="post" action="/admin/${user.id}">
   								<input type="hidden" name="_method" value="put"/>
   								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
   								<button class="btn btn-primary">Make Admin</button>
   							</form>   							
   						</c:otherwise>
   					</c:choose></td>
   				</tr>
   				</c:if>	
   			</c:forEach>
		</tbody>
   	</table>   
</body>
</html>


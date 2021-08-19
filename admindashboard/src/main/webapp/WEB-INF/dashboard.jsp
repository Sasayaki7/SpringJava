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
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" class="btn btn-danger" value="Logout!" />
    </form>
   	<h1>Welcome <c:out value="${currentUser.firstName}"/></h1>
   	<p>First Name: <c:out value="${currentUser.firstName}"/></p>
   	<p>Last Name: <c:out value="${currentUser.lastName}"/></p>
   	<p>Email: <c:out value="${currentUser.email}"/></p>
   	<p>Sign up date: <fmt:formatDate pattern="MMMM dd, yyyy" value="${currentUser.createdAt }"/></p>
   	<p>Last Login date: <fmt:formatDate pattern="MMMM dd, yyyy" value="${currentUser.lastLogin }"/></p>
   	
   	
</body>
</html>


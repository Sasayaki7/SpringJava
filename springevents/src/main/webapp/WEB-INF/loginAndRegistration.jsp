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
    <title>Welcome</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   	<h1>Welcome</h1>
   	
   	<div class="input-row">
   		<div class="left-col box">
   			<h3>Register</h3>
   			<form:form method="post" action="/" modelAttribute="user">
   				<form:errors path="firstName"/>
   				<p>
   					<form:label path="firstName">First Name:</form:label>
   					<form:input path="firstName"/>
   				</p>
   				<form:errors path="lastName"/>
   				<p>
   					<form:label path="lastName">Last Name:</form:label>
   					<form:input path="lastName"/>
   				</p>
   				   	<form:errors path="email"/>
   				<p>
   					<form:label path="email">Email:</form:label>
   					<form:input path="email"/>
   				</p>
   				   	<form:errors path="city"/><form:errors path="state"/>
   				   	
   				<p>
   					<form:label path="city">Location:</form:label>
   					<form:input path="city"/>
   					<form:select path="state">
   						<form:option value="" disabled="true"></form:option>
   						<c:forEach  var = "st" items = "${state}">
   							<form:option value="${st}"><c:out value="${st}"/></form:option>
   						</c:forEach>
   					</form:select>
   				</p>
   					<form:errors path="password"/>
   				<p>
   					<form:label path="password">Password:</form:label>
   					<form:input type="password" path="password"/>
   				</p>
   				   	<form:errors path="confirmPassword"/>
   				<p>
   					<form:label path="confirmPassword">Confirm Password:</form:label>
   					<form:input type="password" path="confirmPassword"/>
   				</p>
   				<button class="btn btn-primary">Register</button>
   			</form:form>
   		</div>
   		<div class="right-col box">
		    <h1>Login</h1>
		    <p><c:out value="${error}" /></p>
		    <form method="post" action="/login">
		        <p>
		            <label for="email">Email</label>
		            <input type="text" id="email" name="email"/>
		        </p>
		        <p>
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password"/>
		        </p>
		        <input type="submit" value="Login!"/>
		    </form>
   		</div>
   	</div>
</body>
</html>


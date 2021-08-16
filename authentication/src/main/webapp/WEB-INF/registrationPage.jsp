<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
</head>
<body>
    <h1>Register!</h1>
    
    
    <form:form method="POST" action="/registration" modelAttribute="user">
        <p>
        	<form:errors path="email"/>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
        </p>
        <p>
        	<form:errors path="password"/>
        	<br/>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </p>
        <p>
        	<form:errors path="passwordConfirmation"/>
        	<br/>
        	
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
    
</body>
</html>


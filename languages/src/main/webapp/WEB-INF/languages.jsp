<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<table class="table table-primary table-striped">
	<thead>
		<tr>
			<td>Name</td>
			<td>Creator</td>
			<td>Version</td>
			<td>Action</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="language" items="${languages}">
			<tr>
				<td><a href=<c:out value="/languages/${language.id}"/>><c:out value="${language.name}"/></a></td>
				<td><c:out value="${language.creator}"/></td>
				<td><c:out value="${language.version}"/></td>
				<td class="inputrow"><a class="btn btn-primary" href=<c:out value="/languages/${language.id}/edit"/>>Edit</a> 
					<form action=<c:out value="/languages/${language.id }"/> method="post">
    					<input type="hidden" name="_method" value="delete">
    					<input type="submit" value="Delete" class="btn btn-danger"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<form:form action="/languages" method="post" modelAttribute="language">
	<div class="input-row">
        <form:label path="name">Name</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </div>
    <div class="input-row">
        <form:label path="creator">Creator</form:label>
        <form:errors path="creator"/>
        <form:input path="creator"/>
    </div>
    <div class="input-row">
        <form:label path="version">Version</form:label>
        <form:errors path="version"/>
        <form:input path="version"/>
	</div>

    <input class="btn btn-primary" type="submit" value="Submit"/>
</form:form>    

</body>
</html>
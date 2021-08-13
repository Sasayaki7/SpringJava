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
   	<h1>${question.text }</h1>
   	<div class="split-col">
	   	<div class="left-col">
		   	<div class="input-row">
		   		<p>Tags:</p>
		   		<c:forEach var="tag" items="${question.tags }">
		   			<p class="btn btn-secondary">${tag.subject }</p>
		   		</c:forEach>
		   	</div>
		   	<table class="table table-striped">
		   		<thead>
		   			<tr>
		   				<th>Answers:</th>
		   			</tr>
		   		</thead>
		   		<tbody>
		   			<c:forEach var="answer" items="${question.answers }">
		   				<tr>
		   					<td>${answer.answer}</td>
		   				</tr>
		   			</c:forEach>
		   		</tbody>
		   	</table>
	   	</div>
	   	<div class="right-col">
	   		<form method="post" action="/questions/${question.id}/answer">
	   			<h3>Add your answer:</h3>
				<p><c:out value="${ansError}"/></p>
	   			<div class="input-row">
	   				<label for="answer">Answer: </label>
	   				<textarea name="answer">${ansval}</textarea>
	   			</div>
	   			<button class="btn btn-primary">Answer It!</button>
	   		</form>
	   	</div>
   	</div>
</body>
</html>


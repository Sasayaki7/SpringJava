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
    <title>Questions Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   	<h1>Questions Dashboard</h1>
   	<table>
   		<thead>
   			<tr>
   				<th>Question</th>
   				<th>Tags</th>
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="question" items="${questions}">
   				<tr>
   					<td><a href="/questions/${question.id}">${question.text}</a></td>
   					<td>
   						<c:forEach var="tag" items="${question.tags }" varStatus="loop">
   							<c:out value="${tag.subject}"/>
   							<c:if test="${!loop.last }">, </c:if>
   						</c:forEach>
   					</td>
   				</tr>
   			</c:forEach>
   		</tbody>
   	</table>
   	<a href="/questions/new" class="btn btn-primary">New Question</a>
</body>
</html>


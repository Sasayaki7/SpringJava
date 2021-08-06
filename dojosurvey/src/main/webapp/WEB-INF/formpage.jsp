<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/form.css"/>

<title>Insert title here</title>
</head>
<body>
	<div class="borderBox">
		<form action="/postform" method="post">
			<div class="inputrow">
				<label for="name">Your Name:</label>
				<input type="text" name="name"/>
			</div>
			<div class="inputrow">
				<label for="location">Dojo Location:</label>
				<select name="location">
					<option value="San Francisco">San Francisco</option>
					<option value="Paris">Paris</option>
					<option value="Beijing">Beijing</option>
					<option value="Tokyo">Tokyo</option>
					<option value="Prague">Prague</option>
					<option value="Buenos Aires">Buenos Aires</option>
				</select>
			</div>
			<div class="inputrow">
				<label for="language">Favorite Language:</label>
				<select name="language">
					<option value="Python">Python</option>
					<option value="Java">Java</option>
					<option value="C++">C++</option>
					<option value="Lua">Lua</option>
					<option value="JavaScript">JavaScript</option>
				</select>
			</div>
			<label for="comment">Comment (optional):</label>
			<textarea name="comment" rows="4" cols="80"></textarea>
			<input class="btn btn-primary" type="submit" value="Submit"/>
		</form>		
	</div>
</body>
</html>
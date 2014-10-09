<%@page import="java.util.List"%>
<%@page import="model.Todo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<input type="text" name="description"> <input type="submit"
			value="créer">
	</form>
	<ul>
		<%
			List<Todo> todos = (List<Todo>) request.getAttribute("todos");
			for (Todo todo : todos) {
		%>

		<li><%=todo.getDescription()%></li>
		<%
			}
		%>
	</ul>
</body>
</html>
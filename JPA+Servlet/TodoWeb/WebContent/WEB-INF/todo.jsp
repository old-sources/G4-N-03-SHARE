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
			name="creer" value="créer" />
	</form>
	<div>
		<%
			List<Todo> todos = (List<Todo>) request.getAttribute("todos");
			for (Todo todo : todos) {
		%>
		<form method="post">
			<div><%=todo.getDescription()%></div>
			<div>
				<input type="hidden" name="id" value="<%=todo.getId()%>"/>
				<input type="submit" name="supprimer" value="supprimer"/>
				<input type="text" name="description" value="<%=todo.getDescription()%>"/>
				<input type="submit" name="modifier" value="modifier"/>
			</div>
		</form>
		<%
			}
		%>
	</div>
</body>
</html>
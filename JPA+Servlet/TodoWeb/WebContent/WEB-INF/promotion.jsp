<%@page import="model.Personne"%>
<%@page import="model.Promotion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center">
		<div style="display: inline-block; text-align: left;">
			<%
				Promotion promotion = (Promotion) request.getAttribute("promotion");
			%>
			<div style="margin-bottom: 20px">
				<span ><%=promotion.getLibelle()%></span>
			</div>
			
			<%
				for (Personne personne : promotion.getPersonnes()) {
			%>
			<div>
				<span><%=personne.getNom()%></span> <span> </span> <span><%=personne.getPrenom()%></span>
			</div>
			<%
				}
			%>

		</div>
	</div>
</body>
</html>
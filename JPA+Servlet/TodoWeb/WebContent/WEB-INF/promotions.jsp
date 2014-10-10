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
				List<Promotion> promotions = (List<Promotion>) request
						.getAttribute("promotions");
				for(Promotion promotion : promotions){
			%>
			<div>
				<span><%=promotion.getLibelle()%></span>
				<span> : </span>
				<span><%=promotion.getPersonnes().size()%></span>
				<a href="./PromotionServlet?promotionId=<%=promotion.getId()%>" ><span>detail<span></span></a>
			</div>
			<%} %>
			
		</div>
	</div>
</body>
</html>
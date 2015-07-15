<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score du Tennis</title>
</head>
<body>
	<form method="post">
		<div id="score" style="font-size: 40px">
			<%=session.getAttribute("score")%>
		</div>
		<div>
			<input type="submit" value="Joueur1 marque 1 point" name="joueur1" />
			<input type="submit" value="Joueur2 marque 1 point" name="joueur2" />
		</div>
		<div>
			<input type="submit" value="Sauvegarder" name="save" /> <input
				type="submit" value="Remise à zero" name="reset" /> <input
				type="submit" value="Recharger" name="load" />
		</div>
		<div>
			<span id="error" type="text" style="font-size: 50px; color: red"><%=session.getAttribute("error")!=null?session.getAttribute("error"):""%></span>
		</div>
	</form>
</body>
</html>
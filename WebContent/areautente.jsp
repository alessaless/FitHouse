<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Area Utente</title>
	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<br>
		<div class="contenitoreAreaUtente">
			<a href="ordini.jsp">
				<div class="boxAreaUtente">
					<img src="images/logistics.png" class="imgBoxAreaUtente">
				</div>
			</a>
			<a href="metodipagamento.jsp">
				<div class="boxAreaUtente">
					<img src="images/credit-card.png" class="imgBoxAreaUtente">
				</div>
			</a>
			<a href="indirizzi.jsp">
				<div class="boxAreaUtente">
					<img src="images/map.png" class="imgBoxAreaUtente">
				</div>
			</a>
			<a href="impostazioni.jsp">
				<div class="boxAreaUtente">
					<img src="images/cogwheel.png" class="imgBoxAreaUtente">
				</div>
			</a>
			<%
				//se l'utente è loggato visualizza il bottone logout
				if(session.getAttribute("currentSessionUser") != null){
					%>
						<form action="Logout" method="get" class="bott" >
							<br><input type="submit" value="Logout" class= "bott1" >
						</form>
					<%
				}else{ //altrimenti un immagine che se cliccata porta alla pagina di login 
					%>
					<a href="LoginPage.jsp">
						<div class="boxAreaUtente">
							<img src="images/login.png" class="imgBoxAreaUtente">
						</div>
					</a>
					<%
				}
			%>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>
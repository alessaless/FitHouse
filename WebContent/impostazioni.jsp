<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.*"  
	import="java.util.*"  
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Impostazioni</title>
	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
		.contenitoreOrdini{
			display: flex;
		}
	</style>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<br>
		<div class="impostazioni">
		<%
			if(session.getAttribute("currentSessionUser") == null){
				// mandalo al login
				response.sendRedirect("LoginPage.jsp");
			}else{
				UserDAO udao = new UserDAO();
				UserBean u = (UserBean) session.getAttribute("currentSessionUser");
				%>
					<form method="get" action="cambiaEmail" id="mailForm">
						<span class="grassetto">Modifica E-mail: </span><br>
						<input type="text" id="newmail" name="newmail" class="dati" value="<%= u.getEmail() %>"><br>
						<input type="hidden" name="oldmail" value="<%= u.getEmail() %>">
						<span id="errorMail" class="formError">Mail non valida</span>
						<br><input class="bottone" type="button" value="Modifica" onclick="controlloCampiMail()">
					</form>
					<br><br>
					<form method="POST" action="cambiaPassword" id="passForm">
						<span class="grassetto">Modifica password: </span><br>
						<input type="password" class="dati" id="oldpss" name="oldpass" placeholder="Inserisci password attuale"><br>
						<span id="errorPass" class="formError">Password non valida</span><br>
						<input type="password" class="dati" id="newpss" name="newpass" placeholder="Inserisci nuova password">
						<br><span id="indicazionePass" class='tipPassword'>La password deve contenere almeno 3 caratteri (una lettera, un numero e un simbolo)</span><br>
						<span id="errorPass2" class="formError">Le password non corrispondono</span><br>
						<input type="hidden" id="mmm" name="mailll" value="<%= u.getEmail() %>">
						<input class="bottone" type="button" value="Modifica" onclick="controlloCampiPass()">
					</form>
				<%
			}
		%>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>
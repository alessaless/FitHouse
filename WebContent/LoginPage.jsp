<%@
	page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Login</title>
        <link rel="stylesheet" href="admin/admin.css">
        <link rel="stylesheet" href="style.css">
        <link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    	<%
    		// parametro restituito se non sei registrato, mostra l'errore di non registrato
    		// request get parameter map restituisce la mappa dei parametri e controlliamo se c'è il parametro corrispondente 
    		if(request.getParameterMap().containsKey("nr")){
   				%>
				<div class="containerMessaggio">
					<div id="messaggioConferma">
						<img src="images/error.png" class="erroreMessaggio">
						<input type="image" src="images/close.png" class="chiudiMessaggio" onclick="nascondiMessaggio()">
						<span>Non sei registrato</span>
					</div>
				</div>
				<%
			// parametro restituito se la password è sbagliata, mostra l'errore di non registrato
    		// request get parameter map restituisce la mappa dei parametri e controlliamo se c'è il parametro corrispondente	
    		}else if(request.getParameterMap().containsKey("ps")){
    			%>
				<div class="containerMessaggio">
					<div id="messaggioConferma">
						<img src="images/error.png" class="erroreMessaggio">
						<input type="image" src="images/close.png" class="chiudiMessaggio" onclick="nascondiMessaggio()">
						<span>Password errata</span>
					</div>
				</div>
				<%
    		}
    	%>
    	<img src='images/fitHouse2.png' class='logo'>
	   	<div id='contenitoreForm'> 
	   		<!-- form che richiama la servlet LoginServlet che verifica le credenziali -->
	   		<form action="LoginServlet" method='POST' id='form'>
		   		<img src='images/social.png' class='social'>
		   		<br> 
		        <input type="email" name="email" placeholder=" example@gmail.com" class='dati' id="mail"><br><br>
		        <input type="password" name="password" placeholder=" Password" class='dati' id="password"><br><br>
		        <span id="error1" class="formError">Inserisci correttamente le credenziali</span>
		        <input type="button" value="Login" class='bottone' onclick="controlloCampiLogin()">
	   		</form>
	   		
	   		<br>
	   		<span id= "account">
	   		 Non hai un account? <a class="asottolineato" href='Registrazione.jsp'>Creane uno</a><br>
	   		</span>
	   	</div>
	   	<script src="script.js"></script>
    </body>
</html>
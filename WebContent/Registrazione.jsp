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
			// parametro restituito se sei già registrato quindi mostra l'errore
			// request get parameter map restituisce la mappa dei parametri e controlliamo se c'è il parametro corrispondente 
    		if(request.getParameterMap().containsKey("ar")){
   				%>
				<div class="containerMessaggio">
					<div id="messaggioConferma">
						<img src="images/error.png" class="erroreMessaggio">
						<input type="image" src="images/close.png" class="chiudiMessaggio" onclick="nascondiMessaggio()">
						<span>Questa email risulta già registrata</span>
					</div>
				</div>
				<%
    		}
    	%>
    	<img src='images/fitHouse2.png' class='logo'>
	   	<div id='contenitoreForm'> 
	   		<form action="RegistrazioneServlet" method='POST' id='form'>
		   		<img src='images/social.png' class='social'>
		   		<br>
		   		<input type="text" name="nome" id="nome" placeholder=" Nome" class='dati'><br><br>
		   		<input type="text" name="cognome" id="cognome" placeholder=" Cognome" class='dati'><br><span class="formError" id="errorNominativo">Il nome e il cognome devono contenere solo lettere</span><br> 
		        <input type="email" name="email" id="email" placeholder=" example@gmail.com" class='dati'><br><span class="formError" id="errorMail">ب necessario inserire un'email valida</span><br>
		        <input type="password" name="password" id="password" placeholder=" Password" class='dati'><br><span id="indicazionePass" class='tipPassword'>La password deve contenere almeno 3 caratteri (una lettera, un numero e un simbolo)</span><span class="formError" id="errorPass">Devi inserire una password che rispetti le regole sopraindicate</span><br>
		        <input type="password" name="password2" id="password2" placeholder=" Conferma Password" class='dati'><br><span class="formError" id="errorPass2">Le password non corrispondono</span><br><br>
		        <input type="button" value="Registrati" class='bottone' onclick="validate(); allLetter();">
	   		</form>
	   		<br>
	   		<span id= "account">
	   		 Hai già un account? <a href='LoginPage.jsp'>Vai a login</a><br>
	   		</span>
	   	</div>
    </body>
    <script src="script.js"></script> 
</html>
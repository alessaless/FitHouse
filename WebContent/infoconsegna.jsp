<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Informazioni sulla consegna</title>

	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<jsp:include page="header.jsp" /> 
	<br><br>
	<div class="errore">
		<img src="images/deliver.gif" class="imgerrore">
	</div>
	<div class= "errore1">
		<h2>Informazioni sulla consegna</h2>
		<p>
			Le consegne vengono effettuate dal lunedì al venerdì tramite corriere GLS, UPS o Poste Italiane.<br>
			Spediamo i nostri prodotti entro 2/3 giorni lavorativi. <br>
			Arrivano, non sappiamo come ma arrivano. <br>
		</p> 
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>
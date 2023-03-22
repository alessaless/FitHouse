<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contattaci</title>
	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<jsp:include page="header.jsp" /> 
	<br><br>
	<div class="errore">
		<img src="images/phone.gif" class="imgerrore">
	</div>
	<br><br>
	<div class= "contatti">
		<span class="grassetto">E-mail: info@fithouse.it</span> 
		<span class="grassetto">Telefono: +39 1234567890</span>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
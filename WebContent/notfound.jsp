<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Not Found</title>

	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<jsp:include page="header.jsp" /> 
	<br><br>
	<div class="errore">
		<img src="images/404gif.gif" class="imgerrore">
	</div>
	<div class= "errore1">
		<h2>Pagina non trovata</h2>
		<a href="catalogo.jsp">Torna al catalogo</a> 
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>
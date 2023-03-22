<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Ospite</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
<style>
	.bottone2{
	margin-left: 20px;
	}
	
	ol{
		padding-left: 20px;
	}

</style>
</head>
<body>
<jsp:include page="header.jsp" /> 
<form action = "Checkout.jsp" method = "POST">
	<ol>
	<li  class="grassetto">Inserisci un indirizzo mail su cui inviare la conferma dell'ordine</li><br><br>
	<input type = "email" id = "email" name = "email" placeholder = "example@gmail.com" class='dati' required><br/><br/>
	<li><label   class="grassetto">Inserisci un nuovo indirizzo:</label><br><br/>

	<b><label>Nome</label><br></b>
	<input type="text" class='dati' name="nome" placeholder="Es. Mario" required></input><br><br>
	
	<b><label>Cognome</label><br></b>
	<input type="text" class='dati' name="cognome" placeholder="Es. Rossi" required></input><br><br>
	
	<b><label>Nazione</label><br></b>
	<input type="text" class='dati' name="nazione" value = "Italia" required></input><br><br>
	
	<b><label>Città</label><br></b>
	<input type="text" class='dati' name="citta" placeholder="Es. Roma" required></input><br><br>
	
	<b><label>CAP</label><br></b>
	<input type="text" class='dati' name="CAP" placeholder="xxxx" required></input><br><br>
	
	<b><label>Provincia</label><br></b>
	<input type="text" class='dati' name="provincia" placeholder="Es. Roma" required></input><br><br>
	
	<b><label>Numero di telefono</label><br></b>
	<input type="text"  class='dati'name="telefono" placeholder="3xxxxxxxxx" required></input><br><br>
	
	<b><label>Via</label><br></b>
	<input type="text" class='dati' name="indirizzo" placeholder="Via e Numero civico" required></input><br><br/>
	<b><label>Note (Facoltativo)</label><br></b>
	<input type="text" class='dati' name="note" placeholder="Scala, piano, interno (Facoltativo)"></input><br/><br/>
	
	</li>
	
	<li><label   class="grassetto">Inserisci un nuovo metodo di pagamento:</label><br><br>
	 <div class="contenitore">
        <div class="immagine1">
            <img src="images/fitHouse2.png" class="immagine">
            <img src="images/chip.png" class="immagine">
        </div>
        <div class="daticarta">
         <span class="estremi">Numero di carta</span><br>
         <input type="text" name="numerodicarta" placeholder="1234 1234 1234 1234" class="dat" required><br>
         <span class="estremi">Scadenza</span><br>
         <input type="text" name="scadenza" placeholder="MM/YY" class="dat" required></br>
         <span class="estremi">Intestatrio</span><br>
         <input type="text" name="proprietario" placeholder="Intestatario" class="dat" required></br>
         <span class="estremi">CVV</span><br>
         <input type="text" name="CVV" placeholder="***" class="dat" required>
        </div>    
    </div>
</li></ol>
	<input type = "submit"  class='bottone bottone2' value = "Vai al checkout">
</form>
	
<jsp:include page="footer.jsp"/>  
</body>
</html>
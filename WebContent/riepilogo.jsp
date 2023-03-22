<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.*" 
    import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Ordine</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
</head>
<body>
	<jsp:include page="header.jsp" /> 
<div class="divRiepilogo">
	<%
	
	// se l'utente è loggato, mi mostra i suoi indirizzi e metodi di pagamento già presenti (e ti da l'opportunità di aggiungerne)
	// se non è loggato ti chiede se vuoi loggarti o fare l'acquisto come guest
	
	UserBean user = (UserBean) session.getAttribute("currentSessionUser") ; 
	IndirizzoDAO daoindirizzo = new IndirizzoDAO() ; 
	IndirizzoBean ind = new IndirizzoBean()  ; 
	if(user != null){ %>
	<br>
	<li  class="grassetto">Inserisci un indirizzo mail su cui inviare la conferma dell'ordine</li><br><br>
	<input type = "email" id = "maill" name = "maill" placeholder = "example@gmail.com" class='dati' value = "<%=user.getEmail() %>" required><br/><br/>
		<hr><li  class="grassetto">Oppure seleziona un indirizzo di spedizione</li><br><br><div class="contcarte"> <%
		List<IndirizzoBean> indirizzi = (List<IndirizzoBean>) daoindirizzo.doRetrieveAllByUser(user.getId()) ;
		if(! (indirizzi.isEmpty())) {
			%>
		<%
			// stampa gli indirizzi se ne ho
			int i = 0 ; 
				String note ;
				Iterator<IndirizzoBean> iter = indirizzi.iterator() ;
				while(iter.hasNext()){
					
					ind = iter.next() ; 
					i++ ; 
					if(ind.getNote() == null)
						note = "" ; 
					else
						note = ind.getNote() ;  %>
					<input type="hidden" id= '<%="indirizzo"+i%>' name="indirizzo" value="<%=ind.getID() %>"></input>
					<div class="singoloSelettoreRiepilogo" id="sel<%= i %>" onClick="SelectRadio('indirizzo',<%=i%>); cambiaBordo('sel<%= i %>');">
						<label for="indirizzo"> <%= ind.getNome()+" "+ind.getCognome() %><br><%= "Telefono : "+ind.getTelefono() %><br><%= ""+ind.getNazione()+", "+ind.getProvincia()+", "+ind.getCitta()+", "+ind.getVia() %><br><%="Note : "+note %> </label>
					</div>	
				<% } %>
					<div  id="addIndirizzo" class="singoloSelettoreRiepilogo">
						<button class="modquantita">Aggiungi nuovo indirizzo +</button>
					</div>
				</div>
			<% }else{
				// se non sono registrato o non ho indirizzi mi deve stampare solo la possibilità di aggiungere un nuovo indirizzo
				%>
					<div  id="addIndirizzo" class="singoloSelettoreRiepilogo">
						<button class="modquantita">Aggiungi nuovo indirizzo +</button>
					</div>
					</div>
				<%
			} %>
		<br><br/>
		
		<!-- form per inserire un nuovo indirizzo -->
		
		<form action = "InserisciIndirizzo" method = "POST" id="formIndirizzo" class="addIndirizzo">
		
		<b><label>Nome</label><br></b>
		<input type="text" name="nome" placeholder="Es. Mario"  class='dati' required></input><br><br>
		
		<b><label>Cognome</label><br></b>
		<input type="text" name="cognome" placeholder="Es. Rossi"  class='dati' required></input><br><br>
		
		<b><label>Nazione</label><br></b>
		<input type="text" name="nazione"  class='dati' value = "Italia"></input><br><br>
		
		<b><label>Città</label><br></b>
		<input type="text" name="citta"  class='dati' placeholder="Es. Roma" required></input><br><br>
		
		<b><label>CAP</label><br></b>
		<input type="text" name="CAP"  class='dati' placeholder="xxxx" required></input><br><br>
		
		<b><label>Provincia</label><br></b>
		<input type="text" name="provincia"  class='dati' placeholder="Es. Roma" required></input><br><br>
		
		<b><label>Numero di telefono</label><br></b>
		<input type="text" name="telefono"  class='dati' placeholder="3xxxxxxxxx" required></input><br><br><br>
		
		<b><label>Via</label><br></b>
		<input type="text" name="indirizzo"  class='dati' placeholder="Via e Numero civico" required></input><br><br/>
		<b><label>Note (Facoltativo)</label><br></b>
		<input type="text" name="note"  class='dati' placeholder="Scala, piano, interno (Facoltativo)"></input><br/><br/>
		<input type = "hidden" name = "id_utente" value = "<%= user.getId() %>">
		
		<input type="submit" class="bottone" value="inserisci indirizzo"></input><br/><br/>
		</form>
		<hr>
		<% 	
			// stampa i metodi di pagamento se esistono, altrimenti ti da la possibilità di inserirne uno nuovo
			UtilizzaDAO daoutilizza = new UtilizzaDAO() ; 
			List<UtilizzaBean> carte =  (List<UtilizzaBean>) daoutilizza.doRetrieveAllByKey(user.getId()) ;
			MetodiPagamentoBean carta = new MetodiPagamentoBean(); 
			if(! (carte.isEmpty())) { %>
				<li><label  class="grassetto" for="metodipagamento">Seleziona un metodo di pagamento</label><br/><br/><div class="contcarte">
				<%
				Iterator<UtilizzaBean> iterr = carte.iterator() ; 
				MetodiPagamentoDAO daoCarta = new MetodiPagamentoDAO() ;
				int j = 0 ; 
				while(iterr.hasNext()){
					j++ ; 
					carta = daoCarta.doRetrieveByKey(iterr.next().getID_carta()) ; %>
					<input type="hidden" id='<%="pagamento"+j %>' name="pagamento" value = "<%=carta.getNCarta() %>" onClick="SelectRadio('pagamento',<%=j%>)">
					
					<div class="singoloSelettoreRiepilogoCarta" id="selCarta<%= j %>" onClick="SelectRadio('pagamento',<%=j%>); cambiaBordoCarta('selCarta<%= j %>');">
						<label for="pagamento"><%=carta.getIntestatario()%><br/><%=carta.getNCarta()%><br/><%=carta.getData_Scadenza() %></label>
					</div>
					
				<% } %>
					<div  id="addCarta" class="singoloSelettoreRiepilogoCarta" id="bottoneCarta" >
						<button class="modquantita">Aggiungi nuova carta +</button>
					</div>	
			<% }else{
				%>
					<div class="contcarte">
					<div  id="addCarta" class="singoloSelettoreRiepilogoCarta" id="bottoneCarta" >
						<button class="modquantita">Aggiungi nuova carta +</button>
					</div>
					</div>
				<%
				
			} %>
			</div>
		<br><br>
		<form action = "InserisciMetodoPagamento" METHOD = "POST" id="formCarta">
		
			 <div class="contenitore">
		        <div class="immagine1">
		            <img src="images/fitHouse2.png" class="immagine">
		            <img src="images/chip.png" class="immagine">
		        </div>
		        <div class="daticarta">
		         <span class="estremi">Numero di carta</span><br>
		         <input type="text" name="numerodicarta" placeholder="1234 1234 1234 1234" class="dat" id="numcarta" required><br>
		         <span class="estremi">Scadenza</span><br>
		         <input type="text" name="scadenza" placeholder="MM/YY" class="dat" required></br>
		         <span class="estremi">Intestatrio</span><br>
		         <input type="text" name="proprietario" placeholder="Intestatario" class="dat" required></br>
		         <span class="estremi">CVV</span><br>
		         <input type="text" name="CVV" placeholder="***" id="numcvv" class="dat" required >
		         <input type = "hidden" name = "ID_utente" value = <%= user.getId() %>>
		         <input type = "hidden" name= "from" value = "riepilogo">
		         <br>
		         <input type = "image" src= "images/add.png" class="iconaMenu addBtn" onclick="cardnumber(); cvv(); ">
		        </div>    
		    </div>
		
		</form></li>
		<form action = "Checkout.jsp" method = "POST" id = "formCheck">
			<input type = "hidden" 	id = "indirizzo" name = "indirizzo" value = "">
			<input type = "hidden" id = "pagamento" name = "pagamento" value = "">
			<input type = "hidden" id = "email" name = "email" value = "">
			<input type = "button" class="bottone bottone2" value = "Val al checkout" onclick=" Email('email','maill') ; RadioChecker('indirizzo','pagamento','email') ; ">
		</form>
		<% }else { %>
				<span class = "spanospite">Stai acquistando come Ospite</span><br/><br/>
				<div class="contcarte">
					<a href = "RiepilogoGuest.jsp">
						<div class="singoloSelettoreRiepilogo">Vai al checkout come ospite</div>
					</a>
					<a href = "LoginPage.jsp?acquisto=si">
						<div class="singoloSelettoreRiepilogo">Accedi o registrati al sito</div>
					</a>
				</div>
		<% } %>
		</div>
		</form> 
	<jsp:include page="footer.jsp"/>  
</body>
</html>
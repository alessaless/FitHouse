<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.*"
    import = "java.util.*"
    import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
</head>
<body>	
	<jsp:include page="header.jsp" />
	<% 
	UserBean user = (UserBean) session.getAttribute("currentSessionUser") ;
	String url  ; 
	
	//vado a controllare se l'utente è loggato, e in base all'esito dell'if determino la prossima servlet che dev'essere chiamata.
	if (user != null)
		url = "EffettuaOrdine" ; 
	else
		url = "EffettuaOrdineGuest" ; 
	%>
	<form action = "<%=url%>" method = "POST" style="padding:20px;">
	<div class = "contenitoreCheckout">
	<% 
		IndirizzoBean ind = new IndirizzoBean() ; 
		MetodiPagamentoBean metod = new MetodiPagamentoBean() ;
    	String note = null ;
    	//in base all'esito del controllo sull'utente loggato determino i parametri da prendere
		if(user != null){
			//prendo gli id di indirizzo e pagamento passati dalla jsp Riepilogo
			IndirizzoDAO daoind = new IndirizzoDAO() ; 
	    	MetodiPagamentoDAO daopag = new MetodiPagamentoDAO() ; 
	    	ind = daoind.doRetrieveByKey(request.getParameter("indirizzo")); 
	    	metod = daopag.doRetrieveByKey(request.getParameter("pagamento")) ; 
		}else{
			//prendo i parametri relativi a indirizzo e metodo di pagamento
			ind = new IndirizzoBean(0,request.getParameter("nome"),request.getParameter("cognome"),request.getParameter("telefono"),request.getParameter("citta"),
					request.getParameter("provincia"),request.getParameter("indirizzo"),request.getParameter("nazione"),Integer.parseInt(request.getParameter("CAP")),request.getParameter("note"),0) ; 
			metod = new MetodiPagamentoBean(request.getParameter("numerodicarta"),request.getParameter("proprietario"),request.getParameter("scadenza"),Integer.parseInt(request.getParameter("CVV"))) ; 
		}
    	//se le note sono vuote setto la stringa vuota
		if(ind.getNote() == null)
    		note = "" ; 
		else
			note = ind.getNote() ;
	    
	%>
	<!-- riepilogo dell ordine indicando indirizzo di spedizione, metodo di pagamenti e prodotti scelti -->
			
		<br> <span class = "grassetto">Riepilogo dell'ordine</span> 
		<ol>
			<li><span class = "grassetto">Indrizzo di spedizione scelto : </span></li><br/><br/>
				<span class=""> <%= ind.getNome()+" "+ind.getCognome() %><br><%= "Telefono : "+ind.getTelefono() %><br><%= ""+ind.getNazione()+", "+ind.getProvincia()+", "+ind.getCitta()+", "+ind.getVia() %><br><%="Note : "+note %> </span><br><br>	
			<li><span class = "grassetto">Metodo di pagamento scelto : </span></li><br/><br/>
				<span class = ""><%=metod.getIntestatario()%><br/><%=metod.getNCarta()%><br/><%=metod.getData_Scadenza() %></span><br/><br/>
			<li><span class = "grassetto">Prodotti : </span></li><br/><br/>
		</ol>
		<div class = "ContenitoreCarrello">
			<div class = "ProdottiCarrello">
			<%
			//faccio visualizzare al cliente i prodotti che ha inserito nel carrello per effettuare l ultimo controllo prima di fare l'ordine
			DecimalFormat df = new DecimalFormat("0.00");
						Carrello carrello = (Carrello) session.getAttribute("carrello") ; 
						Set<DoseBean> prodotti = (Set<DoseBean>)carrello.getProdotti().keySet() ; 
						Iterator<DoseBean> iter = prodotti.iterator() ; 
						DoseBean dose = new DoseBean() ;
						ProdottoDAO Daoprod = new ProdottoDAO() ; 
						ProdottoBean prodotto = new ProdottoBean() ; 
						double totale = 0.0 ; 
						int quantita = 0 ; 
						while(iter.hasNext()){
							dose = iter.next() ;
							prodotto = Daoprod.doRetrieveByKey(dose.getID_prodotto()) ;
							quantita = carrello.getProdotti().get(dose) ; %>
							<div class = "ProdottoCarrello">
							<div class="div_immagine" >
								<img src = "<%=dose.getUrl_foto()%>.jpg" alt = "foto prodotto <%=dose.getID_prodotto() %>" class = "fotoProd">
							</div>
							<div class= "div_dati">	
								<span class = "voceCarrello"> <%= prodotto.getNome() %> </span>
								<% if(dose.getPrezzo_Scontato() == 0.0 ){ 
										totale += dose.getPrezzo() * quantita ; %>
										<span class = "voceCarrello"> <%= dose.getPrezzo() %> $</span><br/> 
								<% }else{ 
										totale += dose.getPrezzo_Scontato() * quantita ;  %>
										<span class = "voceCarrello"> <%= dose.getPrezzo_Scontato() %> $</span><br/> <% } %>
								<%%>
								<span class = "voceCarrello"> QTA. <%=quantita%> </span>
								</div>
							</div><br>
							<% } %>
			</div>
			<div class = "TotaleCarrello">
				<span class = "grass"> Totale : <%= df.format(totale) %> $</span><br/><br/>
				<input type = "hidden" name = "totale" value = "<%=totale%>">
				<input type = "hidden" name = "email" value = "<%= request.getParameter("email") %>">
				<% if(user != null){ %>
				<!-- se l'utente è loggato passiamo solo determinati parametri -->
					<input type = "hidden" name = "user" value = "<%=user.getId() %>">
					<input type = "hidden" name = "pagamento" value = "<%=metod.getNCarta() %>">
					<input type = "hidden" name = "indirizzo" value = "<%=ind.getID() %>">
				<% }else{ %>
				<!-- invece nel caso si tratti di un guester passiamo tutto l'indirizzo e tutto il metodo di pagamento che serviranno per concludere l'ordine -->	
					<input type = "hidden" name = "nome" value = "<%=request.getParameter("nome") %>">
					<input type = "hidden" name = "cognome" value = "<%=request.getParameter("cognome")  %>">
					<input type = "hidden" name = "nazione" value = "<%=request.getParameter("nazione")  %>">
					<input type = "hidden" name = "citta" value = "<%=request.getParameter("citta")  %>">
					<input type = "hidden" name = "CAP" value = "<%=request.getParameter("CAP")  %>">
					<input type = "hidden" name = "provincia" value = "<%=request.getParameter("provincia")  %>">
					<input type = "hidden" name = "telefono" value = "<%=request.getParameter("telefono")  %>">
					<input type = "hidden" name = "indirizzo" value = "<%=request.getParameter("indirizzo")  %>">
					<input type = "hidden" name = "note" value = "<%=request.getParameter("note")  %>">
					<input type = "hidden" name = "numerodicarta" value = "<%=request.getParameter("numerodicarta")  %>">
					<input type = "hidden" name = "scadenza" value = "<%=request.getParameter("scadenza")  %>">
					<input type = "hidden" name = "proprietario" value = "<%=request.getParameter("proprietario")  %>">
					<input type = "hidden" name = "CVV" value = "<%=request.getParameter("CVV")  %>">
				<%} %>
				<input type = "submit" value = "Effettua ordine" class = "bottone">	
			</div>
		</div>
	</div>
	</form>
	<jsp:include page="footer.jsp"/>
</body>
</html>
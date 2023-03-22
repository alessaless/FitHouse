<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.UserDAO"
    import="java.sql.*"
    import="model.*"
    import="java.util.Collection"
    import="java.util.ArrayList"
    import="java.util.Iterator"
    import="java.net.URLEncoder"
    import="java.util.HashMap"
    import="java.util.Set"
    import="java.text.DecimalFormat"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="header.jsp" /> 
	<br><br> 		
	<%
		// per mettere solo due cifre dopo la virgola
		DecimalFormat df = new DecimalFormat("0.00");
		// prendiamo il carrello dalla sessione e se ci sono prodotti e se esiste li stampiamo 
		Carrello carrello = (Carrello) session.getAttribute("carrello") ;
		if (carrello == null || carrello.GetTotale() == 0.0) { %>
		<h1> Non ci sono prodotti nel carrello. </h1> 
		<% }else { %>
		<div class = "ContenitoreCarrello">
			<div class = "ProdottiCarrello">
			<%
						// prendiamo i prodotti dal carrello estraendo le chiavi del hashmap carrello
						Set<DoseBean> prodotti = (Set<DoseBean>)carrello.getProdotti().keySet() ; 
						Iterator<DoseBean> iter = prodotti.iterator() ; 
						DoseBean dose = new DoseBean() ;
						ProdottoDAO Daoprod = new ProdottoDAO() ; 
						ProdottoBean prodotto = new ProdottoBean() ; 
						double totale = 0.0 ; 
						int quantita = 0 ; 
						int contProd = 0 ; 
						while(iter.hasNext()){
							// facciamo riferimento al singolo prodotto
							dose = iter.next() ;
							prodotto = Daoprod.doRetrieveByKey(dose.getID_prodotto()) ;
							quantita = carrello.getProdotti().get(dose) ; %>
							<div class = "ProdottoCarrello">
							<div class="div_immagine" >
								<img src = "<%=dose.getUrl_foto()%>.jpg" alt = "foto prodotto <%=dose.getID_prodotto() %>" class = "fotoProd">
							</div>
							<div class= "div_dati">	
								<span class = "voceCar grassetto"> <%= prodotto.getNome() %> </span>
								<% if(dose.getPrezzo_Scontato() == 0.0 ){ 
										totale += dose.getPrezzo() * quantita ; %>
										<span class = "voceCar" id = "Prezzo<%=contProd%>"> <%= dose.getPrezzo() * quantita %> $</span><br/> 
								<% }else{ 
										totale += dose.getPrezzo_Scontato() * quantita ;  %>
										<span class = "voceCar" id = "Prezzo<%=contProd%>"> <%= dose.getPrezzo_Scontato() * quantita %> $</span><br/> <% } %>
								<button class="modquantita" onclick = "CambiaQuantita(-1,'<%=dose.getID_prodotto() %>','<%=dose.getID_mole() %>','<%=dose.getID_gusto()%>',<%=contProd %>,<%=dose.getPrezzo()%>,<%=dose.getPrezzo_Scontato()%>,<%= totale %>)">-</button>
								<span class = "modquantita" id = "QuantitaCarrelloProdotto<%=contProd%>"><%=quantita%> </span>
								<button class="modquantita" onclick = "CambiaQuantita(+1,'<%=dose.getID_prodotto() %>','<%=dose.getID_mole() %>','<%=dose.getID_gusto()%>',<%=contProd %>,<%=dose.getPrezzo()%>,<%=dose.getPrezzo_Scontato()%>,<%= totale%>)">+</button>
								<form action = "EliminaProdottoCarrello" method = "GET">
									<input type = "hidden" name = "ID_prod" value = "<%=dose.getID_prodotto() %>" >
									<input type = "hidden" name = "ID_mole" value = "<%=dose.getID_mole() %>" >
									<input type = "hidden" name = "ID_gusto" value = "<%=dose.getID_gusto() %>" >
									<input type = "image" src= "images/trash-can.png" class="cestino">
								</form> 
								</div>
							</div><br>
							<% contProd++ ; } %>
			</div>
			<div class = "TotaleCarrello">
				<span class = "grassetto" id = "conttotale"> Totale : <span id="totale"><%= df.format(totale) %></span> $</span><br/><br>
				<form action = "riepilogo.jsp" action = "GET">
					<input type = "submit" class="bottone" value = "Vai al riepilogo" style="margin: 0!important;">
				</form>
			</div>
		</div>
		<% } %>
   	<jsp:include page="footer.jsp"/>  	
</body>
</html>
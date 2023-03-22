<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.*"
     import="java.util.Collection"
    import="java.util.ArrayList"
    import="java.util.Iterator"
    import="java.net.URLDecoder"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prodotto</title>
<link rel="stylesheet" href="style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" /> 
	<br><br>
	<%
		
		DoseDAO daodose = new DoseDAO() ; 
		// prendo la prima corrispondenza della combo mole + gusto + id dal db tramite la doRetriveByKeyFull (full perché mi servono tutti i parametri per identificarla) 
		DoseBean dose = daodose.doRetrieveByKeyFULL(request.getParameter("codiceprodotto") , request.getParameter("mole"), request.getParameter("gusto"));
		// otteniamo tutti i gusti e formati del prodotto con quell'id
		ArrayList<DoseBean> gusti = (ArrayList<DoseBean>)daodose.doRetrieveAllByKeyGroupBy("ID_gusto",request.getParameter("codiceprodotto"));
		ArrayList<DoseBean> formati = (ArrayList<DoseBean>)daodose.doRetrieveAllByKeyGroupBy("ID_mole",request.getParameter("codiceprodotto"));
		//ArrayList<DoseBean> dosaggi = (ArrayList<DoseBean>)daodose.doRetrieveAllByKey(request.getParameter("codiceprodotto"));
		ProdottoDAO prodDAO = new ProdottoDAO() ; 
		ProdottoBean prod = prodDAO.doRetrieveByKey(dose.getID_prodotto()) ;
	%> 
	<div class = "contenitoreProdotto">
		<input type="hidden" id="idProdotto" value="<%= dose.getID_prodotto() %>">
		<div class = "img">
			<%
				// se c'è l'immagine nel db la stampo, altrimenti metto quella di default
				if(dose.getUrl_foto().equals("")){
					%>
						<img src = "images/prodotti/fitHouse1.png" class = "fotoProdotto1" alt = "Foto prodotto <%= dose.getID_prodotto() %>">
					<%
				}else{
					%>
						<img src = "<%= dose.getUrl_foto() %>.jpg" class = "fotoProdotto1" alt = "Foto prodotto <%= dose.getID_prodotto() %>">
					<%
				}
			%>
		</div>
		<div class = "datiProdotto">
			<!-- stampo i dati del prodotto (nome, prezzo, recensioni) -->
			<span class = "titoloProdotto"> <%= prod.getNome() %></span> <br/>
			  <span class="dati"> Prezzo:</span><br>
				<% if(dose.getPrezzo_Scontato() == 0.0){ %>
						<span id="prezzoProd" class= "prezzoDefinitivo" > <%= dose.getPrezzo() %> $</span>
				<% } else { %>
						<span class= "prezzoBarrato"  id="prezzoBarrato"> <%= dose.getPrezzo() %> $</span><br>
						<span id="prezzoProd" class="prezzoDefinitivo"> <%= dose.getPrezzo_Scontato() %> $</span>	
						<% } %>
						<br>
			<br><span class= "dati">Valutazione Prodotto:</span><br>					
			<%
				// prendo le recensioni relative a quel prodotto, faccio la media e stampo le stelle 
				RecensioneDAO recdao = new RecensioneDAO(); 
				ArrayList<RecensioneBean> recs = (ArrayList<RecensioneBean>) recdao.doRetrieveAllByID(request.getParameter("codiceprodotto")) ;
				Iterator<RecensioneBean> iterRec2 = recs.iterator() ; 
				int media = 0;
				if(recs.size() > 0){
					while(iterRec2.hasNext()){
						media += iterRec2.next().getVoto();
					}
					media = media / recs.size();
					// calcolo stelle piene e vuote
					for(int i = 0; i < media; i++){	
						%>
							<img src = "images/starPiena.png" class = "star">
						<%
					}
					for(int i = 0; i < (5-media); i++){
						%>
							<img src = "images/starVuota.png" class = "star">
						<%
					}
				}else{
					// nel caso non ci siano recensioni
					%>
					<span>Nessuna recensione disponibile</span>
					<%
				}
			%>		
				
			<form action = "CarrelloServlet" METHOD = "GET"><br>
			<% 
				// se esistono gusti per quel prodotto, stampa un menu a tendina dove è possibile selezionarne uno tra i disponibili
				if(!dose.getID_gusto().equals("N/A") ) { %>
					<span class= "dati"> Gusto</span>
					<br>
					<select name="ID_gusto" id="idGusto" class = "selectCombinazione">
						<%
							Iterator<DoseBean> it = gusti.iterator();
							DoseBean d;
							while(it.hasNext()){
								d = it.next();
								%>
									<option value = "<%= d.getID_gusto() %>" ><%= d.getID_gusto() %></option>
								<%
							} %>
					</select><br><br>
			<% }else { 
				// essendo che il gusto non esiste settiamo a N/A il valore del parametro idGusto che passiamo alla servlet
				%>
					<input type = "hidden" name = "ID_gusto"  id="idGusto" value = "N/A">
					<% } %>
					
					<span class= "dati"> Formato</span>
					<br>
					<select name = "ID_mole" id="idMole" class = "selectCombinazione">
						<%
							// stampo i formati del prodotto in un menu a tendina
							Iterator<DoseBean> iter = formati.iterator();
							DoseBean dos;
							while(iter.hasNext()){
								dos = iter.next();
								%>
									<option value = "<%= dos.getID_mole() %>" ><%= dos.getID_mole() %></option>
							<% } %>		
					</select><br/><br/>
					    <span class= "dati"> Quantità:</span> <br>
						<input type = "number" class="quantita" name = "quantitaRichiesta" min = "1" max = "<%= dose.getQuantita_disp()  %>" value = "1">
						<input type = "hidden" name = "ID_prod" value = "<%= prod.getID_prod() %>"> <br><br>
						<input type = "submit" value = "Aggiungi al carrello" class= "bottone" style="margin:0px; font-size: 12px;">
					</form> 
				
		</div>
		<!-- informazioni sul prodotto (descrizione, valori nutrizionali ect...) -->
		<div class = "TextProdotto">
			<span class = "titoloProdotto">Descrizione</span>
			<p> <%= prod.getDescrizione() %></p>
			<span class = "titoloProdotto">Ingredienti</span>
			<p> <%= prod.getIngredienti() %></p>
			<span class = "titoloProdotto">Valori Nutrizionali</span>
			<p> <%= prod.getValori_Nutrizionali()  %></p>
		</div>
		
		<div class="recensioniProdotto">
			<%
				// form per aggiunta recensioni visibile solo agli utenti registrati
				UserBean user = (UserBean) session.getAttribute("currentSessionUser");
				if ( user != null){
					%>
					<span class="titoloProdotto">Recensioni utente</span><br/><br/>
					<form action = "EffettuaRecensione" method = "POST" >
						<span class="grassetto">Punteggio</span><br/>
							<img src = "images/starVuota.png" id="1star" class = "star" onclick = "Valutazione(1)">
							<img src = "images/starVuota.png" id="2star" class = "star" onclick = "Valutazione(2)">
							<img src = "images/starVuota.png" id="3star" class = "star" onclick = "Valutazione(3)">
							<img src = "images/starVuota.png" id="4star" class = "star" onclick = "Valutazione(4)">
							<img src = "images/starVuota.png" id="5star" class = "star" onclick = "Valutazione(5)"><br/><br/>
						<span class="grassetto">Titolo</span><br/>
							<input type = "text" name = "titolo" class="dati" required><br/><br/>
						<span class="grassetto">Recensione</span><br/>
							<textarea name = "rec" rows = "4" cols = "50" class="dati" required></textarea><br/><br/>
						<span class="grassetto">Inserisci Il nome che verrà mostrato</span><br/>
						<input type = "text" name = "nome" value = "<%= user.getNome() %>" class="dati" required><br/><br/>
						<!--  parametri da passare alla servlet per la recensione -->
						<input type = "hidden" id = "valutazione" name = "valutazione" value = "">
						<input type = "hidden" id = "id_prod" name = "id_prod" value = "<%=request.getParameter("codiceprodotto") %>">
						<input type = "hidden" id = "id_utente" name = "id_utente" value = "<%= user.getId() %>">
						<!-- necessari per reindirizzare di nuovo alla pagina del prodotto  -->
						<input type = "hidden" id = "mole" name = "mole" value = "<%= request.getParameter("mole") %>">
						<input type = "hidden" id = "gusto" name = "gusto" value = "<%= request.getParameter("gusto") %>">
						<input disabled type = "submit" id="bottFaiRec" value = "Effettua recensione" class="bottone"><br/><br/>
					</form>
					<span class="titoloProdotto">Recensioni effettuate da altri utenti</span><br/><br/>
					<div class="containerRecensioni">
						<%  }
								// stampa delle recensioni di quel prodotto dal database (se presenti)
								RecensioneDAO DAOrec = new RecensioneDAO() ; 
								ArrayList<RecensioneBean> recensioni = (ArrayList<RecensioneBean>) DAOrec.doRetrieveAllByID(request.getParameter("codiceprodotto")) ;
								Iterator<RecensioneBean> iterRec = recensioni.iterator() ; 
								RecensioneBean rec ;
								if(recensioni.size() == 0){
									%>
										<span class="grassetto">Nessuna recensione presente per questo prodotto.</span>
									<%
								}else{
									// con un ciclo verifichiamo quante stelle piene e quante vuote stampare
									while(iterRec.hasNext()){
										rec = iterRec.next() ; %>
										<div class = "singolaRec">
											<br>
											<span class="titoloRec"><%=rec.getTitolo() %> </span><hr>
											<span class="grassetto"><%= rec.getNome_u() %></span> 
											<% int voto = rec.getVoto() ; 
											   int vuote = 5 - voto ; 
											   while(voto > 0){
												   %>
												   <img src = "images/starPiena.png" class = "star">
												   <% voto-- ; 
											   } 
											   while(vuote > 0){ %>
												   <img src = "images/starVuota.png" class = "star">
												  <% vuote-- ; 
											   } %>
											   <br>
											<span class="grassetto">Recensito il <%=rec.getData() %> </span><br/>
											<span><%= rec.getTesto() %></span>
										</div>
									<% }
								}
							%>
					</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/> 
	<script src="script.js"></script>
</body>
</html>

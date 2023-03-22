<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "model.*" 
    import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
<title>Dettagli Ordine</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
		OrdineDAO DAOord = new OrdineDAO() ; 
		OrdineBean ordine = DAOord.doRetrieveByKey(request.getParameter("id_ordine")) ; 
		IndirizzoDAO indDAO = new IndirizzoDAO() ; 
		IndirizzoBean indirizzo = indDAO.doRetrieveByKey(Integer.toString(ordine.getID_indirizzo()) ) ; 
		MetodiPagamentoDAO daoMetodo = new MetodiPagamentoDAO() ;
		MetodiPagamentoBean metodo = daoMetodo.doRetrieveByKey(ordine.getNum_Carta() ) ;
		String note ; 
		if(indirizzo.getNote() == null)
			note = "nessuna" ;
		else
			note = indirizzo.getNote() ; 
	%>
	<br>
	<div class ="contenitoredettagli">
		<div class = "dettagliordine">
			
			<div class="unodeidettagli">
				<span class = "grassetto">Numero Ordine:</span><br>
				<span><%= ordine.getNum_Ordine()%></span>	
			</div>
			
			<div class="unodeidettagli">
				<span class="grassetto">Data:</span><br>
				<span><%= ordine.getData_Ordine() %></span>
			</div>
			
			<div class="unodeidettagli">
				<span class="grassetto">Indirizzo di spedizione</span>
				<br><span><%=indirizzo.getNome()+" "+indirizzo.getCognome() %><br/><%=indirizzo.getVia()%><br/><%= indirizzo.getCitta()+" "+indirizzo.getCAP()+", "+indirizzo.getProvincia()+", "+indirizzo.getNazione() %></span>
			</div>
			
			<div class="unodeidettagli">
				<span class="grassetto">Metodo di pagamento</span>
				<br><span><%= metodo.getNCarta()%><br/><%=metodo.getIntestatario() %></span>
			</div>
			
			<div class="unodeidettagli">
				<span class="grassetto">Totale</span>
				<br><span><%=ordine.getTotale() %> $</span>
			</div>	
		</div>
		
		<%
			ComprendeDAO daoComprende = new ComprendeDAO() ;
			ArrayList<ComprendeBean> prodotti = (ArrayList<ComprendeBean>) daoComprende.DoRetrieveAllByKey(ordine.getNum_Ordine()) ; 
			ProdottoDAO prodDAO = new ProdottoDAO() ; 
			Iterator<ComprendeBean> iter = prodotti.iterator() ; 
			ComprendeBean prodotto ; 
			while(iter.hasNext()){
				prodotto = iter.next() ; %>
				<div class = "ciascunprodotto prodottooo">
					<img src = "<%=prodotto.getUrl_foto() %>.jpg" class = "div_immagine" href = "prodotto.jsp?codiceprodotto=<%=prodotto.getID_prod() %>&mole=<%=prodotto.getMole() %>&gusto=<%=prodotto.getAroma() %>" >
					<a class="margin grassetto" href = "prodotto.jsp?codiceprodotto=<%=prodotto.getID_prod() %>&mole=<%=prodotto.getMole() %>&gusto=<%=prodotto.getAroma() %>"><%=prodDAO.GetNameByID(prodotto.getID_prod()) %> </a>
					<span class="margin grassetto">Quantità: </span> 
					<span class="marginetto"><%= prodotto.getQuantita() %></span>
					<span class="margin grassetto">Prezzo: </span>
					<span class="marginetto"><%= prodotto.getPrezzo()*prodotto.getQuantita() %></span> 
				</div>
				<%
				}
		%>
		<div class="ciascunprodotto prodottooo">
			<input type="button" class="bottone" value="Scarica Fattura" onclick="generatePDF()">
		</div>
	</div>
	
	<script src="https://unpkg.com/jspdf-invoice-template@1.4.0/dist/index.js"></script> 
		<script>
		
			function generatePDF(){
				var pdfObject = jsPDFInvoiceTemplate.default(props);
			}
			
			<%
				OrdineDAO udao = new OrdineDAO();
				ComprendeDAO udaoc = new ComprendeDAO();
				OrdineBean ubean = udao.doRetrieveByKey(request.getParameter("id_ordine"));
				ArrayList<ComprendeBean> ubean2 = (ArrayList<ComprendeBean>) udaoc.DoRetrieveAllByKey(Integer.parseInt(request.getParameter("id_ordine")));
				UserDAO userdao = new UserDAO();
				UserBean userbean = userdao.doRetrieveByKey(String.valueOf(ubean.getID_utente()));
				IndirizzoDAO ind = new IndirizzoDAO();
				IndirizzoBean indb = ind.doRetrieveByKey(String.valueOf(ubean.getID_indirizzo()));
				double totale = 0;
				%>
			// crea array prodotti da server
			var prodotti= new Array();
			var prod;
			<%
			Iterator<ComprendeBean> it = ubean2.iterator();
    		ProdottoDAO pdao = new ProdottoDAO();
    		ProdottoBean tmp1;
    		ComprendeBean tmp;
    		int i = 1;
    		while(it.hasNext()){
    			tmp = it.next();
    			tmp1 = pdao.doRetrieveByKey(tmp.getID_prod());
    			%>
    				prod = {id:"<%= i %>", nome:"<%= tmp1.getNome() %>", prezzo:"<%= tmp.getPrezzo() %>" , quantita: "<%= tmp.getQuantita() %>", tot: "<%= tmp.getQuantita() * tmp.getPrezzo() %>" };
    				prodotti.push(prod);
    			<%
    			totale += tmp.getQuantita() * tmp.getPrezzo(); 
    			i++;
    		}
			%>
			var props = {
				    outputType: jsPDFInvoiceTemplate.OutputType.Save,
				    returnJsPDFDocObject: true,
				    fileName: "Fattura Ordine FitHouse",
				    orientationLandscape: false,
				    compress: true,
				    logo: {
				        src: "images/fitHouse2.png",
				        width: 56.33, //aspect ratio = width/height
				        height: 23.66,
				        margin: {
				            top: 0, //negative or positive num, from the current position
				            left: 0 //negative or positive num, from the current position
				        }
				    },
				    business: {
				        name: "FitHouse",
				        address: "Università degli studi di Salerno",
				        phone: "(+39) 000 11 11 111",
				        email: "fithouse@mail.it",
				        website: "www.fithouse.it",
				    },
				    contact: {
				        address: "<%= indb.getVia() + " " + indb.getProvincia() + " " + indb.getCitta() + " " + indb.getNazione() %>",
				        email: "<%= userbean.getEmail() %>",
				    },
				    invoice: {
				        label: "Fattura #<%= ubean.getNum_Ordine() %>: ",
				        invGenDate: "Data Fattura: <%= ubean.getData_Ordine() %> ",
				        headerBorder: false,
				        tableBodyBorder: false,
				        // qua ci andrebbe l'header
				        header: [
				          {
				            title: "#", 
				            style: { 
				              width: 10 
				            } 
				          }, 
				          { 
				            title: "Prodotto",
				            style: {
				              width: 50
				            } 
				          }, 
				          { title: "Prezzo"},
				          { title: "Quantità"},
				          { title: "Totale"}
				        ],
				        // qua ci va la tabella
				        
				        table: Array.from(prodotti, i=>([
						   i.id,
						   i.nome,
						   i.prezzo,
						   i.quantita,
						   i.quantita * i.prezzo 
						])),
				        
				        invTotalLabel: "Totale:",
				        invTotal: "<%= String.format("%.2f", totale) %> $",
				    },
				    pageEnable: true,
				    pageLabel: "Page ",
				};
			
			
		</script>
	
	
	<jsp:include page="footer.jsp" />
</body>
</html>
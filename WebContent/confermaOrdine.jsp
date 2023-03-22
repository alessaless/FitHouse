<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Ordine Confermato</title>
	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<div class="ordineConfermato">
			<br><br>
			<span class="titoloProdotto">Ordine Confermato!</span>
			<input type="button" value="Visualizza fattura" class="bottone" onclick="generatePDF()">		
		</div>
		<jsp:include page="footer.jsp"/>
		<script src="https://unpkg.com/jspdf-invoice-template@1.4.0/dist/index.js"></script> 
		<script>
		
			function generatePDF(){
				var pdfObject = jsPDFInvoiceTemplate.default(props);
			}
			
			<%
				OrdineDAO udao = new OrdineDAO();
				ComprendeDAO udaoc = new ComprendeDAO();
				OrdineBean ubean = udao.doRetrieveByKey(request.getParameter("id"));
				ArrayList<ComprendeBean> ubean2 = (ArrayList<ComprendeBean>) udaoc.DoRetrieveAllByKey(Integer.parseInt(request.getParameter("id")));
				UserDAO userdao = new UserDAO();
				String mail;
				UserBean userbean;
				if(request.getParameterMap().containsKey("email"))
					mail = request.getParameter("email");
				else{
					userbean = userdao.doRetrieveByKey(String.valueOf(ubean.getID_utente()));
					mail = userbean.getEmail();
				}
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
				        email: "<%= mail %>",
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
		
	</body>
</html>
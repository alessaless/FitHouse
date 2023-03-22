<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.*"  
	import="java.util.*"  
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>I tuoi indirizzi</title>
	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
		.contenitoreOrdini{
			display: flex;
		}
	</style>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<br>
		<%
			if(session.getAttribute("currentSessionUser") == null){
				// mandalo al login
				response.sendRedirect("LoginPage.jsp");
			}else{
				IndirizzoDAO idao = new IndirizzoDAO();
				UserBean u = (UserBean) session.getAttribute("currentSessionUser");
				ArrayList<IndirizzoBean> indirizzi = (ArrayList<IndirizzoBean>) idao.doRetrieveAllByUser(u.getId());
				IndirizzoBean address;
				Iterator<IndirizzoBean> it = indirizzi.iterator();
				String note ; 
				%>
					<span class="indpag">I tuoi indirizzi : </span><br/><br/>
					<div class="contcarte">
					
				<%
				while(it.hasNext()){
					address = it.next();
					if(address.getNote() == null)
						note = "Nessuna" ;
					else 
						note = address.getNote() ; 
					%>
						<div class = "singolopagamento">
						
							<div class="fotcarta">
						     	<img alt="address" src="images/fotoadd.png" class= "iconaMenu">
						    </div>
					    
							<div class= "datcarta">
									<%= address.getNome()+" "+address.getCognome()  %><br/>
									<%= address.getCitta()+" "+address.getCAP()+", "+address.getProvincia()+", "+address.getNazione() %><br/>
									<%= address.getVia() %><br/>
									<%= address.getTelefono() %><br/>
							</div>

						</div>
					<%					
				}
				%>
				</div>
				
					<div class="contnuovopag">
					<span class = "grassetto" id="nuovometodo">Inserisci un nuovo indirizzo di spedizione</span><br/><br/>
					<form action = "InserisciIndirizzo" method = "POST">
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
						<input type = "hidden" name = "id_utente" value = "<%= u.getId() %>">
						<input type="hidden" name="fromAreaUtente" value="y">
						
						<input type="submit" class="bottone" value="inserisci indirizzo"></input><br/><br/>
					</form>
				<%
			}
		%>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>
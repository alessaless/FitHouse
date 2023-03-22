<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.*"  
	import="java.util.*"  
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>I tuoi metodi di pagamento</title>
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
				UtilizzaDAO idao = new UtilizzaDAO();
				UserBean u = (UserBean) session.getAttribute("currentSessionUser");
				ArrayList<UtilizzaBean> indirizzi = (ArrayList<UtilizzaBean>) idao.doRetrieveAllByKey(u.getId());
				Iterator<UtilizzaBean> it = indirizzi.iterator();
				
				UtilizzaBean util ; 
				MetodiPagamentoDAO daoPagamento = new MetodiPagamentoDAO() ; 
				MetodiPagamentoBean carta ;
				%>
					<span class="indpag">I tuoi metodi di pagamento </span><br>
					<div class= "contcarte">
				<%
				while(it.hasNext()){
					util = it.next() ; 
					carta = daoPagamento.doRetrieveByKey(util.getID_carta())  ;
					%>
					<div class = "singolopagamento">
					    <div class="fotcarta">
					     	<img alt="creditcard" src="images/fotcarta.png" class= "iconaMenu">
					    </div>
					    <div class = "datcarta">
							<%= carta.getNCarta() %><br/>
							<%= carta.getIntestatario() %><br/>
							<%= carta.getData_Scadenza() %><br/>
							<%= carta.getCVV() %><br/>
					    </div>
					    
					</div>
					<%				
				}
				%>
					</div>
					
					<div class="contnuovopag">
					<span class ="grassetto">Inserisci un nuovo metodo di pagamento</span><br/><br/>
					<form action = "InserisciMetodoPagamento" METHOD = "POST" onsubmit = "return cardnumber() && cvv() && datacreditcard()">
						 <div class="contenitore">
					        <div class="immagine1">
					            <img src="images/fitHouse2.png" class="immagine">
					            <img src="images/chip.png" class="immagine">
					        </div>
					        <div class="daticarta">
					         <span class="estremi">Numero di carta</span><br>
					         <input type="text" name="numerodicarta" id="numcarta" placeholder="1234 1234 1234 1234" class="dat" required><br>
					         <span class="estremi">Scadenza</span><br>
					         <input type="text" name="scadenza" placeholder="MM/YY" class="dat" required id="scadenzacarta"></br>
					         <span class="estremi">Intestatrio</span><br>
					         <input type="text" name="proprietario" placeholder="Intestatario" class="dat" required></br>
					         <span class="estremi">CVV</span><br>
					         <input type="text" name="CVV" id="numcvv" placeholder="***" class="dat" required>
					         <input type = "hidden" name = "ID_utente" value = <%= u.getId() %>>
					         <input type = "hidden" name= "from" value = "setting">
					         <br>
					         <input type = "image" src= "images/add.png" class="iconaMenu addBtn">
					        </div>    
					    </div>
					</form>
				<%
			}
		%>
					</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>
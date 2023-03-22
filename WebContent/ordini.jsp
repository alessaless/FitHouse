<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="model.*"  
	import="java.util.*"  
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>I tuoi ordini</title>
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
				OrdineDAO odao = new OrdineDAO();
				UserBean u = (UserBean) session.getAttribute("currentSessionUser");
				ArrayList<OrdineBean> ordini = (ArrayList<OrdineBean>) odao.doRetrieveByUtente(u.getId());
				OrdineBean order;
				Iterator<OrdineBean> it = ordini.iterator();
				%>
					<div class = "conteinerrr">
					
					<%
					while(it.hasNext()){
						order=it.next();
						%>
						<div class = "singoloordine">
						<div class = "headerordine">
							<table class="intestazioneordine" id= "costumers">
								<tr>
									<th> Numero Ordine </th>
									<th> Ordine effettuato in data </th>
									<th> Totale ordine </th>
									<th><a href="DettagliOrdine.jsp?id_ordine=<%=order.getNum_Ordine()%>"> Visualizza dettagli dell'ordine </a></th>
								</tr>
								<tr>
									<td><%= order.getNum_Ordine() %> </td>
									<td><%= order.getData_Ordine() %></td>
									<td><%= order.getTotale() %></td>
								</tr>
							</table>
						</div>
						<div class = "bodyordine">
							<%
							ComprendeDAO daoComprende = new ComprendeDAO() ; 
							ArrayList<ComprendeBean> prodotti = (ArrayList<ComprendeBean>) daoComprende.DoRetrieveAllByKey(order.getNum_Ordine()) ; 
							Iterator<ComprendeBean> iter = prodotti.iterator() ; 
							ComprendeBean compr ; 
							ProdottoDAO prod = new ProdottoDAO() ; 
							while(iter.hasNext()){
								compr = iter.next() ; %>
								<div class="ciascunprodotto">
								<img src = "<%=compr.getUrl_foto() %>.jpg"  href = "prodotto.jsp?codiceprodotto=<%=compr.getID_prod() %>&mole=<%=compr.getMole() %>&gusto=<%=compr.getAroma() %>" class = "div_immagine">
								<a class="descArt" href = "prodotto.jsp?codiceprodotto=<%=compr.getID_prod() %>&mole=<%=compr.getMole() %>&gusto=<%=compr.getAroma() %>"> <%= prod.GetNameByID(compr.getID_prod()) %></a>
								</div>
							<%  }
							%>
						</div></div>
						<%					
					}
				}
			%>	
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>
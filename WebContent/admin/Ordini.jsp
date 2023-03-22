<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"
    import="java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordini</title>
<link rel="stylesheet" href="admin.css">
<meta name="viewport" content="width=device-width">
</head>
<body>
	<% 
    	if(session.getAttribute("currentSessionAdmin") != null){
       		OrdineDAO udao = new OrdineDAO();
       		if(request.getParameterMap().containsKey("data1") && !request.getParameter("data1").equals("") &&!request.getParameter("data2").equals("")){
       			ArrayList<OrdineBean> ebean = (ArrayList<OrdineBean>) udao.doRetrieveByDate(request.getParameter("data1"), request.getParameter("data2"));
           		OrdineBean obean;
           		Iterator<OrdineBean> it = ebean.iterator();
       			%>
       				<h2>Lista Ordini</h2>
		   			<form method="get" action="../CercaOrdiniPerData">
		   				<input type="date" name="data1">
		   				<input type="date" name="data2">
		   				<input type="image" src="../images/calendar.png" class="ricercaImg">
		   			</form>
		   			<br>
					<table>
						<tr>
							<th class="grassetto">Numero Ordine</th>
							<th class="grassetto">Utente</th>
							<th class="grassetto">Data Ordine</th>
							<th class="grassetto">Stato Ordine</th>
							<th class="grassetto">Totale</th>
							<th class="grassetto">Dettaglio Ordine</th>
							<th class="grassetto">Registrato?</th>
						</tr>	
					<%
		       		while(it.hasNext()){
		       			obean = it.next();
		       			%>
		       				<tr>
		  						<th><%= obean.getNum_Ordine() %></th>
		  						<th><%= obean.getEmail() %></th>
		  						<th><%= obean.getData_Ordine() %></th>
		  						<th><%= obean.getStato() %></th>
		  						<th><%= obean.getTotale() %></th>
		  						<th>Dettagli...</th>
								<%
									if(obean.getID_utente() == 0){
										%>
											<th>NO</th>
										<%
									}else{
										%>
											<th>SI</th>
										<%
									}
								%>
		  					</tr>
		       			<%
		       		}
       		}else{
       			ArrayList<OrdineBean> ebean = (ArrayList<OrdineBean>) udao.doRetrieveAll(null);
           		OrdineBean obean;
           		Iterator<OrdineBean> it = ebean.iterator();
       			%>
       				<h2>Lista Ordini</h2>
		   			<form method="get" action="../CercaOrdiniPerData">
		   				<input type="date" name="data1">
		   				<input type="date" name="data2" id="data2">
		   				<input type="image" src="../images/calendar.png" class="ricercaImg">
		   			</form>
		   			<br>
					<table>
						<tr>
							<th class="grassetto">Numero Ordine</th>
							<th class="grassetto">Utente</th>
							<th class="grassetto">Data Ordine</th>
							<th class="grassetto">Stato Ordine</th>
							<th class="grassetto">Totale</th>
							<th class="grassetto">Dettaglio Ordine</th>
							<th class="grassetto">Registrato?</th>
						</tr>	
					<%
		       		while(it.hasNext()){
		       			obean = it.next();
		       			%>
		       				<tr>
		  						<th><%= obean.getNum_Ordine() %></th>
		  						<th><%= obean.getEmail() %></th>
		  						<th><%= obean.getData_Ordine() %></th>
		  						<th><%= obean.getStato() %></th>
		  						<th><%= obean.getTotale() %></th>
		  						<th>Dettagli...</th>
								<%
									if(obean.getID_utente() == 0){
										%>
											<th>NO</th>
										<%
									}else{
										%>
											<th>SI</th>
										<%
									}
								%>
		  					</tr>
		       			<%
		       		}
       		}
	    }else{
	    	response.sendRedirect("invalidLogin.jsp"); //error page
	    }
     %>
</body>
<script>
	document.getElementById('data2').max = new Date().toISOString().split("T")[0];
	document.getElementById('data2').value = new Date().toISOString().split("T")[0];
</script>
</html>
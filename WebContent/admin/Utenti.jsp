<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"
    import="java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Utenti</title>
<link rel="stylesheet" href="admin.css">
<meta name="viewport" content="width=device-width">
</head>
<body>
	<% 
    	if(session.getAttribute("currentSessionAdmin") != null){
       		if(request.getParameterMap().containsKey("nomeUtente")){
       			// stampa solo quelli cercati per mail
       			UserDAO udao = new UserDAO();
           		ArrayList<UserBean> ebean = (ArrayList<UserBean>)udao.doRetrieveAll(null);
           		Iterator<UserBean> it = ebean.iterator();
           		UserBean ubean;
           		%>
           			<h2>Lista Utenti</h2>
           			<form method="get" action='../CercaUtente'>
           				<input type="text" placeholder="Cerca Utente..." name="nomeUtente">
           				<input type="image" src="../images/magnifier.png" class="ricercaImg">
           			</form>
           			<br>
    				<table>
    					<tr>
    						<th class="grassetto">Nome</th>
    						<th class="grassetto">Cognome</th>
    						<th class="grassetto">E-Mail</th>
    					</tr>	
       			<%
           		while(it.hasNext()){
           			ubean = it.next();
           			if(ubean.getEmail().toUpperCase().contains(request.getParameter("nomeUtente").toUpperCase())){
	           			%>
	           				<tr>
	    						<th><%= ubean.getNome() %></th>
	    						<th><%= ubean.getCognome() %></th>
	    						<th><%= ubean.getEmail() %></th>
	    					</tr>
	           			<%
           			}
           		}
           		%>
           			</table>
           		<%
       			
       		}else{
       			// stampali tutti
       			UserDAO udao = new UserDAO();
           		ArrayList<UserBean> ebean = (ArrayList<UserBean>)udao.doRetrieveAll(null);
           		Iterator<UserBean> it = ebean.iterator();
           		UserBean ubean;
           		%>
           			<h2>Lista Utenti</h2>
           			<form>
           				<input type="text" placeholder="Cerca Utente..." name="nomeUtente">
           				<input type="image" src="../images/magnifier.png" class="ricercaImg">
           			</form>
           			<br>
    				<table>
    					<tr>
    						<th class="grassetto">Nome</th>
    						<th class="grassetto">Cognome</th>
    						<th class="grassetto">E-Mail</th>
    					</tr>	
       			<%
           		while(it.hasNext()){
           			ubean = it.next();
           			%>
           				<tr>
    						<th><%= ubean.getNome() %></th>
    						<th><%= ubean.getCognome() %></th>
    						<th><%= ubean.getEmail() %></th>
    					</tr>
           			<%
           		}
           		%>
           			</table>
           		<%
       		}
	    }else{
	    	 response.sendRedirect("invalidLogin.jsp"); //error page
	    }
     %>
</body>
</html>
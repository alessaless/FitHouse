<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProdottoBean"
    import = "model.GustoBean"
    import = "model.MoleBean"
    import = "model.ProdottoDAO"
    import = "model.moleDAO"
    import = "model.GustoDAO"
    import = "java.util.*"
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<title>
			Cambia dosaggio
		</title>
		<style>
			body{
				font-family: Arial, Helvetica, sans-serif;
			}
		
			span{
				font-weight:bolder;
				display:block;
			}
			
			select, input, textarea{
				width: 300px;
			}
			
			input, select{
				height:30px;
			}
		</style>
	</head>
	<body>
		<%
			String id = request.getParameter("prod");
			ProdottoBean p = new ProdottoBean();
			ProdottoDAO p1 = new ProdottoDAO();
			p = p1.doRetrieveByKey(id);
			if(session.getAttribute("currentSessionAdmin") != null){
		%>
				<h3>Modifica dosaggi...</h3>
				<form action='../AggiugiDose' method='POST'>
					<span>ID (non modificabile)</span>
		        	<input type="hidden" name="id" value="<%= id %>">
		            <input disabled type="text" value="<%= id %>"><br><br>
		            <span>Gusto</span>
		            <select name="gusto">
		            	<%
		            		GustoBean g = new GustoBean();
		            		GustoDAO g1 = new GustoDAO();
		            		ArrayList<GustoBean> gusti = (ArrayList<GustoBean>) g1.doRetrieveAll(null);
		            		Iterator<GustoBean> i = gusti.iterator();
		            		while(i.hasNext()){
		            			g = i.next();
		            			%>
		            				<option value='<%= g.getAroma() %>'><%= g.getAroma() %></option>
		            			<%
		            		}
		            	%>
		            </select>
		            <br><br>
		            <span>Mole</span>
		            <select name="mole">
		            	<%
		            		MoleBean m = new MoleBean();
		            		moleDAO m1 = new moleDAO();
		            		ArrayList<MoleBean> moli = (ArrayList<MoleBean>) m1.doRetrieveAll(null);
		            		Iterator<MoleBean> it = moli.iterator();
		            		while(it.hasNext()){
		            			m = it.next();
		            			%>
		            				<option value='<%= m.getQuantita() %>'><%= m.getQuantita() %></option>
		            			<%
		            		}
		            	%>
		            </select>
		            <br><br>
		            <span>Quantità disponibile</span>
		            <input type="text" name='qta' placeholder="Quantità disponibile" required><br><br>
		            <span>Prezzo</span>
		            <input type="text" name='prezzo' placeholder="Prezzo" required><br><br>
		            <span>Prezzo Scontato</span>
		            <input type="text" name='prezzoScontato' placeholder="Prezzo Scontato"><br><br>
		            <input type='submit' value='Aggiungi'>
				</form>
				<% 
			}else{
				response.sendRedirect("invalidLogin.jsp"); //error page
			}
			%>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProdottoBean"
    import = "model.SottocategoriaBean"
    import = "model.ProdottoDAO"
    import = "model.SottocategoriaDAO"
    import = "java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica prodotto</title>
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
	        <div>
	             <h3>Modifica il prodotto...</h3><br>
	                <form action="../Modifica" method="POST">
	                	<span>ID (non modificabile)</span>
	                	<input type="hidden" name="id" value="<%= id %>">
	                    <input disabled type="text" value="<%= id %>"><br><br>
	                    <span>Modifica il nome del prodotto</span>
	                    <input type="text" name="nome" placeholder="ES. Burro d'arachidi" value="<%= p.getNome() %>"><br><br>
	                    <span>Modifica la descrizone del prodotto</span>
	                    <textarea name="descrizione" rows="4" cols="50"><%= p.getDescrizione() %></textarea><br><br>
	                    <span>Modifica la lista di ingredienti del prodotto</span>
	                    <textarea name="ingredienti" rows="4" cols="50"><%= p.getIngredienti() %></textarea><br><br>
	                    <span>Modifica  i valori nutrizionali : </span>
	                    <textarea rows="4" cols="30" id="valori_nutrizionali" name = "valori_nutrizionali" placeholder=""><%= p.getValori_Nutrizionali() %></textarea>
	     				<br><br>
	     				<span>IVA applicata sul prodotto</span>
	     				<input type="text" name="iva" placeholder="iva" value="<%= p.getIVA() %>"><br><br>
	     				
	     				<span>Il prodotto deve essere mostrato nel catalogo?</span>
	     				<select name="cancellato" id="cancellato">
	     					<%
	     						if(p.isCancellato()){
	     							%>
	     								<option value="0">SI</option>
	     								<option value="1" selected>NO</option>			
	     							<%
	     						}else{
	     							%>
     									<option value="0" selected>SI</option>
     									<option value="1">NO</option>			
     								<%
	     						}
	     					%>
	     					
	     				</select>
	     				<br><br>
	                    <span>Specifica il tipo di prodotto</span>
	                    <select name="tipo" id="tipo">
	     					<option value="GRAMMI">GRAMMI</option>
	     					<option value="PEZZI">PEZZI</option>
	     				</select>
	                    <br><br>
						<span>Indicare la sottocategoria di appartenenza</span>
						<select name = "sottocat" id = "sottocat">
							<% SottocategoriaDAO sottocat = new SottocategoriaDAO() ;
	                    		ArrayList<SottocategoriaBean> lista = (ArrayList<SottocategoriaBean>) sottocat.doRetrieveAll(null) ;
	                    		SottocategoriaBean name = new SottocategoriaBean() ; 		 
	                    		Iterator<SottocategoriaBean> iterator = lista.iterator() ; 
	                   			while(iterator.hasNext()){
	                   				name = iterator.next() ; 
	                   				if(p.getID_sottocategoria().equals(name.getNome())){	
	                   			%>
	                   					<option selected="selected" value = "<%= name.getNome()  %>"> <%= name.getNome()  %> </option>
	                   			<%
	                   				}else{
	                   			%>
	               					<option value = "<%= name.getNome()  %>"> <%= name.getNome()  %> </option>
	               				<%
	                   				}
	                   			} 
	                   			%>
						</select><br>
						<br/><input type="submit" value="Modifica Prodotto"><br>
	                </form>
	        </div>
			<%
		}else{
			response.sendRedirect("invalidLogin.jsp"); //error page	
		}
		%>
</body>
</html>
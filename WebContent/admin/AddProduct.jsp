<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.SottocategoriaBean"
    import = "model.SottocategoriaDAO" 
    import = "java.util.*"
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

<meta charset="ISO-8859-1">
<title>Aggiunta Prodotto</title>
</head>
<body>
        <%
        if(session.getAttribute("currentSessionAdmin") != null){
        	%>
        		<div>
		             <h3>Aggiungi un nuovo prodotto</h3><br>
		                <form action="../Aggiungi" method="POST">
		                    <span>Inserisci l'id del prodotto:</span>
		                    <input type="text" name="id" placeholder="A1"><br><br>
		                    <span>Inserisci il nome del prodotto</span>
		                    <input type="text" name="nome" placeholder="ES. Burro d'arachidi"><br><br>
		                    <span>Inserisci la descrizone del prodotto</span>
		                    <textarea name="descrizione" rows="4" cols="50"></textarea><br><br>
		                    <span>Inserisci la lista di ingredienti del prodotto</span>
		                    <textarea name="ingredienti" rows="4" cols="50"></textarea><br><br>
		                    <span>Inserisci i valori nutrizionali : </span>
		                    <textarea rows="4" cols="30" id="valori_nutrizionali" name = "valori_nutrizionali" placeholder=""></textarea>
		     				<br><br>
		     				<span>IVA applicata sul prodotto</span>
		     				<input type="text" name="iva" placeholder="iva"><br><br>
		     				
		     				<span>Il prodotto deve essere mostrato nel catalogo?</span>
		     				<select name="cancellato" id="cancellato">
		     					<option value="0">SI</option>
		     					<option value="1">NO</option>
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
		                   				name = iterator.next() ; %>
		                   				<option value = "<%= name.getNome()  %>"> <%= name.getNome()  %> </option>
		                   			<%
		                   			} %>
							</select><br>
							
							
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
				            <input type="text" name='qta' placeholder="Quantità disponibile"><br><br>
				            <span>Prezzo</span>
				            <input type="text" name='prezzo' placeholder="Prezzo"><br><br>
				            <span>Prezzo Scontato</span>
				            <input type="text" name='prezzoScontato' placeholder="Prezzo Scontato"><br><br>
									
							
							<br/><input type="submit" value="Aggiungi Prodotto"><br>
		                </form>
		        </div>
        	<%
        }else{
        	response.sendRedirect("invalidLogin.jsp"); //error page
        }
        %>
</body>
</html>
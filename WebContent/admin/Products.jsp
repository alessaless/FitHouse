<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.SottocategoriaBean"
    import = "model.ProdottoBean"
    import = "model.ProdottoDAO"
    import = "model.SottocategoriaDAO" 
    import = "java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="admin.css ">
<meta charset="ISO-8859-1">
<title>Aggiunta Prodotto</title>
</head>
<body>
	<%
		
		if(request.getParameterMap().containsKey("aggiunto")){
			if(request.getParameter("aggiunto").equals("si")){
				%>
					<div class="containerMessaggio">
						<div id="messaggioConferma">
							<img src="../images/check.gif" class="confermaMessaggioGif">
							<input type="image" src="../images/close.png" class="chiudiMessaggio" onclick="nascondiMessaggio()">
							<span>Prodotto aggiunto correttamente</span>
						</div>
					</div>
				<%
			}
		}
		
	%>
	<h5>Prodotti presenti nel catalogo, seleziona quello da modificare per aprire la sua scheda: </h5>
	<%
		ProdottoBean p = new ProdottoBean();
		ProdottoDAO p1 = new ProdottoDAO();
		ArrayList<ProdottoBean> lista = (ArrayList<ProdottoBean>) p1.doRetrieveAll(null) ;
		Iterator<ProdottoBean> i = lista.iterator();
		if(session.getAttribute("currentSessionAdmin") != null){
			%>
				<table>
					<thead>
						<tr>
							<th style='width: 80%; font-size:25px; font-weight:bolder;'>PRODOTTO</th>
							<th style='font-weight:bolder; font-size:25px;'>MODIFICA | MOLE | GUSTO</th>
						</tr>
					</thead>
					<tbody>
						<%
							while(i.hasNext()){
								p = i.next();
								%>
									<tr>
										<th><%= p.getNome() %></th>
										<th>
											<a href='modProd.jsp?prod=<%= p.getID_prod() %>'>
												<img src='../images/edit.png' style='width:40px; margin-right:20px; height:auto;'>
											</a>
											<a href='dosaggio.jsp?prod=<%= p.getID_prod() %>'>
												<img src='../images/bilancia.png' style='width:40px; margin-right:20px; height:auto;'>
											</a>
										</th>
									</tr>
								<%
							}
						%>    
					</tbody>
				</table>
			<%
		}else{
			response.sendRedirect("invalidLogin.jsp"); //error page
		}
		%>
			
			<script>
				function nascondiMessaggio(){
					document.getElementById("messaggioConferma").style.display = "none";
				}
			</script> 
</body>
</html>
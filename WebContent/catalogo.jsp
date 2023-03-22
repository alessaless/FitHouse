<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.UserDAO"
    import="java.sql.*"
    import="model.*"
    import="java.util.Collection"
    import="java.util.ArrayList"
    import="java.util.Iterator"
    import="java.net.URLEncoder"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogo</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="header.jsp" />  		
	<% 
			// Otteniamo una connessione dal pool di connessioni
			Connection con = DriverManagerConnectionPool.getConnection();
			// otteniamo l'array list dei prodotti restituiti dal db
			ProdottoDAO prodotti = new ProdottoDAO();
			ArrayList<ProdottoBean> rs = new ArrayList<>();
			rs = (ArrayList<ProdottoBean>)prodotti.doRetrieveAll(null); 
			%>
				<br>
				<h1 style='text-align:center;'>PRODOTTI</h1>
				<div class='contenitoreProdotti'>
					<%
						
						ProdottoBean prod;
						DoseBean ds;
						Iterator it = rs.iterator();
						while(it.hasNext()){
							// ogni prodotto
							prod = (ProdottoBean) it.next();
							// mi prendo il suo id
							String idProd = prod.getID_prod();
							// prendo la prima combinazione di gusto + mole che trovo nel db 
							DoseDAO dosi = new DoseDAO();
							ds = (DoseBean) dosi.doRetrieveByKey(idProd);
							// se nella mappa dei parametri c'è il parametro nomeProd
							// mi stampa solo i prodotti che contengono quella parola chiave e che non sono cancellati (oscurati) dal db
							if(request.getParameterMap().containsKey("nomeProd")){
								if(prod.getNome().toLowerCase().indexOf(request.getParameter("nomeProd").toLowerCase()) != -1  && !prod.isCancellato()){
									%>
									<!-- stampa del prodotto -->
									 <div class="prodotto">
										<%
											// carica la foto dal db se presente
											if(!ds.getUrl_foto().equals("")){
												%>
													<a href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">
														<img class="fotoProdotto" src="<%=ds.getUrl_foto()%>.jpg">
													</a>
												<%
											}else{
												// se per il prodotto non c'è una foto nel db usa una di default
												%>
										<a href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">													
														<img class="fotoProdotto" src="images/prodotti/fitHouse1.png">
													</a>
												<%
											}
										%>
										
										<span class="nomeProdotto">
											<!-- cliccando sul nome o sulla foto andiamo alla pagina jsp del prodotto, passandogli id, gusto e mole (della prima corrispondenza nel db) -->
											<a class="nomeprod" href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">
												<%= prod.getNome() %>
											</a>
										</span><br>
										<%
											// se non esiste un prezzo scontato per quel prodotto stampiamo il prezzo standard
											if(ds.getPrezzo_Scontato()==0.0){
												%>
													<span class="prezzo"><%= ds.getPrezzo()%> $</span><br>
												<%
											}else{
												// oppure stampiamo entrambi i prezzi con il prezzo standard barrato
												%>
													<span class="prezzoCancellato"><%= ds.getPrezzo()%> $</span>
													<span class="prezzo"><%=ds.getPrezzo_Scontato()%> $</span><br>
												<%
											}
										%>
									</div>
								<%
								}
							// se per ceercare il prodotto usiamo una categoria del menu
							// se nella mappa dei parametri c'è il parametro categ
							// stamperà solo i prodotti afferenti a quella categoria
							}else if(request.getParameterMap().containsKey("categ")){
								if(prod.getID_sottocategoria().equals(request.getParameter("categ")) && !prod.isCancellato()){
									%>
									 <div class="prodotto">
										<%
											if(!ds.getUrl_foto().equals("")){
												%>
													<a href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">
														<img class="fotoProdotto" src="<%=ds.getUrl_foto()%>.jpg">
													</a>
												<%
											}else{
												%>
										<a href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">													
														<img class="fotoProdotto" src="images/prodotti/fitHouse1.png">
													</a>
												<%
											}
										%>
										
										<span class="nomeProdotto">
											<a  class="nomeprod" href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">
												<%= prod.getNome() %>
											</a>
										</span><br>
										<%
											if(ds.getPrezzo_Scontato()==0.0){
												%>
													<span class="prezzo"><%= ds.getPrezzo()%> $</span><br>
												<%
											}else{
												%>
													<span class="prezzoCancellato"><%= ds.getPrezzo()%> $</span>
													<span class="prezzo"><%=ds.getPrezzo_Scontato()%> $</span><br>
												<%
											}
										%>
									</div>
								<%	
								}
							// altrimenti, non ho fatto nessuna ricerca quindi deve stamparli tutti (chiaramente solo quelli visibili)
							}else if(!prod.isCancellato()){
								%>
								 <div class="prodotto">
									<%
										if(!ds.getUrl_foto().equals("")){
											%>
												<a href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">
													<img class="fotoProdotto" src="<%=ds.getUrl_foto()%>.jpg">
												</a>
											<%
										}else{
											%>
									<a href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">													
													<img class="fotoProdotto" src="images/prodotti/fitHouse1.png">
												</a>
											<%
										}
									%>
									
									<span class="nomeProdotto">
										<a class="nomeprod" href = "prodotto.jsp?codiceprodotto=<%= idProd %>&gusto=<%= ds.getID_gusto() %>&mole=<%= ds.getID_mole() %>">
											<%= prod.getNome() %>
										</a>
									</span><br>
									<%
										if(ds.getPrezzo_Scontato()==0.0){
											%>
												<span class="prezzo"><%= ds.getPrezzo()%> $</span><br>
											<%
										}else{
											%>
												<span class="prezzoCancellato"><%= ds.getPrezzo()%> $</span>
												<span class="prezzo"><%=ds.getPrezzo_Scontato()%> $</span><br>
											<%
										}
									%>
								</div>
							<%
							}
						}
					%>
				</div>
			<%
			// rilascio la connessione
			DriverManagerConnectionPool.releaseConnection(con) ; %> 
   	<jsp:include page="footer.jsp"/>  	
</body>
</html>
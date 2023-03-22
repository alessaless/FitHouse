<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"
    import="java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com" >
<link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Raleway:wght@100;200;500;700&display=swap" rel="stylesheet">
</head>
<body>
	<a href = "catalogo.jsp">
		<img src='images/fitHouse2.png' class='logo'>
	</a>
	<div class="menuricerca">
		<form action="RicercaCatalogo" method="GET">
			<input type="text" id="textboxRicerca" name="ricerca" placeholder=" Ricerca..." class="ricerca">
			<input type="image" src="images/magnifier.png" alt="Submit" class="iconaMenu">
			<a href = "carrello.jsp" style="text-decoration: none;">
				<img src="images/cart.png" class="iconaMenu">
			</a>
			<a href="areautente.jsp">
				<img src="images/user.png" class="iconaMenu">			
			</a>
			<br>
			<div id='divProdottiDellaRicerca' class='divProdottiDellaRicerca'>
				
			</div>
		</form>
	</div>
	<div id="menuTelefono">
		<img class="iconaMenu closeBtn" src="images/close.png" onclick="nascondiMenu()">
		<h1 align='center'>Scegli una categoria</h1>
		<div class="vociMenuTelefono">
			<%
				CategoriaDAO catdaoTEL = new CategoriaDAO();
				ArrayList<CategoriaBean> catbeanTEL = (ArrayList<CategoriaBean>)catdaoTEL.doRetrieveAll(null);
				CategoriaBean tmpTEL;
				Iterator<CategoriaBean> it = catbeanTEL.iterator();
				int i = 0;
				while(it.hasNext()){
					tmpTEL = it.next();
					i++;
					%>
						<br><br>
						<span class="dropbtn" onclick="mostraSottoCat('<%= "sottocat" + i %>')"><%= tmpTEL.getNome() %></span>
					<%
					SottocategoriaDAO sotcatdaoTEL = new SottocategoriaDAO();
			    	ArrayList<SottocategoriaBean> sotcatbeanTEL = (ArrayList<SottocategoriaBean>)sotcatdaoTEL.doRetrieveAllByCat(tmpTEL.getNome());
			    	SottocategoriaBean tmp2;
			    	Iterator<SottocategoriaBean> it2 = sotcatbeanTEL.iterator();
			    	%>
			    		<div id='<%= "sottocat" + i %>' style="display:none;">
					    	<%
					    	while(it2.hasNext()){
					    		tmp2 = it2.next();
					    		%>
					    			<a class="nomeprod" href="catalogo.jsp?categ=<%= tmp2.getNome() %>" class="subCatTelefono"><li><%= tmp2.getNome() %></li></a>
					    		<%
					    	}
					    %>
					    </div>
					    <br><br>
					    <%
				}
			%>
			<br>
			<a href = "carrello.jsp" style="text-decoration: none;">
				<img src="images/cart.png" class="iconaMenu">
			</a>
			<a href="areautente.jsp">
				<img src="images/user.png" class="iconaMenu">			
			</a>
		</div>
	</div>	
	<nav class='menu'>
		<%
			CategoriaDAO catdao = new CategoriaDAO();
			ArrayList<CategoriaBean> catbean = (ArrayList<CategoriaBean>)catdao.doRetrieveAll(null);
			CategoriaBean tmp;
			Iterator<CategoriaBean> itTEL = catbean.iterator();
			while(itTEL.hasNext()){
				tmp = itTEL.next();
				%>
					<div class="dropdown">
					  <span class="dropbtn"><%= tmp.getNome() %></span>
					  <div class="dropdown-content">
					    <%
					    	SottocategoriaDAO sotcatdao = new SottocategoriaDAO();
					    	ArrayList<SottocategoriaBean> sotcatbean = (ArrayList<SottocategoriaBean>)sotcatdao.doRetrieveAllByCat(tmp.getNome());
					    	SottocategoriaBean tmp2;
					    	Iterator<SottocategoriaBean> it2 = sotcatbean.iterator();
					    	while(it2.hasNext()){
					    		tmp2 = it2.next();
					    		%>
					    			<a href="catalogo.jsp?categ=<%= tmp2.getNome() %>"><%= tmp2.getNome() %></a>
					    		<%
					    	}
					    %>
					  </div>
					</div>
				<%
			}
		%>
	</nav>
	<img src="images/menu.png" class="iconaMenu" onclick="mostraMenu()" id="hamburgerMenu">
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src='script.js'></script>
</html>
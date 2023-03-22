   <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
   %>
 
   <!DOCTYPE html>

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   Utente registrato  </title>
		<meta name="viewport" content="width=device-width">
         <style>
         
         	body{
         		font-family: Arial, Helvetica, sans-serif;
         	}
         	
         	button{
         		width:300px;
         		height:150px;
         	}
         	
         	a {
			  text-decoration: none;
			}
         </style>
      </head>
	
      <body>

         <% 
         	if(session.getAttribute("currentSessionAdmin") != null){
         		%>
         			<div>
         				<h2>Benvenuto Admin</h2>
         				<h5>Operazioni implementate: </h5>
         				<a href="AddProduct.jsp">
         					<button>NUOVO PRODOTTO</button>
         				</a>
         				<a href="Products.jsp">
         					<button>MODIFICA/ELIMINA PRODOTTO</button>
         				</a>
         				<a href="Ordini.jsp">
         					<button>VISUALIZZA LISTA ORDINI</button>
         				</a>
         				<a href="Utenti.jsp">
         					<button>VISUALIZZA LISTA UTENTI</button>
         				</a>
         			</div>
         		<%
         	}else{
		    	 response.sendRedirect("invalidLogin.jsp"); //error page
         	}
         %>
      </body>
	
   </html>

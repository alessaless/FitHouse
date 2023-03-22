   <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="model.UserBean"
   %>
 
   <!DOCTYPE html>

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   Utente registrato  </title>
         <link rel="icon" type="image/x-icon" href="images/fitHouse2.png">
      </head>
	
      <body>

         <% 
         	if(session.getAttribute("currentSessionUser") != null){
         			UserBean utente = (UserBean)(session.getAttribute("currentSessionUser"));
         		%>
         			Benvenuto utente registrato, il tuo nome è: <%= utente.getNome() + " " + utente.getCognome() %>
         		<%
         	}else{
		    	 response.sendRedirect("invalidLogin.jsp"); //error page
         	}
         %>
      </body>
	
   </html>

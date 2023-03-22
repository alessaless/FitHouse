<%@
	page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Login</title>
        <link rel="stylesheet" href="../style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    	<img src='../images/fitHouse2.png' class='logo'>
	   	<div id='contenitoreForm'> 
	   		<form action="../LoginServletAdmin" method='POST' id='form'>
		   		<h2>LOGIN AMMINISTRATORE</h2>
		   		<br> 
		        <input type="text" name="email" placeholder=" example@gmail.com" class='dati'><br><br>
		        <input type="password" name="password" placeholder=" Password" class='dati'><br><br>
		        <input type="submit" value="LOGIN" class='bottone'><input type="reset" value="RESET" class='bottone'>
	   		</form>
	   		<br>
	   	</div>
    </body>
</html>
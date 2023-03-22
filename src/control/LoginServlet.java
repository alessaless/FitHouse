package control;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			// prendo i parametri email e password dalla richiesta
		     String e = (request.getParameter("email"));
		     String p = (request.getParameter("password"));
		     // creo oggetti userBean e userDAO 
		     UserBean u;
		     UserDAO ud = new UserDAO();
		     // controllo se è registrato passando l'email al metodo isRegistrato di userDAO
		     if(!ud.isRegistrato(e)) {
		    	 // Se non è registrato fai il redirect al login
		    	 response.sendRedirect("LoginPage.jsp?nr=y");
		     }else {
		    	 
		    	 u = ud.doRetrieveByEmail(e);
			     // controllo la password se è la stessa
			     if (u.getPassword().equals(p))
			     {
			    	// se la password è corretta crea la sessione e setta l'attributo utente all'interno di essa, poi indirizzo al catalogo
			    	HttpSession session = request.getSession(true);
			    	session.setAttribute("currentSessionUser",u); 
			    	response.sendRedirect("catalogo.jsp"); //logged-in page
			     }
			     
			     else //password errata 
			    	 response.sendRedirect("LoginPage.jsp?ps=y"); //error page
		     }
		} 
		catch (Throwable theException)
		{
			System.out.println(theException); 
		}
	}
	
}
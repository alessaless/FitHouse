package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminBean;
import model.AdminDAO;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class LoginServletAdmin
 */
@WebServlet("/LoginServletAdmin")
public class LoginServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		
		     AdminBean user = new AdminBean();
		     AdminBean user2 = new AdminBean();
		     AdminDAO adminD = new AdminDAO();
		     user.setCodice_Accesso(request.getParameter("email"));
		     user.setPassword(request.getParameter("password"));
		     
		     user2 = adminD.doRetrieveByKey(request.getParameter("email"));
		     
		
		     if (user.getPassword().equals(user2.getPassword()))
		     {
		
		    	HttpSession session = request.getSession(true);
		    	session.setAttribute("currentSessionAdmin",user); 
		    	response.sendRedirect("admin/admin.jsp"); //logged-in page
		     }
		
		     else 
		    	 response.sendRedirect("admin/invalidLogin.jsp"); //error page 
		} 
	
	
		catch (Throwable theException)
		{
			System.out.println(theException); 
		}
	}

}

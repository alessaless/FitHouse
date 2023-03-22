package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class cambiaPassword
 */
@WebServlet("/cambiaPassword")
public class cambiaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cambiaPassword() {
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
		// TODO Auto-generated method stub
		String mail = request.getParameter("mailll");
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		UserDAO udao = new UserDAO();
		System.out.println(mail);
		try {
			UserBean ubean = udao.doRetrieveByEmail(mail);
			if(ubean.getPassword().equals(oldpass)) {
				ubean.setPassword(newpass);
				udao.doUpdate(ubean);
				request.getSession().setAttribute("currentSessionUser", ubean);
				response.sendRedirect("areautente.jsp");
			}else {
				// manda messaggio che la password è sbagliata
				response.sendRedirect("areautente.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

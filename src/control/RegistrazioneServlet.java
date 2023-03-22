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
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prendo i parametri necessari alla registrazione
		String n = (request.getParameter("nome"));
	    String c = (request.getParameter("cognome"));
		String e = (request.getParameter("email"));
	    String p = (request.getParameter("password"));
	    
	    UserDAO udao = new UserDAO();
	    // creo l'oggetto userbean e userdao
	    UserBean ubean = new UserBean(0, n, c, e, p);
	    try {
	    	// se la mail non è già presente nel db, lo salva (i controlli sui parametri li fa il client con js) nel db e fa il redirect al catalogo
			if(!udao.isRegistrato(e)) {
				udao.doSave(ubean);
				response.sendRedirect("catalogo.jsp");
			}else {
				// invia alla registrazione settando il parametro ar (already registered) 
				response.sendRedirect("Registrazione.jsp?ar=y");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}

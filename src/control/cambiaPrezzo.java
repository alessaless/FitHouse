package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

import model.DoseBean;
import model.DoseDAO;

/**
 * Servlet implementation class cambiaPrezzo
 */
@WebServlet("/cambiaPrezzo")
public class cambiaPrezzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cambiaPrezzo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DoseDAO doseD = new DoseDAO();
		DoseBean tmp;
		String id = request.getParameter("codProd");
		String gusto = request.getParameter("codGusto");
		String grammi = request.getParameter("codGrammatura");
		try {
			tmp = doseD.doRetrieveByKeyFULL(id, grammi, gusto);
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(tmp));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

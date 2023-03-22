package control;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DoseBean;
import model.DoseDAO;
import model.ProdottoBean;
import model.ProdottoDAO;

/**
 * Servlet implementation class Ricerca
 */
@WebServlet("/Ricerca")
public class Ricerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ricerca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO p = new ProdottoDAO();
		ProdottoBean tmp;
		DoseDAO d = new DoseDAO();
		DoseBean dose;
		try {
			// con il like di mysql
			ArrayList<ProdottoBean> prod = (ArrayList<ProdottoBean>) p.doRetriveByName(request.getParameter("nome"));
			Iterator<ProdottoBean> it = prod.iterator();
			response.setContentType("text/html");
			StringBuffer buff = new StringBuffer();
			while(it.hasNext()) {
				tmp=it.next();
				dose = d.doRetrieveByKey(tmp.getID_prod());
				buff.append(tmp.getNome());
				buff.append('#');
				buff.append(URLEncoder.encode(dose.getID_gusto(), "UTF-8"));
				buff.append('#');
				buff.append(URLEncoder.encode(dose.getID_mole(), "UTF-8"));
				buff.append('#');
				buff.append(URLEncoder.encode(dose.getID_prodotto(), "UTF-8"));
				buff.append(';');
			}
			response.getWriter().write(buff.toString());
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

package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DoseBean;
import model.DoseDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.ProdottoBean.TipoProdotto;

/**
 * Servlet implementation class AggiugiDose
 */
@WebServlet("/AggiugiDose")
public class AggiugiDose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiugiDose() {
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
		try
		{
			// l'admin aggiunge la dose
			DoseBean dose;
			dose = new DoseBean(request.getParameter("id"), request.getParameter("mole"), request.getParameter("gusto"), Integer.parseInt(request.getParameter("qta")), Double.parseDouble(request.getParameter("prezzo")), Double.parseDouble(request.getParameter("prezzoScontato")), "");
			DoseDAO toadd = new DoseDAO();
			toadd.doSave(dose);	
			response.sendRedirect("admin/Products.jsp");
		} 
	
	
		catch (Throwable theException)
		{
			System.out.println(theException); 
		}
		
	}

}

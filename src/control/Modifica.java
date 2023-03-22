package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoBean;
import model.ProdottoDAO;
import model.ProdottoBean.TipoProdotto;

/**
 * Servlet implementation class Modifica
 */
@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifica() {
        super();
        // TODO Auto-generated constructor stub
    }

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
					ProdottoBean prod;
					ProdottoDAO udao = new ProdottoDAO();
					ProdottoBean tmp = udao.doRetrieveByKey(request.getParameter("id"));
					boolean b;
					if(request.getParameter("cancellato").equals("0")) {
						b = false;
					}else {
						b = true;
					}
					
					if(request.getParameter("tipo") == "GRAMMI")
						prod = new ProdottoBean(request.getParameter("id"), request.getParameter("nome"), request.getParameter("descrizione"), request.getParameter("ingredienti"), request.getParameter("valori_nutrizionali"), b, TipoProdotto.GRAMMI, Integer.parseInt(request.getParameter("iva")), request.getParameter("sottocat"));
					else
						prod = new ProdottoBean(request.getParameter("id"), request.getParameter("nome"), request.getParameter("descrizione"), request.getParameter("ingredienti"), request.getParameter("valori_nutrizionali"), b, TipoProdotto.PEZZI, Integer.parseInt(request.getParameter("iva")), request.getParameter("sottocat"));
					ProdottoDAO toupdate = new ProdottoDAO();
					toupdate.doUpdate(prod);	
					response.sendRedirect("admin/admin.jsp");
				} 
			
			
				catch (Throwable theException)
				{
					System.out.println(theException); 
				}
	}


}

package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DoseBean;
import model.DoseDAO;
import model.ProdottoBean;
import model.ProdottoBean.TipoProdotto;
import model.ProdottoDAO;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class Aggiungi
 */
@WebServlet("/Aggiungi")
public class Aggiungi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aggiungi() {
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
					ProdottoBean prod;
					boolean b;
					if(request.getParameter("cancellato")=="0") {
						b = true;
					}else{
						b = false;
					}
					if(request.getParameter("tipo") == "GRAMMI")
						prod = new ProdottoBean(request.getParameter("id"), request.getParameter("nome"), request.getParameter("descrizione"), request.getParameter("ingredienti"), request.getParameter("valori_nutrizionali"), b, TipoProdotto.GRAMMI, Integer.parseInt(request.getParameter("iva")), request.getParameter("sottocat"));
					else
						prod = new ProdottoBean(request.getParameter("id"), request.getParameter("nome"), request.getParameter("descrizione"), request.getParameter("ingredienti"), request.getParameter("valori_nutrizionali"), b, TipoProdotto.PEZZI, Integer.parseInt(request.getParameter("iva")), request.getParameter("sottocat"));
					ProdottoDAO toadd = new ProdottoDAO();
					toadd.doSave(prod);	
					//response.sendRedirect("admin/AddProduct.jsp");
					try
					{
						DoseBean dose;
						dose = new DoseBean(request.getParameter("id"), request.getParameter("mole"), request.getParameter("gusto"), Integer.parseInt(request.getParameter("qta")), Double.parseDouble(request.getParameter("prezzo")), Double.parseDouble(request.getParameter("prezzoScontato")), "");
						DoseDAO toadd1 = new DoseDAO();
						toadd1.doSave(dose);	
						response.sendRedirect("admin/Products.jsp?aggiunto=si");
					} 
				
				
					catch (Throwable theException)
					{
						System.out.println(theException); 
					}
					
					
				} 
			
			
				catch (Throwable theException)
				{
					System.out.println(theException); 
				}
	}

}

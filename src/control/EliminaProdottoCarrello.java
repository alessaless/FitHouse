package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.DoseBean;
import model.DoseDAO;

@WebServlet("/EliminaProdottoCarrello")
public class EliminaProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EliminaProdottoCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prendo il carrello dalla sessione se esiste
		HttpSession sessione = request.getSession(false) ; 
		if(sessione == null)
			response.sendRedirect("catalogo.jsp");
		Carrello carrello = (Carrello) sessione.getAttribute("carrello") ; 
		DoseDAO daoDose = new DoseDAO(); 
		DoseBean dose = new DoseBean(); 
		//prendi la dose dal db tramite la combo dei tre parametri
		try {
			dose = daoDose.doRetrieveByKeyFULL(request.getParameter("ID_prod"),request.getParameter("ID_mole"), request.getParameter("ID_gusto")) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// rimuovo il prodotto dal carrello
		carrello.removeProdotto(dose);
		// se il carrello è vuoto lo metto a null, poi setto l'attributo e faccio il redirect al carrello
		if(carrello.getProdotti().isEmpty())
			carrello = null ; 
		sessione.setAttribute("carrello",carrello);
		response.sendRedirect("carrello.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CarrelloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		HttpSession session = request.getSession(true) ;
		Carrello carrello = new Carrello() ;
		// se no è presente gia nella sessione l'atrtibuto carrello, lo crea
		if(session.getAttribute("carrello") != null)
			carrello = (Carrello) session.getAttribute("carrello") ;
		
		DoseDAO daodose = new DoseDAO() ; 
		DoseBean dose = new DoseBean() ;
		
		// ottengo il dosebean giusto da aggiungere al carrello tramite i 3 parametri (id, mole, gusto)
		try {
			dose = daodose.doRetrieveByKeyFULL(request.getParameter("ID_prod"),request.getParameter("ID_mole"),request.getParameter("ID_gusto")) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// lo metto nell'oggetto carrello con la quantità richiesta, poi setto l' attributo della sessione
		carrello.addProdotto(dose,Integer.parseInt(request.getParameter("quantitaRichiesta"))) ; 
		session.setAttribute("carrello", carrello);
		// faccio il redirect al catalogo
		response.sendRedirect("catalogo.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Carrello;
import model.DoseBean;
import model.DoseDAO;

@WebServlet("/IncrementaQuantitaCarrello")
public class IncrementaQuantitaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IncrementaQuantitaCarrello() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prendo il carrello dalla sessione se esiste
		HttpSession session = request.getSession(true) ;
		Carrello carrello = new Carrello() ;
		
		if(session.getAttribute("carrello") != null)
			carrello = (Carrello) session.getAttribute("carrello") ;
		
		DoseDAO daodose = new DoseDAO() ; 
		DoseBean dose = new DoseBean() ;
		//prendi la dose dal db tramite la combo dei tre parametri
		try {
			dose = daodose.doRetrieveByKeyFULL(request.getParameter("ID_prod"),request.getParameter("ID_mole"),request.getParameter("ID_gusto")) ;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// incremento la quantità nel carrello e restituisco con un oggetto json il totale (serve poi in ajax)
		carrello.Incrementa(dose); 
		session.setAttribute("carrello", carrello);

		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(carrello.GetTotale()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

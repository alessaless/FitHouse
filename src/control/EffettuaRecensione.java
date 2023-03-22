package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecensioneBean;
import model.RecensioneDAO;

/**
 * Servlet implementation class EffettuaRecensione
 */
@WebServlet("/EffettuaRecensione")
public class EffettuaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EffettuaRecensione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response) ; 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//creo il bean della recensione con i parametri della request
		RecensioneDAO DAOrec = new RecensioneDAO() ; 
		RecensioneBean recensione = new RecensioneBean(0,Integer.parseInt(request.getParameter("valutazione")),request.getParameter("titolo"),request.getParameter("rec"),
				new Date(),request.getParameter("id_prod"),Integer.parseInt(request.getParameter("id_utente")),request.getParameter("nome")) ; 
		
		try {
			// lo salvo nel db con la doSave
			DAOrec.doSave(recensione) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// redirect al prodotto recensito
		response.sendRedirect("prodotto.jsp?codiceprodotto="+request.getParameter("id_prod")+"&gusto="+request.getParameter("gusto")+"&mole="+request.getParameter("mole"));
	}

}

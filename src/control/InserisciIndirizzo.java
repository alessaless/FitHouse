package control;

import java.io.IOException;
import java.sql.SQLException;

import model.* ; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserisciIndirizzo
 */
@WebServlet("/InserisciIndirizzo")
public class InserisciIndirizzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciIndirizzo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response) ; 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IndirizzoDAO daoInd = new IndirizzoDAO() ; 
		
		String nome = request.getParameter("nome") ; 
		String cognome = request.getParameter("cognome") ; 
		String tel = request.getParameter("telefono") ; 
		String citta = request.getParameter("citta") ; 
		String prov = request.getParameter("provincia") ; 
		String via = request.getParameter("indirizzo") ;
		String nazione = request.getParameter("nazione") ;
		int cap = Integer.parseInt(request.getParameter("CAP")) ;
		String note = request.getParameter("note") ; 
		int id = Integer.parseInt(request.getParameter("id_utente")) ; 
		//System.out.println(nome +" "+ cognome+" "+ tel+" "+ citta+" "+ nazione+" "+ cap+" "+ note+" "+ id);
		IndirizzoBean indirizzo = new IndirizzoBean(0,nome,cognome,tel,citta,prov,via,nazione,cap,note,id) ; 
		
		
		try {
			daoInd.doSave(indirizzo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(request.getParameterMap().containsKey("fromAreaUtente")) {
			response.sendRedirect("areautente.jsp");
		}else {
			response.sendRedirect("riepilogo.jsp");
		}
	}

}

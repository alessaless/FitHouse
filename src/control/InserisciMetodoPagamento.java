package control;

import java.io.IOException;
import java.sql.SQLException;

import model.* ; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InserisciMetodoPagamento")
public class InserisciMetodoPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InserisciMetodoPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response) ; 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MetodiPagamentoDAO daomet = new MetodiPagamentoDAO() ; 
		UtilizzaDAO daoutil = new UtilizzaDAO() ; 
		UtilizzaBean util ;
		
		MetodiPagamentoBean metodo = new MetodiPagamentoBean(request.getParameter("numerodicarta"),request.getParameter("proprietario"),request.getParameter("scadenza"),
				Integer.parseInt(request.getParameter("CVV"))) ; 
		util = new UtilizzaBean(Integer.parseInt(request.getParameter("ID_utente")),request.getParameter("numerodicarta")) ; 
		
		try {
			daomet.doSave(metodo);
			daoutil.doSave(util);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(request.getParameter("from").equals("riepilogo"))
			response.sendRedirect("riepilogo.jsp");
		else
			response.sendRedirect("metodipagamento.jsp");
	}
}

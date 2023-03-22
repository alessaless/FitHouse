package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.ComprendeBean;
import model.ComprendeDAO;
import model.DoseBean;
import model.IndirizzoBean;
import model.IndirizzoDAO;
import model.MetodiPagamentoBean;
import model.MetodiPagamentoDAO;
import model.OrdineBean;
import model.OrdineDAO;

/**
 * Servlet implementation class EffettuaOrdineGuest
 */
@WebServlet("/EffettuaOrdineGuest")
public class EffettuaOrdineGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EffettuaOrdineGuest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response) ; 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineDAO daoOrd = new OrdineDAO() ;
		IndirizzoDAO daoInd = new IndirizzoDAO() ;
		OrdineBean ordine = new OrdineBean() ; 
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello") ;
		Iterator<DoseBean> prodotto = carrello.getProdotti().keySet().iterator() ; //prendo i prodotti dal carrello
		DoseBean dose = new DoseBean() ;
		ComprendeDAO DAOcomprende = new ComprendeDAO() ; 
		
		//dalla richiesta ho passato l'indirizzo di spedizione usata e la memorizzo nel db come indirizzo effettivo
		IndirizzoBean indirizzo = new IndirizzoBean(0,request.getParameter("nome"),request.getParameter("cognome"),request.getParameter("telefono"),request.getParameter("citta"),
				request.getParameter("provincia"),request.getParameter("indirizzo"),request.getParameter("nazione"),Integer.parseInt(request.getParameter("CAP")),request.getParameter("note"),-1) ;
		int id_indirizzo = 0 ; 
		
		try {
			System.out.println(daoInd.Equal(indirizzo)); 
			if(daoInd.Equal(indirizzo) == false ) { //se l'indirizzo non è già presente nel db (per evitare ridondanze di istanze uguali)
				try {
					id_indirizzo = daoInd.GetLastInsertId(indirizzo) ; //inserisco l'indirizzo nel db facendomi restituire l'id in modo da poterlo associare all'ordine
					System.out.println(id_indirizzo) ; 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}else //se esiste già mi faccio restituire l'id
				id_indirizzo = daoInd.GetKey(indirizzo) ; 
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			
		int fattura = -100 ; //per stabilire il numero della fattura
		
		java.util.Date data = new Date() ; 
		java.sql.Date sqlPackageDate= new java.sql.Date(data.getTime() );
		
		
		try {
			fattura = daoOrd.CountFatture()+1 ; //stabilisco il numero della fattura da associare 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	
		try {
			System.out.println("ciao");
			ordine = new OrdineBean(0,sqlPackageDate,request.getParameter("numerodicarta"),request.getParameter("proprietario"),request.getParameter("email"),Double.parseDouble(request.getParameter("totale")),fattura,"In elaborazione",id_indirizzo,-1);
		} catch (NumberFormatException e1) {
		}
		
		double prezzo ; 
		try {
			int id_ordine = daoOrd.GetLastInsertId(ordine) ;
			int quantita ; 
			
			//associo ogni prodotto all'ordine appena effettuato in modo che posso conoscere cosa ho acquistato in un ordine (tabella Comprende nel db)
			while(prodotto.hasNext()) {
				dose = prodotto.next() ;
				quantita = carrello.getProdotti().get(dose) ;
				if(dose.getPrezzo_Scontato() != 0.0)
					prezzo = dose.getPrezzo_Scontato() ; 
				else
					prezzo = dose.getPrezzo() ; 
				DAOcomprende.doSave(new ComprendeBean(dose.getID_prodotto(),id_ordine,dose.getID_mole(),dose.getID_gusto(),prezzo,dose.getUrl_foto(),quantita));
			}
			// svuota il carrello
			request.getSession().setAttribute("carrello", null);
			response.sendRedirect("confermaOrdine.jsp?id="+id_ordine+"&email="+request.getParameter("email")); //pagina dove poter visualizzare la fattura.
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}

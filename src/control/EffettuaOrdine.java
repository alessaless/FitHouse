package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.ComprendeBean;
import model.ComprendeDAO;
import model.DoseBean;
import model.MetodiPagamentoBean;
import model.MetodiPagamentoDAO;
import model.OrdineBean;
import model.OrdineDAO;
import model.UserBean;
import java.util.* ; 
/**
 * Servlet implementation class EffettuaOrdine
 */
@WebServlet("/EffettuaOrdine")
public class EffettuaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EffettuaOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response) ;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineDAO daoOrd = new OrdineDAO() ;
		MetodiPagamentoDAO daoPag = new MetodiPagamentoDAO() ; 
		MetodiPagamentoBean pagamento = new MetodiPagamentoBean() ; 
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello") ; 
		Iterator<DoseBean> prodotto = carrello.getProdotti().keySet().iterator() ; //prendo tutti i prodotti dal carrello
		DoseBean dose = new DoseBean() ;
		ComprendeDAO DAOcomprende = new ComprendeDAO() ; 
		int fattura = -100 ; //per stabilire il numero della fattura
		
		java.util.Date data = new Date() ; 
		java.sql.Date sqlPackageDate= new java.sql.Date(data.getTime() );
		String numCarta =  request.getParameter("pagamento") ; //prendo il numero della carta
		try {
			pagamento = daoPag.doRetrieveByKey(numCarta) ; //di conseguenza mi faccio restituire l'oggetto metodo di pagamento in base al numero carta.
			fattura = daoOrd.CountFatture()+1 ; //stabilisco il numero della fattura per il nuovo ordine
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		//creo l'oggetto OrdineBean istanziandolo per poi inserirlo nel db
		OrdineBean ordine = new OrdineBean(0,sqlPackageDate,numCarta,pagamento.getIntestatario(),request.getParameter("email"),Double.parseDouble(request.getParameter("totale")),
				fattura,"In elaborazione",Integer.parseInt(request.getParameter("indirizzo")),Integer.parseInt(request.getParameter("user")));
		
		double prezzo ; 
		try {
			int id_ordine = daoOrd.GetLastInsertId(ordine) ; //inserisco l'ordine nel db e mi faccio restituire il suo id
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
			response.sendRedirect("confermaOrdine.jsp?id="+id_ordine); //pagina dove poter visualizzare la fattura.
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
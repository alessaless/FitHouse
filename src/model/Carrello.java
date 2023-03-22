package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// il carrello è un hashmap dove la chiave è la dose di un prodotto (ID, gusto, formato), mentre il valore è la quantità richiesta 
public class Carrello {
	private HashMap<DoseBean,Integer> prodotti = new HashMap<>() ;

	public Map<DoseBean, Integer> getProdotti() {
		return prodotti;
	}

	public void setProdotti(HashMap<DoseBean, Integer> prodotti) {
		this.prodotti = prodotti;
	}

	public Carrello(HashMap<DoseBean, Integer> prodotti) {
		super();
		this.prodotti = prodotti;
	}

	public Carrello() {
		super();  
	} 
	
	public void addProdotto(DoseBean prodotto, Integer quantitaRichiesta) {
		// se il prodotto è già presente deve solo aggiornare la quantità
		if(prodotti.containsKey(prodotto)) { 
			prodotti.put(prodotto, prodotti.get(prodotto)+quantitaRichiesta) ;
		}else
			prodotti.put(prodotto, quantitaRichiesta) ;
	}
	
	public void removeProdotto(DoseBean prodotto) {
		prodotti.remove(prodotto) ; 
	}
	
	public void removeQuantita(DoseBean prodotto, Integer quantitaRichiesta) {
		if(quantitaRichiesta <= 0)
			removeProdotto(prodotto) ; 
		else
			prodotti.put(prodotto, prodotti.get(prodotto) - quantitaRichiesta) ; 
	}
	
	public void Incrementa(DoseBean prodotto) {
		if(prodotti.containsKey(prodotto)) {
			prodotti.put(prodotto, prodotti.get(prodotto)+1) ; 
		}
	}
	
	public void Decrementa(DoseBean prodotto) {
		if(prodotti.containsKey(prodotto)) {
			int quan =  prodotti.get(prodotto) ; 
			
			if(quan == 1 )
				removeProdotto(prodotto) ; 
			else
				prodotti.put(prodotto,quan-1) ; 
			System.out.println(prodotti.toString());
		}
	}
	
	public double GetTotale() {
		double totale = 0.0 ; 
		
		for(DoseBean prodotto : prodotti.keySet()) {
			if(prodotto.getPrezzo_Scontato() == 0.0)
				totale += prodotto.getPrezzo() * prodotti.get(prodotto) ; 
			else
				totale += prodotto.getPrezzo_Scontato() * prodotti.get(prodotto) ; 
			
		}
		return totale ; 
	}
	
	@Override
	public String toString() {
		Set<DoseBean> prodott = prodotti.keySet() ;
		Iterator<DoseBean> iter = prodott.iterator() ; 
		DoseBean d ; 
		while(iter.hasNext()) {
			d = iter.next() ; 
		}
		return super.toString();
	}
}
package model;

public class DoseBean {
	private String ID_prodotto ; 
	private String ID_mole ; 
	private String ID_gusto ; 
	private int quantita_disp ; 
	private double prezzo ; 
	private double Prezzo_Scontato = 0.0 ; 
	private String Url_foto ;
	
	public DoseBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DoseBean(String iD_prodotto, String iD_mole, String iD_gusto, int quantita_disp, double prezzo,
			double prezzo_Scontato, String url_foto) {
		super();
		ID_prodotto = iD_prodotto;
		ID_mole = iD_mole;
		ID_gusto = iD_gusto;
		this.quantita_disp = quantita_disp;
		this.prezzo = prezzo;
		Prezzo_Scontato = prezzo_Scontato;
		Url_foto = url_foto;
	}
	
	public String getID_prodotto() {
		return ID_prodotto;
	}
	public void setID_prodotto(String iD_prodotto) {
		ID_prodotto = iD_prodotto;
	}
	public String getID_mole() {
		return ID_mole;
	}
	public void setID_mole(String iD_mole) {
		ID_mole = iD_mole;
	}
	public String getID_gusto() {
		return ID_gusto;
	}
	public void setID_gusto(String iD_gusto) {
		ID_gusto = iD_gusto;
	}
	public int getQuantita_disp() {
		return quantita_disp;
	}
	public void setQuantita_disp(int quantita_disp) {
		this.quantita_disp = quantita_disp;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public double getPrezzo_Scontato() {
		return Prezzo_Scontato;
	}
	public void setPrezzo_Scontato(double prezzo_Scontato) {
		Prezzo_Scontato = prezzo_Scontato;
	}
	public String getUrl_foto() {
		return Url_foto;
	}
	public void setUrl_foto(String url_foto) {
		Url_foto = url_foto;
	}
	
	@Override
	public boolean equals(Object b) { 
		if (!(b instanceof DoseBean))
	        return false;
		DoseBean bb = (DoseBean) b ; 
		return this.ID_prodotto.equals(bb.ID_prodotto) && this.ID_gusto.equals(bb.ID_gusto) && this.ID_mole.equals(bb.ID_mole) ; 
	}
	
	public int hashCode() {
	    int hash = this.ID_prodotto.hashCode();
	    hash = hash * 31 + this.ID_gusto.hashCode();
	    hash = hash * 31 + this.ID_mole.hashCode();
	    return hash;
	}
}

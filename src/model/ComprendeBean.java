package model;

public class ComprendeBean {
	private String ID_prod ; 
	private int Num_Ordine ; 
	private String Mole ; 
	private String Aroma ; 
	private double prezzo ; 
	private String Url_foto ;
	private int Quantita ; 
	
	public ComprendeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComprendeBean(String iD_prod, int num_Ordine, String mole, String aroma, double prezzo, String url_foto,
			int quantita) {
		super();
		ID_prod = iD_prod;
		Num_Ordine = num_Ordine;
		Mole = mole;
		Aroma = aroma;
		this.prezzo = prezzo;
		Url_foto = url_foto;
		Quantita = quantita;
	}
	
	public int getQuantita() {
		return Quantita;
	}
	public void setQuantita(int quantita) {
		Quantita = quantita;
	}
	public String getID_prod() {
		return ID_prod;
	}
	public void setID_prod(String iD_prod) {
		ID_prod = iD_prod;
	}
	public int getNum_Ordine() {
		return Num_Ordine;
	}
	public void setNum_Ordine(int num_Ordine) {
		Num_Ordine = num_Ordine;
	}
	public String getMole() {
		return Mole;
	}
	public void setMole(String mole) {
		Mole = mole;
	}
	public String getAroma() {
		return Aroma;
	}
	public void setAroma(String aroma) {
		Aroma = aroma;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getUrl_foto() {
		return Url_foto;
	}
	public void setUrl_foto(String url_foto) {
		Url_foto = url_foto;
	} 
	
	
	
}

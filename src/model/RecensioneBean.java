package model;

import java.util.Date;

public class RecensioneBean {
	private int id ; 
	private int voto ;
	private String titolo ; 
	private String Testo ; 
	private Date data ; 
	private String ID_prod ; 
	private int ID_utente ;
	private String Nome_u ; 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVoto() {
		return voto;
	}
	
	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getTesto() {
		return Testo;
	}
	
	public void setTesto(String testo) {
		Testo = testo;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		java.sql.Date sqlPackageDate
        = new java.sql.Date(data.getTime());
		this.data = sqlPackageDate ;
	}
	
	public String getID_prod() {
		return ID_prod;
	}
	
	public void setID_prod(String iD_prod) {
		ID_prod = iD_prod;
	}
	
	public int getID_utente() {
		return ID_utente;
	}
	
	public void setID_utente(int iD_utente) {
		ID_utente = iD_utente;
	}
	
	public String getNome_u() {
		return Nome_u;
	}

	public void setNome_u(String nome_u) {
		Nome_u = nome_u;
	}

	public RecensioneBean(int id, int voto, String titolo, String testo, Date data, String iD_prod, int iD_utente, String nome) {
		super();
		java.sql.Date sqlPackageDate
        = new java.sql.Date(data.getTime());
		
		this.id = id;
		this.voto = voto;
		this.titolo = titolo;
		Testo = testo;
		this.data = sqlPackageDate;
		ID_prod = iD_prod;
		ID_utente = iD_utente;
		Nome_u = nome ; 
	}
	public RecensioneBean() {
		super();
	} 
}


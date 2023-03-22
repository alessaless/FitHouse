package model;

import java.sql.Date; 

public class OrdineBean {
	private int Num_Ordine ; 
	private Date Data_Ordine ; 
	private String Num_Carta ; 
	private String intestatario ; 
	private String email ; 
	private double totale ; 
	private int Num_Fattura ; 
	private String stato ; 
	private int ID_indirizzo ; 
	private int ID_utente ;
	
	
	
	public OrdineBean() {
		super();
	}

	public OrdineBean(int ord,java.util.Date data_Ordine, String num_Carta, String intestatario, String email, double totale,
			int num_Fattura, String stato, int iD_indirizzo, int iD_utente) {
		super();
		
		java.sql.Date sqlPackageDate
        = new java.sql.Date(data_Ordine.getTime());
		Num_Ordine = ord ; 
		Data_Ordine = sqlPackageDate;
		Num_Carta = num_Carta;
		this.intestatario = intestatario;
		this.email = email;
		this.totale = totale;
		Num_Fattura = num_Fattura;
		this.stato = stato;
		ID_indirizzo = iD_indirizzo;
		ID_utente = iD_utente;
	}

	public int getNum_Ordine() {
		return Num_Ordine;
	}
	
	public java.sql.Date getData_Ordine() {
		return Data_Ordine ;
	}
	public void setData_Ordine(java.util.Date data_Ordine) {
		
		java.sql.Date sqlPackageDate
        = new java.sql.Date(data_Ordine.getTime());
		
		Data_Ordine = sqlPackageDate;
	}
	public String getNum_Carta() {
		return Num_Carta;
	}
	public void setNum_Carta(String num_Carta) {
		Num_Carta = num_Carta;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public int getNum_Fattura() {
		return Num_Fattura;
	}
	public void setNum_Fattura(int num_Fattura) {
		Num_Fattura = num_Fattura;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getID_indirizzo() {
		return ID_indirizzo;
	}
	public void setID_indirizzo(int iD_indirizzo) {
		ID_indirizzo = iD_indirizzo;
	}
	public int getID_utente() {
		return ID_utente;
	}
	public void setID_utente(int iD_utente) {
		ID_utente = iD_utente;
	} 
}

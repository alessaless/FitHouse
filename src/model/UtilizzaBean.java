package model;

public class UtilizzaBean {
	private int ID_utente ; 
	private String ID_carta ;
	
	public UtilizzaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UtilizzaBean(int iD_utente, String iD_carta) {
		super();
		ID_utente = iD_utente;
		ID_carta = iD_carta;
	}
	public int getID_utente() {
		return ID_utente;
	}
	public void setID_utente(int iD_utente) {
		ID_utente = iD_utente;
	}
	public String getID_carta() {
		return ID_carta;
	}
	public void setID_carta(String iD_carta) {
		ID_carta = iD_carta;
	} 
	
	
}

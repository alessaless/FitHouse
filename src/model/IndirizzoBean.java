package model;

public class IndirizzoBean {
	private int ID ; 
	private String Nome ; 
	private String Cognome ; 
	private String telefono ; 
	private String Citta ; 
	private String Provincia ; 
	private String Via ; 
	private String Nazione ; 
	private int CAP ; 
	private String Note ; 
	private int ID_utente ;
	
	
	public IndirizzoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IndirizzoBean(int iD, String nome, String cognome, String telefono, String citta, String provincia,
			String via, String nazione, int cAP, String note, int iD_utente) {
		super();
		ID = iD;
		Nome = nome;
		Cognome = cognome;
		this.telefono = telefono;
		Citta = citta;
		Provincia = provincia;
		Via = via;
		Nazione = nazione;
		CAP = cAP;
		Note = note;
		ID_utente = iD_utente;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCitta() {
		return Citta;
	}
	public void setCitta(String citta) {
		Citta = citta;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getVia() {
		return Via;
	}
	public void setVia(String via) {
		Via = via;
	}
	public String getNazione() {
		return Nazione;
	}
	public void setNazione(String nazione) {
		Nazione = nazione;
	}
	public int getCAP() {
		return CAP;
	}
	public void setCAP(int cAP) {
		CAP = cAP;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public int getID_utente() {
		return ID_utente;
	}
	public void setID_utente(int iD_utente) {
		ID_utente = iD_utente;
	} 
}

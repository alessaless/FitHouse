package model;

public class AdminBean {
	private String Password ; 
	private String Codice_Accesso ;
	
	
	
	public AdminBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminBean(String password, String codice_Accesso) {
		super();
		Password = password;
		Codice_Accesso = codice_Accesso;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCodice_Accesso() {
		return Codice_Accesso;
	}
	public void setCodice_Accesso(String codice_Accesso) {
		Codice_Accesso = codice_Accesso;
	} 
}

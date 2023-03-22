package model;

import java.sql.Date;

public class MetodiPagamentoBean {
	private String NCarta ; 
	private String Intestatario ; 
	private String Data_Scadenza ; 
	private int CVV ;
	public String getNCarta() {
		return NCarta;
	}
	public void setNCarta(String nCarta) {
		NCarta = nCarta;
	}
	public String getIntestatario() {
		return Intestatario;
	}
	public void setIntestatario(String intestatario) {
		Intestatario = intestatario;
	}
	public String getData_Scadenza() {
		return Data_Scadenza;
	}
	public void setData_Scadenza(String data_Scadenza) {
		Data_Scadenza = data_Scadenza;
	}
	public int getCVV() {
		return CVV;
	}
	public void setCVV(int cVV) {
		CVV = cVV;
	}
	public MetodiPagamentoBean(String nCarta, String intestatario, String data_Scadenza, int cVV) {
		super();
		NCarta = nCarta;
		Intestatario = intestatario;
		Data_Scadenza = data_Scadenza;
		CVV = cVV;
	}
	public MetodiPagamentoBean() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}

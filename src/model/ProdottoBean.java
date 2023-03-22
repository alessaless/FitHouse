package model;



public class ProdottoBean {
	
	public enum TipoProdotto {
		GRAMMI,
		PEZZI
	}
	
	private String ID_prod ; 
	private String nome ; 
	private String descrizione ; 
	private String ingredienti ; 
	private String Valori_Nutrizionali ; 
	private boolean cancellato = false ; 
	private TipoProdotto tipoProdotto ; 
	private int IVA = 10 ; 
	private String ID_sottocategoria ;
	
	
	
	public ProdottoBean(String iD_prod, String nome, String descrizione, String ingredienti, String valori_Nutrizionali,
			boolean cancellato, TipoProdotto tipoProdotto2, int iVA, String iD_sottocategoria) {
		super();
		ID_prod = iD_prod;
		this.nome = nome;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		Valori_Nutrizionali = valori_Nutrizionali;
		this.cancellato = cancellato;
		this.tipoProdotto = tipoProdotto2;
		IVA = iVA;
		ID_sottocategoria = iD_sottocategoria;
	}
	
	
	public ProdottoBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getID_prod() {
		return ID_prod;
	}
	public void setID_prod(String iD_prod) {
		ID_prod = iD_prod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}
	public String getValori_Nutrizionali() {
		return Valori_Nutrizionali;
	}
	public void setValori_Nutrizionali(String valori_Nutrizionali) {
		Valori_Nutrizionali = valori_Nutrizionali;
	}
	public boolean isCancellato() {
		return cancellato;
	}
	public void setCancellato(boolean cancellato) {
		this.cancellato = cancellato;
	}
	public TipoProdotto getTipoProdotto() {
		return tipoProdotto;
	}
	public void setTipoProdotto(TipoProdotto tipoProdotto) {
		this.tipoProdotto = tipoProdotto;
	}
	public int getIVA() {
		return IVA;
	}
	public void setIVA(int iVA) {
		IVA = iVA;
	}
	public String getID_sottocategoria() {
		return ID_sottocategoria;
	}
	public void setID_sottocategoria(String iD_sottocategoria) {
		ID_sottocategoria = iD_sottocategoria;
	} 
	
	
}

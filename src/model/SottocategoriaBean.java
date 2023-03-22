package model;

public class SottocategoriaBean {
	private String nome ; 
	private String ID_categoria ;
	
	
	
	public SottocategoriaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SottocategoriaBean(String nome, String iD_categoria) {
		super();
		this.nome = nome;
		ID_categoria = iD_categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getID_categoria() {
		return ID_categoria;
	}
	public void setID_categoria(String iD_categoria) {
		ID_categoria = iD_categoria;
	} 
	
	
}

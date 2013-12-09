package utils;

public class Docente {
	private int id;
	private String nome;
	private String cognome;
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public Docente() {
		// TODO Auto-generated constructor stub
	}
	public  String ToString(){
		return nome.concat(" ").concat(cognome);
		
	}
}

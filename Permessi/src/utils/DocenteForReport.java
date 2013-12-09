package utils;

public class DocenteForReport {
	private int Docente_id;
	private String Cognome;
	private String Nome;
	private java.util.Collection <OreData> OreChieste;
	private java.util.Collection <OreData> OreRecuperate;
	private int OreDaRecuperare;
	
	
	public void setOreDaRecuperare(int oreDaRecuperare) {
		OreDaRecuperare = oreDaRecuperare;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	public void setDocente_id(int docente_id) {
		Docente_id = docente_id;
	}
	
	
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	
	public int getOreDaRecuperare() {
		return OreDaRecuperare;
	}
	
	public String getNome() {
		return Nome;
	}
	public int getDocente_id() {
		return Docente_id;
	}
	
	public String getCognome() {
		return Cognome;
	}
	public DocenteForReport() {
		// TODO Auto-generated constructor stub
	}
	public void setOreRecuperate(java.util.Collection<OreData> oreRecuperate) {
		OreRecuperate = oreRecuperate;
	}
	public void setOreChieste(java.util.Collection<OreData> oreChieste) {
		OreChieste = oreChieste;
	}
	public java.util.Collection<OreData>getOreRecuperate() {
		return OreRecuperate;
	}
	public java.util.Collection<OreData> getOreChieste() {
		return OreChieste;
	}
}

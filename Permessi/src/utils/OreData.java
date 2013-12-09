package utils;

public class OreData {
	private int Id;
	private int DocenteId;
	private int Ore;
	private Object Data;
	

	public void setId(int id) {
		Id = id;
	}	
	public void setDocenteId(int docenteId) {
		DocenteId = docenteId;
	}
	public void setOre(int ore) {
		Ore = ore;
	}
	public void setData(Object object) {
		Data = object;
	}
	public int getId() {
		return Id;
	}
	public int getDocenteId() {
		return DocenteId;
	}
	public int getOre() {
		return Ore;
	}
	public Object getData() {
		return Data;
	}
	public OreData() {
		// TODO Auto-generated constructor stub
	}
}

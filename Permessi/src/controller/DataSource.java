package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import utils.Docente;
import utils.DocenteForReport;
import utils.OreDaRecuperare;
import utils.OreData;

import commons.DbHandler;

public class DataSource {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private DbHandler handler;
	private Docente D;
	private OreData OD;
	private static List <OreData> Chieste = new LinkedList<OreData>();
	private static List <OreData> Recuperate = new LinkedList<OreData>();
	private List <Docente> ListaDocenti = new LinkedList<Docente>();
	private List <OreDaRecuperare> ListaRecupero = new LinkedList<OreDaRecuperare>();
	private static java.util.Collection <DocenteForReport> listForReport = new ArrayList<DocenteForReport>();
	private static java.util.Collection <OreData> chiesteForReport = new ArrayList<OreData>();
	private static java.util.Collection <OreData> recuperateForReport = new ArrayList<OreData>();
	public List <Docente> getDocenti() throws SQLException{
		handler = new DbHandler();
		handler.dbConnection();
		handler.stat();
		String SQL = "select * from docente";
		Statement stat = handler.getStatement();
		ResultSet rs = stat.executeQuery(SQL);
		while (rs.next()){
			D = new Docente();
			D.setId(rs.getInt("docente_id"));
			D.setNome(rs.getString("nome"));
			D.setCognome(rs.getString("cognome"));
			ListaDocenti.add(D);
		}
		rs.close();
		stat.close();
		handler.getConnection().close();
		return ListaDocenti;
	}
	
	public List <OreData> getChieste(int id) throws SQLException{
		Chieste.clear();
		handler = new DbHandler();
		handler.dbConnection();
		handler.stat();
		PreparedStatement pstat = handler.getConnection().prepareStatement(
				"Select * from orechieste" +
				" WHERE docente_id = ? ");
		pstat.setInt(1, id);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()){
			OD = new OreData();
			OD.setId(rs.getInt("chieste_id"));
			OD.setDocenteId(rs.getInt("docente_id"));
			OD.setOre(rs.getInt("numeroore"));
			OD.setData(sdf.format(rs.getDate("datarichiesta")));
			Chieste.add(OD);
		}
		rs.close();
		pstat.close();
		handler.getConnection().close();
		return Chieste;
	}
	public void ChiesteForReport(int id) throws SQLException{
		chiesteForReport.clear();
		handler = new DbHandler();
		handler.dbConnection();
		handler.stat();
		PreparedStatement pstat = handler.getConnection().prepareStatement(
				"Select * from orechieste" +
				" WHERE docente_id = ? ");
		pstat.setInt(1, id);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()){
			OD = new OreData();
			OD.setId(rs.getInt("chieste_id"));
			OD.setDocenteId(rs.getInt("docente_id"));
			OD.setOre(rs.getInt("numeroore"));
			OD.setData(sdf.format(rs.getDate("datarichiesta")));
			chiesteForReport.add(OD);
		}
		rs.close();
		pstat.close();
		handler.getConnection().close();
	
	}
	public void RecuperateForReport(int id) throws SQLException{
		recuperateForReport.clear();
		handler = new DbHandler();
		handler.dbConnection();
		handler.stat();
		PreparedStatement pstat = handler.getConnection().prepareStatement(
				"Select * from orerecuperate" +
				" WHERE docente_id = ? ");
		pstat.setInt(1, id);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()){
			OD = new OreData();
			OD.setId(rs.getInt("recuperate_id"));
			OD.setDocenteId(rs.getInt("docente_id"));
			OD.setOre(rs.getInt("numeroore"));
			OD.setData(sdf.format(rs.getDate("datarecupero")));
			recuperateForReport.add(OD);
		}
		
		rs.close();
		pstat.close();
		handler.getConnection().close();
	}
	public List <OreData> getRecuperate(int id) throws SQLException{
		Recuperate.clear();
		handler = new DbHandler();
		handler.dbConnection();
		handler.stat();
		PreparedStatement pstat = handler.getConnection().prepareStatement(
				"Select * from orerecuperate" +
				" WHERE docente_id = ? ");
		pstat.setInt(1, id);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()){
			OD = new OreData();
			OD.setId(rs.getInt("recuperate_id"));
			OD.setDocenteId(rs.getInt("docente_id"));
			OD.setOre(rs.getInt("numeroore"));
			OD.setData(sdf.format(rs.getDate("datarecupero")));
			Recuperate.add(OD);
		}
		rs.close();
		pstat.close();
		handler.getConnection().close();
		return Recuperate;
	}
	public int getOreDaRecuperare(int id) throws SQLException{
		int daRecuperare = -1;
		int chieste = 0;
		int recuperate = 0;
		handler = new DbHandler();
		handler.dbConnection();
		PreparedStatement pstat = handler.getConnection().prepareStatement(
				"select numeroore from orechieste" +
				" WHERE docente_id = ?");
		pstat.setInt(1, id);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()){
			chieste = chieste + rs.getInt("numeroore");
		}
		rs.close();
		pstat.close();
		handler.getConnection().close();
		handler = new DbHandler();
		handler.dbConnection();
		pstat = handler.getConnection().prepareStatement(
				"select numeroore from orerecuperate" +
				" WHERE docente_id = ?");
		pstat.setInt(1, id);
		rs = pstat.executeQuery();
		while (rs.next()){
			recuperate = recuperate + rs.getInt("numeroore");
		}
		rs.close();
		pstat.close();
		handler.getConnection().close();
		daRecuperare = chieste - recuperate;
		return daRecuperare;
		
	}
	
	public List<OreDaRecuperare> getAllRecuperi() throws SQLException{
		ListaRecupero = new LinkedList<OreDaRecuperare>();
		List <Docente> DocList = new LinkedList <Docente>();
		DocList = getDocenti();
		for (Docente doc : DocList) {
			int ore = getOreDaRecuperare(doc.getId());
			if (ore!=0){
				OreDaRecuperare NCO  = new OreDaRecuperare();
				NCO.setNome(doc.getNome());
				NCO.setCognome(doc.getCognome());
				NCO.setOre(ore);
				ListaRecupero.add(NCO);
			}
		}
		return ListaRecupero;
	}
	
	public void insertDocente(Docente D) throws SQLException{
		DbHandler handler = new DbHandler();
		handler.dbConnection();
		PreparedStatement pstat = handler.getConnection().prepareStatement( "INSERT INTO docente (docente_id,nome,cognome)" +
			  		" VALUES (nextval('docente_id'),?,?)");
		  pstat.setString(1, D.getNome());
		  pstat.setString(2, D.getCognome());
		  ResultSet rs = pstat.executeQuery(); 
		  rs.close();
		  pstat.close();
		  handler.getConnection().close();
		}
	
	public void insertOreData(OreData OD, int i) throws SQLException{
		if(OD!=null){
		DbHandler handler = new DbHandler();
		handler.dbConnection();
		PreparedStatement pstat = handler.getConnection().prepareStatement("");
		if (i == 0){
			pstat = handler.getConnection().prepareStatement( "INSERT INTO orechieste (chieste_id,docente_id,numeroore,datarichiesta)" +
			  		" VALUES (nextval('chieste_id'),?,?,?)");
		}else if(i == 1){
			pstat = handler.getConnection().prepareStatement( "INSERT INTO orerecuperate (recuperate_id,docente_id,numeroore,datarecupero)" +
			  		" VALUES (nextval('recuperate_id'),?,?,?)");
		}
		  pstat.setInt(1, OD.getDocenteId());
		  pstat.setInt(2, OD.getOre());
		  pstat.setDate(3, (Date) OD.getData());
		  ResultSet rs = pstat.executeQuery(); 
		  rs.close();
		  pstat.close();
		  handler.getConnection().close();
		}
		}
	
	public void Delete() throws SQLException{
		DbHandler handler = new DbHandler();
		handler.dbConnection();
		handler.stat();
		String Sql = "Delete from docente *";
		Statement stat = handler.getStatement();
		ResultSet rs = stat.executeQuery(Sql);
		rs.close();
		stat.close();
		handler.getConnection().close();
	}
	public void DeleteDocente(int id) throws SQLException{
		DbHandler handler = new DbHandler();
		handler.dbConnection();
		PreparedStatement pstat = handler.getConnection().prepareStatement("");
		pstat = handler.getConnection().prepareStatement( "Delete from docente WHERE Docente_id = ?");
		pstat.setInt(1, id);
		ResultSet rs = pstat.executeQuery();
		rs.close();
		pstat.close();
		handler.getConnection().close();
	}
	public void DeleteOreData(OreData OD, int i) throws SQLException{
		DbHandler handler = new DbHandler();
		handler.dbConnection();
		PreparedStatement pstat = handler.getConnection().prepareStatement("");
		if (i == 0){
			pstat = handler.getConnection().prepareStatement( "DELETE FROM orechieste " +
			  		" WHERE chieste_id = ?");
		}else if(i == 1){
			pstat = handler.getConnection().prepareStatement( "DELETE FROM orerecuperate " +
			  		" WHERE recuperate_id = ?");
		}
		  pstat.setInt(1, OD.getId());
		  ResultSet rs = pstat.executeQuery(); 
		  rs.close();
		  pstat.close();
		  handler.getConnection().close();
		}
	
	public void ForReport(Docente D) throws SQLException{
		listForReport.clear();
		DocenteForReport DFR = new DocenteForReport();
		DFR.setDocente_id(D.getId());
		DFR.setNome(D.getNome());
		DFR.setCognome(D.getCognome());
		listForReport.add(DFR);
	}
	
	public static java.util.Collection <OreData> RecuperateForReport(){
		return recuperateForReport;
	}
	
	public static java.util.Collection <OreData> ChiesteForReport(){
		return chiesteForReport;
	}
	
	public static java.util.Collection <DocenteForReport> ForReport(){
		return listForReport;
	}

}

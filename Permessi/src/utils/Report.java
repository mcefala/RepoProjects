package utils;


import java.io.InputStream;
import java.sql.Connection;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



import commons.DbHandler;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Report {
	
	
	
	public String Stampa(String nomefile, int id) throws JRException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		 	JFileChooser chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle("Stampa Pdf");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);
		    
		    if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) { 	 
		            chooser.getSelectedFile();
		            String path = chooser.getSelectedFile().getAbsolutePath();
		    		DbHandler handler = new DbHandler();
		    		handler.dbConnection();
		    		Connection con = handler.getConnection();
		    		Map parameters = new HashMap();
		    		parameters.put("id", id);
		    		String filename = path + "/" + nomefile + ".pdf";
		    		InputStream is = getClass().getClassLoader().getResourceAsStream("RapportoPersonale.jasper");
		    		JasperPrint jasperPrint = JasperFillManager.fillReport(is,parameters,con);
		    		JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
		    		return "Rapporto stampato in "+path+"/"+nomefile+".pdf";
		        }
		      else {
		        return "Nessun Rapporto Stampato";
		        }
		    
		
		
	}
}

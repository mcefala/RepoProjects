package view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.UnsupportedLookAndFeelException;



import net.sf.jasperreports.engine.JRException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import controller.DataSource;

import utils.Docente;
import utils.DocenteForReport;
import utils.OreData;
import utils.Report;

import view.Inserimento;
import org.eclipse.wb.swt.SWTResourceManager;


public class PermessiView {

	protected Shell shell;
	private Table tablechieste;
	private Table tablerecupero;
	private static List<Docente> Docenti;
	private static List<OreData> OreChieste;
	private static List<OreData> OreRecuperate;
	private static DataSource data;
	private int index = -1;
	private Docente D1;
	private OreData OD;
	private PermessiView parent = this;
	private CCombo cmb;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			PermessiView window = new PermessiView();
			window.DocentiList();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(640, 480);
		shell.setText("Permessi Docenti");
		
		tablechieste = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tablechieste.setBounds(10, 114, 241, 258);
		tablechieste.setHeaderVisible(true);
		tablechieste.setLinesVisible(true);
		
		TableColumn tblclmnNumeroOre = new TableColumn(tablechieste, SWT.NONE);
		tblclmnNumeroOre.setWidth(100);
		tblclmnNumeroOre.setText("Numero Ore");
		
		TableColumn tblclmnData = new TableColumn(tablechieste, SWT.NONE);
		tblclmnData.setWidth(137);
		tblclmnData.setText("Data");
		
		tablerecupero = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tablerecupero.setBounds(373, 114, 241, 258);
		tablerecupero.setHeaderVisible(true);
		tablerecupero.setLinesVisible(true);
		
		TableColumn tblclmnNumeroOre_1 = new TableColumn(tablerecupero, SWT.NONE);
		tblclmnNumeroOre_1.setWidth(100);
		tblclmnNumeroOre_1.setText("Numero Ore");
		
		TableColumn tblclmnData_1 = new TableColumn(tablerecupero, SWT.NONE);
		tblclmnData_1.setWidth(137);
		tblclmnData_1.setText("Data");
		
		final Label labelore = new Label(shell, SWT.NONE);
		labelore.setBounds(107, 55, 62, 13);
		
		
		
		
		final CCombo combo = new CCombo(shell, SWT.BORDER);
		popCombo(combo);
		combo.setBounds(10, 10, 180, 20);		
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				OreChieste = new LinkedList<OreData>();
				System.out.println(combo.getText());
				index = combo.indexOf(combo.getText());
				System.out.println(index);
				D1 = Docenti.get(index);
				try {
					 data.ForReport(D1);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					data.ChiesteForReport(D1.getId());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					data.RecuperateForReport(D1.getId());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(D1.getId());
				tablechieste.removeAll();
				tablerecupero.removeAll();
				try {
					OreChieste = data.getChieste(D1.getId());
					for (OreData OD : OreChieste) {
						TableItem item = new TableItem(tablechieste, SWT.NONE);
						int c = 0;
						item.setText(c++, Integer.toString(OD.getOre()));
						item.setText(c++, (OD.getData().toString()));
						tablechieste.setRedraw(true);
					}
					OreChieste.clear();
					OreRecuperate = data.getRecuperate(D1.getId());
					for (OreData OD : OreRecuperate) {
						TableItem item = new TableItem(tablerecupero, SWT.NONE);
						int c = 0;
						item.setText(c++, Integer.toString(OD.getOre()));
						item.setText(c++, (OD.getData().toString()));
						tablerecupero.setRedraw(true);
					}
						OreRecuperate.clear();
					labelore.setText(String.valueOf(data.getOreDaRecuperare(D1.getId())));
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		
		
		cmb = combo;
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 55, 104, 20);
		lblNewLabel.setText("Ore da recuperare :");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		Menu menutable = new Menu(tablechieste);
		MenuItem item = new MenuItem(menutable, SWT.PUSH);
		item.setText("Cancella");
		tablechieste.setMenu(menutable);
		item.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event){
				if (tablechieste.getItemCount()>0){
					try {
						OreChieste = data.getChieste(D1.getId());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}

				try {
					data.DeleteOreData(OreChieste.get(tablechieste.getSelectionIndex()), 0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				try {
					labelore.setText(String.valueOf(data.getOreDaRecuperare(D1.getId())));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				tablechieste.removeAll();
				OreChieste.clear();
				try {
					OreChieste = data.getChieste(D1.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				for (OreData OD : OreChieste) {
					TableItem item = new TableItem(tablechieste, SWT.NONE);
					int c = 0;
					item.setText(c++, Integer.toString(OD.getOre()));
					item.setText(c++, (OD.getData().toString()));
					tablechieste.setRedraw(true);
				}
				OreChieste.clear();
				}
			
			}
		});
		Menu menutableR = new Menu(tablerecupero);
		MenuItem itemR = new MenuItem(menutableR, SWT.PUSH);
		itemR.setText("Cancella");
		tablerecupero.setMenu(menutableR);
		itemR.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event){
				if(tablerecupero.getItemCount()>0){
					try {
						OreRecuperate = data.getRecuperate(D1.getId());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}

				try {
					data.DeleteOreData(OreRecuperate.get(tablerecupero.getSelectionIndex()), 1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				try {
					labelore.setText(String.valueOf(data.getOreDaRecuperare(D1.getId())));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				tablerecupero.removeAll();
				OreRecuperate.clear();
				try {
					OreRecuperate = data.getRecuperate(D1.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				for (OreData OD : OreRecuperate) {
					TableItem item = new TableItem(tablerecupero, SWT.NONE);
					int c = 0;
					item.setText(c++, Integer.toString(OD.getOre()));
					item.setText(c++, (OD.getData().toString()));
					tablerecupero.setRedraw(true);
				}
				OreRecuperate.clear();
				}
			
			}
		});
		MenuItem mntmAggiungi = new MenuItem(menu_1, SWT.NONE);
		MenuItem mntmDelete = new MenuItem(menu_1,SWT.NONE);
		mntmDelete.setText("Elimina Docente");
		mntmAggiungi.setText("Aggiungi Docente");
		mntmDelete.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				try {
					data.DeleteDocente(D1.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				tablechieste.removeAll();
				tablerecupero.removeAll();
				Docenti.clear();
				labelore.setText("");
				DocentiList();
				combo.removeAll();
				popCombo(combo);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mntmAggiungi.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
		
				Inserimento ins = new Inserimento();
				ins.open(parent);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		
		MenuItem mntmDatabase = new MenuItem(menu, SWT.CASCADE);
		mntmDatabase.setText("Database");
		
		Menu menu_2 = new Menu(mntmDatabase);
		mntmDatabase.setMenu(menu_2);
		
		MenuItem mntmCancellaDatabase = new MenuItem(menu_2, SWT.NONE);
		mntmCancellaDatabase.setText("Cancella Database");
		mntmCancellaDatabase.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
				try {
					data.Delete();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
				tablechieste.removeAll();
				tablerecupero.removeAll();
				combo.removeAll();
				labelore.setText("");
				DocentiList();
				popCombo(combo);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		
		MenuItem mntmElenco = new MenuItem(menu, SWT.NONE);
		mntmElenco.setText("Elenco Recuperi");
		mntmElenco.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				ElencoRecupero elenco = new ElencoRecupero();
				elenco.open();
			}
			
		});
		
		MenuItem mntmCredits = new MenuItem(menu, SWT.NONE);
		mntmCredits.setText("Credits");
		mntmCredits.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Credits credits = new Credits();
				credits.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Label lblPermessiRichiesti = new Label(shell, SWT.NONE);
		lblPermessiRichiesti.setBounds(10, 95, 104, 13);
		lblPermessiRichiesti.setText("Ore Richieste :");
		
		Label lblPermessiRecuperati = new Label(shell, SWT.NONE);
		lblPermessiRecuperati.setBounds(381, 95, 104, 13);
		lblPermessiRecuperati.setText("Ore Recuperate :");
		
		final DateTime datachiesta = new DateTime(shell, SWT.BORDER);
		datachiesta.setBounds(136, 378, 90, 23);
		final Spinner orechieste = new Spinner(shell, SWT.BORDER);
		orechieste.setBounds(84, 380, 46, 21);
		
		Button AggiungiChiesto = new Button(shell, SWT.NONE);
		AggiungiChiesto.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (index != -1){
				OD = new OreData();
				OD.setDocenteId(Docenti.get(index).getId());
				OD.setOre(Integer.parseInt(orechieste.getText()));
				Date date = new Date(0,0,0);
				date.setDate(datachiesta.getDay());
				date.setMonth(datachiesta.getMonth());
				date.setYear(datachiesta.getYear()-1900);
				OD.setData(date);				
				tablechieste.removeAll();
									
					try {
						data.insertOreData(OD, 0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}
					
					try {
						labelore.setText(String.valueOf(data.getOreDaRecuperare(D1.getId())));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}		
					OreChieste.clear();
					try {
						OreChieste = data.getChieste(D1.getId());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}
					for (OreData OD : OreChieste) {
						TableItem item = new TableItem(tablechieste, SWT.NONE);
						int c = 0;
						item.setText(c++, Integer.toString(OD.getOre()));
						item.setText(c++, (OD.getData().toString()));
						tablechieste.setRedraw(true);
					}
					OreChieste.clear();
					
				
			}
			}
		});
		AggiungiChiesto.setBounds(10, 378, 68, 23);
		AggiungiChiesto.setText("Aggiungi");
		
		final Spinner orerecuperate = new Spinner(shell, SWT.BORDER);
		orerecuperate.setBounds(447, 380, 46, 21);
		
		final DateTime datarecupero = new DateTime(shell, SWT.BORDER);
		datarecupero.setBounds(499, 378, 90, 23);
		
		Button AggiungiRecupero = new Button(shell, SWT.NONE);
		AggiungiRecupero.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (index != -1){
				OD = new OreData();
				OD.setDocenteId(Docenti.get(index).getId());
				OD.setOre(Integer.parseInt(orerecuperate.getText()));
				Date date = new Date(0,0,0);
				date.setDate(datarecupero.getDay());
				date.setMonth(datarecupero.getMonth());
				date.setYear(datarecupero.getYear()-1900);
				OD.setData(date);				

				
					tablerecupero.removeAll();
					try {
						data.insertOreData(OD, 1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.toString());
					}
					try {
						labelore.setText(String.valueOf(data.getOreDaRecuperare(D1.getId())));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}
					OreRecuperate.clear();
					try {
						OreRecuperate = data.getRecuperate(D1.getId());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}
					for (OreData OD : OreRecuperate) {
						TableItem item = new TableItem(tablerecupero, SWT.NONE);
						int c = 0;
						item.setText(c++, Integer.toString(OD.getOre()));
						item.setText(c++, (OD.getData().toString()));
						tablerecupero.setRedraw(true);
					}
						OreRecuperate.clear();
				
			}
			}
		});
		AggiungiRecupero.setText("Aggiungi");
		AggiungiRecupero.setBounds(373, 378, 68, 23);
		final Label rapporto = new Label(shell, SWT.NONE);
		rapporto.setBounds(20, 411, 594, 13);
		Button Stampa = new Button(shell, SWT.NONE);
		Stampa.setImage(SWTResourceManager.getImage(PermessiView.class, "/Img/3.gif"));
		Stampa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Report report = new Report();
				
				
				try {
					try {
						rapporto.setText(report.Stampa((D1.getCognome().concat(" ").concat(D1.getNome())),D1.getId()));
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	
		Stampa.setBounds(274, 114, 77, 55);
		
		
		
		
		}
	
	public void DocentiList(){
		Docenti = new LinkedList<Docente>();
		data = new DataSource();
		try {
			Docenti = data.getDocenti();
		} catch (SQLException e1) {
		}
	}
	
	public void popCombo(CCombo combobox){
		for (Docente D : Docenti) {
			combobox.add(D.getCognome().concat(" ").concat(D.getNome()));
		}
	}
	
	public CCombo getcmb(){
		return cmb;
	}
}

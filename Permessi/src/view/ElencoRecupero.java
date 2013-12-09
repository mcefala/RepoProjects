package view;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

import controller.DataSource;

import utils.OreDaRecuperare;

public class ElencoRecupero {

	protected Shell shell;
	private Table table;
	private List <OreDaRecuperare> lista = new LinkedList<OreDaRecuperare>();
	private DataSource data;
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		data = new DataSource();
		try {
			lista = data.getAllRecuperi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		shell.setSize(298, 442);
		shell.setText("Elenco");
	
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 264, 384);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnDocente = new TableColumn(table, SWT.NONE);
		tblclmnDocente.setWidth(221);
		tblclmnDocente.setText("Docente");
		
		TableColumn tblclmnOre = new TableColumn(table, SWT.NONE);
		tblclmnOre.setWidth(39);
		tblclmnOre.setText("Ore");
		for (OreDaRecuperare Ora : lista) {
			TableItem item = new TableItem(table, SWT.NONE);
			int c = 0;
			item.setText(c++, Ora.getCognome().concat(" ").concat(Ora.getNome()));
			item.setText(c++, Integer.toString(Ora.getOre()));
			table.setRedraw(true);
		}
	}
}

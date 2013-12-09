package view;

import java.sql.SQLException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import controller.DataSource;

import utils.Docente;

public class Inserimento {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Docente D;
	private DataSource data;
	private PermessiView parent;
	
	/**
	 * Open the window.
	 */
	public void open(PermessiView view) {
		Display display = Display.getDefault();
		parent= view;
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
		shell.setSize(300, 200);
		shell.setText("Inserisci Docente");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				D = new Docente();
				D.setNome(text.getText());
				D.setCognome(text_1.getText());
				data = new DataSource();
				try {
					data.insertDocente(D);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
				parent.DocentiList();
				parent.getcmb().removeAll();
				parent.popCombo(parent.getcmb());
				shell.close();
				
			}
		});
		btnNewButton.setBounds(48, 128, 68, 23);
		btnNewButton.setText("Inserisci");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				shell.close();
			}
		});
		btnCancel.setBounds(174, 128, 68, 23);
		btnCancel.setText("Cancel!");
		
		Label lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(10, 30, 49, 13);
		lblNome.setText("Nome :");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 66, 60, 13);
		lblNewLabel.setText("Cognome :");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(71, 24, 171, 19);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(71, 60, 171, 19);

	}

}

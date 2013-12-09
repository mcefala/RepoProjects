package view;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

import commons.ResourceLoader;

public class Credits {

	protected Shell shell;
	private Image [] images = new Image [2];
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		images[0] = new Image(display,ResourceLoader.load("Img/1.jpg"));
		images[1] = new Image(display,ResourceLoader.load("Img/2.jpg"));
		int ind = (int) ((Math.random()*100) %2);
		shell.setSize(471, 504);
		shell.setText("Credits");
		Label lblMcefalagmailcom = new Label(shell, SWT.NONE);
		lblMcefalagmailcom.setBounds(10, 441, 435, 15);
		lblMcefalagmailcom.setText("Fatto da: M.cefala@gmail.com");
		Label lblImage = new Label(shell, SWT.NONE);
		lblImage.setBounds(10, 10, 435, 425);
		lblImage.setText("Image");
		lblImage.setImage(images[ind]);
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		
		
	}
}

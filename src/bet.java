import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class bet {

	/**
	 * Launch the application.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Display display = Display.getDefault();
		Shell shell = new Shell(SWT.CLOSE |SWT.MAX|SWT.MIN);
		shell.setSize(1380, 700);
		//shell.setSize(1000, 1200);
		shell.setText("菠菜明灯");
		shell.setLayout(new FillLayout());
		infomation lt=new infomation(shell,SWT.BORDER);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);  
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}

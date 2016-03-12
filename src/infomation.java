import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CLabel;

public class infomation extends Composite {
	private Table table;
	private  Connection con;
	private Table table_1;
	private Table table_2;
	private Table table_3;
	private Text text;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public infomation(Composite parent, int style)throws Exception {
		super(parent, SWT.NONE);
		this.setSize(1380, 700);
		setLayout(null);
		this.setBackgroundImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("back.png")));
		//this.setBackgroundMode(SWT.INHERIT_FORCE);
		//parent.setBackgroundImage();
		Class.forName("com.mysql.jdbc.Driver");
		String dburl="jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
		con=DriverManager.getConnection(dburl, "root", "");
		String query="USE bet";
		Statement stmt=con.createStatement();
		stmt.executeUpdate(query);
	    TableViewer tableViewer_1 = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
	    table_1 = tableViewer_1.getTable();
	    table_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseDoubleClick(MouseEvent e) {
	    		TableItem[] itemList =table_1.getItems();
	    		int choose=table_1.getSelectionIndex();
	    		String id=itemList[choose].getText(0);
	    		Shell s = table_1.getShell();  
                try {
					new predict(s,SWT.NONE,"process",Integer.parseInt(id),con,itemList[choose].getText(1),itemList[choose].getText(2));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               table_2.getParent().dispose();
                //b.getParent().dispose();  
                s.layout();  
	    	}
	    });
	    table_1.setLinesVisible(true);
	    table_1.setHeaderVisible(true);
	    table_1.setBounds(477, 188, 371, 480);
	    
	    TableColumn tableColumn_6 = new TableColumn(table_1, SWT.CENTER);
	    tableColumn_6.setResizable(false);
	    tableColumn_6.setWidth(50);
	    tableColumn_6.setText("ID");
	    
	    TableColumn tableColumn_7 = new TableColumn(table_1, SWT.CENTER);
	    tableColumn_7.setResizable(false);
	    tableColumn_7.setWidth(90);
	    tableColumn_7.setText("左队");
	    
	    TableColumn tableColumn_8 = new TableColumn(table_1, SWT.CENTER);
	    tableColumn_8.setResizable(false);
	    tableColumn_8.setWidth(90);
	    tableColumn_8.setText("右队");
	    
	    TableColumn tableColumn_9 = new TableColumn(table_1, SWT.CENTER);
	    tableColumn_9.setResizable(false);
	    tableColumn_9.setWidth(60);
	    tableColumn_9.setText("左队赔率");
	    
	    TableColumn tableColumn_10 = new TableColumn(table_1, SWT.CENTER);
	    tableColumn_10.setResizable(false);
	    tableColumn_10.setWidth(60);
	    tableColumn_10.setText("右队赔率");
	    
	    TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
	    table_2 = tableViewer.getTable();
	    table_2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseDoubleClick(MouseEvent e) {
	    		TableItem[] itemList =table_2.getItems();
	    		int choose=table_2.getSelectionIndex();
	    		String id=itemList[choose].getText(0);
	    		Shell s = table_2.getShell();  
                try {
					new predict(s, SWT.NONE,"record",Integer.parseInt(id),con,itemList[choose].getText(1),itemList[choose].getText(2));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               table_2.getParent().dispose();
                //b.getParent().dispose();  
               s.setLayout(new FillLayout(SWT.HORIZONTAL));
               s.layout();  
	    	}
	    });
	    table_2.setLinesVisible(true);
	    table_2.setHeaderVisible(true);
	    table_2.setBounds(10, 188, 461, 480);
	    
	    TableColumn tableColumn = new TableColumn(table_2, SWT.CENTER);
	    tableColumn.setWidth(50);
	    tableColumn.setText("ID");
	    tableColumn.setResizable(false);
	    
	    TableColumn tableColumn_1 = new TableColumn(table_2, SWT.CENTER);
	    tableColumn_1.setWidth(90);
	    tableColumn_1.setText("左队");
	    tableColumn_1.setResizable(false);
	    
	    TableColumn tableColumn_2 = new TableColumn(table_2, SWT.CENTER);
	    tableColumn_2.setWidth(90);
	    tableColumn_2.setText("右队");
	    tableColumn_2.setResizable(false);
	    
	    TableColumn tableColumn_3 = new TableColumn(table_2, SWT.CENTER);
	    tableColumn_3.setWidth(60);
	    tableColumn_3.setText("左队赔率");
	    tableColumn_3.setResizable(false);
	    
	    TableColumn tableColumn_4 = new TableColumn(table_2, SWT.CENTER);
	    tableColumn_4.setWidth(60);
	    tableColumn_4.setText("右队赔率");
	    tableColumn_4.setResizable(false);
	    
	    TableColumn tableColumn_5 = new TableColumn(table_2, SWT.CENTER);
	    tableColumn_5.setWidth(90);
	    tableColumn_5.setText("胜者");
	    tableColumn_5.setResizable(false);
	    TableViewer tableViewer_2 = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
	    table_3 = tableViewer_2.getTable();
	    table_3.setLinesVisible(true);
	    table_3.setHeaderVisible(true);
	    table_3.setBounds(854, 188, 501, 480);
	    
	    TableColumn tableColumn_11 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_11.setWidth(50);
	    tableColumn_11.setText("ID");
	    tableColumn_11.setResizable(false);
	    
	    TableColumn tableColumn_12 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_12.setWidth(90);
	    tableColumn_12.setText("左队");
	    tableColumn_12.setResizable(false);
	    
	    TableColumn tableColumn_13 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_13.setWidth(90);
	    tableColumn_13.setText("右队");
	    tableColumn_13.setResizable(false);
	    
	    TableColumn tableColumn_14 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_14.setWidth(60);
	    tableColumn_14.setText("左队赔率");
	    tableColumn_14.setResizable(false);
	    
	    TableColumn tableColumn_15 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_15.setWidth(60);
	    tableColumn_15.setText("右队赔率");
	    tableColumn_15.setResizable(false);
	    
	    TableColumn tableColumn_16 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_16.setWidth(130);
	    tableColumn_16.setText("截止时间");
	    tableColumn_16.setResizable(false);
	    table_3.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseDoubleClick(MouseEvent e) {
	    		TableItem[] itemList =table_3.getItems();
	    		int choose=table_3.getSelectionIndex();
	    		String id=itemList[choose].getText(0);
	    		Shell s = table_3.getShell();  
                try {
					new predict(s, SWT.NONE,"pre_process",Integer.parseInt(id),con,itemList[choose].getText(1),itemList[choose].getText(2));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               table_3.getParent().dispose();
                //b.getParent().dispose();  
               s.setLayout(new FillLayout(SWT.HORIZONTAL));
               s.layout();  
	    	}
	    });
	    
	    Button btnNewButton = new Button(this, SWT.NONE);
	    btnNewButton.setTouchEnabled(true);
	    btnNewButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
	    btnNewButton.setBounds(101, 140, 276, 47);
	    btnNewButton.setText("已结束的比赛");
	    
	    Button button = new Button(this, SWT.NONE);
	    button.setText("正在进行的比赛");
	    button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
	    button.setBounds(525, 140, 276, 47);
	    
	    Button button_1 = new Button(this, SWT.NONE);
	    button_1.setText("即将开始的比赛");
	    button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
	    button_1.setBounds(963, 140, 276, 47);
	    
	    text = new Text(this, SWT.BORDER);
	    text.setBounds(0, 0, 340, 105);
	    text.setBackgroundImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("logo.png")));
	    
	    CLabel label = new CLabel(this, SWT.CENTER|SWT.SHADOW_NONE);
	    label.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
	    label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 30, SWT.BOLD | SWT.ITALIC));
	    label.setBounds(506, 10, 501, 83);
	    label.setText("电子竞技没有菠菜！！");
	    
	    tableViewer_1.setContentProvider(new TableViewerContentProvider());  
	    tableViewer_1.setLabelProvider(new TableViewerLabelProvider()); 
	    Object data_1 = getSqldata("process");  
	    tableViewer_1.setInput(data_1);
	    
	    tableViewer.setContentProvider(new TableViewerContentProvider());  
	    tableViewer.setLabelProvider(new TableViewerLabelProvider()); 
	    Object data_2 = getSqldata("record");  
	    tableViewer.setInput(data_2);
	    
	    tableViewer_2.setContentProvider(new TableViewerContentProvider());  
	    tableViewer_2.setLabelProvider(new TableViewerLabelProvider()); 
	    Object data_3 = getSqldata("pre_process");  
	    tableViewer_2.setInput(data_3);
		
	}

	protected Object getSqldata(String name)throws Exception
	{
		String query="SELECT * FROM "+name+";";
		if(name.equals("record"))
			query="select * from record order by id desc";
			
		ResultSet rs=null;
		Statement stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		List list=new ArrayList(); 
		while(rs.next())
		{
			sqlrecord lt=new sqlrecord();
			int cnt=0;
			if(name.equals("process"))
				cnt=5;
			else 
				cnt=6;
			for(int i=1;i<=cnt;i++)
			{
				lt.set(i-1, rs.getString(i));
			}
			list.add(lt);
		}
		return list;
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

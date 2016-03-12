import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.swtchart.Chart;
import org.swtchart.IAxisSet;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesSet;
import org.swtchart.Chart;

import java.sql.Connection;
import java.sql.Statement;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class predict extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private int id;
	private String tabname;
	private Table table_1;
	private Table table_2;
	private Table table_3;
	private Connection con;
	private Chart chart;
	private Chart chart_1;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws Exception 
	 */
	public predict(Composite parent, int style,String table,int ID,Connection CON,String left,String right) throws Exception {
		super(parent, style);
		this.setBackgroundImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("3.png")));
		setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.NORMAL));
		setLayout(null);
		this.setSize(1380, 700);
		id=ID;
		tabname=table;
		con=CON;
		Composite lt=this;
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		text.setBounds(311, 39, 155, 52);
		text.setText(left);
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text_1.setBounds(920, 39, 155, 52);
		text_1.setText(right);
		text_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		
		Combo combo = new Combo(this, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		combo.setBounds(357, 208, 100, 29);
		combo.setItems(new String[] {"数据表", "折线图"});
		combo.addSelectionListener(new SelectionAdapter(){
		      public void widgetSelected(SelectionEvent e){
		        if (combo.getText().equals("数据表")) {
		        	if(chart!=null)
		        	{
		        		chart.setVisible(false);
		        		//chart=null;
		        	}
		    	    TableViewer tableViewer_1 = new TableViewer(lt, SWT.BORDER | SWT.FULL_SELECTION);
		    	    table_1 = tableViewer_1.getTable();
		    	    table_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",9 , SWT.NORMAL));
		    	    table_1.setLinesVisible(true);
		    	    table_1.setHeaderVisible(true);
		    	    table_1.setBounds(10, 250, 460, 450);
		    	    
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
		    	    
		    	    TableColumn tableColumn_11 = new TableColumn(table_1, SWT.CENTER);
		    	    tableColumn_11.setResizable(false);
		    	    tableColumn_11.setWidth(90);
		    	    tableColumn_11.setText("胜者");
		    	    tableViewer_1.setContentProvider(new TableViewerContentProvider());  
		    	    tableViewer_1.setLabelProvider(new TableViewerLabelProvider());
					try {
						Object data_1 = getSqldata("record",left);
			    	    tableViewer_1.setInput(data_1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	    
		        }
		        else if(combo.getText().equals("折线图")){
		        	if(table_1!=null)
		        	{
		        		table_1.setVisible(false);
		        		//table_2=null;
		        	}
		        	chart = new Chart(lt, SWT.NONE);
		        	List list=null;
		        	List ySeries=new ArrayList<>();
		        	try {
						list=(List) getSqldata("record",left);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	double lo=0,win=0;
		        	for(int i=0;i<list.size();i++)
		        	{
		        		sqlrecord myrecord=(sqlrecord)list.get(i);
		        		if(left.equals(myrecord.get(5)))
		        			win=win+1.0;
		        		else 
		        			lo=lo+1.0;
		        		ySeries.add(win/(win+lo));
		        	}
		        	double[] mySeries=new double[ySeries.size()];
		        	for(int i=0;i<ySeries.size();i++)
		        		mySeries[i]=(double)ySeries.get(i);
		        	ISeriesSet seriesSet = chart.getSeriesSet();
		        	ISeries series = seriesSet.createSeries(SeriesType.LINE, "胜率");
		        	series.setYSeries(mySeries);
		        	IAxisSet axisSet = chart.getAxisSet();
		        	axisSet.adjustRange();
		        	chart.setBounds(10, 250, 460, 450);
		        }
		      }
		});
		
		
		Combo combo_1 = new Combo(this, SWT.READ_ONLY);
		combo_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		combo_1.setBounds(898, 208, 100, 29);
		combo_1.setItems(new String[] {"数据表", "折线图"});
		combo_1.addSelectionListener(new SelectionAdapter(){
		      public void widgetSelected(SelectionEvent e){
		        if (combo_1.getText().equals("数据表")) {
			        if(chart_1!=null)
			        {
			        	chart_1.setVisible(false);
			        	//chart=null;
			        }
		    	    TableViewer tableViewer_2 = new TableViewer(lt, SWT.BORDER | SWT.FULL_SELECTION);
		    	    table_2 = tableViewer_2.getTable();
		    	    table_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.NORMAL));
		    	    table_2.setLinesVisible(true);
		    	    table_2.setHeaderVisible(true);
		    	    table_2.setBounds(900, 250, 460, 450);

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
		    	    tableViewer_2.setContentProvider(new TableViewerContentProvider());  
		    	    tableViewer_2.setLabelProvider(new TableViewerLabelProvider());
					try {
						Object data_2 = getSqldata("record",right);
			    	    tableViewer_2.setInput(data_2);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	    
		        }
		        else if(combo_1.getText().equals("折线图")){
		        	if(table_2!=null)
		        	{
		        		table_2.setVisible(false);
		        		//table_2=null;
		        	}
		        	chart_1 = new Chart(lt, SWT.NONE);
		        	List list=null;
		        	List ySeries=new ArrayList<>();
		        	try {
						list=(List) getSqldata("record",right);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	double lo=0,win=0;
		        	for(int i=0;i<list.size();i++)
		        	{
		        		sqlrecord myrecord=(sqlrecord)list.get(i);
		        		if(right.equals(myrecord.get(5)))
		        			win=win+1.0;
		        		else 
		        			lo=lo+1.0;
		        		ySeries.add(win/(win+lo));
		        	}
		        	double[] mySeries=new double[ySeries.size()];
		        	for(int i=0;i<ySeries.size();i++)
		        		mySeries[i]=(double)ySeries.get(i);
		        	ISeriesSet seriesSet = chart_1.getSeriesSet();
		        	ISeries series = seriesSet.createSeries(SeriesType.LINE, "胜率");
		        	series.setYSeries(mySeries);
		        	IAxisSet axisSet = chart_1.getAxisSet();
		        	axisSet.adjustRange();
		        	chart_1.setBounds(900, 250, 460, 450);
		        }
		      }
		});
		
		Button btnVs = new Button(this, SWT.NONE);
		btnVs.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.NORMAL));
		btnVs.setBounds(615, 28, 136, 81);
		btnVs.setText("VS");
		
		Label lblNewLabel = new Label(this, SWT.CENTER);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel.setBounds(295, 143, 136, 39);
		lblNewLabel.setText("预测胜利队伍：");
		
		Label label = new Label(this, SWT.CENTER);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label.setBounds(714, 143, 178, 39);
		label.setText("建议投入金额(刀)：");
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		text_2.setBounds(444, 137, 136, 42);
		
		
		text_3 = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		text_3.setBounds(898, 137, 110, 45);
		
		TableViewer tableViewer_3 = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
	    table_3 = tableViewer_3.getTable();
	    table_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.NORMAL));
	    table_3.setLinesVisible(true);
	    table_3.setHeaderVisible(true);
	    table_3.setBounds(492, 250, 380, 450);
	    
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
	    
	    TableColumn tableColumn_16 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_16.setWidth(90);
	    tableColumn_16.setText("胜者");
	    tableColumn_16.setResizable(false);
	    
	    TableColumn tableColumn_17 = new TableColumn(table_3, SWT.CENTER);
	    tableColumn_17.setWidth(50);
	    tableColumn_17.setText("赔率");
	    tableColumn_17.setResizable(false);
	    
	    Button button = new Button(this, SWT.CENTER);
	    button.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
	    button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
	    button.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseDown(MouseEvent e) {
	    		Shell s = button.getShell();  
                try {
					new infomation(s,SWT.NONE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                button.getParent().dispose();
                //b.getParent().dispose();  
                s.layout();
	    	}
	    });
	    button.setBounds(10, 10, 92, 42);
	    button.setText("返回");
	    
	    
	    tableViewer_3.setContentProvider(new TableViewerContentProvider());  
	    tableViewer_3.setLabelProvider(new TableViewerLabelProvider());
		
	    Object data_3 = getSqldata("record",left,right);  
	    tableViewer_3.setInput(data_3);

	    Do_Choose();
	}

	
	protected Object getSqldata(String name,String team)throws Exception
	{
		String query="SELECT * FROM "+name+" where leftname=\""+team+"\" or rightname=\""+team+"\"";
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
	
	protected Object getSqldata(String name,String teaml,String teamr)throws Exception
	{
		String query="SELECT * FROM "+name+" where (leftname=\""+teaml+"\" and rightname=\""+teamr+"\") or (leftname=\""+teamr+"\" and rightname=\""+teaml+"\")";
		ResultSet rs=null;
		Statement stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		List list=new ArrayList();
		while(rs.next())
		{
			sqlrecord lt=new sqlrecord();
			lt.set(0, rs.getString(1));
			lt.set(1, rs.getString(2));
			lt.set(2, rs.getString(3));
			lt.set(3, rs.getString(6));
			if(rs.getString(6).equals(rs.getString(2)))
				lt.set(4, rs.getString(4));
			else 
				lt.set(4, rs.getString(5));
			list.add(lt);
		}
		return list;
	}
	
	private void Do_Choose() throws Exception
	{
		String leftname = null,rightname = null,choose;
		float leftodds = 0,rightodds = 0;
		String query="SELECT * FROM "+tabname+" where id="+id;
		ResultSet rs=null;
		Statement stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		if(rs.next())
		{
			leftname=rs.getString(2);
			rightname=rs.getString(3);
			leftodds=rs.getFloat(4);
			rightodds=rs.getFloat(5);
		}
		float leftstren=Get_Strength(leftname),rightstren=Get_Strength(rightname);
		float bet=1;
		if(leftstren*leftodds>=rightstren*rightodds)
		{
			choose=leftname;
			bet=Math.min(leftstren*leftodds/rightstren*rightodds,20);
		}
		else 
		{
			choose=rightname;
			bet=Math.min(rightstren*rightodds/leftstren*leftodds,20);
		}
		bet=(float)(Math.round(bet*100)/100);
		bet=(float) Math.max(bet, (double)(0.5));
		text_2.setText(choose);
		text_3.setText(String.valueOf(bet));
		//System.out.println(leftname+" "+rightname+":"+win);
	}
	
	private float Get_Strength(String name)throws Exception
	{
		String query="SELECT * FROM team WHERE name=\""+name+"\"";
		ResultSet rs=null;
		Statement stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		if(rs.next())
		{
			return rs.getFloat(2);
		}
		else 
			return 1;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

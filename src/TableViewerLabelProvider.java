import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableViewerLabelProvider implements ITableLabelProvider {  

    // 由此方法决定数据记录在表格的每一列显示什么文字。 element参数是一个实体类对象  
    // col是当前要设置的列的列号，0是第一列  
    public String getColumnText(Object element, int col) {  
    	sqlrecord o = (sqlrecord) element; // 类型转换  
    	return o.get(col);
    }  
  
    // getColumnText方法用于显示文字，本方法用于显示图片  
    public Image getColumnImage(Object element, int col) {  
        return null; // 方法可以返回空值  
    }  
  
    // 当TableViewer对象被关闭时触发执行此方法  
    public void dispose() {  
    	
    } 
    public boolean isLabelProperty(Object element, String property) {  
        return false;  
    }  
  
    public void addListener(ILabelProviderListener listener) {  
    }  
  
    public void removeListener(ILabelProviderListener listener) {  
    }  
}  
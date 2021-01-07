package guiLayer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class OrderTableRenderer extends JLabel implements TableCellRenderer{
	
	    public OrderTableRenderer() {
	        setOpaque(true);
	    }
	     
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	        boolean hasFocus, int row, int column) {
	    	//this.setText(value.toString());
	        this.setAlignmentX(LEFT_ALIGNMENT);
	        this.setBackground(Color.WHITE);
	        return this;
	    }
}

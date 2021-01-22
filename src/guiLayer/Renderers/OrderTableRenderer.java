package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *  Custom TableCellRenderer that replaces the
 *  Component of each cell with an extended JLabel Object.
 *  This makes most cells act and have the functionality of a Jlabel.
 * @author group1
 *
 */
public class OrderTableRenderer extends JLabel implements TableCellRenderer{
	
	    public OrderTableRenderer() {
	        setOpaque(true);
	    }
	     /**
	      * Returns a custom component that is a JLabel
	      */
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	        boolean hasFocus, int row, int column) {
	        this.setAlignmentX(LEFT_ALIGNMENT);
	        this.setBackground(Color.WHITE);
	        return this;
	    }
}

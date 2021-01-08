package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import guiLayer.RoundedButton;

public class JTableButtonRenderer implements TableCellRenderer {
	
	   private TableCellRenderer defaultRenderer;
	   
	   public JTableButtonRenderer(TableCellRenderer renderer) {
	      defaultRenderer = renderer;
	   }
	   
	   @Override
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		   
		   if(value instanceof RoundedButton) {
			   	RoundedButton button = (RoundedButton) value;
				//button.setOpaque(true);
				//button.setBackground(Color.RED);
			  }
		   
	      if(value instanceof Component) {
	    	  return (Component)value;
	      }
	         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	   }
	}

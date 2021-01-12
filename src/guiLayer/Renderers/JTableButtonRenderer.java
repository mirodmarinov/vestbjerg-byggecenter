package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			   button.setForeground(Color.WHITE);
		   }
		   
	      if(value instanceof Component) {
	    	  return (Component)value;
	      }
	         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	   }
	}

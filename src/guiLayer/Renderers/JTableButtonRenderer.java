package guiLayer.Renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Custom implementation of TableCellRenderer for retrieving the right content from a button cell.
 * @author Group1
 *
 */
public class JTableButtonRenderer implements TableCellRenderer
{
	
   private TableCellRenderer defaultRenderer;
   
   public JTableButtonRenderer(TableCellRenderer renderer)
   {
      defaultRenderer = renderer;
   }
   
   /**
    * Custom method for overriding the superclass' method for retrieving the TableCellRendererComponent(Table cell content) for a button.
    * @return returns the content of a cell wrapped in a Component object.
    */
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {
	   
      if(value instanceof Component)
      {
    	  return (Component)value;
      }
         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
   }
}

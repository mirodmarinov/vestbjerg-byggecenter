package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

public class ProductJListCellRenderer extends JLabel implements ListCellRenderer<String> {
     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

	private Color babyBlue = new Color(28, 150, 202);
	
	/*@Override
	public Component getListCellRendererComponent(
       JList<? extends String> list,           // the list
       Object value,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // does the cell have focus
     {
         if (isSelected) { <<<
             
         } else {
             
         }

         setEnabled(list.isEnabled());
         setFont(list.getFont());
         setOpaque(true);
         return this;
     }*/

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		setText(value);
		setFont(new Font("Lato", Font.PLAIN, 13));
		setForeground(babyBlue);
		
		
		return this;
	}
 }

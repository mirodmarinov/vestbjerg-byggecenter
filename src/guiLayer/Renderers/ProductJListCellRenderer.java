package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

/**
 * 	Custom implementation of ListCellRenderer,
 *  so all components act as JLabel
 * @author group1
 *
 */
public class ProductJListCellRenderer extends JLabel implements ListCellRenderer<String> {
	public ProductJListCellRenderer() {
	}
     // This is the only method defined by ProductJListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

	/**
	 * a custom overridden method for retrieving
	 * a cell in a form of a JLabel Component
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		setText(value);
		setFont(new Font("Lato", Font.PLAIN, 13));
		setForeground(Color.BLACK);
		
		return this;
	}
 }

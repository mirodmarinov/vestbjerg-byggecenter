package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

public class ProductJListCellRenderer extends JLabel implements ListCellRenderer<String> {
	public ProductJListCellRenderer() {
	}
     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

	private Color babyBlue = new Color(28, 150, 202);

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		setText(value);
		setFont(new Font("Lato", Font.PLAIN, 13));
		setForeground(Color.BLACK);
		
		return this;
	}
 }

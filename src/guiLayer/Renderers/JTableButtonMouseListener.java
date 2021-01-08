package guiLayer.Renderers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import guiLayer.RoundedButton;

public class JTableButtonMouseListener extends MouseAdapter {
    private final JTable table;

    public JTableButtonMouseListener(JTable table) {
        this.table = table;
    }

    public void mouseClicked(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
        int row = e.getY()/table.getRowHeight(); //get the row of the button

        //Checking the row or column is valid or not
        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
            Object value = table.getValueAt(row, column);
            if (value instanceof RoundedButton) {
                //perform a click event
            	System.out.println("CLICKED BUTTON!");
            	((RoundedButton)value).setBorderColor(Color.RED);
            	((RoundedButton)value).repaint();
            }
        }
    }
}

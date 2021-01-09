package guiLayer.Renderers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import guiLayer.RoundedButton;

public class JTableButtonMouseListener extends MouseAdapter {
    private final JTable table;
    private static int x = -1, y = -1;
    private int recolor = 0;

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
            	//((RoundedButton)value).doClick();
            	((RoundedButton)value).setBorderColor(Color.RED);
            	table.repaint();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
        int row = e.getY()/table.getRowHeight(); //get the row of the button

        //Checking the row or column is valid or not
        if (column != y || row != x) {
        	if (recolor == 1)
        	{
        		Object value = table.getValueAt(y, x);
        		recolor = 0;
        		if (value instanceof RoundedButton) {
        			((RoundedButton)value).setBackgroundColor(Color.WHITE);
        			table.repaint();
        		}
        	}
        	if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
        		Object value = table.getValueAt(row, column);
        		if (value instanceof RoundedButton) {
        			((RoundedButton)value).setBackgroundColor(Color.GREEN);
        			table.repaint();
        			y = row;
        			x = column;
        			recolor = 1;
        		}
        	}
        }
    }

    public void mouseExited(MouseEvent e) {
        Object prevValue = table.getValueAt(y, x);
        if (prevValue instanceof RoundedButton) {
            ((RoundedButton)prevValue).setBackgroundColor(Color.WHITE);
        }
        table.repaint();
    }
}

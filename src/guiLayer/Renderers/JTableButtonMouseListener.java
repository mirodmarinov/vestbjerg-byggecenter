package guiLayer.Renderers;



import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlLayer.OrderCtr;
import guiLayer.OrderPanel;
import guiLayer.RoundedButton;

public class JTableButtonMouseListener extends MouseAdapter {
    private final JTable table;
    private static int x = 0, y = 0;
    private int recolor = 0;
    private Color babyBlue = new Color(28, 150, 202);

    public JTableButtonMouseListener(JTable table) {
        this.table = table;
    }

    public void mouseClicked(MouseEvent e) {

        int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
        int row = e.getY()/table.getRowHeight(); //get the row of the button

        //Checking the row or column is valid or not
        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
            Object value = table.getValueAt(row, column);
            if (value instanceof RoundedButton) {
            	if (((RoundedButton) value).getName().equals("Confirm"))
            	{
                //perform a click event
            	doClick();
            	//((RoundedButton)value).setBorderColor(Color.RED);
            	table.repaint();
            	}
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
        int row = e.getY()/table.getRowHeight(); //get the row of the button

        //Checking the row or column is valid or not
        if (column != y || row != x) {
        	      
        	
        	//Leave the button
        	if (recolor == 1)
        	{
        		Object value = table.getValueAt(y, x);
        		recolor = 0;
        		if (value instanceof RoundedButton) {
        			if (((RoundedButton) value).getName().equals("Confirm"))
        			{
        				mouseExited((RoundedButton) value);
        				table.repaint();
        			}
        		}
        	}
        	
        	//Enter the button
        	if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
        		Object value = table.getValueAt(row, column);
        		if (value instanceof RoundedButton) {
        			if (((RoundedButton) value).getName().equals("Confirm"))
        			{

        				((RoundedButton)value).setBackground(babyBlue);
        				((RoundedButton)value).setForeground(Color.BLACK);
        				((RoundedButton)value).setBorderColor(babyBlue);
        				table.repaint();
        			}
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
        	if (((RoundedButton) prevValue).getName().equals("Confirm")) {
        		mouseExited((RoundedButton) prevValue);
        	}
        }
        table.repaint();
    }
    
    public void doClick() 
    {
    	OrderCtr orderCtr = new OrderCtr();
    	orderCtr.confirmOffer(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), table.getColumn("Order Number").getModelIndex())));
		table.setValueAt(new RoundedButton("Confirmed",Color.WHITE), table.getSelectedRow(), table.getColumn("").getModelIndex());
    	
    }
    
    public void mouseExited(RoundedButton button) {
    	 button.setBackground(babyBlue);
    	 button.setForeground(Color.WHITE);
    }
}


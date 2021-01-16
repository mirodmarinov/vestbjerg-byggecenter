package guiLayer.Renderers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlLayer.OrderCtr;
import guiLayer.*;

public class JTableButtonMouseListener extends MouseAdapter
{
	private final JTable table;
	private static int x = 0, y = 0;
	private int recolor = 0;
	private Color babyBlue = new Color(28, 150, 202);
	private String[] tableElements = new String[] {"Order Number", "Customer", "Purchase Date", "Status", "Expiration Date", "Total (DKK)", ""};
	private JDialog popup;

	public JTableButtonMouseListener(JTable table)
	{
		this.table = table;
	}
	
	public JTableButtonMouseListener(JTable table, JDialog popup)
	{
		this.table = table;
		this.popup = popup;
	}

	public void mouseClicked(MouseEvent e)
	{
		int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
		int row = e.getY() / table.getRowHeight(); // get the row of the button
		// Checking the row or column is valid or not

		if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0)
		{
			Object value = table.getValueAt(row, column);
			if (value instanceof RoundedButton)
			{
				if (table.getName().equals("OrderPanel"))
				{
					if (((RoundedButton)value).getName().equals("Confirm"))
					{
						// perform a click event
						confirmOffer();
						table.repaint();
					}
				}
				else if(table.getName().equals("CustomersPanel"))
				{
					//((EditCustomerDialog)popup).addToList(); TODO Create these methods
				}
				else if(table.getName().equals(("ProductsPanel")))
				{
					table.getValueAt(table.getSelectedRow(), table.getColumn("Barcode").getModelIndex());
					((EditProductDialog)popup).setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					((EditProductDialog)popup).setVisible(true);
				}
				else if(table.getName().equals("AddProductsDialog"))
				{
					addToList();
				}
				
				else if(table.getName().equals("CreateOrderPanel"))
				{
					table.remove(table.getSelectedRow());
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e)
	{
		int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
		int row = e.getY() / table.getRowHeight(); // get the row of the button

		// Checking the row or column is valid or not
		if (column != y || row != x)
		{

			// Leave the button
			if (recolor == 1)
			{
				Object value = table.getValueAt(y, x);
				recolor = 0;
				if (value instanceof RoundedButton)
				{
					if (!((RoundedButton)value).getName().equals("Confirmed") && !((RoundedButton)value).getName().equals("Added"))
					{
						mouseExited((RoundedButton)value);
						table.repaint();
					}
				}
			}

			// Enter the button
			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0)
			{
				Object value = table.getValueAt(row, column);
				if (value instanceof RoundedButton)
				{
					if (!((RoundedButton)value).getName().equals("Confirmed") && !((RoundedButton)value).getName().equals("Added"))
					{

						((RoundedButton)value).setBackground(Color.WHITE);
						((RoundedButton)value).setForeground(babyBlue);
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

	public void mouseExited(MouseEvent e)
	{

		Object prevValue = table.getValueAt(y, x);
		if (prevValue instanceof RoundedButton)
		{
			if (!((RoundedButton)prevValue).getName().equals("Confirmed") && !((RoundedButton)prevValue).getName().equals("Added"))
			{
				mouseExited((RoundedButton)prevValue);
			}

			table.repaint();
		}

	}

	public void confirmOffer()
	{
		OrderCtr orderCtr = new OrderCtr();

		// confirm offer
		int index = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(table.getColumn("Order Number").getModelIndex())));
		//int index = 1;

		orderCtr.confirmOffer(index);
		// get new data
		ArrayList<String[]> data = orderCtr.searchBar(Integer.toString(index));
		String[] ourData = new String[6];
		for (String[] e : data)
		{
			if (Integer.parseInt(e[0]) == index)
			{
				ourData = e;
				break;
			}
		}
		
		// change the button to COnfirmed and inactive
		RoundedButton button = (RoundedButton)table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(table.getColumn("").getModelIndex()));
		button.setText("Confirmed");
		button.setBackground(Color.WHITE);
		button.setBorderColor(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Lato", Font.PLAIN, 14));

		// update the table model row with the new content

		for (int e = 0;e<6;e++)
		{
			table.setValueAt(ourData[e], table.getSelectedRow(), table.convertColumnIndexToView(table.getColumn(tableElements[e]).getModelIndex()));
		}


	}

	public void mouseExited(RoundedButton button)
	{
		button.setBackground(babyBlue);
		button.setForeground(Color.WHITE);
		button.setBorderColor(Color.WHITE);
	}
	
	
	private void addToList()
	{
		table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), table.getSelectedRow(), table.getColumn("").getModelIndex());
		((AddProductsDialog)popup).addToList((String)(table.getValueAt(table.getSelectedRow(), table.getColumn("Barcode").getModelIndex())));
	}
}

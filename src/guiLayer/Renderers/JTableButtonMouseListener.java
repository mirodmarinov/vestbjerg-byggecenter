package guiLayer.Renderers;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlLayer.OrderCtr;
import guiLayer.*;

/**
 * This class adds different methods to all tables
 * and buttons inside of them, allowing us
 * to give them different functions based on
 * mouse actions applied by the user.
 */
public class JTableButtonMouseListener extends MouseAdapter
{
	private final JTable table;
	private static int x = 0;
	private static int y = 0;
	private int recolor = 0;
	private Color babyBlue = new Color(28, 150, 202);
	private JDialog popup;
	private OrderCtr orderCtr;

	public JTableButtonMouseListener(JTable table)
	{
		this.table = table;
	}
	
	public JTableButtonMouseListener(JTable table, JDialog popup)
	{
		this.table = table;
		this.popup = popup;
	}
	
	
	public JTableButtonMouseListener(JTable table, OrderCtr orderCtr) {
		this.table = table;
		this.orderCtr = orderCtr;
	}

	/**
	 * On Mouse click in any of the tables in the GUI,
	 * this eventlistener method is triggered and a
	 * position is derived from the mouse click. it is
	 * used to trigger a button, table update or a
	 * relevant action.
	 */
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
					//if Confirm button is pressed in the Order Panel, open Order Details Dialog.
					if (((RoundedButton)value).getName().equals("Confirm"))
					{
						int index = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(table.getColumn("Order Number").getModelIndex())));
						
						try {
							OrderPanel opd = (OrderPanel) table.getParent().getParent().getParent();
							OrderInfoDialog dialog = new OrderInfoDialog(index,opd);
							dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							
						} catch (Exception x) {
							x.printStackTrace();
						}
					}
				}
				else if(table.getName().equals("CustomersPanel"))
				{
					((CustomerDialogs)popup).fillFields((String)table.getValueAt(table.getSelectedRow(), table.getColumn("Phone number").getModelIndex()));
					((CustomerDialogs)popup).reDraw();
					popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					popup.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
					popup.setVisible(true);
				}
				else if(table.getName().equals(("ProductsPanel")))
				{
					((ProductDialogs)popup).fillFields((String)table.getValueAt(table.getSelectedRow(), table.getColumn("Barcode").getModelIndex()));
					((ProductDialogs)popup).setPlaceOnList(table.getSelectedRow());
					((ProductDialogs)popup).reDraw();
					popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					popup.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
					popup.setVisible(true);
				}
				else if(table.getName().equals("AddProductsDialog"))
				{
					addToList();
				}
				
				else if(table.getName().equals("CreateOrderPanel"))
				{
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					String barcode = table.getValueAt(table.getSelectedRow(), table.getColumn("Barcode").getModelIndex()).toString();
					orderCtr.removeProductFromList(barcode);
					dtm.removeRow(table.getSelectedRow());
				}
				
			}
			else if(table.getName().equals("OrderPanel"))
			{
				if(e.getClickCount() > 1)
				{
					int index = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(table.getColumn("Order Number").getModelIndex())));
					try
					{
						OrderPanel opd = (OrderPanel) table.getParent().getParent().getParent();
						OrderInfoDialog dialog = new OrderInfoDialog(index,opd);
						dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}
					catch (Exception x)
					{
						x.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * It is a mouse moved listener that is a custom
	 * implementation of mouse hover for the RandonButton class.
	 * The method is called when the mouse is moved
	 * inside any of the tables. Used for updating the
	 * color of buttons according to their focus/mouse hover.
	 * 
	 */
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
				if (y<table.getRowCount() && x<table.getColumnCount())
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

	/**
	 * Triggered on table mouse exit.
	 * It resets the colors of all buttons
	 */
	public void mouseExited(MouseEvent e)
	{
		if (y<table.getRowCount() && x<table.getColumnCount())
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

	}
	
	/**
	 * Custom method for resetting button colors.
	 */
	public void mouseExited(RoundedButton button)
	{
		button.setBackground(babyBlue);
		button.setForeground(Color.WHITE);
		button.setBorderColor(Color.WHITE);
	}
	
	/**
	 * Checks if the product is added to the list and if not,
	 * it adds the product and its quantity to a list of
	 * products to be added to the order. If quantity is empty,
	 * it adds an amount of 1.
	 */
	private void addToList()
	{	
		RoundedButton button = (RoundedButton)table.getValueAt(table.getSelectedRow(), table.convertColumnIndexToView(table.getColumn("").getModelIndex()));
		if (button.getName().equals("Add"))
		{
			//if no quantity entered, change quantity to 1.
			if (table.getValueAt(table.getSelectedRow(), table.getColumn("Input Quantity").getModelIndex()).toString().isEmpty())
			{
				table.setValueAt("1", table.getSelectedRow(), table.getColumn("Input Quantity").getModelIndex());
			}
			//change value to "Added"
			table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), table.getSelectedRow(), table.getColumn("").getModelIndex());
			//stores the product line for further manipulation
			((AddProductsDialog)popup).addToList(Integer.toString(table.getSelectedRow()),table.getValueAt(table.getSelectedRow(),table.getColumn("Barcode").getModelIndex()).toString(),table.getValueAt(table.getSelectedRow(),table.getColumn("Input Quantity").getModelIndex()).toString());
		}
	}
}

package guiLayer;
import controlLayer.*;
import uiLayer.MainMenu;


import guiLayer.Renderers.JTableButtonMouseListener;
import guiLayer.Renderers.JTableButtonRenderer;
import modelLayer.OrderContainer;
import modelLayer.ProductContainer;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.crypto.Data;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderPanel extends JPanel {
	private JTable table;
	private OrderCtr orderCtr;
	private JTextField searchTextField;
	JLabel foundLabel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public OrderPanel() {
		MainMenu mn = new MainMenu();
		mn.populateClasses();
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 811, -69, 0, 0, 50};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.1, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel ordersLabel = new JLabel("Orders");
		ordersLabel.setFont(new Font("Lato", Font.PLAIN, 35));
		GridBagConstraints gbc_ordersLabel = new GridBagConstraints();
		gbc_ordersLabel.anchor = GridBagConstraints.WEST;
		gbc_ordersLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ordersLabel.gridx = 1;
		gbc_ordersLabel.gridy = 1;
		add(ordersLabel, gbc_ordersLabel);

		table = new JTable();
		table.setFocusable(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Lato", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setRowHeight(50);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));
		
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.addMouseMotionListener(new JTableButtonMouseListener(table));
		TableCellRenderer tableRenderer = table.getDefaultRenderer(RoundedButton.class);
	    table.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));
	      
		// Could be moved to a custom header renderer
		DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
		table.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);

		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Order Number", "Customer", "Purchase Date", "Status", "Expiration Date", "Total (DKK)", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, RoundedButton.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(0).setMinWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setMinWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setMinWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setMinWidth(70);
		table.getColumnModel().getColumn(6).setMinWidth(75);
		
		searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					searchTextField.setFocusable(false);
					searchTextField.setFocusable(true);
					if (searchTextField.getText().equals(""))
					{
						searchTextField.setText("🔍 Search...");
					}
					else
					{
						searchBar();
					}
			}
		});
		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchTextField.setFocusable(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (searchTextField.getText().equals("🔍 Search..."))
				{
					searchTextField.setText("");
				}
			}
		});
		
		//****** TODO SIZE MUST BE FIXED AT THIS LABEL ***** \\
		foundLabel = new JLabel("Order not found!");
		foundLabel.setVisible(false);
		foundLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		foundLabel.setForeground(Color.RED);
		GridBagConstraints gbc_foundLabel = new GridBagConstraints();
		gbc_foundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_foundLabel.anchor = GridBagConstraints.EAST;
		gbc_foundLabel.gridx = 2;
		gbc_foundLabel.gridy = 1;
		add(foundLabel, gbc_foundLabel);
		searchTextField.setFocusable(false);
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 3;
		gbc_searchTextField.gridy = 1;
		searchTextField.setText("🔍 Search...");
		add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		add(tablePane, gbc_table);
		defaultFillTable();
		

		
	}

	/**
	 * This method fills the table with the first x elements from the orderContainer
	 * 
	 */
	private void defaultFillTable() {
		orderCtr = new OrderCtr();
		//we get the order information from the orderContainer
		ArrayList<String[]> data = orderCtr.getOrders(table.getRowCount());
		//Check if the order quantity is less the the row amount,
		//we fill the table with the order data quantity, otherwise we fill
		//the whole table with information
		int columncount = data.size() > table.getRowCount() ? table.getRowCount() : data.size();
		for (int e = 0; e<columncount;e++)
		{
			
			for (int element = 0; element < 6; element++)
			{
				
				table.setValueAt(data.get(e)[element], e, element);
			}

			//********** TODO EDIT NEEDED TO DISABLE THE CONFIRMED ORDER'S BUTTON ******** \\
			if (data.get(e)[3].equals("pending"))
			{
			table.setValueAt(new RoundedButton("Confirm",Color.LIGHT_GRAY), e, 6);
			}
			else
			{
				table.setValueAt(new RoundedButton("Confirmed",Color.WHITE), e, 6);
			}
			
		}
		
		/*
		table.setValueAt(1, 0, 0);
		table.setValueAt("Bob", 0, 1);
		table.setValueAt("1.1.2021", 0, 2);
		table.setValueAt("confirmed", 0, 3);
		table.setValueAt("2.1.2021", 0, 4);
		table.setValueAt(400, 0, 5);
		table.setValueAt(new RoundedButton("Confirm"), 0, 6);
		
		table.setValueAt(2, 1, 0);
		table.setValueAt("Not Bob", 1, 1);
		table.setValueAt("5.1.2021", 1, 2);
		table.setValueAt("pending", 1, 3);
		table.setValueAt("7.1.2021", 1, 4);
		table.setValueAt(1500, 1, 5);
		table.setValueAt(new RoundedButton("Confirm"), 1, 6);
		*/
	}
	
	/**
	 * This method gets the data from the searchField and displays the found order.
	 * If the order couldn't be found it shows an error message. The error message
	 * disappears when an order is found.
	 */
	private void searchBar()
	{
		
		if (!searchTextField.getText().equals(""))
		{
			orderCtr = new OrderCtr();
			String[] data = orderCtr.searchBar(Integer.parseInt(searchTextField.getText())); // TODO Check this
			if (data != null)
			{
				foundLabel.setVisible(false);
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(1);
				table.setValueAt(searchTextField.getText(), 0, 0);
				table.setValueAt(data[0], 0, 1);
				table.setValueAt(data[1], 0, 2);
				table.setValueAt(data[2], 0, 3);
				table.setValueAt(data[3], 0, 4);
				table.setValueAt(data[4], 0, 5);
				table.setValueAt(new RoundedButton("Confirm",Color.WHITE), 0, 6);
			}
			else
			{
				foundLabel.setVisible(true);
			}
			
		}
	}
	
	
}

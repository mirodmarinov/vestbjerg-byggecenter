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
import javax.swing.table.JTableHeader;
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
	private Color babyBlue = new Color(28, 150, 202);
	JLabel foundLabel;
	public JLabel tablePageLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public OrderPanel() {
		MainMenu mn = new MainMenu();
		mn.populateClasses();
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 0, 0, 122, 0, 0, 20};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.5, 0.0, 0.5, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel ordersLabel = new JLabel("Orders");
		ordersLabel.setFont(new Font("Lato", Font.BOLD, 35));
		GridBagConstraints gbc_ordersLabel = new GridBagConstraints();
		gbc_ordersLabel.anchor = GridBagConstraints.WEST;
		gbc_ordersLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ordersLabel.gridx = 1;
		gbc_ordersLabel.gridy = 1;
		add(ordersLabel, gbc_ordersLabel);

		table = new JTable();
		
		//Changes the Color of the header
		JTableHeader header = table.getTableHeader();
		header.setBackground(babyBlue);
		header.setForeground(Color.WHITE);


		table.setRowSelectionAllowed(false);
		table.setFocusable(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Lato", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setRowHeight(50);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));
		
		TableCellRenderer tableRenderer = table.getDefaultRenderer(RoundedButton.class);
	    table.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));
	      
		// Could be moved to a custom header renderer
		DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
		table.getTableHeader().setFont(new Font("Lato", Font.BOLD, 14));
		table.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);

		table.setModel(new DefaultTableModel(
			new Object[][] {
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
		
		
		foundLabel = new JLabel("Order not found!");
		foundLabel.setVisible(false);
		
		leftArrowLabel = new JLabel(" < ");
		
		leftArrowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setText("🔍 Search...");
				loadPage(getPageIndex() - 1);
			}
		});
		leftArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_leftArrowLabel = new GridBagConstraints();
		gbc_leftArrowLabel.anchor = GridBagConstraints.EAST;
		gbc_leftArrowLabel.insets = new Insets(0, 0, 5, 5);
		gbc_leftArrowLabel.gridx = 2;
		gbc_leftArrowLabel.gridy = 1;
		add(leftArrowLabel, gbc_leftArrowLabel);
		
		tablePageLabel = new JLabel("<html><u>1</u></html>");
		tablePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_tablePageLabel = new GridBagConstraints();
		gbc_tablePageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tablePageLabel.gridx = 3;
		gbc_tablePageLabel.gridy = 1;
		add(tablePageLabel, gbc_tablePageLabel);
		
		rightArrowLabel = new JLabel(" > ");
		rightArrowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setFocusable(false);
				searchTextField.setFocusable(true);
				searchTextField.setText("🔍 Search...");
				loadPage(getPageIndex() + 1);
			}
		});
		rightArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_rightArrowLabel = new GridBagConstraints();
		gbc_rightArrowLabel.anchor = GridBagConstraints.WEST;
		gbc_rightArrowLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rightArrowLabel.gridx = 4;
		gbc_rightArrowLabel.gridy = 1;
		add(rightArrowLabel, gbc_rightArrowLabel);
		foundLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		foundLabel.setForeground(Color.RED);
		GridBagConstraints gbc_foundLabel = new GridBagConstraints();
		gbc_foundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_foundLabel.anchor = GridBagConstraints.EAST;
		gbc_foundLabel.gridx = 5;
		gbc_foundLabel.gridy = 1;
		add(foundLabel, gbc_foundLabel);
		
		searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					foundLabel.setVisible(false);
					searchTextField.setFocusable(false);
					searchTextField.setFocusable(true);
					if (searchTextField.getText().equals(""))
					{
						System.out.println("inside");
						loadPage(1);
						searchTextField.setText("🔍 Search...");
						loadPage(Integer.parseInt(tablePageLabel.getText().replaceAll("\\<.*?\\>", "")));
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
		searchTextField.setFocusable(false);
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 6;
		gbc_searchTextField.gridy = 1;
		searchTextField.setText("🔍 Search...");
		add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 6;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		add(tablePane, gbc_table);
		loadPage(1);
		
		//table listeners moved to the bottom, in order for the page to be initialized
		table.addMouseListener(new JTableButtonMouseListener(table, getPageIndex()));
		table.addMouseMotionListener(new JTableButtonMouseListener(table, getPageIndex()));
		
	}

	/**
	 * This method fills the table with the first x elements from the orderContainer
	 * 
	 */
	private void defaultFillTable(int index) {
		orderCtr = new OrderCtr();
		//we get the order information from the orderContainer
		ArrayList<String[]> data = orderCtr.getOrders(index);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		dtm.setRowCount(data.size());
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

			
			if (data.get(e)[3].equals("pending"))
			{
				table.setValueAt(new RoundedButton("Confirm", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, 6);
			}
			else
			{
				table.setValueAt(new RoundedButton("Confirmed",Color.WHITE, Color.WHITE, new Font("Lato", Font.PLAIN, 14)), e, 6);
			}
			
		}
	}
	
	/**
	 * This method gets the data from the searchField and displays the found order.
	 * If the order couldn't be found it shows an error message. The error message
	 * disappears when an order is found.
	 */
	private void searchBar()
	{
		foundLabel.setVisible(false);
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
				if (data[2].equals("pending"))
				{
					table.setValueAt(new RoundedButton("Confirm", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), 0, 6);
				}
				else
				{
					table.setValueAt(new RoundedButton("Confirmed",Color.WHITE, Color.WHITE, new Font("Lato", Font.PLAIN, 14)), 0, 6);
				}
				
			}
			else
			{
				foundLabel.setVisible(true);
			}
			
		}
	}
	
	public void loadPage(int index)
	{
		foundLabel.setVisible(false);
		if(index == 0) {
			return;
		}
		
		
		orderCtr = new OrderCtr();
		if (orderCtr.getOrders(index).isEmpty())
		{
			return;
		}
		defaultFillTable(index);

		
		
		tablePageLabel.setText("<html><u>" + index + "</u></html>");

		
	}
	
	/**
	 * Returns the current page index
	 * @return an integer based on the current page index.
	 */
	public int getPageIndex()  
	{
		return Integer.parseInt(tablePageLabel.getText().replaceAll("\\<.*?\\>", ""));
	}
}

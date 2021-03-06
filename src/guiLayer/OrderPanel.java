package guiLayer;

import controlLayer.*;
import guiLayer.Renderers.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is part of the Warehouse System for 
 * Vestbjerg Byggecenter. It creates a panel,
 * containing all of the order inside the system,
 * and allows the user to interact with them.
 */

public class OrderPanel extends JPanel {
	private JTable table;
	private OrderCtr orderCtr;
	private JTextField searchTextField;
	private Color babyBlue = new Color(28, 150, 202);
	private JLabel foundLabel;
	private String[] tableElements = new String[] {"Order Number", "Customer", "Purchase Date", "Status", "Expiration Date", "Total (DKK)", ""};
	private JLabel tablePageLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	private JTableButtonMouseListener mouseListener;
	private DocumentListener cl = new DocumentListener(){
		@Override
		public void insertUpdate(DocumentEvent e)
		{
			searchBar(false);
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			searchBar(false);
		}

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			searchBar(false);
		}
	};

	/**
	 * Creates the panel.
	 */
	/********************************************** Order Panel**********************************************/
	@SuppressWarnings("serial") // Make sure it doesn't complain about serialisation
	public OrderPanel() {
		setBackground(new Color(252, 252, 252));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 0, 0, 122, 0, 0, 20};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0, 100, 21};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.5, 0.0, 0.5, 0.0, 0.1, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 2.0, 0.0, 1.0, 0.2};
		setLayout(gridBagLayout);

		//Orders Label***********************************************************************
		JLabel ordersLabel = new JLabel("Orders");
		ordersLabel.setFont(new Font("Lato", Font.BOLD, 35));
		
		GridBagConstraints gbc_ordersLabel = new GridBagConstraints();
		gbc_ordersLabel.anchor = GridBagConstraints.WEST;
		gbc_ordersLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ordersLabel.gridx = 1;
		gbc_ordersLabel.gridy = 1;
		add(ordersLabel, gbc_ordersLabel);

		//Order Table*************************************************************************
		table = new JTable();
		mouseListener = new JTableButtonMouseListener(table); // adds Mouse listener to table
		//Changes the Color of the header
		JTableHeader header = table.getTableHeader();
		header.setBackground(babyBlue);
		header.setForeground(Color.WHITE);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 3;
		gbc_table.gridwidth = 6;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;

		table.setName("OrderPanel");
		table.setRowSelectionAllowed(false);
		table.setFocusable(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Lato", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setRowHeight(50);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));
		
		TableCellRenderer tableRenderer = table.getDefaultRenderer(RoundedButton.class);
	    table.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));

		DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
		table.getTableHeader().setFont(new Font("Lato", Font.BOLD, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);

		//Setting the number of columns, the header names and the data types
		table.setModel(new DefaultTableModel(new Object[][]{}, tableElements) 
		{
			//Variables of the custom table model
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, RoundedButton.class
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
			
			//Methods of the custom table model
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
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
		
		//Error Label Not Found*************************************************************
		foundLabel = new JLabel("Order not found");
		foundLabel.setVisible(false);
		foundLabel.setFont(new Font("Lato", Font.BOLD, 14));
		foundLabel.setForeground(Color.RED);
		
		GridBagConstraints gbc_foundLabel = new GridBagConstraints();
		gbc_foundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_foundLabel.anchor = GridBagConstraints.EAST;
		gbc_foundLabel.gridx = 5;
		gbc_foundLabel.gridy = 1;
		add(foundLabel, gbc_foundLabel);
		
		
		//Left Arrow Label "<"**************************************************************
		leftArrowLabel = new JLabel(" < ");
		leftArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GridBagConstraints gbc_leftArrowLabel = new GridBagConstraints();
		gbc_leftArrowLabel.anchor = GridBagConstraints.EAST;
		gbc_leftArrowLabel.insets = new Insets(0, 0, 5, 5);
		gbc_leftArrowLabel.gridx = 2;
		gbc_leftArrowLabel.gridy = 1;
		add(leftArrowLabel, gbc_leftArrowLabel);
		
		leftArrowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Resets the searchfield
				searchTextField.setFocusable(false);
				searchTextField.setFocusable(true);
				searchTextField.getDocument().removeDocumentListener(cl);
				searchTextField.setForeground(new Color(149, 149, 149));
				searchTextField.setText("🔍 Search...");
				searchTextField.getDocument().addDocumentListener(cl);
				//Loads the previous page
				loadPage(getPageIndex() - 1);
			}
		});
		
		//Page Number Label**********************************************************
		tablePageLabel = new JLabel("<html><u>1</u></html>");
		tablePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		GridBagConstraints gbc_tablePageLabel = new GridBagConstraints();
		gbc_tablePageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tablePageLabel.gridx = 3;
		gbc_tablePageLabel.gridy = 1;
		add(tablePageLabel, gbc_tablePageLabel);
		
		
		//Right Arrow Label ">"******************************************************
		rightArrowLabel = new JLabel(" > ");
		rightArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GridBagConstraints gbc_rightArrowLabel = new GridBagConstraints();
		gbc_rightArrowLabel.anchor = GridBagConstraints.WEST;
		gbc_rightArrowLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rightArrowLabel.gridx = 4;
		gbc_rightArrowLabel.gridy = 1;
		add(rightArrowLabel, gbc_rightArrowLabel);
		
		rightArrowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Resets the searchfield
				searchTextField.setFocusable(false);
				searchTextField.setFocusable(true);
				searchTextField.getDocument().removeDocumentListener(cl);
				searchTextField.setForeground(new Color(149, 149, 149));
				searchTextField.setText("🔍 Search...");
				searchTextField.getDocument().addDocumentListener(cl);
				//Loads the next page
				loadPage(getPageIndex() + 1);
			}
		});
		
	
		//Search Bar******************************************************************
		searchTextField = new JTextField();
		searchTextField.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		searchTextField.setForeground(new Color(149, 149, 149));
		
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					foundLabel.setVisible(false);
					searchTextField.setFocusable(false);
					searchTextField.setFocusable(true);
					
					if (searchTextField.getText().equals(""))
					{
						loadPage(1);
						searchTextField.getDocument().removeDocumentListener(cl);
						searchTextField.setForeground(new Color(149, 149, 149));
						searchTextField.setText("🔍 Search...");
						searchTextField.getDocument().addDocumentListener(cl);
					}
					else
					{
						searchBar(true);
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
					searchTextField.getDocument().removeDocumentListener(cl);
					searchTextField.setForeground(Color.BLACK);
					searchTextField.setText("");
					searchTextField.getDocument().addDocumentListener(cl);
				}
			}
		});
		searchTextField.setFocusable(false);
		
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 6;
		gbc_searchTextField.gridy = 1;
		searchTextField.setForeground(new Color(149, 149, 149));
		searchTextField.setText("🔍 Search...");
		searchTextField.getDocument().addDocumentListener(cl);
		add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);
		
		//Scroll Pane******************************************************************	
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		add(tablePane, gbc_table);
		loadPage(getPageIndex());
		
		//table listeners moved to the bottom, in order for the page to be initialized
		table.addMouseListener(mouseListener);
		table.addMouseMotionListener(new JTableButtonMouseListener(table));
	}

	/**
	 * This method fills the table with the first x elements from the orderContainer
	 */
	private void defaultFillTable(int index) {
		orderCtr = new OrderCtr();
		//we get the order information from the orderContainer
		ArrayList<String[]> data = orderCtr.getOrdersInfo(index);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(data.size());
		
		for (int e = 0; e < data.size(); e++)
		{
			
			for (int element = 0; element < 6; element++)
			{
				table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
			}

			
			if (data.get(e)[3].equals("pending"))
			{
				table.setValueAt(new RoundedButton("Confirm", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
			}
			else
			{
				table.setValueAt(new RoundedButton("Confirmed",Color.WHITE, Color.WHITE, new Font("Lato", Font.PLAIN, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
			}
		}
	}
	
	/**
	 * This method gets the data from the searchField and displays the found order.
	 * If the order couldn't be found it shows an error message. The error message
	 * disappears when an order is found.
	 */
	private void searchBar(Boolean Notdynamic)
	{
		foundLabel.setVisible(false);
		if ((!searchTextField.getText().equals(""))&&(!searchTextField.getText().equals("🔍 Search...")))
		{
			orderCtr = new OrderCtr();
			
			ArrayList<String[]> data = orderCtr.searchBar(searchTextField.getText());
			
			if (data.size() != 0)
			{ 
				foundLabel.setVisible(false);
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(data.size());
				for (int e = 0; e < data.size();e++)
				{
					for (int element = 0; element < 6; element++)
					{						
						table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
					}
					if (data.get(e)[3].equals("pending"))
					{
						table.setValueAt(new RoundedButton("Confirm", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
					}
					else
					{
						table.setValueAt(new RoundedButton("Confirmed",Color.WHITE, Color.WHITE, new Font("Lato", Font.PLAIN, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
					}	
				}
			}
			else
			{
				if (Notdynamic)
				{
					foundLabel.setText("Order not found");
					foundLabel.setVisible(true);
				}
			}
		}
		else
		{
			loadPage(1);
		}
	}
	
	/**
	 * This method load a certain page and sets the page number based on the index
	 * @param index - the page number
	 */
	public void loadPage(int index)
	{
		foundLabel.setVisible(false);
		if(index == 0) {
			return;
		}
		
		orderCtr = new OrderCtr();
		if (orderCtr.getOrdersInfo(index).isEmpty())
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
	
	public JTextField getSearchTextField()
	{
		return searchTextField;
	}
	
	public JLabel getTablePageLabel()
	{
		return tablePageLabel;
	}
	
	/**
	 * Refreshes the page
	 */
	public void reset()
	{
		loadPage(getPageIndex()); 
	}	
}

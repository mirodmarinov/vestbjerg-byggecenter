package guiLayer;

import controlLayer.*;
import guiLayer.Renderers.*;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class CustomersPanel extends JPanel {
	private JTable table;
	private JTextField searchTextField;
	private Color babyBlue = new Color(28, 150, 202);
	JLabel foundLabel;
	private String[] tableElements = new String[] {"Name", "Phone number", "Address", "Group", "Discount", ""};
	public JLabel tablePageLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	private CustomerCtr customerCtr;
	
	private DocumentListener cl = new DocumentListener()
	
	
	{
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
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public CustomersPanel() {
		setBackground(new Color(252, 252, 252));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 40, 0, 0, 0, 122, 0, 0, 20};
		gridBagLayout.rowHeights = new int[]{100, 0, 40, 0, 0, 100, 21};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.5, 0.0, 0.5, 0.0, 0.1, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 2.0, 0.0, 1.0, 0.2};
		setLayout(gridBagLayout);
		
		//Customers Label*************************************************************
		JLabel customersLabel = new JLabel("Customers");
		customersLabel.setFont(new Font("Lato", Font.BOLD, 35));
		
		GridBagConstraints gbc_customersLabel = new GridBagConstraints();
		gbc_customersLabel.anchor = GridBagConstraints.WEST;
		gbc_customersLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customersLabel.gridx = 1;
		gbc_customersLabel.gridy = 1;
		add(customersLabel, gbc_customersLabel);

		//Customers Table*****************************************************************
		table = new JTable();
		table.setName("CustomersPanel");
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

		//Setting the number of columns, the data types
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			tableElements
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, RoundedButton.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		//Setting the sizes of the columns
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
		
		table.addMouseListener(new JTableButtonMouseListener(table,new CustomerDialogs(this,true)));
		table.addMouseMotionListener(new JTableButtonMouseListener(table));
		
		
		//Left Arrow Label "<"**********************************************************
		leftArrowLabel = new JLabel(" < ");
		//Loads the previous page
		leftArrowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.getDocument().removeDocumentListener(cl);
				searchTextField.setForeground(new Color(149, 149, 149));
				searchTextField.setText("🔍 Search...");
				searchTextField.getDocument().addDocumentListener(cl);
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
		
		//Page Number Label*********************************************************************
		tablePageLabel = new JLabel("<html><u>1</u></html>");
		tablePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		GridBagConstraints gbc_tablePageLabel = new GridBagConstraints();
		gbc_tablePageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tablePageLabel.gridx = 3;
		gbc_tablePageLabel.gridy = 1;
		add(tablePageLabel, gbc_tablePageLabel);
		
		//Right Arrow Label ">"*****************************************************************
		rightArrowLabel = new JLabel(" > ");
		rightArrowLabel.addMouseListener(new MouseAdapter() {
			//Loads the next page
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setFocusable(false);
				searchTextField.setFocusable(true);
				searchTextField.getDocument().removeDocumentListener(cl);
				searchTextField.setForeground(new Color(149, 149, 149));
				searchTextField.setText("🔍 Search...");
				searchTextField.getDocument().addDocumentListener(cl);
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
		
		//Customer not found Label***************************************************************
		foundLabel = new JLabel("Customer not found!");
		foundLabel.setVisible(false);
		foundLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		foundLabel.setForeground(Color.RED);
		
		GridBagConstraints gbc_foundLabel = new GridBagConstraints();
		gbc_foundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_foundLabel.anchor = GridBagConstraints.EAST;
		gbc_foundLabel.gridx = 5;
		gbc_foundLabel.gridy = 2;
		add(foundLabel, gbc_foundLabel);
		
		//Search Bar*****************************************************************************
		searchTextField = new JTextField();
		searchTextField.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
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
		gbc_searchTextField.gridy = 2;
		searchTextField.setForeground(new Color(149, 149, 149));
		searchTextField.setText("🔍 Search...");
		searchTextField.getDocument().addDocumentListener(cl);
		add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 3;
		gbc_table.gridwidth = 6;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		
		//Scroll Pane********************************************************************
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		add(tablePane, gbc_table);
		loadPage(1);
		
		
		//Add Products button ***********************************************************
		
		RoundedButton addProductButton = new RoundedButton("\u002B Add Customer", babyBlue,
					Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 14));
		CustomersPanel thisClass = this;
		addProductButton.addMouseListener(new MouseAdapter() {
			//Calls the addProductsDialog
			@Override
			public void mouseClicked(MouseEvent e) {

				CustomerDialogs dialog = new CustomerDialogs(thisClass,false);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
				dialog.setVisible(true);
			}
		});
		addProductButton.addOffset(-13, 2);
		blueButton(addProductButton);
		
		GridBagConstraints gbc_addCustomerButton = new GridBagConstraints();
		gbc_addCustomerButton.fill = GridBagConstraints.BOTH;
		gbc_addCustomerButton.insets = new Insets(0, 0, 5, 5);
		gbc_addCustomerButton.gridx = 1;
		gbc_addCustomerButton.gridy = 2;
		add(addProductButton, gbc_addCustomerButton);
	}

	/**
	 * This method fills the table with the first x elements from the orderContainer
	 * 
	 */
	protected void defaultFillTable(int index) {
		customerCtr = new CustomerCtr();
		//we get the order information from the orderContainer
		ArrayList<String[]> data = customerCtr.getCustomers(index);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		dtm.setRowCount(data.size());
		//Check if the order quantity is less the the row amount,
		//we fill the table with the order data quantity, otherwise we fill
		//the whole table with information
		for (int e = 0; e<data.size();e++)
		{
			
			for (int element = 0; element < 5; element++)
			{
				table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
			}
			table.setValueAt(new RoundedButton("Manage", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, 5);

			
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
			customerCtr = new CustomerCtr();
			
			ArrayList<String[]> data = customerCtr.searchField(searchTextField.getText());
			
			if (data.size() != 0)
			{ 
				foundLabel.setVisible(false);
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(data.size());
				for (int e = 0; e < data.size();e++)
				{
					for (int element = 0; element < 5; element++)
					{						
						table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
	
					}
					table.setValueAt(new RoundedButton("Manage", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, 5);
					
				}
				
			}
			else
			{
				if (Notdynamic)
				{
				foundLabel.setText("Customer nor found!");
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
	 * Loads the page depending on the index in the parameter and sets the page number
	 * @param index - page number
	 */
	public void loadPage(int index)
	{
		foundLabel.setVisible(false);
		if(index == 0) {
			return;
		}
		
		customerCtr = new CustomerCtr();
		if (customerCtr.getCustomers(index).isEmpty())
		{
			return;
		}
		
		defaultFillTable(index);

		tablePageLabel.setText("<html><u>" + index + "</u></html>");
	}
	
	//Getters**********************************************************************
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
	
	public JTable getTable()
	{
		return table;
	}
	
	/**
	 * This method adds events to the blue buttons - changing their color when a mouse enters or exits the button
	 * @param button to be formatted
	 */
	public void blueButton(RoundedButton button) {
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
					button.setBackground(Color.WHITE);
					button.setForeground(babyBlue);
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
					button.setBackground(babyBlue);
					button.setForeground(Color.WHITE);
			}
		});
	}
}

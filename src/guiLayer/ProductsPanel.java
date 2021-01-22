package guiLayer;

/**
 * This class is a part of the Warehouse System
 * for Vestbjerg Byggecenter. It contains the
 * formatting and functions of the products panel
 * which is used to show and manage products
 * in the system.
 */

import controlLayer.*;

import guiLayer.Renderers.JTableButtonMouseListener;
import guiLayer.Renderers.JTableButtonRenderer;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ProductsPanel extends JPanel {
	private JTable table;
	private JTextField searchTextField;
	private Color babyBlue = new Color(28, 150, 202);
	JLabel foundLabel;
	private String[] tableElements = new String[] {"Barcode", "Name", "Group", "Location", "Price", "Quantity", ""};
	public JLabel tablePageLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	private	ProductCtr productCtr = new ProductCtr();
	
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
	/********************************************** Products Panel **********************************************/
	public ProductsPanel() {
		System.setProperty("file.encoding", "UTF-8");
		setBackground(new Color(252, 252, 252));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 40, 0, 0, 0, 122, 0, 0, 20};
		gridBagLayout.rowHeights = new int[]{100, 0, 40, 0, 0, 100, 21};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.5, 0.0, 0.5, 0.0, 0.1, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 2.0, 0.0, 1.0, 0.2};
		setLayout(gridBagLayout);
		
		//Products header*****************************************************************************
		JLabel productsHeaderLabel = new JLabel("Products");
		productsHeaderLabel.setFont(new Font("Lato", Font.BOLD, 35));
		GridBagConstraints gbc_productsHeaderLabel = new GridBagConstraints();
		gbc_productsHeaderLabel.anchor = GridBagConstraints.WEST;
		gbc_productsHeaderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productsHeaderLabel.gridx = 1;
		gbc_productsHeaderLabel.gridy = 1;
		add(productsHeaderLabel, gbc_productsHeaderLabel);

		//Products Table*****************************************************************************
		table = new JTable();
		table.setName("ProductsPanel");
		//Changes the Color of the header
		JTableHeader header = table.getTableHeader();
		header.setBackground(babyBlue);
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));

		table.setRowSelectionAllowed(false);
		table.setFocusable(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Lato", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setRowHeight(50);
		
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
			tableElements
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
	
		//"<"Left arrow label*****************************************************************************
		leftArrowLabel = new JLabel(" < ");
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
		
		//Table page indicator label*****************************************************************************
		tablePageLabel = new JLabel("<html><u>1</u></html>");
		tablePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_tablePageLabel = new GridBagConstraints();
		gbc_tablePageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tablePageLabel.gridx = 3;
		gbc_tablePageLabel.gridy = 1;
		add(tablePageLabel, gbc_tablePageLabel);
		
		//">"Right arrow label*****************************************************************************
		rightArrowLabel = new JLabel(" > ");
		rightArrowLabel.addMouseListener(new MouseAdapter() {
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
		
		//Product not found error label*****************************************************************************
		foundLabel = new JLabel("Product not found!");
		foundLabel.setVisible(false);
		foundLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		foundLabel.setForeground(Color.RED);
		GridBagConstraints gbc_foundLabel = new GridBagConstraints();
		gbc_foundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_foundLabel.anchor = GridBagConstraints.EAST;
		gbc_foundLabel.gridx = 5;
		gbc_foundLabel.gridy = 2;
		add(foundLabel, gbc_foundLabel);
		
		//Search Text field*****************************************************************************
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
		
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		add(tablePane, gbc_table);
		loadPage(1);

		
		//Add  products button*****************************************************************************
		RoundedButton addProductButton = new RoundedButton("\u002B Add Product", babyBlue,
					Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 14));
		ProductsPanel thisObject = this;
		addProductButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductDialogs dialog = new ProductDialogs(thisObject,0,productCtr,false);
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
		
		
		table.addMouseListener(new JTableButtonMouseListener(table,new ProductDialogs(this,0,productCtr,true)));
		table.addMouseMotionListener(new JTableButtonMouseListener(table));
	}

	/**
	 * This method fills the table with the first x elements from the orderContainer
	 */
	protected void defaultFillTable(int index) {
		//we get the order information from the orderContainer
		ArrayList<String[]> data = productCtr.defaultFill(index);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		dtm.setRowCount(data.size());
		//Check if the order quantity is less the the row amount,
		//we fill the table with the order data quantity, otherwise we fill
		//the whole table with information
		for (int e = 0; e<data.size();e++)
		{
			
			for (int element = 0; element < 6; element++)
			{
				
				table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
			}
			table.setValueAt(new RoundedButton("Manage", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
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
			ArrayList<String[]> data = productCtr.searchField(searchTextField.getText());
			
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
					table.setValueAt(new RoundedButton("Manage", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
					}
				}
			else
			{
				if (Notdynamic)
				{
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
	 * This method is used to refresh the table
	 * after a product has been deleted,
	 * added and edited.
	 * 
	 * @param index
	 */
	public void loadPage(int index)
	{
		foundLabel.setVisible(false);
		if(index == 0) {
			return;
		}
		
		if (productCtr.defaultFill(index).isEmpty())
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
	 * Formats a Roundedbutton
	 * 
	 * @param button
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

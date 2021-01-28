package guiLayer;

import controlLayer.OrderCtr;
import guiLayer.Renderers.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is a part of the Warehouse System
 * for Vestbjerg Byggecenter. It contains creates the Dialog
 * that allows users to add products to the system.
 */

public class AddProductsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private JTextField searchBar;
	private JLabel productErrorLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	private JLabel tablePageLabel;
	private JTable createOrderPanelTable;
	private JTable table;
	private RoundedButton cancelButton;
	private OrderCtr orderCtr;
	private ArrayList<String[]> productPlace = new ArrayList<>();
	private String[] tableElements = new String[] {"Barcode", "Name", "Discount", "Input Quantity", "Price", "Stock", ""};
	
	private DocumentListener cl = new DocumentListener()
	{
		@Override
		public void insertUpdate(DocumentEvent e)
		{
			searchProduct(false);
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			searchProduct(false);
		}

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			searchProduct(false);
		}
	};
	private JPanel header;
	
	/**
	 * Create the dialog.
	 */
	public AddProductsDialog(JTable createOrderPanelTable, OrderCtr orderCtr) {
		this.orderCtr = orderCtr;
		this.createOrderPanelTable = createOrderPanelTable;
		setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		setBounds(100, 100, 1042, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.2, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.5, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			
			//Product SearchBar********************************************************
			{
				header = new JPanel();
				GridBagConstraints gbc_header = new GridBagConstraints();
				header.setBackground(babyBlue);
				gbc_header.gridwidth = 5;
				gbc_header.insets = new Insets(0, 0, 5, 0);
				gbc_header.fill = GridBagConstraints.BOTH;
				gbc_header.gridx = 0;
				gbc_header.gridy = 0;
				contentPanel.add(header, gbc_header);
				{
					JLabel headerLabel = new JLabel("Add Product");
					header.add(headerLabel);
					headerLabel.setVisible(true);
					headerLabel.setForeground(Color.WHITE);
					headerLabel.setFont(new Font("LATO", Font.BOLD, 20));
				}
			}
			searchBar = new JTextField();
			searchBar.setFocusable(false);
			searchBar.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
			searchBar.setText("🔍 Product Name...");
			searchBar.setForeground(new Color(149, 149, 149));
			searchBar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						searchBar.setFocusable(false);
						searchBar.setFocusable(true);
						if (searchBar.getText().equals(""))
						{
							
							searchBar.getDocument().removeDocumentListener(cl);
							searchBar.setText("🔍 Product Name...");
							searchBar.getDocument().addDocumentListener(cl);
							
						}
						else
						{
							searchProduct(true);
						}
				}
			});
			searchBar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					searchBar.setFocusable(true);
				}
				
				//Setting the text inside the searchbar to be nothing when clicked so the user can input words
				@Override
				public void mousePressed(MouseEvent e) {
					if (searchBar.getText().equals("🔍 Product Name..."))
					{
						searchBar.getDocument().removeDocumentListener(cl);
						searchBar.setText("");
						searchBar.getDocument().addDocumentListener(cl);
					}
				}
			});
			
			GridBagConstraints gbc_searchBar = new GridBagConstraints();
			gbc_searchBar.insets = new Insets(0, 0, 5, 5);
			gbc_searchBar.fill = GridBagConstraints.BOTH;
			gbc_searchBar.gridx = 0;
			gbc_searchBar.gridy = 2;
			contentPanel.add(searchBar, gbc_searchBar);
			searchBar.setColumns(10);
			
			//Error product label**************************************************************
			//A Label that shows when the searched product is not in the list of products
			productErrorLabel = new JLabel("Product not found!");
			productErrorLabel.setVisible(false);
			productErrorLabel.setForeground(Color.RED);
			productErrorLabel.setFont(new Font("Lato", Font.BOLD, 14));
			
			GridBagConstraints gbc_productErrorLabel = new GridBagConstraints();
			gbc_productErrorLabel.gridwidth = 2;
			gbc_productErrorLabel.anchor = GridBagConstraints.WEST;
			gbc_productErrorLabel.insets = new Insets(0, 0, 5, 5);
			gbc_productErrorLabel.gridx = 2;
			gbc_productErrorLabel.gridy = 2;
			contentPanel.add(productErrorLabel, gbc_productErrorLabel);
			
			//Page number*********************************************************************
			tablePageLabel = new JLabel("<html><u>1</u></html>");
			tablePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
			GridBagConstraints gbc_tablePageLabel = new GridBagConstraints();
			gbc_tablePageLabel.insets = new Insets(0, 0, 5, 5);
			gbc_tablePageLabel.gridx = 1;
			gbc_tablePageLabel.gridy = 3;
			contentPanel.add(tablePageLabel, gbc_tablePageLabel);
			
			//Left Arrow "<" ******************************************************************
			//A Label that loads the previous page when clicked
			leftArrowLabel = new JLabel(" < ");
			leftArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			leftArrowLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					searchBar.getDocument().removeDocumentListener(cl);
					searchBar.setText("🔍 Product Name...");
					searchBar.getDocument().addDocumentListener(cl);
					loadPage(getPageIndex() - 1);
				}
			});
			
			GridBagConstraints gbc_leftArrowLabel = new GridBagConstraints();
			gbc_leftArrowLabel.anchor = GridBagConstraints.EAST;
			gbc_leftArrowLabel.insets = new Insets(0, 0, 5, 5);
			gbc_leftArrowLabel.gridx = 0;
			gbc_leftArrowLabel.gridy = 3;
			contentPanel.add(leftArrowLabel, gbc_leftArrowLabel);
			
			//Right Arrow ">" ******************************************************************
			//A Label that loads the next page when clicked
			rightArrowLabel = new JLabel(" > ");
			rightArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rightArrowLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					searchBar.setFocusable(false);
					searchBar.setFocusable(true);
					searchBar.getDocument().removeDocumentListener(cl);
					searchBar.setText("🔍 Product Name...");
					searchBar.getDocument().addDocumentListener(cl);
					loadPage(getPageIndex() + 1);
				}
			});
			
			GridBagConstraints gbc_rightArrowLabel = new GridBagConstraints();
			gbc_rightArrowLabel.anchor = GridBagConstraints.WEST;
			gbc_rightArrowLabel.insets = new Insets(0, 0, 5, 5);
			gbc_rightArrowLabel.gridx = 2;
			gbc_rightArrowLabel.gridy = 3;
			contentPanel.add(rightArrowLabel, gbc_rightArrowLabel);
		}
		
		/********************************************** Product Table **********************************************/
		{
			table = new JTable();
			table.setName("AddProductsDialog");
			
			//Changes the Color of the header
			JTableHeader header = table.getTableHeader();
			header.setBackground(babyBlue);
			header.setForeground(Color.WHITE);
			header.setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));
			header.setFont(new Font("Lato", Font.BOLD, 14));
			header.setReorderingAllowed(false);
			DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
			defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
			header.setDefaultRenderer(defaultHeaderRenderer);

			table.setRowSelectionAllowed(false);
			table.setFocusable(false);
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Lato", Font.PLAIN, 14));
			table.setShowVerticalLines(false);
			table.setRowHeight(50);
			
			TableCellRenderer tableRenderer = table.getDefaultRenderer(RoundedButton.class);
		    table.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));
			
			//Setting the number of columns and their headers
			table.setModel(new DefaultTableModel(new Object[][] {}, tableElements) 
			{
				//Variables of the class
				Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, RoundedButton.class
				};
				
				//Methods of the class
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, true, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			
			table.addMouseListener(new JTableButtonMouseListener(table,this));
			table.addMouseMotionListener(new JTableButtonMouseListener(table));
			
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.gridwidth = 5;
			gbc_table.insets = new Insets(0, 0, 5, 0);
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 4;
			
			//Table Scroll Pane********************************************************************
			JScrollPane tablePane = new JScrollPane(table);
			tablePane.setBorder(BorderFactory.createEmptyBorder());
			contentPanel.add(tablePane, gbc_table);
			loadPage(1);
		}
		{
			//Finish Button***************************************************************************
			//Button used to confirm creating a new product to be added to a list of products
			RoundedButton finishButton = new RoundedButton("Finish", new Color(28, 150, 202), Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			finishButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					finishCreation();
				}
			});
			formatButton(finishButton);
			blueButton(finishButton);
			
			GridBagConstraints gbc_finishButton = new GridBagConstraints();
			gbc_finishButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_finishButton.insets = new Insets(0, 0, 0, 5);
			gbc_finishButton.gridx = 3;
			gbc_finishButton.gridy = 5;
			contentPanel.add(finishButton, gbc_finishButton);
		}
			
			//Cancel Button*************************************************************************
			cancelButton = new RoundedButton("Cancel", Color.WHITE, new Color(28, 150, 202), new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			formatButton(cancelButton);
			whiteButton(cancelButton);
			
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 5;
			contentPanel.add(cancelButton, gbc_cancelButton);
		}

	/** 
	 * This method is used to set size and offset to the buttons
	 */
	private void formatButton(RoundedButton button) {
		button.setPreferredSize(new Dimension(100, 30));
		button.addOffset(-5, 2);
	}
	
	/**
	 * This method is used to add change color functions for blue buttons 
	 * when mouse enters or exits
	 */
	private void blueButton(RoundedButton button) {
		button.addMouseListener(new MouseAdapter()
		{	
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
	
	/*
	 * This method is used to add change color functions for white buttons 
	 * when mouse enters or exits
	 */
	private void whiteButton(RoundedButton button) {
		button.addMouseListener(new MouseAdapter()
		{	
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				button.setBackground(babyBlue);
				button.setForeground(Color.WHITE);
			}
					
			@Override
			public void mouseExited(MouseEvent e) 
			{
				button.setBackground(Color.WHITE);
				button.setForeground(babyBlue);
			}
		});
	}
	
	/**
	 * The method helps us fill the table, by retrieving data and 
	 * inserting it. 
	 * 
	 */
	private void defaultFillTable(int index) {
		//we get the product information from the productContainer
		ArrayList<String[]> data = orderCtr.fillTable(index, "");
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		DefaultTableModel createOrderPanelTabledtm = (DefaultTableModel) createOrderPanelTable.getModel();
		dtm.setRowCount(0);
		dtm.setRowCount(data.size());
		
		//used for detecting if the table already contains the barcode.
		//if yes, we created the "Added" button instead of "Add"
		for (int e = 0; e<data.size();e++)
		{
			table.setValueAt(data.get(e)[3], e, table.convertColumnIndexToView(table.getColumn(tableElements[0]).getModelIndex()));
			table.setValueAt(data.get(e)[0], e, table.convertColumnIndexToView(table.getColumn(tableElements[1]).getModelIndex()));
			table.setValueAt(data.get(e)[4], e, table.convertColumnIndexToView(table.getColumn(tableElements[2]).getModelIndex()));
			table.setValueAt("", e, table.getColumn("Input Quantity").getModelIndex());
			table.setValueAt(data.get(e)[5], e, table.convertColumnIndexToView(table.getColumn(tableElements[4]).getModelIndex()));
			table.setValueAt(data.get(e)[2], e, table.convertColumnIndexToView(table.getColumn(tableElements[5]).getModelIndex()));
			
			
			for (int rows = 0; rows < createOrderPanelTabledtm.getRowCount(); rows++)
			{
				String a = createOrderPanelTable.getValueAt(rows, createOrderPanelTable.getColumn("Barcode").getModelIndex()).toString();
				String b = data.get(e)[3].toString();
				/*
				 * We check if the barcode is in the other table, or if the product has already been added
				 * either through previously opening the dialog, or by pressing the add button.
				 */
				
				if (!a.equals(b) && !productPlace.get(rows)[0].equals(a))
				{
					
					table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
					
					break;
				}
				else
				{
					table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
				}
			}
			if(createOrderPanelTabledtm.getRowCount() == 0) 
			{
				
				table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
			}
			
			if (dtm.getRowCount() != 0)
			{
				for (String[] string : productPlace)
				{
					if(data.get(e)[3].equals(string[1]))
					{
						table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
						table.setValueAt(string[2], e, table.getColumn("Input Quantity").getModelIndex());
						break;
					}
					else
					{
						table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
					}
				}
			}
		}
	}
	
	/**
	 * This method is used to search for specific products using the Search Bar through the product list
	 * @param notDynamic - if true, enter was pressed, false means that the search is happening as letters are written
	 */
	private void searchProduct(Boolean notDynamic)
	{
		productErrorLabel.setVisible(false);
		if ((!searchBar.getText().equals(""))&&(!searchBar.getText().equals("🔍 Product Name...")))
		{
			ArrayList<String[]> data = orderCtr.fillTable(getPageIndex(), searchBar.getText());
			
			if (data.size() != 0)
			{ 
				productErrorLabel.setVisible(false);
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				DefaultTableModel createOrderPanelTabledtm = (DefaultTableModel) createOrderPanelTable.getModel();
				dtm.setRowCount(data.size());
				for (int e = 0; e < data.size();e++)
				{
					table.setValueAt(data.get(e)[3], e, table.convertColumnIndexToView(table.getColumn(tableElements[0]).getModelIndex()));
					table.setValueAt(data.get(e)[0], e, table.convertColumnIndexToView(table.getColumn(tableElements[1]).getModelIndex()));
					table.setValueAt(data.get(e)[4], e, table.convertColumnIndexToView(table.getColumn(tableElements[2]).getModelIndex()));
					table.setValueAt("", e, table.getColumn("Input Quantity").getModelIndex());
					table.setValueAt(data.get(e)[5], e, table.convertColumnIndexToView(table.getColumn(tableElements[4]).getModelIndex()));
					table.setValueAt(data.get(e)[2], e, table.convertColumnIndexToView(table.getColumn(tableElements[5]).getModelIndex()));
					
					for (int rows = 0; rows < createOrderPanelTabledtm.getRowCount(); rows++)
					{
						String a = createOrderPanelTable.getValueAt(rows, createOrderPanelTable.getColumn("Barcode").getModelIndex()).toString();
						String b = data.get(e)[3].toString();
						
						/*
						 * We check if the barcode is in the other table, or if the product has already been added
						 * either through previously opening the dialog, or by pressing the add button.
						 */
						
						if (!a.equals(b) && !productPlace.get(rows)[0].equals(a))
						{
							table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
							
							break;
						}
						else
						{
							table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
						}
					}
					
					if (dtm.getRowCount() != 0)
					{
						for (String[] string : productPlace)
						{
							if(data.get(e)[3].equals(string[1]))
							{
								table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
								table.setValueAt(string[2], e, table.getColumn("Input Quantity").getModelIndex());
								break;
							}
							else
							{
								table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
							}
						}
						
					}
					
					
					
				}
			}
			else
			{
				if (notDynamic)
				{
					productErrorLabel.setVisible(true);
				}
			}
		}
		else
		{
			loadPage(1);
		}
	}
	
	/**
	 * Based on a products place in the list, we add it to our
	 * product list.
	 * @param placeInList - specific place in list
	 */
	public void addToList(String placeInList, String barcode, String quantity) 
	{
		productPlace.add(new String[] {placeInList,barcode,quantity});
	}
	
	/**
	 * Happens when the Finish Button is pressed, it takes all the information from creation of the product
	 * and puts it in the table
	 */
	private void finishCreation() {
		if (productPlace.size() == 0)
		{
			return;
		}
		JPanel tempPanel = (JPanel)createOrderPanelTable.getParent().getParent().getParent();
		for(int i = 0; i < tempPanel.getComponentCount(); i++)
		{
			try 
			{
				JLabel label = (JLabel)tempPanel.getComponent(i);
				if (label.getText().contains("select a product"))
				{
					label.setVisible(false);
					break;
				}	
			}
			catch (Exception e){}
		}
		DefaultTableModel dtm = (DefaultTableModel) createOrderPanelTable.getModel();
		
		int rowCount = dtm.getRowCount();
		dtm.setRowCount(productPlace.size() + rowCount);
		int quantity = 1;
		for (int e = 0; e < productPlace.size(); e++)
		{
			String name = (String) table.getValueAt(Integer.parseInt(productPlace.get(e)[0]), table.getColumn("Name").getModelIndex());
			String barcode = (String)table.getValueAt(Integer.parseInt(productPlace.get(e)[0]), table.getColumn("Barcode").getModelIndex());
			int discount = Integer.parseInt((String)table.getValueAt(Integer.parseInt(productPlace.get(e)[0]), table.getColumn("Discount").getModelIndex()));
			float price = Float.valueOf(((String)table.getValueAt(Integer.parseInt(productPlace.get(e)[0]), table.getColumn("Price").getModelIndex())));
				quantity = Integer.parseInt((String)table.getValueAt(Integer.parseInt(productPlace.get(e)[0]), table.getColumn("Input Quantity").getModelIndex()));
			String totalPrice = String.format("%.2f", (quantity * price));
			
			orderCtr.selectProduct(Integer.parseInt(productPlace.get(e)[0]), quantity);
			
			createOrderPanelTable.setValueAt(name, e + rowCount, createOrderPanelTable.getColumn("Name").getModelIndex());
			createOrderPanelTable.setValueAt(barcode, e + rowCount, createOrderPanelTable.getColumn("Barcode").getModelIndex());
			createOrderPanelTable.setValueAt(discount, e + rowCount, createOrderPanelTable.getColumn("Discount").getModelIndex());
			createOrderPanelTable.setValueAt(price, e + rowCount, createOrderPanelTable.getColumn("Price").getModelIndex());
			createOrderPanelTable.setValueAt(quantity, e + rowCount, createOrderPanelTable.getColumn("Quantity").getModelIndex());
			createOrderPanelTable.setValueAt(totalPrice, e + rowCount, createOrderPanelTable.getColumn("Total").getModelIndex());
			
			createOrderPanelTable.setValueAt(new RoundedButton("Remove", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e + rowCount, createOrderPanelTable.getColumn("").getModelIndex());
		}
		dispose();
	}
	
	/**
	 * @return - the page number
	 */
	public int getPageIndex()  
	{
		return Integer.parseInt(tablePageLabel.getText().replaceAll("\\<.*?\\>", ""));
	}
	
	/**
	 * This method loads a certain page of the product table
	 * @param index - the page number
	 */
	public void loadPage(int index)
	{
		productErrorLabel.setVisible(false);
		if(index == 0) {
			return;
		}

		if (orderCtr.fillTable(index, "").isEmpty())
		{
			return;
		}
		
		defaultFillTable(index);
		
		//Sets the page number
		tablePageLabel.setText("<html><u>" + index + "</u></html>");
	}
}

package guiLayer;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import controlLayer.*;
import guiLayer.Renderers.JTableButtonMouseListener;
import guiLayer.Renderers.JTableButtonRenderer;

public class CreateOrderPanel extends JPanel {
	private JTextField searchBar;
	private JTable orderTable;
	private Color babyBlue = new Color(28, 150, 202);
	private OrderCtr orderCtr = new OrderCtr();
	private JLabel customerErrorLabel;
	private JLabel orderErrorLabel;
	private JLabel nameValueLabel;
	private JLabel groupValueLabel;
	private JLabel phoneValueLabel;
	private JLabel deleteButton;
	private String[] orderTableElements = {"Barcode", "Name", "Price", "Quantity", "Discount", "Total", ""};

	/**
	 * Create the panel.
	 */
	public CreateOrderPanel() {
		System.setProperty("file.encoding","UTF-8");
		setBackground(new Color(252, 252, 252));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 10, 20};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 123, 0, 0, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.8, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel createOrderLabel = new JLabel("Create Offer/Order");
		createOrderLabel.setHorizontalAlignment(SwingConstants.LEFT);
		createOrderLabel.setFont(new Font("Lato", Font.BOLD, 35));
		GridBagConstraints gbc_createOrderLabel = new GridBagConstraints();
		gbc_createOrderLabel.anchor = GridBagConstraints.WEST;
		gbc_createOrderLabel.insets = new Insets(0, 0, 5, 0);
		gbc_createOrderLabel.gridx = 1;
		gbc_createOrderLabel.gridy = 1;
		add(createOrderLabel, gbc_createOrderLabel);
		
		/********************************************** Customer Panel **********************************************/
		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
		customerPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_customerPanel = new GridBagConstraints();
		gbc_customerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_customerPanel.fill = GridBagConstraints.BOTH;
		gbc_customerPanel.gridx = 1;
		gbc_customerPanel.gridy = 3;
		add(customerPanel, gbc_customerPanel);
		GridBagLayout gbl_customerPanel = new GridBagLayout();
		gbl_customerPanel.columnWidths = new int[]{15, 0, 0, 200, 10, 0, 0, 0, 15};
		gbl_customerPanel.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 10};
		gbl_customerPanel.columnWeights = new double[]{0.0, 0.2, 0.2, 0.2, 0.0, 0.2, 0.2, 0.0, Double.MIN_VALUE};
		gbl_customerPanel.rowWeights = new double[]{0.0, 0.0, 0.1, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		customerPanel.setLayout(gbl_customerPanel);
		
		//Customer Panel Header********************************************************
		JLabel customerLabel = new JLabel("Customer Details");
		customerLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.gridwidth = 2;
		gbc_customerLabel.anchor = GridBagConstraints.WEST;
		gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerLabel.gridx = 1;
		gbc_customerLabel.gridy = 1;
		customerPanel.add(customerLabel, gbc_customerLabel);
		
		//Customer Panel Search Bar********************************************************
		searchBar = new JTextField();
		searchBar.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		searchBar.setText("🔍 Phone number...");
		searchBar.setForeground(new Color(149, 149, 149));
		searchBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					searchBar.setFocusable(false);
					searchBar.setFocusable(true);
					if (searchBar.getText().equals(""))
					{
						searchBar.setForeground(new Color(149, 149, 149));
						searchBar.setText("🔍 Phone number...");
						
					}
					else
					{
						searchCustomer();
					}
			}
		});
		searchBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchBar.setFocusable(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (searchBar.getText().equals("🔍 Phone number..."))
				{
					searchBar.setForeground(Color.BLACK);
					searchBar.setText("");
				}
			}
		});
		
		customerErrorLabel = new JLabel("Customer not found!");
		customerErrorLabel.setVisible(false);
		customerErrorLabel.setForeground(Color.RED);
		customerErrorLabel.setFont(new Font("Lato", Font.BOLD, 14));
		GridBagConstraints gbc_customerErrorLabel = new GridBagConstraints();
		gbc_customerErrorLabel.anchor = GridBagConstraints.EAST;
		gbc_customerErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerErrorLabel.gridx = 3;
		gbc_customerErrorLabel.gridy = 1;
		customerPanel.add(customerErrorLabel, gbc_customerErrorLabel);
		
		GridBagConstraints gbc_searchBar = new GridBagConstraints();
		gbc_searchBar.insets = new Insets(0, 0, 5, 5);
		gbc_searchBar.fill = GridBagConstraints.BOTH;
		gbc_searchBar.gridx = 5;
		gbc_searchBar.gridy = 1;
		customerPanel.add(searchBar, gbc_searchBar);
		searchBar.setColumns(10);
		

		//Customer Panel Add Customer Button********************************************************
		RoundedButton addCustomerButton = new RoundedButton("➕ Add Customer", babyBlue,
				Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 14));
		addCustomerButton.addOffset(-17, 2);
		blueButton(addCustomerButton);
		
		addCustomerButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				searchCustomer();
			}
		});
		
		GridBagConstraints gbc_addCustomerButton = new GridBagConstraints();
		gbc_addCustomerButton.fill = GridBagConstraints.BOTH;
		gbc_addCustomerButton.insets = new Insets(0, 0, 5, 5);
		gbc_addCustomerButton.gridx = 6;
		gbc_addCustomerButton.gridy = 1;
		customerPanel.add(addCustomerButton, gbc_addCustomerButton);
		
		//Customer Panel Separator Line********************************************************
		JSeparator separator = new JSeparator();
		separator.setBackground(babyBlue);
		separator.setOpaque(true);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 9;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		customerPanel.add(separator, gbc_separator);
		
		//Customer Panel Name Label********************************************************
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Lato", Font.PLAIN, 14));
		nameLabel.setForeground(babyBlue);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.WEST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 3;
		customerPanel.add(nameLabel, gbc_nameLabel);
		
		//Customer Panel Group Label********************************************************
		JLabel groupLabel = new JLabel("Group:");
		groupLabel.setFont(new Font("Lato", Font.PLAIN, 14));
		groupLabel.setForeground(babyBlue);
		GridBagConstraints gbc_groupLabel = new GridBagConstraints();
		gbc_groupLabel.anchor = GridBagConstraints.WEST;
		gbc_groupLabel.insets = new Insets(0, 0, 5, 5);
		gbc_groupLabel.gridx = 2;
		gbc_groupLabel.gridy = 3;
		customerPanel.add(groupLabel, gbc_groupLabel);
		
		//Customer Panel Phone Number Label********************************************************
		JLabel phoneLabel = new JLabel("Phone Number:");
		phoneLabel.setFont(new Font("Lato", Font.PLAIN, 14));
		phoneLabel.setForeground(babyBlue);
		GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
		gbc_phoneLabel.anchor = GridBagConstraints.WEST;
		gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneLabel.gridx = 3;
		gbc_phoneLabel.gridy = 3;
		customerPanel.add(phoneLabel, gbc_phoneLabel);
		
		//Customer Panel Name Value Label********************************************************
		nameValueLabel = new JLabel("...");
		nameValueLabel.setFont(new Font("Lato", Font.PLAIN, 14));
		GridBagConstraints gbc_nameValueLabel = new GridBagConstraints();
		gbc_nameValueLabel.anchor = GridBagConstraints.WEST;
		gbc_nameValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameValueLabel.gridx = 1;
		gbc_nameValueLabel.gridy = 4;
		customerPanel.add(nameValueLabel, gbc_nameValueLabel);
		
		//Customer Panel Group Value Label********************************************************
		groupValueLabel = new JLabel("...");
		groupValueLabel.setFont(new Font("Lato", Font.PLAIN, 14));
		GridBagConstraints gbc_groupValueLabel = new GridBagConstraints();
		gbc_groupValueLabel.anchor = GridBagConstraints.WEST;
		gbc_groupValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_groupValueLabel.gridx = 2;
		gbc_groupValueLabel.gridy = 4;
		customerPanel.add(groupValueLabel, gbc_groupValueLabel);
		
		//Customer Panel Phone Value Label********************************************************
		phoneValueLabel = new JLabel("...");
		phoneValueLabel.setFont(new Font("Lato", Font.PLAIN, 14));
		GridBagConstraints gbc_phoneValueLabel = new GridBagConstraints();
		gbc_phoneValueLabel.anchor = GridBagConstraints.WEST;
		gbc_phoneValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneValueLabel.gridx = 3;
		gbc_phoneValueLabel.gridy = 4;
		customerPanel.add(phoneValueLabel, gbc_phoneValueLabel);
		
		deleteButton = new JLabel("X");
		deleteButton.setVisible(false);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearCustomerLabels();
				deleteButton.setVisible(false);
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteButton.setForeground(Color.RED);
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 4;
		gbc_deleteButton.gridy = 4;
		customerPanel.add(deleteButton, gbc_deleteButton);
		
		/********************************************** Product Panel **********************************************/
		JPanel productPanel = new JPanel();
		productPanel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
		productPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_productPanel = new GridBagConstraints();
		gbc_productPanel.fill = GridBagConstraints.BOTH;
		gbc_productPanel.gridx = 1;
		gbc_productPanel.gridy = 4;
		add(productPanel, gbc_productPanel);

		GridBagLayout gbl_productPanel = new GridBagLayout();
		gbl_productPanel.columnWidths = new int[]{15, 0, 0, 135, 135, 135, 15};
		gbl_productPanel.rowHeights = new int[]{10, 0, 0, 0, 33};
		gbl_productPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_productPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		productPanel.setLayout(gbl_productPanel);
		
		//Product Panel Header********************************************************
		JLabel detailsLabel = new JLabel("Order Details");
		detailsLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_detailsLabel = new GridBagConstraints();
		gbc_detailsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_detailsLabel.anchor = GridBagConstraints.WEST;
		gbc_detailsLabel.gridx = 1;
		gbc_detailsLabel.gridy = 1;
		productPanel.add(detailsLabel, gbc_detailsLabel);
		
		//Product Panel Table of Products********************************************************
		orderTable = new JTable();
		orderTable.setName("CreateOrderPanel");
		JTableHeader header = orderTable.getTableHeader();
		header.setFont(new Font("Lato", Font.BOLD, 14));
		header.setBackground(babyBlue);
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(orderTable.getTableHeader().getWidth(), 50));
		
		// Here we format the table to make it look nice, and remove selections functions we do not wish to include
		orderTable.setRowSelectionAllowed(false);
		orderTable.setFocusable(false);
		orderTable.setFillsViewportHeight(true);
		orderTable.setFont(new Font("Lato", Font.PLAIN, 14));
		orderTable.setShowVerticalLines(false);
		orderTable.setRowHeight(50);
		
		TableCellRenderer tableRenderer = orderTable.getDefaultRenderer(RoundedButton.class);
		orderTable.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));
		
		DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) orderTable.getTableHeader().getDefaultRenderer();
		defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
		orderTable.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);
		
		
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			orderTableElements
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, RoundedButton.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(0).setMinWidth(50);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(1).setMinWidth(50);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(2).setMinWidth(50);
		orderTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(3).setMinWidth(50);
		orderTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(4).setMinWidth(50);
		orderTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(5).setMinWidth(50);
		orderTable.setFont(new Font("Lato", Font.PLAIN, 14));
		GridBagConstraints gbc_orderTable = new GridBagConstraints();
		gbc_orderTable.gridwidth = 5;
		gbc_orderTable.insets = new Insets(0, 0, 5, 5);
		gbc_orderTable.fill = GridBagConstraints.BOTH;
		gbc_orderTable.gridx = 1;
		gbc_orderTable.gridy = 2;
		
		DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
		dtm.setRowCount(0);
		orderTable.addMouseListener(new JTableButtonMouseListener(orderTable, orderCtr));
		orderTable.addMouseMotionListener(new JTableButtonMouseListener(orderTable, orderCtr));
		
		
		//We remove the border of the table by making it empty
		JScrollPane tablePane = new JScrollPane(orderTable); //It is added to a JScrollPane to ensure we have a header
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		productPanel.add(tablePane, gbc_orderTable);
		
		
		//Product Panel Add Product Button********************************************************
		RoundedButton addProductsButton = new RoundedButton("➕ Add Products", babyBlue,
						Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
		addProductsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddProductsDialog dialog = new AddProductsDialog(orderTable, orderCtr);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
				dialog.setVisible(true);
			}
		});
		addProductsButton.addOffset(-17, 2);
		blueButton(addProductsButton);
		GridBagConstraints gbc_addProductsButton = new GridBagConstraints();
		gbc_addProductsButton.insets = new Insets(0, 0, 5, 5);
		gbc_addProductsButton.fill = GridBagConstraints.BOTH;
		gbc_addProductsButton.gridx = 5;
		gbc_addProductsButton.gridy = 1;
		productPanel.add(addProductsButton, gbc_addProductsButton);
		
		
		//Product Panel Total price Label********************************************************
		JLabel totalLabel = new JLabel("Total Price");
		totalLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_totalLabel = new GridBagConstraints();
		gbc_totalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalLabel.gridx = 4;
		gbc_totalLabel.gridy = 3;
		productPanel.add(totalLabel, gbc_totalLabel);
		
		//Product Panel Total Price Calculated********************************************************
		JLabel totalValueLabel = new JLabel("... DKK");
		totalValueLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_totalValueLabel = new GridBagConstraints();
		gbc_totalValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalValueLabel.gridx = 5;
		gbc_totalValueLabel.gridy = 3;
		productPanel.add(totalValueLabel, gbc_totalValueLabel);
		
		//Error Order JLabel********************************************************
		orderErrorLabel = new JLabel("Please add a customer!");
		orderErrorLabel.setVisible(false);
		orderErrorLabel.setForeground(Color.RED);
		orderErrorLabel.setFont(new Font("Lato", Font.BOLD, 14));
		GridBagConstraints gbc_orderErrorLabel = new GridBagConstraints();
		gbc_orderErrorLabel.anchor = GridBagConstraints.EAST;
		gbc_orderErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_orderErrorLabel.gridx = 2;
		gbc_orderErrorLabel.gridy = 4;
		productPanel.add(orderErrorLabel, gbc_orderErrorLabel);
		
		//Product Panel Create Offer Button********************************************************
		RoundedButton createOfferButton = new RoundedButton("Create Offer", babyBlue,
						Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
		createOfferButton.addOffset(-9, 2);
		blueButton(createOfferButton);
		createOfferButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
				if (phoneValueLabel.getText().equals("..."))
				{
					orderErrorLabel.setVisible(true);
				}
				else if (dtm.getRowCount() == 0)
				{
					orderErrorLabel.setText("Please select a product!");
					orderErrorLabel.setVisible(true);
				}
				else
				{
					orderCtr.createOffer();
					reset();
				}
			}
			
			
		});
		GridBagConstraints gbc_createOfferButton = new GridBagConstraints();
		gbc_createOfferButton.fill = GridBagConstraints.BOTH;
		gbc_createOfferButton.insets = new Insets(0, 0, 5, 5);
		gbc_createOfferButton.gridx = 3;
		gbc_createOfferButton.gridy = 4;
		productPanel.add(createOfferButton, gbc_createOfferButton);
		
		//Product Panel Place Order Button********************************************************
		RoundedButton placeOrderButton = new RoundedButton("Place Order", babyBlue,
						Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
		placeOrderButton.addOffset(-8, 2);
		placeOrderButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
				if (phoneValueLabel.getText().equals("..."))
				{
					orderErrorLabel.setVisible(true);
				}
				else if (dtm.getRowCount() == 0)
				{
					orderErrorLabel.setText("Please select a product!");
					orderErrorLabel.setVisible(true);
				}
				else
				{
					orderCtr.createOrder();
					reset();
				}
			}
			
			
		});
		blueButton(placeOrderButton);
		GridBagConstraints gbc_placeOrderButton = new GridBagConstraints();
		gbc_placeOrderButton.fill = GridBagConstraints.BOTH;
		gbc_placeOrderButton.insets = new Insets(0, 0, 5, 5);
		gbc_placeOrderButton.gridx = 4;
		gbc_placeOrderButton.gridy = 4;
		productPanel.add(placeOrderButton, gbc_placeOrderButton);
		
		//Product Panel Cancel Button********************************************************
		RoundedButton cancelButton = new RoundedButton("Cancel", Color.WHITE,
						babyBlue, babyBlue, new Font("Lato", Font.BOLD, 15));
		cancelButton.addOffset(-6, 2);
		whiteButton(cancelButton);
		cancelButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.BOTH;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 5;
		gbc_cancelButton.gridy = 4;
		productPanel.add(cancelButton, gbc_cancelButton);
	}

	
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
	
	public void whiteButton(RoundedButton button) {
		button.addMouseListener(new MouseAdapter() {
			
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
	
	
	public void searchCustomer()
	{
		if(searchBar.getText().isEmpty() || searchBar.getText().equals("🔍 Phone number..."))
		{
			customerErrorLabel.setVisible(true);
			clearCustomerLabels();
			return;
		}
		
		String phone = searchBar.getText();
		String[] info = orderCtr.getCustomerInfo(phone);
		if (info == null)
		{
			return;
		}
		
		customerErrorLabel.setVisible(false);
		if(info[0].isEmpty())
		{
			customerErrorLabel.setVisible(true);
			clearCustomerLabels();
			deleteButton.setVisible(false);
			return;
		}
		deleteButton.setVisible(true);
		nameValueLabel.setText(info[0]);
		groupValueLabel.setText(info[1]);
		phoneValueLabel.setText(phone);
	}
	
	
	private void clearCustomerLabels()
	{
		nameValueLabel.setText("...");
		groupValueLabel.setText("...");
		phoneValueLabel.setText("...");
		if (searchBar.getText().equals(""))
		{
			searchBar.setForeground(new Color(149, 149, 149));
			searchBar.setText("🔍 Phone number...");
		}
	}

	
	
	public void reset()
	{
		DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
		orderErrorLabel.setVisible(false);
		dtm.setRowCount(0);
		orderCtr = new OrderCtr();
		searchBar.setFocusable(false);
		searchBar.setFocusable(true);
		if (searchBar.isFocusOwner())
		{
			searchBar.setForeground(Color.BLACK);
			searchBar.setText("");
		}
		else
		{
			searchBar.setForeground(new Color(149, 149, 149));
			searchBar.setText("🔍 Phone number...");
		}
		clearCustomerLabels();
		
		customerErrorLabel.setVisible(false);
		deleteButton.setVisible(false);
	}
	
	
}
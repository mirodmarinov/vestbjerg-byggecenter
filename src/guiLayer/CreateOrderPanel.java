package guiLayer;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class CreateOrderPanel extends JPanel {
	private JTextField searchBar;
	private JTable orderTable;

	/**
	 * Create the panel.
	 */
	public CreateOrderPanel() {
		setBackground(new Color(252, 252, 252));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 20};
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
		customerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		customerPanel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
		customerPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_customerPanel = new GridBagConstraints();
		gbc_customerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_customerPanel.fill = GridBagConstraints.BOTH;
		gbc_customerPanel.gridx = 1;
		gbc_customerPanel.gridy = 3;
		add(customerPanel, gbc_customerPanel);
		GridBagLayout gbl_customerPanel = new GridBagLayout();
		gbl_customerPanel.columnWidths = new int[]{15, 0, 0, 0, 0, 0, 0, 15};
		gbl_customerPanel.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 10};
		gbl_customerPanel.columnWeights = new double[]{0.0, 0.2, 0.2, 0.2, 0.2, 0.2, 0.0, Double.MIN_VALUE};
		gbl_customerPanel.rowWeights = new double[]{0.0, 0.0, 0.1, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		customerPanel.setLayout(gbl_customerPanel);
		
		//************Customer Panel Header
		JLabel customerLabel = new JLabel("Customer Details");
		customerLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.gridwidth = 3;
		gbc_customerLabel.anchor = GridBagConstraints.WEST;
		gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerLabel.gridx = 1;
		gbc_customerLabel.gridy = 1;
		customerPanel.add(customerLabel, gbc_customerLabel);
		
		//************Customer Panel Search Bar
		searchBar = new JTextField();
		searchBar.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		searchBar.setText("  \uD83D\uDD0D Phone number...");
		searchBar.setForeground(new Color(149, 149, 149));
		GridBagConstraints gbc_searchBar = new GridBagConstraints();
		gbc_searchBar.insets = new Insets(0, 0, 5, 5);
		gbc_searchBar.fill = GridBagConstraints.BOTH;
		gbc_searchBar.gridx = 4;
		gbc_searchBar.gridy = 1;
		customerPanel.add(searchBar, gbc_searchBar);
		searchBar.setColumns(10);
		

		//************Customer Panel Add Customer Button
		RoundedButton addCustomerButton = new RoundedButton("➕ Add Customer", new Color(234, 234, 234),
				new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		addCustomerButton.addOffset(-17, 2);
		GridBagConstraints gbc_addCustomerButton = new GridBagConstraints();
		gbc_addCustomerButton.fill = GridBagConstraints.BOTH;
		gbc_addCustomerButton.insets = new Insets(0, 0, 5, 5);
		gbc_addCustomerButton.gridx = 5;
		gbc_addCustomerButton.gridy = 1;
		customerPanel.add(addCustomerButton, gbc_addCustomerButton);
		
		//************Customer Panel Separator Line
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(237, 237, 237));
		separator.setOpaque(true);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 8;
		gbc_separator.insets = new Insets(0, 0, 0, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		customerPanel.add(separator, gbc_separator);
		
		//************Customer Panel Name Label
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		nameLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.WEST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 3;
		customerPanel.add(nameLabel, gbc_nameLabel);
		
		//************Customer Panel Group Label
		JLabel groupLabel = new JLabel("Group:");
		groupLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		groupLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_groupLabel = new GridBagConstraints();
		gbc_groupLabel.anchor = GridBagConstraints.WEST;
		gbc_groupLabel.insets = new Insets(0, 0, 5, 5);
		gbc_groupLabel.gridx = 2;
		gbc_groupLabel.gridy = 3;
		customerPanel.add(groupLabel, gbc_groupLabel);
		
		//************Customer Panel Phone Number Label
		JLabel phoneLabel = new JLabel("Phone Number:");
		phoneLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		phoneLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
		gbc_phoneLabel.anchor = GridBagConstraints.WEST;
		gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneLabel.gridx = 3;
		gbc_phoneLabel.gridy = 3;
		customerPanel.add(phoneLabel, gbc_phoneLabel);
		
		//************Customer Panel Name Value Label
		JLabel nameValueLabel = new JLabel("...");
		nameValueLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_nameValueLabel = new GridBagConstraints();
		gbc_nameValueLabel.anchor = GridBagConstraints.WEST;
		gbc_nameValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_nameValueLabel.gridx = 1;
		gbc_nameValueLabel.gridy = 4;
		customerPanel.add(nameValueLabel, gbc_nameValueLabel);
		
		//************Customer Panel Group Value Label
		JLabel groupValueLabel = new JLabel("...");
		groupValueLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_groupValueLabel = new GridBagConstraints();
		gbc_groupValueLabel.anchor = GridBagConstraints.WEST;
		gbc_groupValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_groupValueLabel.gridx = 2;
		gbc_groupValueLabel.gridy = 4;
		customerPanel.add(groupValueLabel, gbc_groupValueLabel);
		
		//************Customer Panel Phone Value Label
		JLabel phoneValueLabel = new JLabel("...");
		phoneValueLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_phoneValueLabel = new GridBagConstraints();
		gbc_phoneValueLabel.anchor = GridBagConstraints.WEST;
		gbc_phoneValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_phoneValueLabel.gridx = 3;
		gbc_phoneValueLabel.gridy = 4;
		customerPanel.add(phoneValueLabel, gbc_phoneValueLabel);
		
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
		
		JLabel detailsLabel = new JLabel("Order Details");
		detailsLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_detailsLabel = new GridBagConstraints();
		gbc_detailsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_detailsLabel.anchor = GridBagConstraints.WEST;
		gbc_detailsLabel.gridx = 1;
		gbc_detailsLabel.gridy = 1;
		productPanel.add(detailsLabel, gbc_detailsLabel);
		
		RoundedButton addProductsButton = new RoundedButton("➕ Add Products", new Color(234, 234, 234),
						new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		addProductsButton.addOffset(-17, 2);
		GridBagConstraints gbc_addProductsButton = new GridBagConstraints();
		gbc_addProductsButton.insets = new Insets(0, 0, 5, 5);
		gbc_addProductsButton.fill = GridBagConstraints.BOTH;
		gbc_addProductsButton.gridx = 5;
		gbc_addProductsButton.gridy = 1;
		productPanel.add(addProductsButton, gbc_addProductsButton);
		
		orderTable = new JTable();
		orderTable.setFont(new Font("Lato", Font.PLAIN, 14));
		GridBagConstraints gbc_orderTable = new GridBagConstraints();
		gbc_orderTable.gridwidth = 5;
		gbc_orderTable.insets = new Insets(0, 0, 5, 5);
		gbc_orderTable.fill = GridBagConstraints.BOTH;
		gbc_orderTable.gridx = 1;
		gbc_orderTable.gridy = 2;
		productPanel.add(orderTable, gbc_orderTable);
		
		JLabel totalLabel = new JLabel("Total Price");
		totalLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_totalLabel = new GridBagConstraints();
		gbc_totalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalLabel.gridx = 4;
		gbc_totalLabel.gridy = 3;
		productPanel.add(totalLabel, gbc_totalLabel);
		
		JLabel totalValueLabel = new JLabel("... DKK");
		totalValueLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_totalValueLabel = new GridBagConstraints();
		gbc_totalValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalValueLabel.gridx = 5;
		gbc_totalValueLabel.gridy = 3;
		productPanel.add(totalValueLabel, gbc_totalValueLabel);
		
		RoundedButton createOfferButton = new RoundedButton("Create Offer", new Color(234, 234, 234),
						new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		createOfferButton.addOffset(-17, 2);
		GridBagConstraints gbc_createOfferButton = new GridBagConstraints();
		gbc_createOfferButton.fill = GridBagConstraints.BOTH;
		gbc_createOfferButton.insets = new Insets(0, 0, 5, 5);
		gbc_createOfferButton.gridx = 3;
		gbc_createOfferButton.gridy = 4;
		productPanel.add(createOfferButton, gbc_createOfferButton);
		
		RoundedButton placeOrderButton = new RoundedButton("Place Order", new Color(234, 234, 234),
						new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		placeOrderButton.addOffset(-17, 2);
		GridBagConstraints gbc_placeOrderButton = new GridBagConstraints();
		gbc_placeOrderButton.fill = GridBagConstraints.BOTH;
		gbc_placeOrderButton.insets = new Insets(0, 0, 5, 5);
		gbc_placeOrderButton.gridx = 4;
		gbc_placeOrderButton.gridy = 4;
		productPanel.add(placeOrderButton, gbc_placeOrderButton);
		
		RoundedButton cancelButton = new RoundedButton("Cancel", new Color(234, 234, 234),
						new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		cancelButton.addOffset(-17, 2);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.BOTH;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 5;
		gbc_cancelButton.gridy = 4;
		productPanel.add(cancelButton, gbc_cancelButton);

	}
}
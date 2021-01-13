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

public class CreateOrderPanel extends JPanel {
	private JTextField searchBar;

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
		gbl_customerPanel.rowHeights = new int[]{10, 0, 0, 0, 0, 0};
		gbl_customerPanel.columnWeights = new double[]{0.0, 0.2, 0.2, 0.2, 0.2, 0.2, 0.0, Double.MIN_VALUE};
		gbl_customerPanel.rowWeights = new double[]{0.0, 0.0, 0.1, 0.0, 0.0, Double.MIN_VALUE};
		customerPanel.setLayout(gbl_customerPanel);
		
		JLabel customerLabel = new JLabel("Customer Details");
		customerLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.gridwidth = 3;
		gbc_customerLabel.anchor = GridBagConstraints.WEST;
		gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerLabel.gridx = 1;
		gbc_customerLabel.gridy = 1;
		customerPanel.add(customerLabel, gbc_customerLabel);
		
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
		
		
		RoundedButton addButton = new RoundedButton("➕ Add Customer", new Color(234, 234, 234),
				new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		addButton.addOffset(-17, 2);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.BOTH;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 5;
		gbc_addButton.gridy = 1;
		customerPanel.add(addButton, gbc_addButton);
		
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
		
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		nameLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.WEST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 3;
		customerPanel.add(nameLabel, gbc_nameLabel);
		
		JLabel groupLabel = new JLabel("Group:");
		groupLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		groupLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_groupLabel = new GridBagConstraints();
		gbc_groupLabel.anchor = GridBagConstraints.WEST;
		gbc_groupLabel.insets = new Insets(0, 0, 5, 5);
		gbc_groupLabel.gridx = 2;
		gbc_groupLabel.gridy = 3;
		customerPanel.add(groupLabel, gbc_groupLabel);
		
		JLabel phoneLabel = new JLabel("Phone Number:");
		phoneLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		phoneLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
		gbc_phoneLabel.anchor = GridBagConstraints.WEST;
		gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneLabel.gridx = 3;
		gbc_phoneLabel.gridy = 3;
		customerPanel.add(phoneLabel, gbc_phoneLabel);
		
		JLabel nameValueLabel = new JLabel("...");
		nameValueLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_nameValueLabel = new GridBagConstraints();
		gbc_nameValueLabel.anchor = GridBagConstraints.WEST;
		gbc_nameValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_nameValueLabel.gridx = 1;
		gbc_nameValueLabel.gridy = 4;
		customerPanel.add(nameValueLabel, gbc_nameValueLabel);
		
		JLabel groupValueLabel = new JLabel("...");
		groupValueLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_groupValueLabel = new GridBagConstraints();
		gbc_groupValueLabel.anchor = GridBagConstraints.WEST;
		gbc_groupValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_groupValueLabel.gridx = 2;
		gbc_groupValueLabel.gridy = 4;
		customerPanel.add(groupValueLabel, gbc_groupValueLabel);
		
		JLabel phoneValueLabel = new JLabel("...");
		phoneValueLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_phoneValueLabel = new GridBagConstraints();
		gbc_phoneValueLabel.anchor = GridBagConstraints.WEST;
		gbc_phoneValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_phoneValueLabel.gridx = 3;
		gbc_phoneValueLabel.gridy = 4;
		customerPanel.add(phoneValueLabel, gbc_phoneValueLabel);
		
		JPanel productPanel = new JPanel();
		productPanel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
		productPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_productPanel = new GridBagConstraints();
		gbc_productPanel.fill = GridBagConstraints.BOTH;
		gbc_productPanel.gridx = 1;
		gbc_productPanel.gridy = 4;
		add(productPanel, gbc_productPanel);
		
		

	}
}
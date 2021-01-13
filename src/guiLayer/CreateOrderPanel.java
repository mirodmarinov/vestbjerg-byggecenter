package guiLayer;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CreateOrderPanel extends JPanel {
	private JTextField searchBar;

	/**
	 * Create the panel.
	 */
	public CreateOrderPanel() {
		setBackground(new Color(252, 252, 252));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 20};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0, 0, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.2, 0.8, 0.0, Double.MIN_VALUE};
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
		customerPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_customerPanel = new GridBagConstraints();
		gbc_customerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_customerPanel.fill = GridBagConstraints.BOTH;
		gbc_customerPanel.gridx = 1;
		gbc_customerPanel.gridy = 3;
		add(customerPanel, gbc_customerPanel);
		GridBagLayout gbl_customerPanel = new GridBagLayout();
		gbl_customerPanel.columnWidths = new int[]{15, 0, 0, 0, 0};
		gbl_customerPanel.rowHeights = new int[]{10, 0, 0, 0};
		gbl_customerPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_customerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		customerPanel.setLayout(gbl_customerPanel);
		
		JLabel customerLabel = new JLabel("Customer Details");
		customerLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.anchor = GridBagConstraints.EAST;
		gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerLabel.gridx = 1;
		gbc_customerLabel.gridy = 1;
		customerPanel.add(customerLabel, gbc_customerLabel);
		
		searchBar = new JTextField();
		searchBar.setText("  \uD83D\uDD0D Phone number...");
		searchBar.setForeground(new Color(149, 149, 149));
		GridBagConstraints gbc_searchBar = new GridBagConstraints();
		gbc_searchBar.insets = new Insets(0, 0, 5, 5);
		gbc_searchBar.fill = GridBagConstraints.BOTH;
		gbc_searchBar.gridx = 2;
		gbc_searchBar.gridy = 1;
		customerPanel.add(searchBar, gbc_searchBar);
		searchBar.setColumns(10);
		/*
		RoundedButton addButton = new RoundedButton("  ➕ Add Customer", new Color(234, 234, 234),
				new Color(143, 143, 143), new Color(143, 143, 143), new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 0);
		gbc_addButton.gridx = 3;
		gbc_addButton.gridy = 1;
		customerPanel.add(addButton, gbc_addButton);
		*/
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		nameLabel.setForeground(new Color(196, 196, 196));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_nameLabel.anchor = GridBagConstraints.WEST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 2;
		customerPanel.add(nameLabel, gbc_nameLabel);
		
		JPanel productPanel = new JPanel();
		productPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_productPanel = new GridBagConstraints();
		gbc_productPanel.fill = GridBagConstraints.BOTH;
		gbc_productPanel.gridx = 1;
		gbc_productPanel.gridy = 4;
		add(productPanel, gbc_productPanel);

	}
}
package guiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controlLayer.CustomerCtr;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CreateCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private JLabel headerLabel;
	private RoundedButton cancelButton;
	private JPanel panel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JTextField discountTextField;
	private JLabel phoneLabel;
	private JTextField phoneTextField;
	private JLabel groupLabel;
	private JTextField groupTextField;
	private JLabel addressLabel;
	private JTextField addressTextField;
	private JPanel header;
	private CustomerCtr customerCtr;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CreateCustomerDialog dialog = new CreateCustomerDialog(String productNameAndID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CreateCustomerDialog() {
		setBounds(100, 100, 567, 599);
		setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			header = new JPanel();
			header.setBackground(babyBlue);
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.gridwidth = 2;
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			contentPanel.add(header, gbc_panel_1);
			{
				
				headerLabel = new JLabel("Create Customer");
				header.add(headerLabel);
				headerLabel.setVisible(true);
				headerLabel.setForeground(Color.WHITE);
				headerLabel.setFont(new Font("LATO", Font.BOLD, 20));
			}
		}
		{
			panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 2;
			gbc_panel.insets = new Insets(20, 50, 20, 50);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0};
			gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				nameLabel = new JLabel("Name");
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.anchor = GridBagConstraints.WEST;
				gbc_nameLabel.insets = new Insets(0, 10, 5, 0);
				gbc_nameLabel.gridx = 0;
				gbc_nameLabel.gridy = 1;
				panel.add(nameLabel, gbc_nameLabel);
			}
			{
				nameTextField = new JTextField();
				nameTextField.setName("Name");
				textFieldFunctions(nameTextField);
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 10, 20, 10);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 0;
				gbc_nameTextField.gridy = 2;
				panel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			{
				phoneLabel = new JLabel("Phone");
				GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
				gbc_phoneLabel.anchor = GridBagConstraints.WEST;
				gbc_phoneLabel.insets = new Insets(0, 10, 5, 0);
				gbc_phoneLabel.gridx = 0;
				gbc_phoneLabel.gridy = 3;
				panel.add(phoneLabel, gbc_phoneLabel);
			}
			{
				phoneTextField = new JTextField();
				phoneTextField.setName("Phone");
				textFieldFunctions(phoneTextField);
				GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
				gbc_phoneTextField.insets = new Insets(0, 10, 20, 10);
				gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneTextField.gridx = 0;
				gbc_phoneTextField.gridy = 4;
				panel.add(phoneTextField, gbc_phoneTextField);
				phoneTextField.setColumns(10);
			}
			{
				addressLabel = new JLabel("Address");
				GridBagConstraints gbc_addressLabel = new GridBagConstraints();
				gbc_addressLabel.anchor = GridBagConstraints.WEST;
				gbc_addressLabel.insets = new Insets(0, 10, 5, 0);
				gbc_addressLabel.gridx = 0;
				gbc_addressLabel.gridy = 5;
				panel.add(addressLabel, gbc_addressLabel);
			}
			{
				addressTextField = new JTextField();
				addressTextField.setName("Address");
				textFieldFunctions(addressTextField);
				GridBagConstraints gbc_addressTextField = new GridBagConstraints();
				gbc_addressTextField.insets = new Insets(0, 10, 20, 10);
				gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_addressTextField.gridx = 0;
				gbc_addressTextField.gridy = 6;
				panel.add(addressTextField, gbc_addressTextField);
				addressTextField.setColumns(10);
			}
			{
				JLabel discountLabel = new JLabel("Discount");
				GridBagConstraints gbc_discountLabel = new GridBagConstraints();
				gbc_discountLabel.anchor = GridBagConstraints.WEST;
				gbc_discountLabel.insets = new Insets(0, 10, 5, 0);
				gbc_discountLabel.gridx = 0;
				gbc_discountLabel.gridy = 8;
				panel.add(discountLabel, gbc_discountLabel);
			}
			{
				discountTextField = new JTextField();
				discountTextField.setName("Discount");
				textFieldFunctions(discountTextField);
				GridBagConstraints gbc_discountTextField = new GridBagConstraints();
				gbc_discountTextField.insets = new Insets(0, 10, 20, 10);
				gbc_discountTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_discountTextField.gridx = 0;
				gbc_discountTextField.gridy = 9;
				panel.add(discountTextField, gbc_discountTextField);
				discountTextField.setColumns(10);
			}
			{
				groupLabel = new JLabel("Group");
				GridBagConstraints gbc_groupLabel = new GridBagConstraints();
				gbc_groupLabel.anchor = GridBagConstraints.WEST;
				gbc_groupLabel.insets = new Insets(0, 10, 5, 0);
				gbc_groupLabel.gridx = 0;
				gbc_groupLabel.gridy = 11;
				panel.add(groupLabel, gbc_groupLabel);
			}
			{
				groupTextField = new JTextField();
				groupTextField.setName("Group");
				textFieldFunctions(groupTextField);
				GridBagConstraints gbc_groupTextField = new GridBagConstraints();
				gbc_groupTextField.insets = new Insets(0, 10, 20, 10);
				gbc_groupTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_groupTextField.gridx = 0;
				gbc_groupTextField.gridy = 12;
				panel.add(groupTextField, gbc_groupTextField);
				groupTextField.setColumns(10);
			}
			fillFields();
		}
		
		{
			RoundedButton okButton = new RoundedButton("Finish", new Color(28, 150, 202), Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			formatButton(okButton);
			blueButton(okButton);
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_okButton.insets = new Insets(0, 0, 5, 5);
			gbc_okButton.gridx = 0;
			gbc_okButton.gridy = 2;
			contentPanel.add(okButton, gbc_okButton);
		}
		
			cancelButton = new RoundedButton("Cancel", Color.WHITE, new Color(28, 150, 202), new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
			formatButton(cancelButton);
			whiteButton(cancelButton);
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 1;
			gbc_cancelButton.gridy = 2;
			contentPanel.add(cancelButton, gbc_cancelButton);
		}
	

	
	private void formatButton(RoundedButton button) {
		button.setPreferredSize(new Dimension(100, 30));
		button.addOffset(-5, 2);
	}
	
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
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				finishCreation();
			}
			
		});
	}
	
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
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				dispose();
			}
		});
	}
	
	private void textFieldFunctions(JTextField field)
	{
		field.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		field.setForeground(new Color(149, 149, 149));
		field.setFocusable(false);
		field.addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				field.setFocusable(true);
			}
				
		});
		
		field.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (field.getText().equals(field.getName()+"..."))
				{
					field.setText("");
					field.setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e)
			{
				if (field.getText().equals(""))
				{
					field.setText(field.getName()+"...");
					field.setForeground(new Color(149, 149, 149));
				}
			}
		});

	}
	
	
	private void textPaneFunctions(JTextPane pane)
	{

	}
	
	private void finishCreation()
	{
		customerCtr = new CustomerCtr();
		//TODO Check all fields's value and create the product then dispose the window
		dispose();
	}

	private void fillFields()
	{
		nameTextField.setText("Name...");
		phoneTextField.setText("Phone...");
		addressTextField.setText("Address...");
		groupTextField.setText("Group...");
		discountTextField.setText("Discount...");
	}
	
}

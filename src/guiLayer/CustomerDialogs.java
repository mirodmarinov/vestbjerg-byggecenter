package guiLayer;

import controlLayer.CustomerCtr;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class CustomerDialogs extends JDialog {

	private int phone;
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
	private JLabel customerName;
	private CustomersPanel customersPanel;
	private boolean isEditCustomer;



	/**
	 * Create the dialog.
	 */
	public CustomerDialogs(CustomersPanel customersPanel,boolean isEditCustomer) {
		this.customersPanel = customersPanel;
		this.isEditCustomer = isEditCustomer;
		setBounds(100, 100, 567, 599);
		setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			//Blue Header Panel*****************************************************************
			header = new JPanel();
			header.setBackground(babyBlue);
			
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.gridwidth = 3;
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			contentPanel.add(header, gbc_panel_1);
			{
				//Manage Customer Label************************************************************
				if (isEditCustomer)
				{
					headerLabel = new JLabel("Manage Customer");
				}
				else
				{
					headerLabel = new JLabel("Create Customer");
				}
				header.add(headerLabel);
				headerLabel.setVisible(true);
				headerLabel.setForeground(Color.WHITE);
				headerLabel.setFont(new Font("LATO", Font.BOLD, 20));
			}
		}
		{
			if (isEditCustomer)
			{
				//Customer name Label*******************************************************************
				customerName = new JLabel("<CustomerNamePlaceHolder>");
				customerName.setHorizontalAlignment(SwingConstants.LEFT);
				customerName.setFont(new Font("LATO", Font.BOLD, 18));
				
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 1;
				contentPanel.add(customerName, gbc_lblNewLabel);
			}
		}
		{
			panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
			panel.setBackground(Color.WHITE);
			
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(20, 50, 20, 50);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0};
			gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			//Name Label and Text Field*******************************************************************
			{
				nameLabel = new JLabel("Name");
				
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.anchor = GridBagConstraints.WEST;
				gbc_nameLabel.insets = new Insets(0, 10, 10, 0);
				gbc_nameLabel.gridx = 0;
				gbc_nameLabel.gridy = 1;
				panel.add(nameLabel, gbc_nameLabel);
			}
			{
				nameTextField = new JTextField();
				
				nameTextField.setName("Name");
				textFieldFunctions(nameTextField);
				
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 10, 25, 10);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 0;
				gbc_nameTextField.gridy = 2;
				panel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
				if (isEditCustomer)
				{
					nameTextField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!nameTextField.getText().equals(""))
							{
								customerName.setText(nameTextField.getText());
							}
						}
					});
					}
				}
			
			//Phone Label and Text Field**********************************************************
			{
				phoneLabel = new JLabel("Phone");
				
				GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
				gbc_phoneLabel.anchor = GridBagConstraints.WEST;
				gbc_phoneLabel.insets = new Insets(0, 10, 10, 0);
				gbc_phoneLabel.gridx = 0;
				gbc_phoneLabel.gridy = 3;
				panel.add(phoneLabel, gbc_phoneLabel);
			}
			{
				phoneTextField = new JTextField();
				phoneTextField.setName("Phone");
				textFieldFunctions(phoneTextField);
				
				GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
				gbc_phoneTextField.insets = new Insets(0, 10, 25, 10);
				gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneTextField.gridx = 0;
				gbc_phoneTextField.gridy = 4;
				panel.add(phoneTextField, gbc_phoneTextField);
				phoneTextField.setColumns(10);
			}
			
			//Address Label and Text Field***************************************************
			{
				addressLabel = new JLabel("Address");
				
				GridBagConstraints gbc_addressLabel = new GridBagConstraints();
				gbc_addressLabel.anchor = GridBagConstraints.WEST;
				gbc_addressLabel.insets = new Insets(0, 10, 10, 0);
				gbc_addressLabel.gridx = 0;
				gbc_addressLabel.gridy = 5;
				panel.add(addressLabel, gbc_addressLabel);
			}
			{
				addressTextField = new JTextField();
				addressTextField.setName("Address");
				textFieldFunctions(addressTextField);
				
				GridBagConstraints gbc_addressTextField = new GridBagConstraints();
				gbc_addressTextField.insets = new Insets(0, 10, 25, 10);
				gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_addressTextField.gridx = 0;
				gbc_addressTextField.gridy = 6;
				panel.add(addressTextField, gbc_addressTextField);
				addressTextField.setColumns(10);
			}
			
			//Discount Label and Text Fields***************************************************************
			{
				JLabel discountLabel = new JLabel("Discount");
				
				GridBagConstraints gbc_discountLabel = new GridBagConstraints();
				gbc_discountLabel.anchor = GridBagConstraints.WEST;
				gbc_discountLabel.insets = new Insets(0, 10, 10, 0);
				gbc_discountLabel.gridx = 0;
				gbc_discountLabel.gridy = 8;
				panel.add(discountLabel, gbc_discountLabel);
			}
			{
				discountTextField = new JTextField();
				discountTextField.setName("Discount");
				textFieldFunctions(discountTextField);
				
				GridBagConstraints gbc_discountTextField = new GridBagConstraints();
				gbc_discountTextField.insets = new Insets(0, 10, 25, 10);
				gbc_discountTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_discountTextField.gridx = 0;
				gbc_discountTextField.gridy = 9;
				panel.add(discountTextField, gbc_discountTextField);
				discountTextField.setColumns(10);
			}
			
			//Group Label and Text Field************************************************************
			{
				groupLabel = new JLabel("Group");
				
				GridBagConstraints gbc_groupLabel = new GridBagConstraints();
				gbc_groupLabel.anchor = GridBagConstraints.WEST;
				gbc_groupLabel.insets = new Insets(0, 10, 10, 0);
				gbc_groupLabel.gridx = 0;
				gbc_groupLabel.gridy = 11;
				panel.add(groupLabel, gbc_groupLabel);
			}
			{
				groupTextField = new JTextField();
				groupTextField.setName("Group");
				textFieldFunctions(groupTextField);
				
				GridBagConstraints gbc_groupTextField = new GridBagConstraints();
				gbc_groupTextField.insets = new Insets(0, 10, 25, 10);
				gbc_groupTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_groupTextField.gridx = 0;
				gbc_groupTextField.gridy = 12;
				panel.add(groupTextField, gbc_groupTextField);
				groupTextField.setColumns(10);
			}
		}
		{
			if (isEditCustomer)
			{
				//Delete Button***************************************************************************
				RoundedButton deleteButton = new RoundedButton("Delete", Color.RED, Color.WHITE, Color.RED, new Font("Lato", Font.BOLD, 15));
				formatButton(deleteButton);
				redButton(deleteButton);
				
				GridBagConstraints gbc_deleteButton = new GridBagConstraints();
				gbc_deleteButton.anchor = GridBagConstraints.WEST;
				gbc_deleteButton.insets = new Insets(10, 5, 5, 0);
				gbc_deleteButton.gridx = 0;
				gbc_deleteButton.gridy = 3;
				contentPanel.add(deleteButton, gbc_deleteButton);
			}
		}
		
		{
			//Finish Button***************************************************************************
			RoundedButton finishButton = new RoundedButton("Finish", new Color(28, 150, 202), Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			formatButton(finishButton);
			blueButton(finishButton);
			
			GridBagConstraints gbc_finishButton = new GridBagConstraints();
			gbc_finishButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_finishButton.insets = new Insets(0, 0, 5, 5);
			gbc_finishButton.gridx = 1;
			gbc_finishButton.gridy = 3;
			contentPanel.add(finishButton, gbc_finishButton);
		}
			//Cancel Button************************************************************************
			cancelButton = new RoundedButton("Cancel", Color.WHITE, new Color(28, 150, 202), new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
			formatButton(cancelButton);
			whiteButton(cancelButton);
			
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 2;
			gbc_cancelButton.gridy = 3;
			contentPanel.add(cancelButton, gbc_cancelButton);
		}

	/**
	 * Sets size and offsets to the button
	 * @param button to be formatted
	 */
	private void formatButton(RoundedButton button) 
	{
		button.setPreferredSize(new Dimension(100, 30));
		button.addOffset(-5, 2);
	}
	
	/**
	 * Adds events to the blue buttons - changing color when mouse enters and exits the button and a click function
	 * @param button to be formatted
	 */
	private void blueButton(RoundedButton button) 
	{
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
	
	/**
	 * Adds events to the buttons - changing color when mouse enters and exits the button and a click function
	 * that shows a pop up for a confirmation of deletion
	 * @param button to be formatted
	 */
	private void redButton(RoundedButton button) {
		button.addMouseListener(new MouseAdapter()
		{	
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				button.setBackground(Color.WHITE);
				button.setForeground(Color.RED);
			}
					
			@Override
			public void mouseExited(MouseEvent e) 
			{
				button.setBackground(Color.RED);
				button.setForeground(Color.WHITE);
			}
			
			public void mouseClicked(MouseEvent e)
			{
				int confirmation = JOptionPane.showConfirmDialog(null, "Confirm deletion", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(confirmation == JOptionPane.YES_OPTION) 
				{
					customerCtr.deleteCustomerByPhone(phone);
					customersPanel.defaultFillTable(customersPanel.getPageIndex());
					dispose();
				}
				button.setBackground(Color.RED);
				button.setForeground(Color.WHITE);
			}
		});
	}
	
	/**
	 * Adds events to the white buttons - changing color when mouse enters and exits the button and a click function
	 * @param button to be formatted
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
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				dispose();
			}
		});
	}
	
	/**
	 * This method adds functions to the text field - changing border color when focused and clearing the text field when clicked,
	 * setting the text to ... when nothing was entered
	 * 
	 * @param field that gets the functions
	 */
	private void textFieldFunctions(JTextField field)
	{
		field.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		field.setText(field.getName()+"...");
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
				}
			}
			
			@Override
			public void focusLost(FocusEvent e)
			{
				if (field.getText().equals(""))
				{
					field.setText(field.getName()+"...");
				}
			}
		});

	}

	
	/**
	 * Gets the barcode of the product, fills up all the fields with the current data.
	 * Also changes the namelabel to the actual product name + barcode.
	 * 
	 * @param barcode
	 */
	public void fillFields(String phone)
	{
		if (isEditCustomer)
		{
			customerCtr = new CustomerCtr();
			ArrayList<String[]> data = customerCtr.searchField(phone);
			if (data.size() != 0)
			{
				nameTextField.setText(data.get(0)[0]);
				phoneTextField.setText(data.get(0)[1]);
				addressTextField.setText(data.get(0)[2]);
				groupTextField.setText(data.get(0)[3]);
				discountTextField.setText(data.get(0)[4]);
				customerName.setText(data.get(0)[0]);
				
				this.phone = Integer.parseInt(data.get(0)[1]);
			}
		}
		
	}
	
	/**
	 * When the finish button is clicked, all the information input is taken from the text fields and areas
	 * and put to the customers table, then the dialog is closed
	 */
	private void finishCreation()
	{
		customerCtr = new CustomerCtr();
		if (checkValues())
		{
			if (isEditCustomer)
			{
				customerCtr.updateCustomer(Integer.parseInt(phoneTextField.getText()), Integer.parseInt(discountTextField.getText()),
						nameTextField.getText(), addressTextField.getText(), groupTextField.getText());
				customersPanel.defaultFillTable(customersPanel.getPageIndex());
				dispose();
			}
			else
			{
				customerCtr.addCustomer(Integer.parseInt(phoneTextField.getText()), Integer.parseInt(discountTextField.getText()),
						nameTextField.getText(), addressTextField.getText(), groupTextField.getText());
				customersPanel.defaultFillTable(customersPanel.getPageIndex());
				dispose();
			}
		}

	}
	
	/**
	 * Checks if the input is correct in the text fields, if not right, an error message is shown telling the user
	 * what to do
	 * @return true if the input is correct, false if not
	 */
	private boolean checkValues()
	{
		JTextComponent[] strings = new JTextComponent[] {nameTextField,groupTextField,addressTextField}; 
		JTextField[] integers = new JTextField[] {discountTextField,phoneTextField};
		
		String errorMessage = "";
		
		
		//Checking string textfield values
		
		for (int string = 0;string<strings.length;string++)
		{
			strings[string].setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
			if (!customerCtr.checkValues(strings[string].getName(), strings[string].getText(), true))
			{
				strings[string].setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
				errorMessage += strings[string].getName() + ", ";	
			}
		}
		
		//Checking integer textfield values
		
		for (int integer = 0;integer<integers.length;integer++)
		{
			integers[integer].setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
			if (!customerCtr.checkValues(integers[integer].getName(), integers[integer].getText(), false))
			{
				integers[integer].setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
				errorMessage += integers[integer].getName() + ", ";	
			}
		}
		
		//Modifying the errorMessage and printing it
		
		if (errorMessage != "")
		{
			errorMessage = errorMessage.substring(0, errorMessage.length()-2);
			errorMessage += "!";
			JOptionPane.showMessageDialog(null, "Invalid value for the field(s): " + errorMessage);
			return false;
		}
		
		
		return true;
	}
	
	/**
	 * Refreshes the table
	 */
	public void reDraw()
	{
		JTextComponent[] fields = new JTextComponent[] {nameTextField,groupTextField,addressTextField,discountTextField,phoneTextField};
		for (JTextComponent field : fields)
		{
			field.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		}
	}
}

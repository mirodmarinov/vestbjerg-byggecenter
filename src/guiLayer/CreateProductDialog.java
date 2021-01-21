package guiLayer;

import controlLayer.ProductCtr;

import java.util.ArrayList;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;

import java.awt.event.*;

public class CreateProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private JLabel headerLabel;
	private RoundedButton cancelButton;
	private JPanel panel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel barcodeLabel;
	private JTextField barcodeTextField;
	private JLabel thresholdLabel;
	private JTextField thresholdTextField;
	private JLabel quantityLabel;
	private JTextField quantityTextField;
	private JLabel groupLabel;
	private JTextField groupTextField;
	private JLabel locationLabel;
	private JTextField locationTextField;
	private JLabel purchasePriceLabel;
	private JTextField purchasePriceTextField;
	private JLabel salesPriceLabel;
	private JTextField salesPriceTextField;
	private JLabel discountLabel;
	private JTextField discountTextField;
	private JLabel descriptionLabel;
	private JTextArea descriptionTextField;
	private JPanel header;
	private ProductCtr productCtr;
	private ProductsPanel productsPanel;
	private ArrayList<Object[]> checkData;

	
	/**
	 * Create the dialog.
	 */
	public CreateProductDialog(ProductsPanel productsPanel) {
		this.productsPanel = productsPanel;
		setBounds(100, 100, 1042, 600);
		setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 20, 50, 0, 0, 0, 50};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.1, 0.1, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			//Blue Header Panel****************************************************************
			header = new JPanel();
			header.setBackground(babyBlue);
			
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.anchor = GridBagConstraints.NORTH;
			gbc_panel_1.gridwidth = 3;
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			contentPanel.add(header, gbc_panel_1);
			{
				//Create Product Label*********************************************************
				headerLabel = new JLabel("Create Product");
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
			gbc_panel.gridheight = 2;
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 50, 5, 50);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
			
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{50,0, 0, 300,50};
			gbl_panel.rowHeights = new int[]{30, 0, 0, 50, 0, 0, 50, 0, 0, 50, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			//Name Label and Text Field****************************************************************
			{
				nameLabel = new JLabel("Name");
				
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.anchor = GridBagConstraints.WEST;
				gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nameLabel.gridx = 1;
				gbc_nameLabel.gridy = 1;
				panel.add(nameLabel, gbc_nameLabel);
			}
			{
				nameTextField = new JTextField();
				nameTextField.setName("Name");
				textFieldFunctions(nameTextField);
				
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 1;
				gbc_nameTextField.gridy = 2;
				panel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			
			//Group Label and Text Field***************************************************************
			{
				groupLabel = new JLabel("Group");
				
				GridBagConstraints gbc_groupLabel = new GridBagConstraints();
				gbc_groupLabel.gridheight = 2;
				gbc_groupLabel.anchor = GridBagConstraints.WEST;
				gbc_groupLabel.insets = new Insets(0, 0, 5, 5);
				gbc_groupLabel.gridx = 2;
				gbc_groupLabel.gridy = 0;
				panel.add(groupLabel, gbc_groupLabel);
			}
			{
				groupTextField = new JTextField();
				groupTextField.setName("Group");
				textFieldFunctions(groupTextField);
				
				GridBagConstraints gbc_groupTextField = new GridBagConstraints();
				gbc_groupTextField.insets = new Insets(0, 0, 5, 5);
				gbc_groupTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_groupTextField.gridx = 2;
				gbc_groupTextField.gridy = 2;
				panel.add(groupTextField, gbc_groupTextField);
				groupTextField.setColumns(10);
			}
			
			//Discount Label and Text Field************************************************************
			{
				discountLabel = new JLabel("Discount");
				
				GridBagConstraints gbc_discountLabel = new GridBagConstraints();
				gbc_discountLabel.gridheight = 2;
				gbc_discountLabel.anchor = GridBagConstraints.WEST;
				gbc_discountLabel.insets = new Insets(0, 0, 5, 5);
				gbc_discountLabel.gridx = 3;
				gbc_discountLabel.gridy = 0;
				panel.add(discountLabel, gbc_discountLabel);
			}
			{
				discountTextField = new JTextField();
				discountTextField.setName("Discount");
				textFieldFunctions(discountTextField);
				
				GridBagConstraints gbc_discountTextField = new GridBagConstraints();
				gbc_discountTextField.insets = new Insets(0, 0, 5, 5);
				gbc_discountTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_discountTextField.gridx = 3;
				gbc_discountTextField.gridy = 2;
				panel.add(discountTextField, gbc_discountTextField);
				discountTextField.setColumns(10);
			}
			
			//Barcode Label and Text Field*************************************************************
			{
				barcodeLabel = new JLabel("Barcode");
				
				GridBagConstraints gbc_barcodeLabel = new GridBagConstraints();
				gbc_barcodeLabel.anchor = GridBagConstraints.WEST;
				gbc_barcodeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_barcodeLabel.gridx = 1;
				gbc_barcodeLabel.gridy = 4;
				panel.add(barcodeLabel, gbc_barcodeLabel);
			}
			{
				barcodeTextField = new JTextField();
				barcodeTextField.setName("Barcode");
				textFieldFunctions(barcodeTextField);
				
				GridBagConstraints gbc_barcodeTextField = new GridBagConstraints();
				gbc_barcodeTextField.insets = new Insets(0, 0, 5, 5);
				gbc_barcodeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_barcodeTextField.gridx = 1;
				gbc_barcodeTextField.gridy = 5;
				panel.add(barcodeTextField, gbc_barcodeTextField);
				barcodeTextField.setColumns(10);
			}
			
			//Location Label and Text Field*********************************************************
			{
				locationLabel = new JLabel("Location");
				
				GridBagConstraints gbc_locationLabel = new GridBagConstraints();
				gbc_locationLabel.anchor = GridBagConstraints.WEST;
				gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
				gbc_locationLabel.gridx = 2;
				gbc_locationLabel.gridy = 4;
				panel.add(locationLabel, gbc_locationLabel);
			}
			{
				locationTextField = new JTextField();
				locationTextField.setName("Location");
				textFieldFunctions(locationTextField);
				
				GridBagConstraints gbc_locationTextField = new GridBagConstraints();
				gbc_locationTextField.insets = new Insets(0, 0, 5, 5);
				gbc_locationTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_locationTextField.gridx = 2;
				gbc_locationTextField.gridy = 5;
				panel.add(locationTextField, gbc_locationTextField);
				locationTextField.setColumns(10);
			}
			
			//Description Label and Text Area******************************************************
			{
				descriptionLabel = new JLabel("Description");
				
				GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
				gbc_descriptionLabel.anchor = GridBagConstraints.WEST;
				gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionLabel.gridx = 3;
				gbc_descriptionLabel.gridy = 4;
				panel.add(descriptionLabel, gbc_descriptionLabel);
			}
			{
				descriptionTextField = new JTextArea();
				descriptionTextField.setName("Description");
				textAreaFunctions(descriptionTextField);
				
				GridBagConstraints gbc_descriptionTextField = new GridBagConstraints();
				gbc_descriptionTextField.gridheight = 7;
				gbc_descriptionTextField.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionTextField.fill = GridBagConstraints.BOTH;
				gbc_descriptionTextField.gridx = 3;
				gbc_descriptionTextField.gridy = 5;
				panel.add(descriptionTextField, gbc_descriptionTextField);
			}
			
			//Threshold Label and Text Field***************************************************
			{
				thresholdLabel = new JLabel("Threshold");
				
				GridBagConstraints gbc_thresholdLabel = new GridBagConstraints();
				gbc_thresholdLabel.anchor = GridBagConstraints.WEST;
				gbc_thresholdLabel.insets = new Insets(0, 0, 5, 5);
				gbc_thresholdLabel.gridx = 1;
				gbc_thresholdLabel.gridy = 7;
				panel.add(thresholdLabel, gbc_thresholdLabel);
			}
			{
				thresholdTextField = new JTextField();
				thresholdTextField.setName("Threshold");
				textFieldFunctions(thresholdTextField);
				
				GridBagConstraints gbc_thresholdTextField = new GridBagConstraints();
				gbc_thresholdTextField.insets = new Insets(0, 0, 5, 5);
				gbc_thresholdTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_thresholdTextField.gridx = 1;
				gbc_thresholdTextField.gridy = 8;
				panel.add(thresholdTextField, gbc_thresholdTextField);
				thresholdTextField.setColumns(10);
			}
			
			//Purchase Price Label and Text Field****************************************************
			{
				purchasePriceLabel = new JLabel("Purchase price");
				
				GridBagConstraints gbc_purchasePriceLabel = new GridBagConstraints();
				gbc_purchasePriceLabel.anchor = GridBagConstraints.WEST;
				gbc_purchasePriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_purchasePriceLabel.gridx = 2;
				gbc_purchasePriceLabel.gridy = 7;
				panel.add(purchasePriceLabel, gbc_purchasePriceLabel);
			}
			{
				purchasePriceTextField = new JTextField();
				purchasePriceTextField.setName("Purchase price");
				textFieldFunctions(purchasePriceTextField);
				
				GridBagConstraints gbc_purchasePriceTextField = new GridBagConstraints();
				gbc_purchasePriceTextField.insets = new Insets(0, 0, 5, 5);
				gbc_purchasePriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_purchasePriceTextField.gridx = 2;
				gbc_purchasePriceTextField.gridy = 8;
				panel.add(purchasePriceTextField, gbc_purchasePriceTextField);
				purchasePriceTextField.setColumns(10);
			}
			
			//Quantity Label and Text Field*************************************************************
			{
				quantityLabel = new JLabel("Quantity");
				
				GridBagConstraints gbc_quantityLabel = new GridBagConstraints();
				gbc_quantityLabel.anchor = GridBagConstraints.WEST;
				gbc_quantityLabel.insets = new Insets(0, 0, 5, 5);
				gbc_quantityLabel.gridx = 1;
				gbc_quantityLabel.gridy = 10;
				panel.add(quantityLabel, gbc_quantityLabel);
			}
			{
				quantityTextField = new JTextField();
				quantityTextField.setName("Quantity");
				textFieldFunctions(quantityTextField);
				
				GridBagConstraints gbc_quantityTextField = new GridBagConstraints();
				gbc_quantityTextField.insets = new Insets(0, 0, 0, 5);
				gbc_quantityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_quantityTextField.gridx = 1;
				gbc_quantityTextField.gridy = 11;
				panel.add(quantityTextField, gbc_quantityTextField);
				quantityTextField.setColumns(10);
			}
			
			//Sales Price Label and Text Field********************************************************
			{
				salesPriceLabel = new JLabel("Sale price");
				
				GridBagConstraints gbc_salesPriceLabel = new GridBagConstraints();
				gbc_salesPriceLabel.anchor = GridBagConstraints.WEST;
				gbc_salesPriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_salesPriceLabel.gridx = 2;
				gbc_salesPriceLabel.gridy = 10;
				panel.add(salesPriceLabel, gbc_salesPriceLabel);
			}
			{
				salesPriceTextField = new JTextField();
				salesPriceTextField.setName("Sale price");
				textFieldFunctions(salesPriceTextField);
				
				GridBagConstraints gbc_salesPriceTextField = new GridBagConstraints();
				gbc_salesPriceTextField.insets = new Insets(0, 0, 0, 5);
				gbc_salesPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salesPriceTextField.gridx = 2;
				gbc_salesPriceTextField.gridy = 11;
				panel.add(salesPriceTextField, gbc_salesPriceTextField);
				salesPriceTextField.setColumns(10);
			}
			
			fillFields();
		}
		
		{
			//Finish Button******************************************************************************
			RoundedButton finishButton = new RoundedButton("Finish", new Color(28, 150, 202), Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			formatButton(finishButton);
			blueButton(finishButton);
			GridBagConstraints gbc_finishButton = new GridBagConstraints();
			gbc_finishButton.gridheight = 4;
			gbc_finishButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_finishButton.insets = new Insets(0, 0, 0, 5);
			gbc_finishButton.gridx = 1;
			gbc_finishButton.gridy = 3;
			contentPanel.add(finishButton, gbc_finishButton);
		}
			//Cancel Button**********************************************************************************
			cancelButton = new RoundedButton("Cancel", Color.WHITE, new Color(28, 150, 202), new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
			formatButton(cancelButton);
			whiteButton(cancelButton);
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.gridheight = 4;
			gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_cancelButton.gridx = 2;
			gbc_cancelButton.gridy = 3;
			contentPanel.add(cancelButton, gbc_cancelButton);
		}
	
	/**
	 * This method sets the buttons size and offsets
	 * @param button to be formatted
	 */
	private void formatButton(RoundedButton button) {
		button.setPreferredSize(new Dimension(100, 30));
		button.addOffset(-5, 2);
	}
	
	/**
	 * This method adds events to the blue buttons - changing colors when entering and exiting the button with a mouse
	 * and a click function
	 * @param button to be formatted
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
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				finishCreation();
			}
			
		});
	}
	
	/**
	 * This method adds events to the white buttons - changing colors when entering and exiting the button with a mouse
	 * and a click function that closes the window
	 * @param button
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
	
	
	/**
	 * This method adds functions to the text area - changing border color when focused and clearing the text area when clicked,
	 * setting the text to ... when nothing was entered
	 * 
	 * @param textArea that gets the functions
	 */
	private void textAreaFunctions(JTextArea textArea)
	{
		textArea.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		textArea.setFocusable(false);
		textArea.setForeground(new Color(149, 149, 149));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				textArea.setFocusable(true);
			}
				
		});
		
		textArea.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (textArea.getText().equals(textArea.getName()+"..."))
				{
					textArea.setText("");
					textArea.setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e)
			{
				if (textArea.getText().equals(""))
				{
					textArea.setText(textArea.getName()+"...");
					textArea.setForeground(new Color(149, 149, 149));
				}
			}
		});

	}
	
	/**
	 * When the finish button is clicked, all the information input is taken from the text fields and areas
	 * and put to the product list, then the dialog is closed
	 */
	private void finishCreation()
	{
		productCtr = new ProductCtr();

		if(checkValues())
		{
			productCtr.createProduct(Integer.parseInt(thresholdTextField.getText()), Integer.parseInt(quantityTextField.getText()),
					Integer.parseInt(discountTextField.getText()), Integer.parseInt(purchasePriceTextField.getText()), 
					Integer.parseInt(salesPriceTextField.getText()), barcodeTextField.getText(), nameTextField.getText(), descriptionTextField.getText(),
					groupTextField.getText(), locationTextField.getText());
			productsPanel.defaultFillTable(Integer.parseInt(productsPanel.getTablePageLabel().getText()));
			productsPanel.defaultFillTable(productsPanel.getPageIndex());
			dispose();
		}
	}

	/**
	 * This method fills the text fields accordingly before they are edited
	 */
	private void fillFields()
	{
		nameTextField.setText("Name...");
		descriptionTextField.setText("Description...");
		groupTextField.setText("Group...");
		barcodeTextField.setText("Barcode...");
		locationTextField.setText("Location...");
		quantityTextField.setText("Quantity...");
		thresholdTextField.setText("Threshold...");
		salesPriceTextField.setText("Sale price...");
		purchasePriceTextField.setText("Purchase price...");
		discountTextField.setText("Discount...");
	}
	

	/**
	 * Checks if the input is correct in the text fields, if not right, an error message is shown telling the user
	 * what to do
	 * @return true if the input is correct, false if not
	 */
	private boolean checkValues()
	{
		JTextComponent[] strings = new JTextComponent[] {nameTextField,groupTextField,barcodeTextField,locationTextField,descriptionTextField};
		JTextField[] integers = new JTextField[] {quantityTextField,thresholdTextField,salesPriceTextField,purchasePriceTextField,discountTextField};
		
		String errorMessage = "";
		
		descriptionTextField.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		if (!productCtr.checkValues(descriptionTextField.getName(), descriptionTextField.getText(), true))
		{
			errorMessage += descriptionTextField.getName() + ", ";
			descriptionTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
		}
		
		//Checking string textfield values
		
		for (int string = 0;string<strings.length;string++)
		{
			strings[string].setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
			if (!productCtr.checkValues(strings[string].getName(), strings[string].getText(), true))
			{
				strings[string].setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
				errorMessage += strings[string].getName() + ", ";	
			}
		}
		
		//Checking integer textfield values
		
		for (int integer = 0;integer<integers.length;integer++)
		{
			integers[integer].setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
			if (!productCtr.checkValues(integers[integer].getName(), integers[integer].getText(), false))
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
}

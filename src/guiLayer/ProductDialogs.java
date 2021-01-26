package guiLayer;

import controlLayer.ProductCtr;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;

public class ProductDialogs extends JDialog {

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
	private JLabel groupLabel;
	private JTextField groupTextField;
	private JLabel quantityLabel;
	private JTextField quantityTextField;
	private JLabel purchasePriceLabel;
	private JTextField purchasePriceTextField;
	private JLabel salesPriceLabel;
	private JTextField salesPriceTextField;
	private JLabel discountLabel;
	private JTextField discountTextField;
	private JLabel descriptionLabel;
	private JTextPane descriptionTextField;
	private JPanel header;
	private ProductCtr productCtr;
	private JLabel productName;
	private String barcode = "";
	private int placeOnList;
	private ProductsPanel productsPanel;
	private boolean isEditProduct;
	private JLabel locationLabel;
	private JTextField rowTextField;
	private JTextField columnTextField;
	private JTextField placeTextField;
	private JLabel rowLabel;
	private JLabel placeLabel;


	/**
	 * Create the dialog.
	 */
	public ProductDialogs(ProductsPanel productsPanel, int placeOnList, ProductCtr productCtr,boolean isEditProduct) {
		this.productsPanel = productsPanel;
		this.placeOnList = placeOnList;
		this.productCtr = productCtr;
		this.isEditProduct = isEditProduct;
		setBounds(100, 100, 1237, 713);
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
		}
		{
			//Blue Header Panel***********************************************************************
			header = new JPanel();
			header.setBackground(babyBlue);
			
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.anchor = GridBagConstraints.NORTH;
			gbc_panel_1.gridwidth = 3;
			gbc_panel_1.insets = new Insets(0, 0, 5, 5);
			gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			contentPanel.add(header, gbc_panel_1);
			{
				//Edit Product Label*******************************************************************
				if (isEditProduct)
				{
					headerLabel = new JLabel("Edit Product");
				}
				else
				{
					headerLabel = new JLabel("Create Product");
				}
				header.add(headerLabel);
				headerLabel.setVisible(true);
				headerLabel.setForeground(Color.WHITE);
				headerLabel.setFont(new Font("LATO", Font.BOLD, 20));
			}
		}
		{
			if (isEditProduct)
			{
			//Product Name Label***********************************************************************
			productName = new JLabel("<ProductNamePlaceHolder>");
			productName.setFont(new Font("LATO", Font.BOLD, 18));
			
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(productName, gbc_lblNewLabel);
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
			gbl_panel.columnWidths = new int[]{50, 0, 0, 0,50};
			gbl_panel.rowHeights = new int[]{20, 0, 0, 50, 0, 0, 50, 0, 0, 50, 0, 0, 40, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			//Name Label and Text Field**************************************************************
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
				if (isEditProduct)
				{
					nameTextField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!nameTextField.getText().equals(""))
							{
								//It should split the string by ':', add the name to it and connect with the rest of the string (add barcode)
								//And of course set it as the name label
	
								productName.setText(nameTextField.getText() + " " +productName.getText().substring(productName.getText().indexOf(":"),productName.getText().length()));
							}		
						}
					});
				}
			
			}
			//Group Label and Text Field**************************************************************
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
			
			//Barcode Label and Text Field*********************************************************
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
				if (isEditProduct)
				{
					barcodeTextField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!barcodeTextField.getText().equals(""))
							{
								//It should split the string by ':', add the name to it and connect with the rest of the string (add barcode)
								//And of course set it as the name label
								
								productName.setText(productName.getText().substring(0,productName.getText().indexOf(":")) + ": " + barcodeTextField.getText());
							}		
						}
					});
				}
			}
			
			//Location Label and Text Field**************************************************************
			{
				quantityLabel = new JLabel("Quantity");
				
				GridBagConstraints gbc_quantityLabel = new GridBagConstraints();
				gbc_quantityLabel.anchor = GridBagConstraints.WEST;
				gbc_quantityLabel.insets = new Insets(0, 0, 5, 5);
				gbc_quantityLabel.gridx = 2;
				gbc_quantityLabel.gridy = 4;
				panel.add(quantityLabel, gbc_quantityLabel);
			}
			{
				quantityTextField = new JTextField();
				quantityTextField.setName("Quantity");
				textFieldFunctions(quantityTextField);
				
				GridBagConstraints gbc_quantityTextField = new GridBagConstraints();
				gbc_quantityTextField.insets = new Insets(0, 0, 5, 5);
				gbc_quantityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_quantityTextField.gridx = 2;
				gbc_quantityTextField.gridy = 5;
				panel.add(quantityTextField, gbc_quantityTextField);
				quantityTextField.setColumns(10);
			}
			
			//Description Label and Text Field*******************************************************
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
				descriptionTextField = new JTextPane();
				descriptionTextField.setName("Description");
				textPaneFunctions(descriptionTextField);
				descriptionTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
				
				GridBagConstraints gbc_descriptionTextField = new GridBagConstraints();
				gbc_descriptionTextField.gridheight = 3;
				gbc_descriptionTextField.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionTextField.fill = GridBagConstraints.BOTH;
				gbc_descriptionTextField.gridx = 3;
				gbc_descriptionTextField.gridy = 5;
				panel.add(descriptionTextField, gbc_descriptionTextField);
			}
			
			//Threshold Label and Text Field**********************************************************
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
			
			//Purchase Price Label and Text Field**************************************************
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
			
			//Sales Price Label and Text Field**********************************************
			{
				salesPriceLabel = new JLabel("Sale price");
				
				GridBagConstraints gbc_salesPriceLabel = new GridBagConstraints();
				gbc_salesPriceLabel.anchor = GridBagConstraints.WEST;
				gbc_salesPriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_salesPriceLabel.gridx = 1;
				gbc_salesPriceLabel.gridy = 10;
				panel.add(salesPriceLabel, gbc_salesPriceLabel);
			}
			{
				salesPriceTextField = new JTextField();
				salesPriceTextField.setName("Sale price");
				textFieldFunctions(salesPriceTextField);
				
				GridBagConstraints gbc_salesPriceTextField = new GridBagConstraints();
				gbc_salesPriceTextField.insets = new Insets(0, 0, 5, 5);
				gbc_salesPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salesPriceTextField.gridx = 1;
				gbc_salesPriceTextField.gridy = 11;
				panel.add(salesPriceTextField, gbc_salesPriceTextField);
				salesPriceTextField.setColumns(10);
			}
			{
				locationLabel = new JLabel("Location");
				locationLabel.setFont(new Font("Lato", Font.BOLD, 19));
				GridBagConstraints gbc_locationLabel = new GridBagConstraints();
				gbc_locationLabel.anchor = GridBagConstraints.WEST;
				gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
				gbc_locationLabel.gridx = 1;
				gbc_locationLabel.gridy = 13;
				panel.add(locationLabel, gbc_locationLabel);
			}
			{
				rowLabel = new JLabel("Row");
				GridBagConstraints gbc_rowLabel = new GridBagConstraints();
				gbc_rowLabel.anchor = GridBagConstraints.WEST;
				gbc_rowLabel.insets = new Insets(0, 0, 5, 5);
				gbc_rowLabel.gridx = 1;
				gbc_rowLabel.gridy = 14;
				panel.add(rowLabel, gbc_rowLabel);
			}
			{
				JLabel columnLabel = new JLabel("Column");
				GridBagConstraints gbc_columnLabel = new GridBagConstraints();
				gbc_columnLabel.anchor = GridBagConstraints.WEST;
				gbc_columnLabel.insets = new Insets(0, 0, 5, 5);
				gbc_columnLabel.gridx = 2;
				gbc_columnLabel.gridy = 14;
				panel.add(columnLabel, gbc_columnLabel);
			}
			{
				placeLabel = new JLabel("Place");
				GridBagConstraints gbc_placeLabel = new GridBagConstraints();
				gbc_placeLabel.anchor = GridBagConstraints.WEST;
				gbc_placeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_placeLabel.gridx = 3;
				gbc_placeLabel.gridy = 14;
				panel.add(placeLabel, gbc_placeLabel);
			}
			{
				rowTextField = new JTextField();
				rowTextField.setName("Row");
				textFieldFunctions(rowTextField);
				GridBagConstraints gbc_rowTextField = new GridBagConstraints();
				gbc_rowTextField.insets = new Insets(0, 0, 0, 5);
				gbc_rowTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_rowTextField.gridx = 1;
				gbc_rowTextField.gridy = 15;
				panel.add(rowTextField, gbc_rowTextField);
				rowTextField.setColumns(10);
			}
			{
				columnTextField = new JTextField();
				columnTextField.setName("Column");
				textFieldFunctions(columnTextField);
				GridBagConstraints gbc_columnTextField = new GridBagConstraints();
				gbc_columnTextField.insets = new Insets(0, 0, 0, 5);
				gbc_columnTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_columnTextField.gridx = 2;
				gbc_columnTextField.gridy = 15;
				panel.add(columnTextField, gbc_columnTextField);
				columnTextField.setColumns(10);
			}
			{
				placeTextField = new JTextField();
				placeTextField.setName("Place");
				textFieldFunctions(placeTextField);
				GridBagConstraints gbc_placeTextField = new GridBagConstraints();
				gbc_placeTextField.insets = new Insets(0, 0, 0, 5);
				gbc_placeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_placeTextField.gridx = 3;
				gbc_placeTextField.gridy = 15;
				panel.add(placeTextField, gbc_placeTextField);
				placeTextField.setColumns(10);
			}
			if (isEditProduct)
			{
			fillFields(barcode);
			}
		}
		
		{
			//Finish Button********************************************************************
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
		
		{
			//Delete Button*******************************************************************
			if (isEditProduct)
			{
				RoundedButton deleteButton = new RoundedButton("Delete", Color.RED, Color.WHITE, Color.RED, new Font("Lato", Font.BOLD, 15));
				formatButton(deleteButton);
				redButton(deleteButton);
				
				GridBagConstraints gbc_deleteButton = new GridBagConstraints();
				gbc_deleteButton.anchor = GridBagConstraints.SOUTHWEST;
				gbc_deleteButton.insets = new Insets(0, 0, 0, 100);
				gbc_deleteButton.gridx = 0;
				gbc_deleteButton.gridy = 6;
				contentPanel.add(deleteButton, gbc_deleteButton);
			}
		}
		
			//Cancel Button*************************************************************************
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
	 * This method changes the button's size and offsets
	 * @param button to be formatted
	 */
	private void formatButton(RoundedButton button) {
		button.setPreferredSize(new Dimension(100, 30));
		button.addOffset(-5, 2);
	}
	
	/**
	 * This method adds events to the blue buttons - changing color when mouse enters and exits the button and a click
	 * event
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
				editProduct(placeOnList);
			}
		});
	}
	
	/**
	 * This method adds event to the white buttons - changing colors when mouse enters and exits the button and 
	 * a click event that closes the window
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
	 * This method adds events to the red button - changing color when mouse enters and exits the button and a click
	 * event that deletes the product
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
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cofirmation = JOptionPane.showConfirmDialog(null, "Confirm deletion","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
				if (cofirmation == JOptionPane.YES_OPTION)
				{
					deleteProduct(placeOnList);
					dispose();
				}
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
	 * This method adds functions to the text pane - changing border color when focused and clearing the text pane when clicked,
	 * setting the text to ... when nothing was entered
	 * 
	 * @param field that gets the functions
	 */
	private void textPaneFunctions(JTextPane pane)
	{
		pane.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		pane.setText(pane.getName()+"...");
		pane.setFocusable(false);
		pane.addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				pane.setFocusable(true);
			}
				
		});
		
		pane.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (pane.getText().equals(pane.getName()+"..."))
				{
					pane.setText("");
				}
			}
			
			@Override
			public void focusLost(FocusEvent e)
			{
				if (pane.getText().equals(""))
				{
					pane.setText(pane.getName()+"...");
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
	public void fillFields(String barcode)
	{
		String[] data = productCtr.getProductInfoByBarcode(barcode);
		if (data != null)
		{
			nameTextField.setText(data[0]);
			descriptionTextField.setText(data[1]);
			groupTextField.setText(data[2]);
			barcodeTextField.setText(data[3]);
			quantityTextField.setText(data[5]);
			thresholdTextField.setText(data[6]);
			salesPriceTextField.setText(data[7]);
			purchasePriceTextField.setText(data[8]);
			discountTextField.setText(data[9]);
			productName.setText(data[0] + " : " + data[3]);

			if (!data[4].equals("") && data[4] != null)
			{
				String[] location = data[4].split("[.]");
				rowTextField.setText(location[0]);
				columnTextField.setText(location[1]);
				placeTextField.setText(location[2]);
			}
		}
		
	}
	
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}
	
	/**
	 * This method updates the current product details with the details taken from the textfields when the button
	 * is clicked
	 * @param placeOnList
	 */
	private void editProduct(int placeOnList)
	{				
		if(checkValues())
		{
			String row = (rowTextField.getText().equals("Row...") || rowTextField.getText().replaceAll("[.]", "").equals("")) ? "#" : rowTextField.getText().replaceAll("[.]", "");
			String column = (columnTextField.getText().equals("Column...") || columnTextField.getText().replaceAll("[.]", "").equals("")) ? "#" : columnTextField.getText().replaceAll("[.]", "");
			String place = (placeTextField.getText().equals("Place...") || placeTextField.getText().replaceAll("[.]", "").equals("")) ? "#" : placeTextField.getText().replaceAll("[.]", "");
			if (isEditProduct)
			{
				productCtr.updateParameter(placeOnList, 0, nameTextField.getText());
				productCtr.updateParameter(placeOnList, 1, descriptionTextField.getText());
				productCtr.updateParameter(placeOnList, 2, groupTextField.getText());
				productCtr.updateParameter(placeOnList, 3, barcodeTextField.getText());
				productCtr.updateParameter(placeOnList, 5, quantityTextField.getText());
				productCtr.updateParameter(placeOnList, 6, thresholdTextField.getText());
				productCtr.updateParameter(placeOnList, 7, salesPriceTextField.getText());
				productCtr.updateParameter(placeOnList, 8, purchasePriceTextField.getText());
				productCtr.updateParameter(placeOnList, 9, discountTextField.getText());
				productCtr.updateLocation(barcodeTextField.getText(), (row + "." + column + "." + place));
				productsPanel.defaultFillTable(productsPanel.getPageIndex());
				dispose();
			}
			else
			{
				
				productCtr.createProduct(Integer.parseInt(thresholdTextField.getText()), Integer.parseInt(quantityTextField.getText()),
						Integer.parseInt(discountTextField.getText()), Integer.parseInt(purchasePriceTextField.getText()), 
						Integer.parseInt(salesPriceTextField.getText()), barcodeTextField.getText(), nameTextField.getText(), descriptionTextField.getText(),
						groupTextField.getText(),"");
				productCtr.updateLocation(barcodeTextField.getText(),(row + "." + column + "." + place));
				productsPanel.defaultFillTable(Integer.parseInt(productsPanel.getTablePageLabel().getText()));
				productsPanel.defaultFillTable(productsPanel.getPageIndex());
				dispose();
			}
		}

	}
	
	/**
	 * This method deletes the currently edited product
	 * @param placeOnList
	 */
	private void deleteProduct(int placeOnList)
	{
		productCtr.deleteProduct(placeOnList);
		productsPanel.defaultFillTable(productsPanel.getPageIndex());
	}
	
	/**
	 * 
	 * @param placeOnList
	 */
	public void setPlaceOnList(int placeOnList)
	{
		this.placeOnList = placeOnList;
	}

	/**
	 * Checks if the input is correct in the text fields, if not right, an error message is shown telling the user
	 * what to do
	 * @return true if the input is correct, false if not
	 */
	private boolean checkValues()
	{
		JTextComponent[] strings = new JTextComponent[] {nameTextField,groupTextField,barcodeTextField,quantityTextField,descriptionTextField}; 
		JTextField[] integers = new JTextField[] {thresholdTextField,salesPriceTextField,purchasePriceTextField,discountTextField};
		
		String errorMessage = "";
		
		
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
	
	/**
	 * Refreshes the table
	 */
	public void reDraw()
	{
		JTextComponent[] fields = new JTextComponent[] {nameTextField,groupTextField,barcodeTextField,quantityTextField,
				descriptionTextField,thresholdTextField,salesPriceTextField,purchasePriceTextField,discountTextField};
		for (JTextComponent field : fields)
		{
			field.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
		}
	}
	
}

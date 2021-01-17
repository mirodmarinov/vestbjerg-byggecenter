package guiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import controlLayer.ProductCtr;
import guiLayer.Renderers.JTableButtonMouseListener;
import guiLayer.Renderers.JTableButtonRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private JLabel headerLabel;
	private DocumentListener cl = new DocumentListener()
	
	{
		@Override
		public void insertUpdate(DocumentEvent e)
		{
			
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			
		}

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			
		}
	};
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
	private JTextPane descriptionTextField;
	private JPanel header;
	private ProductCtr productCtr;
	private JLabel productName;
	private String barcode = "";

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			EditCustomerDialog dialog = new EditCustomerDialog(String productNameAndID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public EditProductDialog(ProductsPanel productsPanel) {
		setBounds(100, 100, 1042, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{60, 0, 0, 0, 0, 0, 90, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.3, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.2, 0.5, 0.3, 0.2, 0.2, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
		}
		{
			header = new JPanel();
			header.setBackground(babyBlue);
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.gridwidth = 8;
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			contentPanel.add(header, gbc_panel_1);
			{
				
				headerLabel = new JLabel("Edit Product");
				//customerNameLabel.setHorizontalAlignment(JLabel.LEFT); TODO IT DOESN't WOOORK ;((((((
				header.add(headerLabel);
				headerLabel.setVisible(true);
				headerLabel.setForeground(Color.WHITE);
				headerLabel.setFont(new Font("LATO", Font.BOLD, 20));
			}
		}
		{
			productName = new JLabel("<ProductNamePlaceHolder>");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(productName, gbc_lblNewLabel);
		}
		{
			panel = new JPanel();
			panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.gridwidth = 6;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				nameLabel = new JLabel("Name");
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.anchor = GridBagConstraints.WEST;
				gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nameLabel.gridx = 1;
				gbc_nameLabel.gridy = 0;
				panel.add(nameLabel, gbc_nameLabel);
			}
			{
				groupLabel = new JLabel("Group");
				GridBagConstraints gbc_groupLabel = new GridBagConstraints();
				gbc_groupLabel.anchor = GridBagConstraints.WEST;
				gbc_groupLabel.insets = new Insets(0, 0, 5, 5);
				gbc_groupLabel.gridx = 2;
				gbc_groupLabel.gridy = 0;
				panel.add(groupLabel, gbc_groupLabel);
			}
			{
				discountLabel = new JLabel("Discount");
				GridBagConstraints gbc_discountLabel = new GridBagConstraints();
				gbc_discountLabel.anchor = GridBagConstraints.WEST;
				gbc_discountLabel.insets = new Insets(0, 0, 5, 0);
				gbc_discountLabel.gridx = 3;
				gbc_discountLabel.gridy = 0;
				panel.add(discountLabel, gbc_discountLabel);
			}
			{
				nameTextField = new JTextField();
				nameTextField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!nameTextField.getText().equals(""))
						{
							//It should split the string by ':', add the name to it and connect with the rest of the string (add barcode)
							//And of course set it as the name label
							//TODO Do the same for the barcode

							productName.setText(nameTextField.getText() + " " +productName.getText().substring(productName.getText().indexOf(":"),productName.getText().length()));
						}
							
								
					}
				});
				nameTextField.setName("Name");
				textFieldFunctions(nameTextField);
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 1;
				gbc_nameTextField.gridy = 1;
				panel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			{
				groupTextField = new JTextField();
				groupTextField.setName("Group");
				textFieldFunctions(groupTextField);
				GridBagConstraints gbc_groupTextField = new GridBagConstraints();
				gbc_groupTextField.insets = new Insets(0, 0, 5, 5);
				gbc_groupTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_groupTextField.gridx = 2;
				gbc_groupTextField.gridy = 1;
				panel.add(groupTextField, gbc_groupTextField);
				groupTextField.setColumns(10);
			}
			{
				discountTextField = new JTextField();
				discountTextField.setName("Discount");
				textFieldFunctions(discountTextField);
				GridBagConstraints gbc_discountTextField = new GridBagConstraints();
				gbc_discountTextField.insets = new Insets(0, 0, 5, 0);
				gbc_discountTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_discountTextField.gridx = 3;
				gbc_discountTextField.gridy = 1;
				panel.add(discountTextField, gbc_discountTextField);
				discountTextField.setColumns(10);
			}
			{
				barcodeLabel = new JLabel("Barcode");
				GridBagConstraints gbc_barcodeLabel = new GridBagConstraints();
				gbc_barcodeLabel.anchor = GridBagConstraints.WEST;
				gbc_barcodeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_barcodeLabel.gridx = 1;
				gbc_barcodeLabel.gridy = 3;
				panel.add(barcodeLabel, gbc_barcodeLabel);
			}
			{
				locationLabel = new JLabel("Location");
				GridBagConstraints gbc_locationLabel = new GridBagConstraints();
				gbc_locationLabel.anchor = GridBagConstraints.WEST;
				gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
				gbc_locationLabel.gridx = 2;
				gbc_locationLabel.gridy = 3;
				panel.add(locationLabel, gbc_locationLabel);
			}
			{
				descriptionLabel = new JLabel("Description");
				GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
				gbc_descriptionLabel.anchor = GridBagConstraints.WEST;
				gbc_descriptionLabel.insets = new Insets(0, 0, 5, 0);
				gbc_descriptionLabel.gridx = 3;
				gbc_descriptionLabel.gridy = 3;
				panel.add(descriptionLabel, gbc_descriptionLabel);
			}
			{
				barcodeTextField = new JTextField();
				barcodeTextField.setName("Barcode");
				textFieldFunctions(barcodeTextField);
				GridBagConstraints gbc_barcodeTextField = new GridBagConstraints();
				gbc_barcodeTextField.insets = new Insets(0, 0, 5, 5);
				gbc_barcodeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_barcodeTextField.gridx = 1;
				gbc_barcodeTextField.gridy = 4;
				panel.add(barcodeTextField, gbc_barcodeTextField);
				barcodeTextField.setColumns(10);
			}
			{
				locationTextField = new JTextField();
				locationTextField.setName("Location");
				textFieldFunctions(locationTextField);
				GridBagConstraints gbc_locationTextField = new GridBagConstraints();
				gbc_locationTextField.insets = new Insets(0, 0, 5, 5);
				gbc_locationTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_locationTextField.gridx = 2;
				gbc_locationTextField.gridy = 4;
				panel.add(locationTextField, gbc_locationTextField);
				locationTextField.setColumns(10);
			}
			{
				descriptionTextField = new JTextPane();
				descriptionTextField.setName("Description");
				textPaneFunctions(descriptionTextField);
				descriptionTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
				GridBagConstraints gbc_descriptionTextField = new GridBagConstraints();
				gbc_descriptionTextField.gridheight = 2;
				gbc_descriptionTextField.insets = new Insets(0, 0, 5, 0);
				gbc_descriptionTextField.fill = GridBagConstraints.BOTH;
				gbc_descriptionTextField.gridx = 3;
				gbc_descriptionTextField.gridy = 4;
				panel.add(descriptionTextField, gbc_descriptionTextField);
			}
			{
				thresholdLabel = new JLabel("Threshold");
				GridBagConstraints gbc_thresholdLabel = new GridBagConstraints();
				gbc_thresholdLabel.anchor = GridBagConstraints.WEST;
				gbc_thresholdLabel.insets = new Insets(0, 0, 5, 5);
				gbc_thresholdLabel.gridx = 1;
				gbc_thresholdLabel.gridy = 6;
				panel.add(thresholdLabel, gbc_thresholdLabel);
			}
			{
				purchasePriceLabel = new JLabel("Purchase price");
				GridBagConstraints gbc_purchasePriceLabel = new GridBagConstraints();
				gbc_purchasePriceLabel.anchor = GridBagConstraints.WEST;
				gbc_purchasePriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_purchasePriceLabel.gridx = 2;
				gbc_purchasePriceLabel.gridy = 6;
				panel.add(purchasePriceLabel, gbc_purchasePriceLabel);
			}
			{
				thresholdTextField = new JTextField();
				thresholdTextField.setName("Threshold");
				textFieldFunctions(thresholdTextField);
				GridBagConstraints gbc_thresholdTextField = new GridBagConstraints();
				gbc_thresholdTextField.insets = new Insets(0, 0, 5, 5);
				gbc_thresholdTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_thresholdTextField.gridx = 1;
				gbc_thresholdTextField.gridy = 7;
				panel.add(thresholdTextField, gbc_thresholdTextField);
				thresholdTextField.setColumns(10);
			}
			{
				purchasePriceTextField = new JTextField();
				purchasePriceTextField.setName("Purchase price");
				textFieldFunctions(purchasePriceTextField);
				GridBagConstraints gbc_purchasePriceTextField = new GridBagConstraints();
				gbc_purchasePriceTextField.insets = new Insets(0, 0, 5, 5);
				gbc_purchasePriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_purchasePriceTextField.gridx = 2;
				gbc_purchasePriceTextField.gridy = 7;
				panel.add(purchasePriceTextField, gbc_purchasePriceTextField);
				purchasePriceTextField.setColumns(10);
			}
			{
				quantityLabel = new JLabel("Quantity");
				GridBagConstraints gbc_quantityLabel = new GridBagConstraints();
				gbc_quantityLabel.anchor = GridBagConstraints.WEST;
				gbc_quantityLabel.insets = new Insets(0, 0, 5, 5);
				gbc_quantityLabel.gridx = 1;
				gbc_quantityLabel.gridy = 9;
				panel.add(quantityLabel, gbc_quantityLabel);
			}
			{
				salesPriceLabel = new JLabel("Sale price");
				GridBagConstraints gbc_salesPriceLabel = new GridBagConstraints();
				gbc_salesPriceLabel.anchor = GridBagConstraints.WEST;
				gbc_salesPriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_salesPriceLabel.gridx = 2;
				gbc_salesPriceLabel.gridy = 9;
				panel.add(salesPriceLabel, gbc_salesPriceLabel);
			}
			{
				quantityTextField = new JTextField();
				quantityTextField.setName("Quantity");
				textFieldFunctions(quantityTextField);
				GridBagConstraints gbc_quantityTextField = new GridBagConstraints();
				gbc_quantityTextField.insets = new Insets(0, 0, 0, 5);
				gbc_quantityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_quantityTextField.gridx = 1;
				gbc_quantityTextField.gridy = 10;
				panel.add(quantityTextField, gbc_quantityTextField);
				quantityTextField.setColumns(10);
			}
			{
				salesPriceTextField = new JTextField();
				salesPriceTextField.setName("Sale price");
				textFieldFunctions(salesPriceTextField);
				GridBagConstraints gbc_salesPriceTextField = new GridBagConstraints();
				gbc_salesPriceTextField.insets = new Insets(0, 0, 0, 5);
				gbc_salesPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salesPriceTextField.gridx = 2;
				gbc_salesPriceTextField.gridy = 10;
				panel.add(salesPriceTextField, gbc_salesPriceTextField);
				salesPriceTextField.setColumns(10);
			}
			fillFields(barcode);
		}
		{
			RoundedButton okButton = new RoundedButton("Finish", new Color(28, 150, 202), Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			formatButton(okButton);
			blueButton(okButton);
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.gridwidth = 2;
			gbc_okButton.gridheight = 4;
			gbc_okButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 5;
			gbc_okButton.gridy = 3;
			contentPanel.add(okButton, gbc_okButton);
		}
		
			cancelButton = new RoundedButton("Cancel", Color.WHITE, new Color(28, 150, 202), new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
			formatButton(cancelButton);
			whiteButton(cancelButton);
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.gridheight = 4;
			gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_cancelButton.gridx = 7;
			gbc_cancelButton.gridy = 3;
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
	
	
	private void textPaneFunctions(JTextPane pane)
	{
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
		productCtr = new ProductCtr();
		String[] data = productCtr.getProductrByBarcode(barcode);
		if (data != null)
		{
			nameTextField.setText(data[0]);
			descriptionTextField.setText(data[1]);
			groupTextField.setText(data[2]);
			barcodeTextField.setText(data[3]);
			locationTextField.setText(data[4]);
			quantityTextField.setText(data[5]);
			thresholdTextField.setText(data[6]);
			salesPriceTextField.setText(data[7]);
			purchasePriceTextField.setText(data[8]);
			discountTextField.setText(data[9]);
			productName.setText(data[0] + " : " + data[3]);
		}
		
	}
	
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}
	
	private void finishCreation()
	{
		productCtr = new ProductCtr();
		//TODO Check all fields's value and create the product then dispose the window

	}
	
}

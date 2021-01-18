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

import controlLayer.CustomerCtr;
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

public class EditCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private JLabel headerLabel;
	private RoundedButton cancelButton;
	private JPanel panel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel discountLabel;
	private JTextField discountTextField;
	private JLabel phoneLabel;
	private JTextField phoneTextField;
	private JLabel groupLabel;
	private JTextField groupTextField;
	private JLabel Address;
	private JTextField addressTextField;
	private JPanel header;
	private CustomerCtr customerCtr;
	private JLabel customerName;

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
	public EditCustomerDialog(CustomersPanel customersPanel) {
		//TODO redraw the customersPanel table after finish the editing (update the table)
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
			customerName = new JLabel("<ProductNamePlaceHolder>");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(customerName, gbc_lblNewLabel);
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
				phoneLabel = new JLabel("Phone");
				GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
				gbc_phoneLabel.anchor = GridBagConstraints.WEST;
				gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
				gbc_phoneLabel.gridx = 2;
				gbc_phoneLabel.gridy = 0;
				panel.add(phoneLabel, gbc_phoneLabel);
			}
			{
				Address = new JLabel("Address");
				GridBagConstraints gbc_Address = new GridBagConstraints();
				gbc_Address.anchor = GridBagConstraints.WEST;
				gbc_Address.insets = new Insets(0, 0, 5, 0);
				gbc_Address.gridx = 3;
				gbc_Address.gridy = 0;
				panel.add(Address, gbc_Address);
			}
			{
				nameTextField = new JTextField();
				nameTextField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!nameTextField.getText().equals(""))
						{
							customerName.setText(nameTextField.getText());
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
				phoneTextField = new JTextField();
				phoneTextField.setName("Phone");
				textFieldFunctions(phoneTextField);
				GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
				gbc_phoneTextField.insets = new Insets(0, 0, 5, 5);
				gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneTextField.gridx = 2;
				gbc_phoneTextField.gridy = 1;
				panel.add(phoneTextField, gbc_phoneTextField);
				phoneTextField.setColumns(10);
			}
			{
				addressTextField = new JTextField();
				addressTextField.setName("Address");
				textFieldFunctions(addressTextField);
				GridBagConstraints gbc_addressTextField = new GridBagConstraints();
				gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
				gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_addressTextField.gridx = 3;
				gbc_addressTextField.gridy = 1;
				panel.add(addressTextField, gbc_addressTextField);
				addressTextField.setColumns(10);
			}
			{
				discountLabel = new JLabel("Discount");
				GridBagConstraints gbc_discountLabel = new GridBagConstraints();
				gbc_discountLabel.anchor = GridBagConstraints.WEST;
				gbc_discountLabel.insets = new Insets(0, 0, 5, 5);
				gbc_discountLabel.gridx = 1;
				gbc_discountLabel.gridy = 3;
				panel.add(discountLabel, gbc_discountLabel);
			}
			{
				groupLabel = new JLabel("Group");
				GridBagConstraints gbc_groupLabel = new GridBagConstraints();
				gbc_groupLabel.anchor = GridBagConstraints.WEST;
				gbc_groupLabel.insets = new Insets(0, 0, 5, 5);
				gbc_groupLabel.gridx = 2;
				gbc_groupLabel.gridy = 3;
				panel.add(groupLabel, gbc_groupLabel);
			}
			{
				discountTextField = new JTextField();
				discountTextField.setName("Discount");
				textFieldFunctions(discountTextField);
				GridBagConstraints gbc_discountTextField = new GridBagConstraints();
				gbc_discountTextField.insets = new Insets(0, 0, 5, 5);
				gbc_discountTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_discountTextField.gridx = 1;
				gbc_discountTextField.gridy = 4;
				panel.add(discountTextField, gbc_discountTextField);
				discountTextField.setColumns(10);
			}
			{
				groupTextField = new JTextField();
				groupTextField.setName("Group");
				textFieldFunctions(groupTextField);
				GridBagConstraints gbc_groupTextField = new GridBagConstraints();
				gbc_groupTextField.insets = new Insets(0, 0, 5, 5);
				gbc_groupTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_groupTextField.gridx = 2;
				gbc_groupTextField.gridy = 4;
				panel.add(groupTextField, gbc_groupTextField);
				groupTextField.setColumns(10);
			}
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

	}
	
	/**
	 * Gets the barcode of the product, fills up all the fields with the current data.
	 * Also changes the namelabel to the actual product name + barcode.
	 * 
	 * @param barcode
	 */
	public void fillFields(String phone)
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
		}
		
	}
	
	
	private void finishCreation()
	{
		customerCtr = new CustomerCtr();
		//TODO Check all fields's value and create the product then dispose the window

	}
	
}

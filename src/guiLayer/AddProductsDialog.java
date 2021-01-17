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

public class AddProductsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private JTextField searchBar;
	private JLabel productErrorLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	private JLabel tablePageLabel;
	private JTable createOrderPanelTable;
	private JTable table;
	private RoundedButton cancelButton;
	private ProductCtr productCtr;
	private ArrayList<String> barcodes = new ArrayList<>();
	private String[] tableElements = new String[] {"Barcode", "Name", "Discount", "Input Quantity", "Price", "Stock", ""};
	private DocumentListener cl = new DocumentListener()
	
	{
		@Override
		public void insertUpdate(DocumentEvent e)
		{
			searchProduct(false);
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			searchProduct(false);
		}

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			searchProduct(false);
		}
	};

	/**
	 * Create the dialog.
	 */
	public AddProductsDialog(JTable createOrderPanelTable) {
		this.createOrderPanelTable = createOrderPanelTable;
		setBounds(100, 100, 1042, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.5, 0.0, 0.5, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.2, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			searchBar = new JTextField();
			searchBar.setFocusable(false);
			searchBar.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143), 1, true));
			searchBar.setText("🔍 Product Name...");
			searchBar.setForeground(new Color(149, 149, 149));
			searchBar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						searchBar.setFocusable(false);
						searchBar.setFocusable(true);
						if (searchBar.getText().equals(""))
						{
							
							searchBar.getDocument().removeDocumentListener(cl);
							searchBar.setText("🔍 Product Name...");
							searchBar.getDocument().addDocumentListener(cl);
							
						}
						else
						{
							searchProduct(true);
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
					if (searchBar.getText().equals("🔍 Product Name..."))
					{
						searchBar.getDocument().removeDocumentListener(cl);
						searchBar.setText("");
						searchBar.getDocument().addDocumentListener(cl);
					}
				}
			});
			
			GridBagConstraints gbc_searchBar = new GridBagConstraints();
			gbc_searchBar.insets = new Insets(0, 0, 5, 5);
			gbc_searchBar.fill = GridBagConstraints.HORIZONTAL;
			gbc_searchBar.gridx = 0;
			gbc_searchBar.gridy = 0;
			contentPanel.add(searchBar, gbc_searchBar);
			searchBar.setColumns(10);
		}
		
		productErrorLabel = new JLabel("Product not found!");
		productErrorLabel.setVisible(false);
		productErrorLabel.setForeground(Color.RED);
		productErrorLabel.setFont(new Font("Lato", Font.BOLD, 14));
		GridBagConstraints gbc_productErrorLabel = new GridBagConstraints();
		gbc_productErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_productErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productErrorLabel.gridx = 1;
		gbc_productErrorLabel.gridy = 0;
		contentPanel.add(productErrorLabel, gbc_productErrorLabel);
		{
			leftArrowLabel = new JLabel(" < ");
			
			leftArrowLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					searchBar.getDocument().removeDocumentListener(cl);
					searchBar.setText("🔍 Product Name...");
					searchBar.getDocument().addDocumentListener(cl);
					loadPage(getPageIndex() - 1);
				}
			});
			leftArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GridBagConstraints gbc_leftArrowLabel = new GridBagConstraints();
			gbc_leftArrowLabel.anchor = GridBagConstraints.EAST;
			gbc_leftArrowLabel.insets = new Insets(0, 0, 5, 5);
			gbc_leftArrowLabel.gridx = 0;
			gbc_leftArrowLabel.gridy = 1;
			contentPanel.add(leftArrowLabel, gbc_leftArrowLabel);
			
			tablePageLabel = new JLabel("<html><u>1</u></html>");
			tablePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_tablePageLabel = new GridBagConstraints();
			gbc_tablePageLabel.insets = new Insets(0, 0, 5, 5);
			gbc_tablePageLabel.gridx = 1;
			gbc_tablePageLabel.gridy = 1;
			contentPanel.add(tablePageLabel, gbc_tablePageLabel);
			
			rightArrowLabel = new JLabel(" > ");
			rightArrowLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					searchBar.setFocusable(false);
					searchBar.setFocusable(true);
					searchBar.getDocument().removeDocumentListener(cl);
					searchBar.setText("🔍 Product Name...");
					searchBar.getDocument().addDocumentListener(cl);
					loadPage(getPageIndex() + 1);
				}
			});
			
			rightArrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GridBagConstraints gbc_rightArrowLabel = new GridBagConstraints();
			gbc_rightArrowLabel.anchor = GridBagConstraints.WEST;
			gbc_rightArrowLabel.insets = new Insets(0, 0, 5, 5);
			gbc_rightArrowLabel.gridx = 2;
			gbc_rightArrowLabel.gridy = 1;
			contentPanel.add(rightArrowLabel, gbc_rightArrowLabel);
		}
		{
			table = new JTable();
			table.setName("AddProductsDialog");
			//Changes the Color of the header
			JTableHeader header = table.getTableHeader();
			header.setBackground(babyBlue);
			header.setForeground(Color.WHITE);
			header.setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));

			table.setRowSelectionAllowed(false);
			table.setFocusable(false);
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Lato", Font.PLAIN, 14));
			table.getTableHeader().setFont(new Font("Lato", Font.BOLD, 14));
			table.setShowVerticalLines(false);
			table.setRowHeight(50);
			
			TableCellRenderer tableRenderer = table.getDefaultRenderer(RoundedButton.class);
		    table.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));
		      
			// Could be moved to a custom header renderer
			DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
			defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
			table.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);
			
			

			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				tableElements
				
			) {
				Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, RoundedButton.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			

			table.addMouseListener(new JTableButtonMouseListener(table,this));
			table.addMouseMotionListener(new JTableButtonMouseListener(table));
			
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.gridwidth = 5;
			gbc_table.insets = new Insets(0, 0, 5, 5);
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 2;
			
			JScrollPane tablePane = new JScrollPane(table);
			tablePane.setBorder(BorderFactory.createEmptyBorder());
			contentPanel.add(tablePane, gbc_table);
			loadPage(1);
		}
		{
			RoundedButton okButton = new RoundedButton("Finish", new Color(28, 150, 202), Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					finishCreation();
				}
			});
			formatButton(okButton);
			blueButton(okButton);
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 3;
			gbc_okButton.gridy = 3;
			contentPanel.add(okButton, gbc_okButton);
		}
		
			cancelButton = new RoundedButton("Cancel", Color.WHITE, new Color(28, 150, 202), new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			formatButton(cancelButton);
			whiteButton(cancelButton);
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
			gbc_cancelButton.gridx = 4;
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
			
		});
	}
	
	private void defaultFillTable(int index) {
		productCtr = new ProductCtr();
		//we get the product information from the productContainer
		ArrayList<String[]> data = productCtr.defaultFill(index);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		DefaultTableModel createOrderPanelTabledtm = (DefaultTableModel) createOrderPanelTable.getModel();
		dtm.setRowCount(0);
		dtm.setRowCount(data.size());
		
		//used for detecting if the table already contains the barcode.
		//if yes, we created the "Added" button instead of "Add"
		boolean tableContains = false;

		for (int e = 0; e<data.size();e++)
		{
			
			for (int element = 0; element < 6; element++)
			{
				table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
			}
			
			table.setValueAt(data.get(e)[6], e, table.convertColumnIndexToView(table.getColumn("Discount").getModelIndex()));
			table.setValueAt("", e, table.convertColumnIndexToView(table.getColumn("Input Quantity").getModelIndex()));
			//TODO Check if the barcode is already in the other table THIS IS A REALLY A REALL STUPID SYINTAX WHAT I JUST WROTE, JUST DELETE IT <3
			for (int rows = 0; rows<createOrderPanelTabledtm.getRowCount();rows++)
			{
				if (createOrderPanelTable.getValueAt(rows, createOrderPanelTable.getColumn("Barcode").getModelIndex()).equals(data.get(e)[0]))
				{
					tableContains = true;
				}
			}
			
			if (tableContains)
			{
				table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
			}
			else
			{
				table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
			}
			
		}
	}
	
	private void searchProduct(Boolean Notdynamic)
	{
		
		productErrorLabel.setVisible(false);
		if ((!searchBar.getText().equals(""))&&(!searchBar.getText().equals("🔍 Product Name...")))
		{
			productCtr = new ProductCtr();
			ArrayList<String[]> data = productCtr.searchField(searchBar.getText()); // TODO Check this
			

			
			if (data.size() != 0)
			{ 
				productErrorLabel.setVisible(false);
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(data.size());
				for (int e = 0; e < data.size();e++)
				{
					for (int element = 0; element < 5; element++)
					{						
						table.setValueAt(data.get(e)[element], e, table.convertColumnIndexToView(table.getColumn(tableElements[element]).getModelIndex()));
	
					}
					if (!barcodes.contains(table.getValueAt(e, table.convertColumnIndexToView(table.getColumn("Barcode").getModelIndex()))))
					{
						table.setValueAt(new RoundedButton("Add", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, table.convertColumnIndexToView(table.getColumn(tableElements[6]).getModelIndex()));
					}
					else
					{
						table.setValueAt(new RoundedButton("Added", Color.WHITE, Color.BLACK, Color.WHITE, new Font("Lato", Font.BOLD, 14)), table.getSelectedRow(), table.getColumn("").getModelIndex());
					}
					
					
				}
				
			}
			else
			{
				if (Notdynamic)
				{
					productErrorLabel.setVisible(true);
				}
			}
			
		}
		else
		{
			loadPage(1);
		}
	}
	
	public void addToList(String barcode) 
	{
		barcodes.add(barcode);
	}
	
	private void finishCreation() {
		productCtr = new ProductCtr();
		if (barcodes.size() == 0)
		{
			return;
		}
		DefaultTableModel dtm = (DefaultTableModel) createOrderPanelTable.getModel();
		//dtm.setRowCount(0);
		dtm.setRowCount(barcodes.size());
		String[] data;
		int quantity,price;
		for (int e = 0; e< barcodes.size(); e++)
		{
			data = productCtr.getProductrByBarcode(barcodes.get(e));
			//TODO ERRORHANDLING TO FOR THE QUANTITY
			quantity = Integer.parseInt((String)table.getValueAt(e, table.getColumn("Input Quantity").getModelIndex()));
			price = Integer.parseInt(data[8]);
			createOrderPanelTable.setValueAt(data[0], e, createOrderPanelTable.getColumn("Name").getModelIndex());
			createOrderPanelTable.setValueAt(data[3], e, createOrderPanelTable.getColumn("Barcode").getModelIndex());
			createOrderPanelTable.setValueAt(data[9], e, createOrderPanelTable.getColumn("Discount").getModelIndex());
			createOrderPanelTable.setValueAt(price, e, createOrderPanelTable.getColumn("Price").getModelIndex());
			createOrderPanelTable.setValueAt(table.getValueAt(e, table.getColumn("Input Quantity").getModelIndex()), e, createOrderPanelTable.getColumn("Quantity").getModelIndex());
			createOrderPanelTable.setValueAt((quantity+price),e, createOrderPanelTable.getColumn("Total").getModelIndex());

			createOrderPanelTable.setValueAt(new RoundedButton("Remove", babyBlue, Color.WHITE, Color.WHITE, new Font("Lato", Font.BOLD, 14)), e, createOrderPanelTable.getColumn("").getModelIndex());
		}
		//TODO Check data and send it back to the CreateOrderPanel with the method setOrderPanelData(barcodes)
		dispose();
	}
	
	public int getPageIndex()  
	{
		return Integer.parseInt(tablePageLabel.getText().replaceAll("\\<.*?\\>", ""));
	}
	
	public void loadPage(int index)
	{
		productErrorLabel.setVisible(false);
		if(index == 0) {
			return;
		}
		
		
		productCtr = new ProductCtr();
		if (productCtr.defaultFill(index).isEmpty())
		{
			return;
		}
		
		defaultFillTable(index);

		tablePageLabel.setText("<html><u>" + index + "</u></html>");
	}

}

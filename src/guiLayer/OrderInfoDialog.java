package guiLayer;

import controlLayer.*;
import guiLayer.Renderers.ProductJListCellRenderer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class OrderInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private int orderNumber;
	private OrderCtr orderCtr = new OrderCtr();
	private JPanel orderDetailsPanel;
	private JPanel orderPanel;
	private JPanel customerPanel;
	private JPanel productsListPanel;
	private JList<String> orderLineItemList;
	private RoundedButton confirmOfferButton;

	/**
	 * Creates the dialog.
	 */
	public OrderInfoDialog(int orderNumber) {
		this.orderNumber = orderNumber; 
		
		setBounds(100, 100, 794, 545);
		setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(252, 252, 252));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
		}
		
		//Blue headline********************************************************
		JPanel header = new JPanel();
		header.setBackground(babyBlue);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPanel.add(header, gbc_panel_1);
		{
			
			JLabel headerLabel = new JLabel("Order Details");
			header.add(headerLabel);
			headerLabel.setVisible(true);
			headerLabel.setForeground(Color.WHITE);
			headerLabel.setFont(new Font("LATO", Font.BOLD, 20));
		}
		
		//Order Details Panel********************************************************
		{
			orderDetailsPanel = new JPanel();
			orderDetailsPanel.setBorder(BorderFactory.createLineBorder(new Color(243, 243, 243), 2, true));
			orderDetailsPanel.setBackground(Color.WHITE);
			GridBagConstraints gbc_orderDetailsPanel = new GridBagConstraints();
			gbc_orderDetailsPanel.gridwidth = 3;
			gbc_orderDetailsPanel.insets = new Insets(20, 50, 20, 50);
			gbc_orderDetailsPanel.fill = GridBagConstraints.BOTH;
			gbc_orderDetailsPanel.gridx = 0;
			gbc_orderDetailsPanel.gridy = 1;
			contentPanel.add(orderDetailsPanel, gbc_orderDetailsPanel);
			GridBagLayout gbl_orderDetailsPanel = new GridBagLayout();
			gbl_orderDetailsPanel.columnWidths = new int[]{0, 0, 0};
			gbl_orderDetailsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_orderDetailsPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_orderDetailsPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			orderDetailsPanel.setLayout(gbl_orderDetailsPanel);
			
			/********************************************** Order info panel**********************************************/
			{
				orderPanel = new JPanel();
				orderPanel.setBorder(new TitledBorder(new LineBorder(babyBlue), "Order", TitledBorder.LEADING, TitledBorder.TOP, null, babyBlue));
				((TitledBorder)orderPanel.getBorder()).setTitleFont(new Font("Lato", Font.BOLD, 14));
				orderPanel.setBackground(Color.WHITE);
				GridBagConstraints gbc_orderPanel = new GridBagConstraints();
				gbc_orderPanel.insets = new Insets(0, 0, 5, 5);
				gbc_orderPanel.fill = GridBagConstraints.BOTH;
				gbc_orderPanel.gridx = 0;
				gbc_orderPanel.gridy = 0;
				orderDetailsPanel.add(orderPanel, gbc_orderPanel);
				GridBagLayout gbl_orderPanel = new GridBagLayout();
				gbl_orderPanel.columnWidths = new int[]{0, 0, 0, 0};
				gbl_orderPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
				gbl_orderPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_orderPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				orderPanel.setLayout(gbl_orderPanel);
				
				//Order number********************************************************
				{
					JLabel orderNumberLabel = new JLabel("Order number:");
					orderNumberLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_ordernumberLabel = new GridBagConstraints();
					gbc_ordernumberLabel.anchor = GridBagConstraints.WEST;
					gbc_ordernumberLabel.insets = new Insets(0, 0, 5, 5);
					gbc_ordernumberLabel.gridx = 0;
					gbc_ordernumberLabel.gridy = 0;
					orderPanel.add(orderNumberLabel, gbc_ordernumberLabel);
				}
				{
					JLabel orderNumberValueLabel = new JLabel("...");
					orderNumberValueLabel.setName("OrderNumberInfoLabel");
					orderNumberValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					orderNumberValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_orderNumberValueLabel = new GridBagConstraints();
					gbc_orderNumberValueLabel.insets = new Insets(0, 0, 5, 5);
					gbc_orderNumberValueLabel.anchor = GridBagConstraints.WEST;
					gbc_orderNumberValueLabel.gridx = 0;
					gbc_orderNumberValueLabel.gridy = 1;
					orderPanel.add(orderNumberValueLabel, gbc_orderNumberValueLabel);
				}
				
				//Order status********************************************************
				{
					JLabel statusLabel = new JLabel("Status:");
					statusLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_statusLabel = new GridBagConstraints();
					gbc_statusLabel.anchor = GridBagConstraints.WEST;
					gbc_statusLabel.insets = new Insets(0, 0, 5, 5);
					gbc_statusLabel.gridx = 1;
					gbc_statusLabel.gridy = 0;
					orderPanel.add(statusLabel, gbc_statusLabel);
				}
				{
					JLabel statusValueLabel = new JLabel("...");
					statusValueLabel.setName("StatusInfoLabel");
					statusValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					statusValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_statusValueLabel = new GridBagConstraints();
					gbc_statusValueLabel.anchor = GridBagConstraints.WEST;
					gbc_statusValueLabel.insets = new Insets(0, 0, 5, 5);
					gbc_statusValueLabel.gridx = 1;
					gbc_statusValueLabel.gridy = 1;
					orderPanel.add(statusValueLabel, gbc_statusValueLabel);
				}
				
				//Order delivery********************************************************
				{
					JLabel deliveryLabel = new JLabel("Delivery:");
					deliveryLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_deliveryLabel = new GridBagConstraints();
					gbc_deliveryLabel.insets = new Insets(0, 0, 5, 0);
					gbc_deliveryLabel.gridx = 2;
					gbc_deliveryLabel.gridy = 0;
					orderPanel.add(deliveryLabel, gbc_deliveryLabel);
				}
				{
					JLabel deliveryValueLabel = new JLabel("...");
					deliveryValueLabel.setName("DeliveryInfoLabel");
					deliveryValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					deliveryValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_deliveryValueLabel = new GridBagConstraints();
					gbc_deliveryValueLabel.anchor = GridBagConstraints.WEST;
					gbc_deliveryValueLabel.insets = new Insets(0, 0, 5, 0);
					gbc_deliveryValueLabel.gridx = 2;
					gbc_deliveryValueLabel.gridy = 1;
					orderPanel.add(deliveryValueLabel, gbc_deliveryValueLabel);
				}
				
				
				//Order expiration date********************************************************
				{
					JLabel expirationDateLabel = new JLabel("Expiration Date:");
					expirationDateLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_expirationDateLabel = new GridBagConstraints();
					gbc_expirationDateLabel.anchor = GridBagConstraints.WEST;
					gbc_expirationDateLabel.insets = new Insets(0, 0, 5, 5);
					gbc_expirationDateLabel.gridx = 0;
					gbc_expirationDateLabel.gridy = 2;
					orderPanel.add(expirationDateLabel, gbc_expirationDateLabel);
				}
				{
					JLabel expirationDateValueLabel = new JLabel("...");
					expirationDateValueLabel.setName("ExperationDateInfoLabel");
					expirationDateValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					expirationDateValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_expirationDateValueLabel = new GridBagConstraints();
					gbc_expirationDateValueLabel.anchor = GridBagConstraints.WEST;
					gbc_expirationDateValueLabel.insets = new Insets(0, 0, 0, 5);
					gbc_expirationDateValueLabel.gridx = 0;
					gbc_expirationDateValueLabel.gridy = 3;
					orderPanel.add(expirationDateValueLabel, gbc_expirationDateValueLabel);
				}
				
				//Order purchase date********************************************************
				{
					JLabel purchaseDateLabel = new JLabel("Purchase Date:");
					purchaseDateLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_purchaseDateLabel = new GridBagConstraints();
					gbc_purchaseDateLabel.anchor = GridBagConstraints.WEST;
					gbc_purchaseDateLabel.insets = new Insets(0, 0, 5, 5);
					gbc_purchaseDateLabel.gridx = 1;
					gbc_purchaseDateLabel.gridy = 2;
					orderPanel.add(purchaseDateLabel, gbc_purchaseDateLabel);
				}
				{
					JLabel purchaseDateValueLabel = new JLabel("...");
					purchaseDateValueLabel.setName("PurchaseDateInfoLabel");
					purchaseDateValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					purchaseDateValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_purchaseDateValueLabel = new GridBagConstraints();
					gbc_purchaseDateValueLabel.anchor = GridBagConstraints.WEST;
					gbc_purchaseDateValueLabel.insets = new Insets(0, 0, 0, 5);
					gbc_purchaseDateValueLabel.gridx = 1;
					gbc_purchaseDateValueLabel.gridy = 3;
					orderPanel.add(purchaseDateValueLabel, gbc_purchaseDateValueLabel);
				}
			}
			
			/********************************************** Customer Info Panel**********************************************/
			{
				//Customer Info********************************************************
				customerPanel = new JPanel();
				customerPanel.setBorder(new TitledBorder(new LineBorder(babyBlue), "Customer", TitledBorder.LEADING, TitledBorder.TOP, null, babyBlue));
				((TitledBorder)customerPanel.getBorder()).setTitleFont(new Font("Lato", Font.BOLD, 14));
				customerPanel.setBackground(Color.WHITE);
				GridBagConstraints gbc_customerPanel = new GridBagConstraints();
				gbc_customerPanel.insets = new Insets(0, 0, 5, 0);
				gbc_customerPanel.fill = GridBagConstraints.BOTH;
				gbc_customerPanel.gridx = 1;
				gbc_customerPanel.gridy = 0;
				orderDetailsPanel.add(customerPanel, gbc_customerPanel);
				GridBagLayout gbl_customerPanel = new GridBagLayout();
				gbl_customerPanel.columnWidths = new int[]{0, 0, 0, 0};
				gbl_customerPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
				gbl_customerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_customerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				customerPanel.setLayout(gbl_customerPanel);
				
				//Customer name********************************************************
				{
					JLabel nameLabel = new JLabel("Name:");
					nameLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_nameLabel = new GridBagConstraints();
					gbc_nameLabel.anchor = GridBagConstraints.WEST;
					gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
					gbc_nameLabel.gridx = 0;
					gbc_nameLabel.gridy = 0;
					customerPanel.add(nameLabel, gbc_nameLabel);
				}
				{
					JLabel customerValueLabel = new JLabel("...");
					customerValueLabel.setName("CustomerInfoLabel");
					customerValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					customerValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_customerValueLabel = new GridBagConstraints();
					gbc_customerValueLabel.insets = new Insets(0, 0, 5, 5);
					gbc_customerValueLabel.anchor = GridBagConstraints.WEST;
					gbc_customerValueLabel.gridx = 0;
					gbc_customerValueLabel.gridy = 1;
					customerPanel.add(customerValueLabel, gbc_customerValueLabel);
				}
				
				//Customer phone********************************************************
				{
					JLabel phoneLabel = new JLabel("Phone:");
					phoneLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
					gbc_phoneLabel.anchor = GridBagConstraints.WEST;
					gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
					gbc_phoneLabel.gridx = 1;
					gbc_phoneLabel.gridy = 0;
					customerPanel.add(phoneLabel, gbc_phoneLabel);
				}
				{
					JLabel phoneValueLabel = new JLabel("...");
					phoneValueLabel.setName("PhoneInfoLabel");
					phoneValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					phoneValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_phoneValueLabel = new GridBagConstraints();
					gbc_phoneValueLabel.insets = new Insets(0, 0, 5, 5);
					gbc_phoneValueLabel.anchor = GridBagConstraints.WEST;
					gbc_phoneValueLabel.gridx = 1;
					gbc_phoneValueLabel.gridy = 1;
					customerPanel.add(phoneValueLabel, gbc_phoneValueLabel);
				}
				
				//Customer group********************************************************
				{
					JLabel groupLabel = new JLabel("Group:");
					groupLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_groupLabel = new GridBagConstraints();
					gbc_groupLabel.anchor = GridBagConstraints.WEST;
					gbc_groupLabel.insets = new Insets(0, 0, 5, 0);
					gbc_groupLabel.gridx = 2;
					gbc_groupLabel.gridy = 0;
					customerPanel.add(groupLabel, gbc_groupLabel);
				}
				{
					JLabel groupValueLabel = new JLabel("...");
					groupValueLabel.setName("GroupInfoLabel");
					groupValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					groupValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_groupValueLabel = new GridBagConstraints();
					gbc_groupValueLabel.insets = new Insets(0, 0, 5, 0);
					gbc_groupValueLabel.anchor = GridBagConstraints.WEST;
					gbc_groupValueLabel.gridx = 2;
					gbc_groupValueLabel.gridy = 1;
					customerPanel.add(groupValueLabel, gbc_groupValueLabel);
				}
				
				//Customer address********************************************************
				{
					JLabel addressLabel = new JLabel("Address:");
					addressLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_addressLabel = new GridBagConstraints();
					gbc_addressLabel.anchor = GridBagConstraints.WEST;
					gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
					gbc_addressLabel.gridx = 0;
					gbc_addressLabel.gridy = 2;
					customerPanel.add(addressLabel, gbc_addressLabel);
				}
				{
					JLabel addressValueLabel = new JLabel("...");
					addressValueLabel.setName("AddressInfoLabel");
					addressValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					addressValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_addressValueLabel = new GridBagConstraints();
					gbc_addressValueLabel.anchor = GridBagConstraints.WEST;
					gbc_addressValueLabel.insets = new Insets(0, 0, 0, 5);
					gbc_addressValueLabel.gridx = 0;
					gbc_addressValueLabel.gridy = 3;
					customerPanel.add(addressValueLabel, gbc_addressValueLabel);
				}
				
				//Customer discount********************************************************
				{
					JLabel discountLabel = new JLabel("Discount:");
					discountLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					GridBagConstraints gbc_discountLabel = new GridBagConstraints();
					gbc_discountLabel.anchor = GridBagConstraints.WEST;
					gbc_discountLabel.insets = new Insets(0, 0, 5, 5);
					gbc_discountLabel.gridx = 1;
					gbc_discountLabel.gridy = 2;
					customerPanel.add(discountLabel, gbc_discountLabel);
				}
				{
					JLabel discountValueLabel = new JLabel("...");
					discountValueLabel.setName("CustomerDiscountInfoLabel");
					discountValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
					discountValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
					GridBagConstraints gbc_discountValueLabel = new GridBagConstraints();
					gbc_discountValueLabel.anchor = GridBagConstraints.WEST;
					gbc_discountValueLabel.insets = new Insets(0, 0, 0, 5);
					gbc_discountValueLabel.gridx = 1;
					gbc_discountValueLabel.gridy = 3;
					customerPanel.add(discountValueLabel, gbc_discountValueLabel);
				}
			}
			
			
			/********************************************** Products List  Panel**********************************************/
			{
				productsListPanel = new JPanel();
				productsListPanel.setBorder(new TitledBorder(new LineBorder(babyBlue), "Products", TitledBorder.LEADING, TitledBorder.TOP, null, babyBlue));
				((TitledBorder)productsListPanel.getBorder()).setTitleFont(new Font("Lato", Font.BOLD, 14));
				productsListPanel.setBackground(Color.WHITE);
				GridBagConstraints gbc_productsListPanel = new GridBagConstraints();
				gbc_productsListPanel.gridwidth = 2;
				gbc_productsListPanel.insets = new Insets(0, 0, 5, 0);
				gbc_productsListPanel.fill = GridBagConstraints.BOTH;
				gbc_productsListPanel.gridx = 0;
				gbc_productsListPanel.gridy = 1;
				orderDetailsPanel.add(productsListPanel, gbc_productsListPanel);
				GridBagLayout gbl_productsListPanel = new GridBagLayout();
				gbl_productsListPanel.columnWidths = new int[]{0};
				gbl_productsListPanel.rowHeights = new int[]{0};
				gbl_productsListPanel.columnWeights = new double[]{1.0};
				gbl_productsListPanel.rowWeights = new double[]{1.0};
				productsListPanel.setLayout(gbl_productsListPanel);
				
				//Products List********************************************************
				{
					orderLineItemList = new JList<String>();
					GridBagConstraints gbc_orderLineItemList = new GridBagConstraints();
					gbc_orderLineItemList.fill = GridBagConstraints.BOTH;
					gbc_orderLineItemList.gridx = 0;
					gbc_orderLineItemList.gridy = 0;
					productsListPanel.add(orderLineItemList, gbc_orderLineItemList);
				}
			}
			
			/********************************************** Order summation panel **********************************************/
			//Discount Label********************************************************
			{
				JLabel discountLabel = new JLabel("Discount:");
				discountLabel.setFont(new Font("Lato", Font.PLAIN, 13));
				GridBagConstraints gbc_discountLabel = new GridBagConstraints();
				gbc_discountLabel.anchor = GridBagConstraints.WEST;
				gbc_discountLabel.insets = new Insets(0, 0, 5, 0);
				gbc_discountLabel.gridx = 1;
				gbc_discountLabel.gridy = 2;
				orderDetailsPanel.add(discountLabel, gbc_discountLabel);
			}
			{
				JLabel discountValueLabel = new JLabel("...");
				discountValueLabel.setName("OrderDiscountInfoLabel");
				discountValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
				discountValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
				GridBagConstraints gbc_discountValueLabel = new GridBagConstraints();
				gbc_discountValueLabel.anchor = GridBagConstraints.WEST;
				gbc_discountValueLabel.insets = new Insets(0, 0, 5, 0);
				gbc_discountValueLabel.gridx = 1;
				gbc_discountValueLabel.gridy = 3;
				orderDetailsPanel.add(discountValueLabel, gbc_discountValueLabel);
			}
			
			//Total Price Label********************************************************
			{
				JLabel totalPriceLabel = new JLabel("Total:");
				totalPriceLabel.setFont(new Font("Lato", Font.PLAIN, 13));
				GridBagConstraints gbc_totalPriceLabel = new GridBagConstraints();
				gbc_totalPriceLabel.anchor = GridBagConstraints.WEST;
				gbc_totalPriceLabel.insets = new Insets(0, 0, 5, 0);
				gbc_totalPriceLabel.gridx = 1;
				gbc_totalPriceLabel.gridy = 4;
				orderDetailsPanel.add(totalPriceLabel, gbc_totalPriceLabel);
			}
			{
				JLabel totalPriceValueLabel = new JLabel("...");
				totalPriceValueLabel.setName("TotalInfoLabel");
				totalPriceValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
				totalPriceValueLabel.setFont(new Font("Lato", Font.PLAIN, 13));
				GridBagConstraints gbc_totalPriceValueLabel = new GridBagConstraints();
				gbc_totalPriceValueLabel.anchor = GridBagConstraints.WEST;
				gbc_totalPriceValueLabel.gridx = 1;
				gbc_totalPriceValueLabel.gridy = 5;
				orderDetailsPanel.add(totalPriceValueLabel, gbc_totalPriceValueLabel);
			}
		}
		
		/********************************************** Buttons **********************************************/
		//Confirm Offer Button********************************************************
		{
			confirmOfferButton = new RoundedButton("Confirm Offer", babyBlue, Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			confirmOfferButton.setPreferredSize(new Dimension(150, 30));
			confirmOfferButton.addOffset(-13, 2);
			
			confirmOfferButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					orderCtr.confirmOffer(orderNumber);
					//confirmOfferButton.setVisible(false);
					retrieveInfo();
				}
			});
			
			blueButton(confirmOfferButton);
			
			GridBagConstraints gbc_confirmOfferButton = new GridBagConstraints();
			gbc_confirmOfferButton.anchor = GridBagConstraints.EAST;
			gbc_confirmOfferButton.insets = new Insets(0, 0, 0, 5);
			gbc_confirmOfferButton.gridx = 0;
			gbc_confirmOfferButton.gridy = 2;
			contentPanel.add(confirmOfferButton, gbc_confirmOfferButton);
		}
		
		//Generate Invoice Button********************************************************
		{	
			RoundedButton invoiceButton = new RoundedButton("Generate Invoice", babyBlue, Color.WHITE, babyBlue, new Font("Lato", Font.BOLD, 15));
			invoiceButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						
				}
			});
			invoiceButton.setPreferredSize(new Dimension(150, 30));
			invoiceButton.addOffset(-13, 2);
					
			blueButton(invoiceButton);
					
			GridBagConstraints gbc_invoiceButton = new GridBagConstraints();
			gbc_invoiceButton.anchor = GridBagConstraints.EAST;
			gbc_invoiceButton.insets = new Insets(0, 0, 0, 5);
			gbc_invoiceButton.gridx = 1;
			gbc_invoiceButton.gridy = 2;
			contentPanel.add(invoiceButton, gbc_invoiceButton);
		}
		
		//Close Button******************************************************************
		{
			RoundedButton closeButton = new RoundedButton("Close", Color.WHITE, babyBlue, babyBlue, new Font("Lato", Font.BOLD, 15));
			closeButton.setPreferredSize(new Dimension(100, 30));
			closeButton.addOffset(-5, 2);
			
			closeButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();	
				}
			});
			
			//Formats button to have hover function
			whiteButton(closeButton);
			
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.anchor = GridBagConstraints.SOUTH;
			gbc_okButton.gridx = 2;
			gbc_okButton.gridy = 2;
			contentPanel.add(closeButton, gbc_okButton);
		}
		
		retrieveInfo();
	}
	
	/**
	 * Retrieves info about the order, based on what
	 * order is double clicked to open the window.
	 * The info of the order is then filled into the
	 * created labels using this method.
	 */
	public void retrieveInfo()
	{
		ArrayList<String[]> orderInfo = orderCtr.findOrder(orderNumber);
		JLabel[] labels = new JLabel[5];
		String[] orderDetails = orderInfo.get(orderInfo.size()-1);
		String[] customerDetails = orderInfo.get(0);
		int index = 0;
		
		//Confirm Offer Button****************************
		if(!orderDetails[2].equalsIgnoreCase("pending")) {
			confirmOfferButton.setVisible(false);
		}
		
		//Order panel info****************************
		for(Component c : orderPanel.getComponents())
		{
			if(c.getName() != null)
			{
				if(c.getName().contains("InfoLabel")) 
				{
					labels[index] = ((JLabel) c);
					index++;
				}
			}
		}
		
		labels[0].setText(String.valueOf(orderNumber));
		labels[1].setText(orderDetails[2]);
		labels[2].setText(orderDetails[5]);
		labels[3].setText(orderDetails[3]);
		labels[4].setText(orderDetails[1]);
		
		//resets index and JLabel
		labels = new JLabel[5];
		index = 0;
		
		//Customer panel info****************************
		for(Component c : customerPanel.getComponents())
		{
			if(c.getName() != null)
			{
				if(c.getName().contains("InfoLabel"))
				{
					labels[index] = ((JLabel) c);
					index++;
				}
			}
		}
		
		labels[0].setText(customerDetails[0]);
		labels[1].setText(customerDetails[2]);
		labels[2].setText(customerDetails[1]);
		labels[3].setText(customerDetails[3]);
		labels[4].setText(customerDetails[4]);
		
		//resets index and JLabel
		labels = new JLabel[2];
		index = 0;
		
		//Order total price and discount****************************
		for(Component c : orderDetailsPanel.getComponents())
		{
			if(c.getName() != null)
			{
				if(c.getName().contains("InfoLabel")) 
				{
					labels[index] = ((JLabel) c);
					index++;
				}
			}
		}
		labels[0].setText(String.valueOf(orderDetails[4]));
		labels[1].setText(String.valueOf(orderDetails[0]));
	
	
		ArrayList<String> products = new ArrayList<String>();
		
		for(int i = 1; i < orderInfo.size()-1; i++)
		{
			String productInfo = orderInfo.get(i)[3];
			productInfo += " x " + orderInfo.get(i)[1];
			
			int salesPrice = Integer.parseInt(orderInfo.get(i)[2]);
			int quantity = Integer.parseInt(orderInfo.get(i)[3]);
			int totalPrice = salesPrice * quantity;
			
			productInfo += "  .   .   .   .   .   .   " + totalPrice + "DKK";
			products.add(productInfo);
		}
		
		orderLineItemList.setModel(new AbstractListModel<String>() {
			ArrayList<String> values = products;
			public int getSize() {
				return values.size();
			}
			public String getElementAt(int index) {
				return values.get(index);
			}
		});
	
		orderLineItemList.setCellRenderer(new ProductJListCellRenderer());
	}
	
	public void blueButton(RoundedButton button) {
		
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
	
	public void whiteButton(RoundedButton button) {
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
}

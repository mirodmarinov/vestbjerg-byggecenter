package guiLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

import modelLayer.Serialization;

public class OrderMenuGUI {

	private JFrame frmVestbjergByggecenterManagement;
	private CardLayout cards = new CardLayout();
	private JPanel mainFrame = new JPanel();
	private Color babyBlue = new Color(28, 150, 202);
	private OrderPanel orderPanel;
	private ProductsPanel productsPanel = new ProductsPanel();
	private CustomersPanel customersPanel = new CustomersPanel();
	private CreateOrderPanel createOrderPanel = new CreateOrderPanel();
	private RoundedButton orderMenuButton,createOrderButton,productMenuButton,customerMenuButton;
	private RoundedButton selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMenuGUI window = new OrderMenuGUI();
					Locale.setDefault(new Locale("en", "US"));
					window.frmVestbjergByggecenterManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderMenuGUI() {

		//load saved data if available
		Serialization.getInstance().deserializeClass("modelLayer.ProductContainer");
		Serialization.getInstance().deserializeClass("modelLayer.CustomerContainer");
		Serialization.getInstance().deserializeClass("modelLayer.OrderContainer");
		
		//populate with test data
		//Serialization.populateClasses();
		//Deserialization/class population must happen before initialization of orderPanel to ensure that the main table is not empty before refreshing.
		
		orderPanel = new OrderPanel();
		initialize(); //Autogenerated
		init(); //Made from scratch
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVestbjergByggecenterManagement = new JFrame();
		frmVestbjergByggecenterManagement.setTitle("Vestbjerg Byggecenter Management System");
		frmVestbjergByggecenterManagement.setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		Rectangle height = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frmVestbjergByggecenterManagement.setBounds(0, 0, (int)height.getWidth(), (int)height.getHeight());
		frmVestbjergByggecenterManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVestbjergByggecenterManagement.addWindowListener(new WindowAdapter() {
		
			@Override
		public void windowClosing(WindowEvent e)
		{
			
			Serialization.getInstance().serializeClass("modelLayer.OrderContainer");
			Serialization.getInstance().serializeClass("modelLayer.ProductContainer");
			Serialization.getInstance().serializeClass("modelLayer.CustomerContainer");
		}
		});
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		frmVestbjergByggecenterManagement.getContentPane().setLayout(gridBagLayout);
		
		/*
		 * Here we format the side bar, and prepare it
		 * for the buttons that will be put inside it.
		 * This is done by adding a grid bag layout.
		 */
		JPanel sideBar = new JPanel();
		sideBar.setBackground(babyBlue);
		sideBar.setMinimumSize(new Dimension((int)(height.getWidth()*0.14),(int)height.getHeight()));
		sideBar.setLocation((int)(height.getWidth()*0.14),(int)height.getHeight());
		GridBagConstraints gbc_sideBar = new GridBagConstraints();
		gbc_sideBar.fill = GridBagConstraints.BOTH;
		gbc_sideBar.insets = new Insets(0, 0, 0, 0);
		gbc_sideBar.gridx = 0;
		gbc_sideBar.gridy = 0;
		frmVestbjergByggecenterManagement.getContentPane().add(sideBar, gbc_sideBar);
		GridBagLayout gbl_sideBar = new GridBagLayout();
		gbl_sideBar.columnWidths = new int[]{200};
		gbl_sideBar.rowHeights = new int[]{75, 50, 50, 50, 50, 50, 0};
		gbl_sideBar.columnWeights = new double[]{1.0};
		gbl_sideBar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_sideBar.minimumLayoutSize(sideBar);
		sideBar.setLayout(gbl_sideBar);
		
		/*
		 * Here we create the buttons for the side bar
		 */
		/********************************************** Order Button **********************************************/
		orderMenuButton = new RoundedButton("Orders", babyBlue);
		orderMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(mainFrame, "Orders");
				orderPanel.getSearchTextField().setForeground(new Color(149, 149, 149));
				orderPanel.getSearchTextField().setText("🔍 Search...");
				orderPanel.loadPage(1);
				orderPanel.getTablePageLabel().setText("1");
				orderPanel.getTablePageLabel().setVisible(true);
				changeSelectedButton(orderMenuButton);
			}
		});
		selected = orderMenuButton;
		formatButton(orderMenuButton);
		orderMenuButton.addOffset(-70, 3);
		GridBagConstraints gbc_orderMenuButton = new GridBagConstraints();
		gbc_orderMenuButton.fill = GridBagConstraints.BOTH;
		gbc_orderMenuButton.insets = new Insets(30, 40, 0, 20);
		gbc_orderMenuButton.gridx = 0;
		gbc_orderMenuButton.gridy = 1;
		sideBar.add(orderMenuButton, gbc_orderMenuButton);
		
		
		/********************************************** Create Order/Offer Button **********************************************/                                 
		createOrderButton = new RoundedButton("Create Offer/Order", babyBlue);
		createOrderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(mainFrame, "Create Order");
				changeSelectedButton(createOrderButton);
				
			}
		});
		formatButton(createOrderButton);
		createOrderButton.addOffset(-30, 3);
		GridBagConstraints gbc_createOrderButton = new GridBagConstraints();
		gbc_createOrderButton.fill = GridBagConstraints.BOTH;
		gbc_createOrderButton.insets = new Insets(0, 60, 0, 20);
		gbc_createOrderButton.gridx = 0;
		gbc_createOrderButton.gridy = 2;
		sideBar.add(createOrderButton, gbc_createOrderButton);
		
		
		/********************************************** Products Button**********************************************/
		productMenuButton = new RoundedButton("Products", babyBlue);
		productMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(mainFrame, "Products");
				productsPanel.getSearchTextField().setForeground(new Color(149, 149, 149));
				productsPanel.getSearchTextField().setText("🔍 Search...");
				productsPanel.loadPage(1);
				productsPanel.getTablePageLabel().setText("1");
				productsPanel.getTablePageLabel().setVisible(true);
				changeSelectedButton(productMenuButton);
			}
		});
		formatButton(productMenuButton);
		productMenuButton.addOffset(-65, 3);
		GridBagConstraints gbc_productMenuButton = new GridBagConstraints();
		gbc_productMenuButton.fill = GridBagConstraints.BOTH;
		gbc_productMenuButton.insets = new Insets(0, 40, 0, 20);
		gbc_productMenuButton.gridx = 0;
		gbc_productMenuButton.gridy = 3;
		sideBar.add(productMenuButton, gbc_productMenuButton);
		
		
		/********************************************** Customers Button **********************************************/
		customerMenuButton = new RoundedButton("Customers", babyBlue);
		customerMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(mainFrame, "Customers");
				customersPanel.getSearchTextField().setForeground(new Color(149, 149, 149));
				customersPanel.getSearchTextField().setText("🔍 Search...");
				customersPanel.loadPage(1);
				customersPanel.getTablePageLabel().setText("1");
				customersPanel.getTablePageLabel().setVisible(true);
				changeSelectedButton(customerMenuButton);
				
			}
		});
		formatButton(customerMenuButton);
		customerMenuButton.addOffset(-59, 3);
		GridBagConstraints gbc_customerMenuButton = new GridBagConstraints();
		gbc_customerMenuButton.fill = GridBagConstraints.BOTH;
		gbc_customerMenuButton.insets = new Insets(0, 40, 0, 20);
		gbc_customerMenuButton.gridx = 0;
		gbc_customerMenuButton.gridy = 4;
		sideBar.add(customerMenuButton, gbc_customerMenuButton);
		
		
		
		mainFrame = new JPanel();
		mainFrame.setBackground(new Color(252, 252, 252));
		GridBagConstraints gbc_mainFrame = new GridBagConstraints();
		gbc_mainFrame.fill = GridBagConstraints.BOTH;
		gbc_mainFrame.gridx = 1;
		gbc_mainFrame.gridy = 0;
		frmVestbjergByggecenterManagement.getContentPane().add(mainFrame, gbc_mainFrame);
		mainFrame.setLayout(new CardLayout(0, 0));
	}
	
	private void init() {
		cards = (CardLayout)(mainFrame.getLayout());
		mainFrame.add(orderPanel, "Orders");
		mainFrame.add(createOrderPanel, "Create Order");
		mainFrame.add(productsPanel, "Products");
		mainFrame.add(customersPanel, "Customers");
		orderMenuButton.setBackground(babyBlue.darker());
		orderMenuButton.setFocusable(false);
		
		
		
	}
	
	/**
	 * The method is used to format the buttons inside
	 * the side panel, to ensure we do not have
	 * to rewrite the code numerous time.
	 * 
	 * @param button
	 */
	private void formatButton(RoundedButton button) {

		button.setPreferredSize(new Dimension(200, 50));
		button.setFont(new Font("Lato", Font.BOLD, 18));
		button.setForeground(Color.WHITE);
		button.setBackground(babyBlue);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (selected != button)
				{
					button.setBackground(Color.WHITE);
					button.setForeground(Color.BLACK);
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (selected != button)
				{
					button.setBackground(babyBlue);
					button.setForeground(Color.WHITE);
				}
			}
		});
		}
	
	
	/**
	 * Changed the current selected button to the other selected one
	 * 
	 * @param button
	 */
	private void changeSelectedButton(RoundedButton button)
	{
		formatButton(selected);
		selected = button;
		button.setBackground(babyBlue.darker());
		button.setForeground(Color.WHITE);
	}

}

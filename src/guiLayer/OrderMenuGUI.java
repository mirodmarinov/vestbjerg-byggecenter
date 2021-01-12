package guiLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class OrderMenuGUI {

	private JFrame frame;
	private CardLayout cards = new CardLayout();
	private JPanel mainFrame = new JPanel();
	private Color blueGreen = new Color(16, 188, 171);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMenuGUI window = new OrderMenuGUI();
					window.frame.setVisible(true);
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
		initialize(); //Autogenerated
		init(); //Made from scratch
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1680, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		/*
		 * Here we format the side bar, and prepare it
		 * for the buttons that will be put inside it.
		 * This is done by adding a grid bag layout.
		 */
		JPanel sideBar = new JPanel();
		sideBar.setBackground(blueGreen);
		GridBagConstraints gbc_sideBar = new GridBagConstraints();
		gbc_sideBar.fill = GridBagConstraints.BOTH;
		gbc_sideBar.insets = new Insets(0, 0, 0, 0);
		gbc_sideBar.gridx = 0;
		gbc_sideBar.gridy = 0;
		frame.getContentPane().add(sideBar, gbc_sideBar);
		GridBagLayout gbl_sideBar = new GridBagLayout();
		gbl_sideBar.columnWidths = new int[]{200};
		gbl_sideBar.rowHeights = new int[]{75, 50, 50, 50, 50, 50, 0};
		gbl_sideBar.columnWeights = new double[]{1.0};
		gbl_sideBar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		sideBar.setLayout(gbl_sideBar);
		
		/*
		 * Here we create the buttons for the side bar
		 */
		/********************************************** Order Button **********************************************/
		RoundedButton orderMenuButton = new RoundedButton("Orders", blueGreen);
		formatButton(orderMenuButton);
		orderMenuButton.addOffset(-64, 0);
		GridBagConstraints gbc_orderMenuButton = new GridBagConstraints();
		gbc_orderMenuButton.fill = GridBagConstraints.BOTH;
		gbc_orderMenuButton.insets = new Insets(30, 40, 0, 20);
		gbc_orderMenuButton.gridx = 0;
		gbc_orderMenuButton.gridy = 1;
		sideBar.add(orderMenuButton, gbc_orderMenuButton);
		
		
		/********************************************** Create Order/Offer Button **********************************************/                                 
		RoundedButton createOrderButton = new RoundedButton("Create Offer/Order", blueGreen);
		createOrderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		formatButton(createOrderButton);
		createOrderButton.addOffset(-4, 0);
		GridBagConstraints gbc_createOrderButton = new GridBagConstraints();
		gbc_createOrderButton.fill = GridBagConstraints.BOTH;
		gbc_createOrderButton.insets = new Insets(0, 60, 0, 20);
		gbc_createOrderButton.gridx = 0;
		gbc_createOrderButton.gridy = 2;
		sideBar.add(createOrderButton, gbc_createOrderButton);
		
		
		/********************************************** Products Button**********************************************/
		RoundedButton productMenuButton = new RoundedButton("Products", blueGreen);
		productMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		formatButton(productMenuButton);
		productMenuButton.addOffset(-53, 0);
		GridBagConstraints gbc_productMenuButton = new GridBagConstraints();
		gbc_productMenuButton.fill = GridBagConstraints.BOTH;
		gbc_productMenuButton.insets = new Insets(0, 40, 0, 20);
		gbc_productMenuButton.gridx = 0;
		gbc_productMenuButton.gridy = 3;
		sideBar.add(productMenuButton, gbc_productMenuButton);
		
		
		/********************************************** Customers Button **********************************************/
		RoundedButton customerMenuButton = new RoundedButton("Customers", blueGreen);
		customerMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		formatButton(customerMenuButton);
		customerMenuButton.addOffset(-45, 0);
		GridBagConstraints gbc_customerMenuButton = new GridBagConstraints();
		gbc_customerMenuButton.fill = GridBagConstraints.BOTH;
		gbc_customerMenuButton.insets = new Insets(0, 40, 0, 20);
		gbc_customerMenuButton.gridx = 0;
		gbc_customerMenuButton.gridy = 4;
		sideBar.add(customerMenuButton, gbc_customerMenuButton);
		
		
		/********************************************** Customers **********************************************/
		mainFrame = new JPanel();
		mainFrame.setBackground(new Color(252, 252, 252));
		GridBagConstraints gbc_mainFrame = new GridBagConstraints();
		gbc_mainFrame.fill = GridBagConstraints.BOTH;
		gbc_mainFrame.gridx = 1;
		gbc_mainFrame.gridy = 0;
		frame.getContentPane().add(mainFrame, gbc_mainFrame);
		mainFrame.setLayout(new CardLayout(0, 0));
	}
	
	private void init() {
		cards = (CardLayout)(mainFrame.getLayout());
		mainFrame.add(new OrderPanel(), "Orders");
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
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			button.setBackgroundColor(Color.WHITE);
			button.setForeground(Color.BLACK);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackgroundColor(blueGreen);
				button.setForeground(Color.WHITE);
			}
		});
	}

}

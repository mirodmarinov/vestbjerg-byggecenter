package guiLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class OrderMenuGUI {

	private JFrame frame;
	private CardLayout cards = new CardLayout();
	private JPanel mainFrame = new JPanel();

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
		
		JPanel sideBar = new JPanel();
		sideBar.setBackground(new Color(16, 188, 171));
		GridBagConstraints gbc_sideBar = new GridBagConstraints();
		gbc_sideBar.fill = GridBagConstraints.BOTH;
		gbc_sideBar.insets = new Insets(0, 0, 0, 0);
		gbc_sideBar.gridx = 0;
		gbc_sideBar.gridy = 0;
		frame.getContentPane().add(sideBar, gbc_sideBar);
		GridBagLayout gbl_sideBar = new GridBagLayout();
		gbl_sideBar.columnWidths = new int[]{47};
		gbl_sideBar.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_sideBar.columnWeights = new double[]{1.0};
		gbl_sideBar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		sideBar.setLayout(gbl_sideBar);
		
		JButton orderMenuButton = new JButton("Orders");
		orderMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderMenuButton.setFont(new Font("Lato", Font.PLAIN, 22));
		GridBagConstraints gbc_orderMenuButton = new GridBagConstraints();
		gbc_orderMenuButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_orderMenuButton.insets = new Insets(40, 20, 5, 22);
		gbc_orderMenuButton.gridx = 0;
		gbc_orderMenuButton.gridy = 0;
		sideBar.add(orderMenuButton, gbc_orderMenuButton);
		
		JButton newOfferOrderButton = new JButton("Create Offer/Order");
		newOfferOrderButton.setHorizontalAlignment(SwingConstants.LEFT);
		newOfferOrderButton.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_newOfferOrderButton = new GridBagConstraints();
		gbc_newOfferOrderButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_newOfferOrderButton.insets = new Insets(0, 60, 5, 22);
		gbc_newOfferOrderButton.gridx = 0;
		gbc_newOfferOrderButton.gridy = 1;
		sideBar.add(newOfferOrderButton, gbc_newOfferOrderButton);
		
		JButton confirmOfferButton = new JButton("Confirm Offer");
		confirmOfferButton.setHorizontalAlignment(SwingConstants.LEFT);
		confirmOfferButton.setFont(new Font("Lato", Font.PLAIN, 15));
		GridBagConstraints gbc_confirmOfferButton = new GridBagConstraints();
		gbc_confirmOfferButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmOfferButton.insets = new Insets(0, 60, 5, 22);
		gbc_confirmOfferButton.gridx = 0;
		gbc_confirmOfferButton.gridy = 2;
		sideBar.add(confirmOfferButton, gbc_confirmOfferButton);
		
		JButton customerMenuButton = new JButton("Customers");
		customerMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		customerMenuButton.setFont(new Font("Lato", Font.PLAIN, 22));
		GridBagConstraints gbc_customerMenuButton = new GridBagConstraints();
		gbc_customerMenuButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerMenuButton.insets = new Insets(0, 20, 5, 22);
		gbc_customerMenuButton.gridx = 0;
		gbc_customerMenuButton.gridy = 3;
		sideBar.add(customerMenuButton, gbc_customerMenuButton);
		
		JButton productsButton = new JButton("Products");
		productsButton.setHorizontalAlignment(SwingConstants.LEFT);
		productsButton.setFont(new Font("Lato", Font.PLAIN, 22));
		GridBagConstraints gbc_productsButton = new GridBagConstraints();
		gbc_productsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_productsButton.insets = new Insets(0, 20, 5, 22);
		gbc_productsButton.gridx = 0;
		gbc_productsButton.gridy = 4;
		sideBar.add(productsButton, gbc_productsButton);
		
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
	}

}

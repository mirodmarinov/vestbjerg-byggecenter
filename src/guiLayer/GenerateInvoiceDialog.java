package guiLayer;

/**
 * This class is a part of the warehouse management system
 * for Vestbjerg Byggecenter. It creates an Invoice dialog, containing
 * a preview of the invoice that should be sent to customers.
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import controlLayer.OrderCtr;

public class GenerateInvoiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public GenerateInvoiceDialog(int orderNumber) {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		//Invoice text area******************************************************************
		{
			JTextArea textArea = new JTextArea();
			OrderCtr orderCtr = new OrderCtr();
			String text = orderCtr.generateInvoice(orderNumber);
			textArea.setPreferredSize(new Dimension(300,300));
			textArea.setEditable(false);
			textArea.setText(text);
			contentPanel.add(textArea);
		}
		
		/********************************************** Button Panel **********************************************/
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY.brighter());
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			//Close Button******************************************************************
			{
				RoundedButton closeButton = new RoundedButton("Close", new Color(28, 150, 202), Color.WHITE, new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
				closeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) 
					{
						dispose();
					}
				});

				closeButton.setPreferredSize(new Dimension(100, 30));
				closeButton.addOffset(-5, 2);
				closeButton.setBackground(Color.WHITE);
				closeButton.setForeground(new Color(28, 150, 202));
				GridBagConstraints gbc_closeButton = new GridBagConstraints();
				
				//Hover functions are added to the button*****************
				closeButton.addMouseListener(new MouseAdapter()
				{	
					@Override
					public void mouseEntered(MouseEvent e) 
					{
						closeButton.setBackground(new Color(28, 150, 202));
						closeButton.setForeground(Color.WHITE);
					}
							
					@Override
					public void mouseExited(MouseEvent e) 
					{
						closeButton.setBackground(Color.WHITE);
						closeButton.setForeground(new Color(28, 150, 202));
					}
					
				});
				gbc_closeButton.anchor = GridBagConstraints.SOUTHEAST;
				gbc_closeButton.gridx = 4;
				gbc_closeButton.gridy = 3;
				buttonPane.add(closeButton, gbc_closeButton);
			}
		}
	}

}

package guiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlLayer.OrderCtr;

import javax.swing.JTextArea;

public class GenerateInvoiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public GenerateInvoiceDialog(int orderNumber) {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextArea textArea = new JTextArea();
			OrderCtr orderCtr = new OrderCtr();
			String text = orderCtr.generateInvoice(orderNumber);
			textArea.setPreferredSize(new Dimension(300,300));
			textArea.setEditable(false);
			textArea.setText(text);
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY.brighter());
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				RoundedButton cancelButton = new RoundedButton("OK", new Color(28, 150, 202), Color.WHITE, new Color(28, 150, 202), new Font("Lato", Font.BOLD, 15));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) 
					{
						dispose();
					}
				});

				cancelButton.setPreferredSize(new Dimension(100, 30));
				cancelButton.addOffset(-5, 2);
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setForeground(new Color(28, 150, 202));
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				cancelButton.addMouseListener(new MouseAdapter()
				{	
					@Override
					public void mouseEntered(MouseEvent e) 
					{
						cancelButton.setBackground(new Color(28, 150, 202));
						cancelButton.setForeground(Color.WHITE);
					}
							
					@Override
					public void mouseExited(MouseEvent e) 
					{
						cancelButton.setBackground(Color.WHITE);
						cancelButton.setForeground(new Color(28, 150, 202));
					}
					
				});
				gbc_cancelButton.anchor = GridBagConstraints.SOUTHEAST;
				gbc_cancelButton.gridx = 4;
				gbc_cancelButton.gridy = 3;
				buttonPane.add(cancelButton, gbc_cancelButton);
				
				/*JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);*/
			}
		}
	}

}

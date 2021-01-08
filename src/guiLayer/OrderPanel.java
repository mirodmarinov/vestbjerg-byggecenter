package guiLayer;

import guiLayer.Renderers.JTableButtonMouseListener;
import guiLayer.Renderers.JTableButtonRenderer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class OrderPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public OrderPanel() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 50};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel ordersLabel = new JLabel("Orders");
		ordersLabel.setFont(new Font("Lato", Font.PLAIN, 35));
		GridBagConstraints gbc_ordersLabel = new GridBagConstraints();
		gbc_ordersLabel.anchor = GridBagConstraints.WEST;
		gbc_ordersLabel.insets = new Insets(0, 0, 5, 0);
		gbc_ordersLabel.gridx = 1;
		gbc_ordersLabel.gridy = 1;
		add(ordersLabel, gbc_ordersLabel);

		table = new JTable();
		table.setFocusable(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Lato", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setRowHeight(50);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));
		
		table.addMouseListener(new JTableButtonMouseListener(table));
		TableCellRenderer tableRenderer = table.getDefaultRenderer(RoundedButton.class);
	    table.setDefaultRenderer(RoundedButton.class, new JTableButtonRenderer(tableRenderer));
	      
		// Could be moved to a custom header renderer
		DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
		table.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);

		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Order Number", "Customer", "Purchase Date", "Status", "Expiration Date", "Total", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, RoundedButton.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(0).setMinWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setMinWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setMinWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setMinWidth(70);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder());
		add(tablePane, gbc_table);
		fillTable();
	}

	private void fillTable() {
		table.setValueAt(1, 0, 0);
		table.setValueAt("Bob", 0, 1);
		table.setValueAt("1.1.2021", 0, 2);
		table.setValueAt("confirmed", 0, 3);
		table.setValueAt("2.1.2021", 0, 4);
		table.setValueAt(400, 0, 5);
		table.setValueAt(new RoundedButton("Confirm"), 0, 6);
		
		table.setValueAt(2, 1, 0);
		table.setValueAt("Not Bob", 1, 1);
		table.setValueAt("5.1.2021", 1, 2);
		table.setValueAt("pending", 1, 3);
		table.setValueAt("7.1.2021", 1, 4);
		table.setValueAt(1500, 1, 5);
		table.setValueAt(new RoundedButton("Confirm"), 1, 6);
		
	}
	
}

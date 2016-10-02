package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MealsPanel extends JPanel {

	private static final long serialVersionUID = -3015175045558720497L;
	private JTable table;

	public MealsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 430, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.gridwidth = 4;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);
		
		JButton btnAddNewMeal = new JButton("Add New Meal");
		btnAddNewMeal.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddNewMeal.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAddNewMeal.setIcon(new ImageIcon(MealsPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/JavaCup32.png")));
		btnAddNewMeal.setToolTipText("adds new meal to a public database");
		toolBar.add(btnAddNewMeal);
		
		JButton btnOpenSelectedDiet = new JButton("Open Selected");
		toolBar.add(btnOpenSelectedDiet);
		
		Component leftStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_leftStrut = new GridBagConstraints();
		gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftStrut.gridx = 0;
		gbc_leftStrut.gridy = 1;
		add(leftStrut, gbc_leftStrut);
		
		JLabel lblDescriptionYourDiets = new JLabel("Your Diets");
		GridBagConstraints gbc_lblDescriptionYourDiets = new GridBagConstraints();
		gbc_lblDescriptionYourDiets.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionYourDiets.anchor = GridBagConstraints.NORTH;
		gbc_lblDescriptionYourDiets.gridx = 1;
		gbc_lblDescriptionYourDiets.gridy = 1;
		add(lblDescriptionYourDiets, gbc_lblDescriptionYourDiets);
		
		Component rightStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_rightStrut = new GridBagConstraints();
		gbc_rightStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightStrut.gridx = 3;
		gbc_rightStrut.gridy = 1;
		add(rightStrut, gbc_rightStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Mass Gain", "02.10.2016", "09.10.2016"},
				{"Reduction", "10.10.2016", "17.10.2016"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Diet Type", "Start Date", "Valid To"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		Component bottomStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
		gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
		gbc_bottomStrut.gridx = 1;
		gbc_bottomStrut.gridy = 4;
		add(bottomStrut, gbc_bottomStrut);

	}

}

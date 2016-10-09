package gui.diets;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import database.controller.DatabaseController;
import database.entities.Diet;
import gui.meals.AllMealsDialog;

public class DietsPanel extends JPanel {

	private static final long serialVersionUID = -3015175045558720497L;
	private JTable table;
	private DietsTableModel tableModel;
	private DatabaseController db;

	public DietsPanel() {
		db = new DatabaseController();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 430, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.gridwidth = 3;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);
		
		JButton btnOpenSelectedDiet = new JButton("Open Selected Plan");
		btnOpenSelectedDiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openSelectedDietPlan();
			}
		});
		btnOpenSelectedDiet.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpenSelectedDiet.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOpenSelectedDiet.setIcon(new ImageIcon(DietsPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		toolBar.add(btnOpenSelectedDiet);
		
		JButton btnShowAllMeals = new JButton("Show All Meals");
		btnShowAllMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllMeals();
			}
		});
		btnShowAllMeals.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowAllMeals.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowAllMeals.setIcon(new ImageIcon(DietsPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/JavaCup32.png")));
		toolBar.add(btnShowAllMeals);
		
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
		gbc_rightStrut.gridx = 2;
		gbc_rightStrut.gridy = 1;
		add(rightStrut, gbc_rightStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		refreshTable();
		
		Component bottomStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
		gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
		gbc_bottomStrut.gridx = 1;
		gbc_bottomStrut.gridy = 3;
		add(bottomStrut, gbc_bottomStrut);

	}

	protected void openSelectedDietPlan() {
		Diet selectedDiet = tableModel.getDietAt(table.getSelectedRow());
		
	}

	protected void showAllMeals() {
		AllMealsDialog showAllMealDlg = new AllMealsDialog();
		showAllMealDlg.setLocationRelativeTo(this);
		showAllMealDlg.setVisible(true);
	}
	
	protected void refreshTable(){
		List<Diet> diets = db.getAll(Diet.class);
		tableModel = new DietsTableModel(diets);
		table.setModel(tableModel);
	}

}

package gui.diets;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import database.controller.DatabaseController;
import database.entities.Diet;
import database.entities.User;
import gui.meals.AllMealsDialog;

public class DietsPanel extends JPanel {

	private static final long serialVersionUID = -3015175045558720497L;
	private JTable table;
	private DietsTableModel tableModel;
	private User currentUser;
	private ImageIcon openButtonIcon;
	private ImageIcon showMealsButtonIcon;
	private ImageIcon refreshButtonIcon;

	public DietsPanel() {
		this(new User());
	}

	public DietsPanel(User userContainingDiets) {
		currentUser = userContainingDiets;
		loadIcons();
		initializeSwingComponents();
		refreshTable();
	}

	protected void openSelectedDietPlan() {
		if (table.getSelectedRow() != -1) {
			Diet selectedDiet = tableModel.getDietAt(table.getSelectedRow());
			DietOverviewDialog dietDetailsDlg = new DietOverviewDialog(selectedDiet);
			dietDetailsDlg.setLocationRelativeTo(this);
			dietDetailsDlg.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No row selected!", "Error", 0);
		}
	}

	protected void showAllMealsButtonPressed() {
		AllMealsDialog showAllMealDlg = new AllMealsDialog();
		showAllMealDlg.setLocationRelativeTo(this);
	}

	protected void refreshTable() {
		DatabaseController.refreshObject(currentUser);
		List<Diet> dietsList = new ArrayList<Diet>(currentUser.getDiets());
		tableModel = new DietsTableModel(dietsList);
		table.setModel(tableModel);
	}

	private void loadIcons() {
		openButtonIcon = new ImageIcon(getClass().getResource("/images/open_icon.png"));
		showMealsButtonIcon = new ImageIcon(getClass().getResource("/images/generate_diet_button.png"));
		refreshButtonIcon = new ImageIcon(getClass().getResource("/images/refresh_icon.png"));
	}

	private void initializeSwingComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 430, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
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
		btnOpenSelectedDiet.setIcon(openButtonIcon);
		toolBar.add(btnOpenSelectedDiet);

		JButton btnShowAllMeals = new JButton("Show All Meals");
		btnShowAllMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllMealsButtonPressed();
			}
		});
		btnShowAllMeals.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowAllMeals.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowAllMeals.setIcon(showMealsButtonIcon);
		toolBar.add(btnShowAllMeals);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRefresh.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRefresh.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRefresh.setIcon(refreshButtonIcon);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		toolBar.add(btnRefresh);

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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2)
					openSelectedDietPlan();
			}
		});
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		Component bottomStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
		gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
		gbc_bottomStrut.gridx = 1;
		gbc_bottomStrut.gridy = 3;
		add(bottomStrut, gbc_bottomStrut);
	}

}

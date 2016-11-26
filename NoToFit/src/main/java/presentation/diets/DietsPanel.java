package presentation.diets;

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
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import database.controller.DatabaseController;
import database.entities.Diet;
import database.entities.User;
import logic.diet.DietGenerationPreferences;
import logic.diet.DietPlanGenerator;
import presentation.MainFrame;
import presentation.common.WaitDialog;
import presentation.meals.AllMealsDialog;

public class DietsPanel extends JPanel {
	private static final long serialVersionUID = -3015175045558720497L;

	public static final String MSG_TOO_LESS_MEALS = "Diet could not have been generated. Meals library consists of too less meals that match your specific needs.";
	private static final String POPUP_HEADER_ERROR = "Error!";
	private static final String MSG_WAIT_FOR_DIET = "Please wait while your diet is being generated.";

	private JTable table;
	private DietsTableModel tableModel;
	private User currentUser;
	private ImageIcon openButtonIcon;
	private ImageIcon showMealsButtonIcon;
	private ImageIcon generateDietButtonIcon;
	private ImageIcon exitButtonIcon;

	public DietsPanel() {
		this(new User());
	}

	public DietsPanel(User userContainingDiets) {
		currentUser = userContainingDiets;
		loadIcons();
		initializeSwingComponents();
		refreshTable();
	}

	protected void generateDietPlanButtonPressed() {
		DietGenerationPreferences dietPreferences = extractDietPreferencesFromInputDialog();
		if (dietPreferences != null) {
			dietPreferences.setUser(currentUser);
			SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
				private WaitDialog waitDlg = new WaitDialog(MSG_WAIT_FOR_DIET);

				@Override
				protected Boolean doInBackground() throws Exception {
					waitDlg.setLocationRelativeTo(DietsPanel.this);
					waitDlg.setVisible(true);
					try {
						Diet generatedDiet = DietPlanGenerator.generateDiet(dietPreferences);
						currentUser.getDiets().add(generatedDiet);
						DatabaseController.saveEntityToDatabase(generatedDiet);
						refreshTable();
						return true;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					return false;
				}

				protected void done() {
					Boolean successfullyGenerated = false;
					try {
						successfullyGenerated = get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					waitDlg.setVisible(false);
					waitDlg.dispose();
					if(!successfullyGenerated){
						JOptionPane.showMessageDialog(DietsPanel.this, MSG_TOO_LESS_MEALS, POPUP_HEADER_ERROR, 0);						
					}
				};

			};
			worker.execute();
		}
	}

	private DietGenerationPreferences extractDietPreferencesFromInputDialog() {
		GenerateDietDialog dietPropertiesDialog = new GenerateDietDialog(currentUser);
		dietPropertiesDialog.setLocationRelativeTo(this);
		dietPropertiesDialog.setVisible(true);

		return dietPropertiesDialog.getNewDietPreferences();
	}

	protected void openSelectedDietPlan() {
		if (table.getSelectedRow() != -1) {
			Diet selectedDiet = tableModel.getDietAt(table.getSelectedRow());
			DietOverviewDialog dietDetailsDlg = new DietOverviewDialog(selectedDiet);
			dietDetailsDlg.setLocationRelativeTo(this);
			dietDetailsDlg.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No row selected!", POPUP_HEADER_ERROR, 0);
		}
	}

	protected void manageMealsButtonPressed() {
		AllMealsDialog mealsLibDialog = new AllMealsDialog();
		mealsLibDialog.setLocationRelativeTo(this);
		mealsLibDialog.setVisible(true);
	}

	protected void refreshTable() {
		List<Diet> dietsList = new ArrayList<Diet>(currentUser.getDiets());
		tableModel = new DietsTableModel(dietsList);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
	}

	private void loadIcons() {
		openButtonIcon = new ImageIcon(getClass().getResource("/images/open_icon.png"));
		showMealsButtonIcon = new ImageIcon(getClass().getResource("/images/generate_diet_button.png"));
		generateDietButtonIcon = new ImageIcon(getClass().getResource("/images/generate_diet_button.png"));
		exitButtonIcon = new ImageIcon(getClass().getResource("/images/exit_button.png"));
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

		JButton btnShowAllMeals = new JButton("Manage Meals");
		btnShowAllMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageMealsButtonPressed();
			}
		});
		btnShowAllMeals.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowAllMeals.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowAllMeals.setIcon(showMealsButtonIcon);
		toolBar.add(btnShowAllMeals);

		JButton btnGenerateDietPlan = new JButton("Generate Diet Plan");
		btnGenerateDietPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateDietPlanButtonPressed();
			}
		});
		btnGenerateDietPlan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGenerateDietPlan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGenerateDietPlan.setIcon(generateDietButtonIcon);
		toolBar.add(btnGenerateDietPlan);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitButtonPressed();
			}
		});
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setIcon(exitButtonIcon);
		toolBar.add(btnExit);

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

	protected void exitButtonPressed() {
		((MainFrame) SwingUtilities.getWindowAncestor(this)).tidyUp();
	}

}

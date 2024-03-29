package presentation.workouts;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
import database.entities.User;
import database.entities.Workout;
import logic.workout.WorkoutGenerationPreferences;
import logic.workout.WorkoutGenerator;
import presentation.MainFrame;
import presentation.common.WaitDialog;
import presentation.exercises.AllExercisesDialog;

public class WorkoutsPanel extends JPanel {
	private static final long serialVersionUID = -4659573390181954313L;

	private static final String POPUP_HEADER_ERROR = "Error!";
	private static final String MSG_TOO_LESS_EXERCISES = "Workout could not have been generated. Probably exercises library consists of too less entries.";
	private static final String MSG_WAIT_FOR_WORKOUT = "Please wait until your workout is generated.";

	private User currentUser;
	private JTable table;
	private WorkoutsTableModel tableModel;
	private ImageIcon openButtonIcon;
	private ImageIcon showExercisesButtonIcon;
	private ImageIcon generateWorkoutButtonIcon;

	private ImageIcon exitButtonIcon;

	public WorkoutsPanel() {
		this(new User());
	}

	public WorkoutsPanel(User currentUser) {
		this.currentUser = currentUser;
		initializeSwingComponents();
	}

	protected void generateWorkoutPlanButtonPressed() {
		WorkoutGenerationPreferences preferences = askForWorkoutGenerationPreferences();
		if (preferences != null) {
			preferences.setUser(currentUser);

			new SwingWorker<Boolean, Void>() {
				private WaitDialog waitDlg = new WaitDialog(MSG_WAIT_FOR_WORKOUT);

				@Override
				protected Boolean doInBackground() throws Exception {
					waitDlg.setLocationRelativeTo(WorkoutsPanel.this);
					waitDlg.setVisible(true);
					Workout generatedWorkout = WorkoutGenerator.generateWorkout(preferences);
					if (generatedWorkout != null) {
						currentUser.getWorkouts().add(generatedWorkout);
						try {
							DatabaseController.saveEntityToDatabase(generatedWorkout);
							refreshTable();
							return true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return false;
				}

				@Override
				protected void done() {
					Boolean successfullyGenerated = false;
					try {
						successfullyGenerated = get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
					waitDlg.setVisible(false);
					waitDlg.dispose();
					if (!successfullyGenerated) {
						JOptionPane.showMessageDialog(WorkoutsPanel.this, MSG_TOO_LESS_EXERCISES, POPUP_HEADER_ERROR,
								0);
					}

				}
			}.execute();
		}
	}

	private WorkoutGenerationPreferences askForWorkoutGenerationPreferences() {
		GenerateWorkoutDialog generateWorkoutDlg = new GenerateWorkoutDialog();
		generateWorkoutDlg.setLocationRelativeTo(this);
		generateWorkoutDlg.setVisible(true);
		return generateWorkoutDlg.getWorkoutGenerationPreferences();
	}

	protected void refreshTable() {
		List<Workout> workouts = new ArrayList<>(currentUser.getWorkouts());
		tableModel = new WorkoutsTableModel(workouts);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
	}

	protected void manageExercisesButtonPressed() {
		AllExercisesDialog exercisesLibDlg = new AllExercisesDialog();
		exercisesLibDlg.setLocationRelativeTo(this);
		exercisesLibDlg.setVisible(true);
	}

	protected void openSelectedWorkoutPlan() {
		int selectedRowNumber = table.getSelectedRow();
		if (selectedRowNumber != -1) {
			Workout selectedWorkout = tableModel.getWorkoutAt(selectedRowNumber);
			WorkoutOverviewDialog workoutView = new WorkoutOverviewDialog(selectedWorkout);
			workoutView.setLocationRelativeTo(this);
			workoutView.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No row selected.");
		}
	}

	private void initializeSwingComponents() {
		initializeLayout();
		loadIcons();
		initializeToolbar();
		refreshTable();
	}

	private void initializeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		Component leftStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_leftStrut = new GridBagConstraints();
		gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftStrut.gridx = 0;
		gbc_leftStrut.gridy = 1;
		add(leftStrut, gbc_leftStrut);

		Component rightStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_rightStrut = new GridBagConstraints();
		gbc_rightStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightStrut.gridx = 2;
		gbc_rightStrut.gridy = 2;
		add(rightStrut, gbc_rightStrut);

		Component bottomStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
		gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
		gbc_bottomStrut.gridx = 1;
		gbc_bottomStrut.gridy = 3;
		add(bottomStrut, gbc_bottomStrut);

		JLabel lblYourWorkoutPlans = new JLabel("Your Workout Plans");
		GridBagConstraints gbc_lblYourWorkoutPlans = new GridBagConstraints();
		gbc_lblYourWorkoutPlans.insets = new Insets(0, 0, 5, 5);
		gbc_lblYourWorkoutPlans.gridx = 1;
		gbc_lblYourWorkoutPlans.gridy = 1;
		add(lblYourWorkoutPlans, gbc_lblYourWorkoutPlans);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					openSelectedWorkoutPlan();
				}
			}
		});
		scrollPane.setViewportView(table);
	}

	private void loadIcons() {
		openButtonIcon = new ImageIcon(getClass().getResource("/images/open_icon.png"));
		showExercisesButtonIcon = new ImageIcon(getClass().getResource("/images/generate_workout_button.png"));
		generateWorkoutButtonIcon = new ImageIcon(getClass().getResource("/images/generate_workout_button.png"));
		exitButtonIcon = new ImageIcon(getClass().getResource("/images/exit_button.png"));
	}

	private void initializeToolbar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.gridwidth = 3;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);

		JButton btnOpenSelectedWorkout = new JButton("Open Selected Plan");
		btnOpenSelectedWorkout.addActionListener(e->openSelectedWorkoutPlan());
		btnOpenSelectedWorkout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpenSelectedWorkout.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOpenSelectedWorkout.setIcon(openButtonIcon);
		toolBar.add(btnOpenSelectedWorkout);

		JButton btnShowAllExercises = new JButton("Show All Exercises");
		btnShowAllExercises.addActionListener(e->manageExercisesButtonPressed());
		btnShowAllExercises.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowAllExercises.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShowAllExercises.setIcon(showExercisesButtonIcon);
		toolBar.add(btnShowAllExercises);

		JButton btnGenerateDietPlan = new JButton("Generate Workout Plan");
		btnGenerateDietPlan.addActionListener(e->generateWorkoutPlanButtonPressed());
		btnGenerateDietPlan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGenerateDietPlan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGenerateDietPlan.setIcon(generateWorkoutButtonIcon);
		toolBar.add(btnGenerateDietPlan);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e->exitButtonPressed());
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setIcon(exitButtonIcon);
		toolBar.add(btnExit);
	}

	protected void exitButtonPressed() {
		((MainFrame) SwingUtilities.getWindowAncestor(this)).tidyUp();
	}
}

package presentation.user;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import database.controller.DatabaseController;
import database.entities.User;
import database.entities.Weighthistory;
import database.tools.UserTools;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;
import logic.enums.Month;
import presentation.MainFrame;
import presentation.charts.WeightHistoryChart;
import presentation.common.Translator;

public class UserPanel extends JPanel {

	private static final long serialVersionUID = -8424451595957894544L;
	private User currentUser;
	private JLabel lblValueNameAndSurname;
	private JLabel lblValueStartWeight;
	private JLabel lblValueGoalWeight;
	private JLabel lblValueHeight;
	private JLabel lblValueAge;
	private JLabel lblValueUserObjective;
	private JLabel lblValueSex;
	private JLabel lblValueBmiState;
	private JLabel lblValueBmi;
	private JLabel lblValueActualWeight;
	private ImageIcon editUserButtonIcon;
	private ImageIcon updateWeightButtonIcon;
	private ImageIcon logoutButtonIcon;
	private ImageIcon exitButtonIcon;
	private float userActualWeight;
	private final static Color COLOR_GREEN = new Color(0, 120, 0);
	private final static String MSG_BMI_OK = "Good";
	private final static String MSG_BMI_TOO_LOW = "Underweight";
	private final static String MSG_BMI_TOO_HIGH = "Overweight";
	private WeightHistoryChart chart;
	private JComboBox<Month> comboBoxMonth;

	public UserPanel() {
		this(new User());
	}

	public UserPanel(User userToMaintain) {
		currentUser = userToMaintain;
		chart = new WeightHistoryChart();
		loadIcons();
		initializeSwingComponents();
		refreshUserDetails();
	}

	protected void editUserToolbarButtonPressed() {
		MaintainUserDialog editUserDialog = new MaintainUserDialog(currentUser);
		editUserDialog.setLocationRelativeTo(this);
		editUserDialog.setVisible(true);
		refreshUserDetails();
	}

	protected void updateWeight() {
		UpdateUserWeightDialog updateStatsDlg = new UpdateUserWeightDialog(currentUser, userActualWeight);
		updateStatsDlg.setLocationRelativeTo(this);
		updateStatsDlg.setVisible(true);
		Weighthistory newWeightHistoryEntry = updateStatsDlg.getNewSavedWeightEntry();
		if (newWeightHistoryEntry != null && matchesToCurrentlySelectedMonth(newWeightHistoryEntry)) {
			chart.addSinglePointToCurrentSeries(newWeightHistoryEntry);
			refreshUserDetails();
		}
	}

	private boolean matchesToCurrentlySelectedMonth(Weighthistory newWeightHistoryEntry) {
		return (newWeightHistoryEntry.getId().getDate().getMonth() + 1) == ((Month) comboBoxMonth.getSelectedItem())
				.getMonthNumber();
	}

	private void switchUser() {
		((MainFrame) SwingUtilities.getWindowAncestor(this)).switchUser();
	}

	protected void refreshUserDetails() {
		userActualWeight = UserTools.retrieveActualWeight(currentUser);

		lblValueNameAndSurname.setText(currentUser.getName() + " " + currentUser.getSurname());
		lblValueSex.setText(Translator.parseSexCharToString(currentUser.getSex()));
		lblValueAge.setText(String.format("%d years", UserTools.calculateAge(currentUser)));
		lblValueHeight.setText(String.format("%d cm", currentUser.getHeight()));
		lblValueStartWeight.setText(String.format("%.1f kg", UserTools.retrieveInitialWeight(currentUser)));
		lblValueActualWeight.setText(String.format("%.1f kg", userActualWeight));
		lblValueGoalWeight.setText(String.format("%.1f kg", currentUser.getGoalWeight()));
		lblValueUserObjective.setText(Translator.parseObjectiveCharToString(currentUser.getUserObjective()));
		setBmiLabelsFormatted(UserTools.calculateBMI(currentUser));
	}

	private void setBmiLabelsFormatted(float bmi) {
		Color bmiLabelColor = COLOR_GREEN;
		String bmiStateLabelMsg = MSG_BMI_OK;

		if (bmi >= 25.0f) {
			bmiLabelColor = Color.RED;
			bmiStateLabelMsg = MSG_BMI_TOO_HIGH;
		} else if (bmi < 18.5f) {
			bmiLabelColor = Color.RED;
			bmiStateLabelMsg = MSG_BMI_TOO_LOW;
		}

		lblValueBmi.setForeground(bmiLabelColor);
		lblValueBmi.setText(String.format("%.2f", bmi));
		lblValueBmiState.setText(bmiStateLabelMsg);
	}

	private void loadIcons() {
		editUserButtonIcon = new ImageIcon(getClass().getResource("/images/edit_user_button.png"));
		updateWeightButtonIcon = new ImageIcon(getClass().getResource("/images/update_weight_button.png"));
		logoutButtonIcon = new ImageIcon(getClass().getResource("/images/logout_button.png"));
		exitButtonIcon = new ImageIcon(getClass().getResource("/images/exit_button.png"));
	}

	private void initializeSwingComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 25, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 16, 0, 23, 0, 0, 0, 0, 0, 0, 0, 136, 1, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 2.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setBorder(UIManager.getBorder("ToolBar.border"));
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.gridwidth = 5;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);

		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEditUser.setHorizontalTextPosition(SwingConstants.CENTER);

		btnEditUser.setIcon(editUserButtonIcon);
		toolBar.add(btnEditUser);

		JButton btnUpdateWeight = new JButton("Update Weight");
		btnUpdateWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateWeight();
			}
		});
		btnUpdateWeight.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdateWeight.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdateWeight.setIcon(updateWeightButtonIcon);
		toolBar.add(btnUpdateWeight);

		JButton btnSwitchUser = new JButton("Switch User");
		btnSwitchUser.setPreferredSize(new Dimension(100, 23));
		btnSwitchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchUser();
			}
		});
		btnSwitchUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSwitchUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSwitchUser.setIcon(logoutButtonIcon);
		toolBar.add(btnSwitchUser);

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

		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editUserToolbarButtonPressed();

			}
		});

		Component topStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_topStrut = new GridBagConstraints();
		gbc_topStrut.insets = new Insets(0, 0, 5, 5);
		gbc_topStrut.gridx = 1;
		gbc_topStrut.gridy = 1;
		add(topStrut, gbc_topStrut);

		Component leftStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_leftStrut = new GridBagConstraints();
		gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftStrut.gridx = 0;
		gbc_leftStrut.gridy = 2;
		add(leftStrut, gbc_leftStrut);

		JLabel lblDescriptionName = new JLabel("Name:");
		lblDescriptionName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescriptionName.setForeground(Color.GRAY);
		GridBagConstraints gbc_lblDescriptionName = new GridBagConstraints();
		gbc_lblDescriptionName.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDescriptionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionName.gridx = 1;
		gbc_lblDescriptionName.gridy = 2;
		add(lblDescriptionName, gbc_lblDescriptionName);

		lblValueNameAndSurname = new JLabel();
		lblValueNameAndSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueNameAndSurname = new GridBagConstraints();
		gbc_lblValueNameAndSurname.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblValueNameAndSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueNameAndSurname.gridx = 2;
		gbc_lblValueNameAndSurname.gridy = 2;
		add(lblValueNameAndSurname, gbc_lblValueNameAndSurname);

		comboBoxMonth = new JComboBox<Month>();
		comboBoxMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Month selectedMonth = (Month) comboBoxMonth.getSelectedItem();
				changeSeriesToSelectedMonth(selectedMonth);
			}
		});

		JLabel lblChartTitle = new JLabel("Weight Progress Monitoring for:");
		lblChartTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblChartTitle = new GridBagConstraints();
		gbc_lblChartTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblChartTitle.anchor = GridBagConstraints.EAST;
		gbc_lblChartTitle.gridx = 3;
		gbc_lblChartTitle.gridy = 2;
		add(lblChartTitle, gbc_lblChartTitle);
		comboBoxMonth.setModel(new DefaultComboBoxModel<Month>(Month.values()));
		GridBagConstraints gbc_comboBoxMonth = new GridBagConstraints();
		gbc_comboBoxMonth.anchor = GridBagConstraints.WEST;
		gbc_comboBoxMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMonth.gridx = 4;
		gbc_comboBoxMonth.gridy = 2;
		add(comboBoxMonth, gbc_comboBoxMonth);

		JLabel lblDescriptionSex = new JLabel("Sex:");
		lblDescriptionSex.setForeground(Color.GRAY);
		lblDescriptionSex.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionSex = new GridBagConstraints();
		gbc_lblDescriptionSex.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionSex.gridx = 1;
		gbc_lblDescriptionSex.gridy = 3;
		add(lblDescriptionSex, gbc_lblDescriptionSex);

		lblValueSex = new JLabel();
		lblValueSex.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueSex = new GridBagConstraints();
		gbc_lblValueSex.anchor = GridBagConstraints.WEST;
		gbc_lblValueSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueSex.gridx = 2;
		gbc_lblValueSex.gridy = 3;
		add(lblValueSex, gbc_lblValueSex);

		JLabel lblDescriptionAge = new JLabel("Age:");
		lblDescriptionAge.setForeground(Color.GRAY);
		lblDescriptionAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionAge = new GridBagConstraints();
		gbc_lblDescriptionAge.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionAge.gridx = 1;
		gbc_lblDescriptionAge.gridy = 4;
		add(lblDescriptionAge, gbc_lblDescriptionAge);

		lblValueAge = new JLabel("TODO");
		lblValueAge.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueAge = new GridBagConstraints();
		gbc_lblValueAge.anchor = GridBagConstraints.WEST;
		gbc_lblValueAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueAge.gridx = 2;
		gbc_lblValueAge.gridy = 4;
		add(lblValueAge, gbc_lblValueAge);

		JLabel lblDescriptionHeight = new JLabel("Height:");
		lblDescriptionHeight.setForeground(Color.GRAY);
		lblDescriptionHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionHeight = new GridBagConstraints();
		gbc_lblDescriptionHeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionHeight.gridx = 1;
		gbc_lblDescriptionHeight.gridy = 5;
		add(lblDescriptionHeight, gbc_lblDescriptionHeight);

		lblValueHeight = new JLabel();
		lblValueHeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueHeight = new GridBagConstraints();
		gbc_lblValueHeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueHeight.gridx = 2;
		gbc_lblValueHeight.gridy = 5;
		add(lblValueHeight, gbc_lblValueHeight);

		JLabel lblDescriptionStartWeight = new JLabel("Start Weight:");
		lblDescriptionStartWeight.setForeground(Color.GRAY);
		lblDescriptionStartWeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionStartWeight = new GridBagConstraints();
		gbc_lblDescriptionStartWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionStartWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionStartWeight.gridx = 1;
		gbc_lblDescriptionStartWeight.gridy = 6;
		add(lblDescriptionStartWeight, gbc_lblDescriptionStartWeight);

		lblValueStartWeight = new JLabel();
		lblValueStartWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueStartWeight = new GridBagConstraints();
		gbc_lblValueStartWeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueStartWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueStartWeight.gridx = 2;
		gbc_lblValueStartWeight.gridy = 6;
		add(lblValueStartWeight, gbc_lblValueStartWeight);

		JLabel lblDescriptionActualWeight = new JLabel("Actual Weight:");
		lblDescriptionActualWeight.setForeground(Color.GRAY);
		lblDescriptionActualWeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionActualWeight = new GridBagConstraints();
		gbc_lblDescriptionActualWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionActualWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionActualWeight.gridx = 1;
		gbc_lblDescriptionActualWeight.gridy = 7;
		add(lblDescriptionActualWeight, gbc_lblDescriptionActualWeight);

		lblValueActualWeight = new JLabel("");
		lblValueActualWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueActualWeight = new GridBagConstraints();
		gbc_lblValueActualWeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueActualWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueActualWeight.gridx = 2;
		gbc_lblValueActualWeight.gridy = 7;
		add(lblValueActualWeight, gbc_lblValueActualWeight);

		JLabel lblDescriptionGoalWeight = new JLabel("Goal Weight:");
		lblDescriptionGoalWeight.setForeground(Color.GRAY);
		lblDescriptionGoalWeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionGoalWeight = new GridBagConstraints();
		gbc_lblDescriptionGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionGoalWeight.gridx = 1;
		gbc_lblDescriptionGoalWeight.gridy = 8;
		add(lblDescriptionGoalWeight, gbc_lblDescriptionGoalWeight);

		lblValueGoalWeight = new JLabel();
		lblValueGoalWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueGoalWeight = new GridBagConstraints();
		gbc_lblValueGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueGoalWeight.gridx = 2;
		gbc_lblValueGoalWeight.gridy = 8;
		add(lblValueGoalWeight, gbc_lblValueGoalWeight);

		JLabel lblDescriptionMainObjective = new JLabel("Main Objective:");
		lblDescriptionMainObjective.setForeground(Color.GRAY);
		lblDescriptionMainObjective.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionMainObjective = new GridBagConstraints();
		gbc_lblDescriptionMainObjective.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionMainObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionMainObjective.gridx = 1;
		gbc_lblDescriptionMainObjective.gridy = 9;
		add(lblDescriptionMainObjective, gbc_lblDescriptionMainObjective);

		lblValueUserObjective = new JLabel();
		lblValueUserObjective.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueUserObjective = new GridBagConstraints();
		gbc_lblValueUserObjective.anchor = GridBagConstraints.WEST;
		gbc_lblValueUserObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueUserObjective.gridx = 2;
		gbc_lblValueUserObjective.gridy = 9;
		add(lblValueUserObjective, gbc_lblValueUserObjective);

		JLabel lblDescriptionBmi = new JLabel("BMI:");
		lblDescriptionBmi.setForeground(Color.GRAY);
		lblDescriptionBmi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionBmi = new GridBagConstraints();
		gbc_lblDescriptionBmi.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionBmi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionBmi.gridx = 1;
		gbc_lblDescriptionBmi.gridy = 10;
		add(lblDescriptionBmi, gbc_lblDescriptionBmi);

		lblValueBmi = new JLabel("");
		lblValueBmi.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueBmi = new GridBagConstraints();
		gbc_lblValueBmi.anchor = GridBagConstraints.WEST;
		gbc_lblValueBmi.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueBmi.gridx = 2;
		gbc_lblValueBmi.gridy = 10;
		add(lblValueBmi, gbc_lblValueBmi);

		JLabel lblBmiState = new JLabel("Overall state:");
		lblBmiState.setForeground(Color.GRAY);
		lblBmiState.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblBmiState = new GridBagConstraints();
		gbc_lblBmiState.anchor = GridBagConstraints.WEST;
		gbc_lblBmiState.insets = new Insets(0, 0, 5, 5);
		gbc_lblBmiState.gridx = 1;
		gbc_lblBmiState.gridy = 11;
		add(lblBmiState, gbc_lblBmiState);

		lblValueBmiState = new JLabel("state");
		lblValueBmiState.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueBmiState = new GridBagConstraints();
		gbc_lblValueBmiState.anchor = GridBagConstraints.WEST;
		gbc_lblValueBmiState.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueBmiState.gridx = 2;
		gbc_lblValueBmiState.gridy = 11;
		add(lblValueBmiState, gbc_lblValueBmiState);

		final JFXPanel fxPanel = embedWeightHistoryChart();
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 3;
		gbc_lblNewLabel.gridheight = 10;
		add(fxPanel, gbc_lblNewLabel);

		comboBoxMonth.setSelectedIndex(new Date().getMonth());
	}

	private JFXPanel embedWeightHistoryChart() {
		JFXPanel embeddedPanel = new JFXPanel();
		embeddedPanel.setScene(chart.createScene());
		return embeddedPanel;
	}

	private void changeSeriesToSelectedMonth(Month month) {
		List<Weighthistory> weightHistory = DatabaseController.getWeightHistoryOfMonth(currentUser.getId(),
				month.getMonthNumber());

		new Task<Void>() {
			protected Void call() throws Exception {
				Platform.runLater(() -> chart.setWeightHistorySeries("Weight Changes History", weightHistory));
				return null;
			}
		}.run();
	}

	protected void exitButtonPressed() {
		((MainFrame) SwingUtilities.getWindowAncestor(this)).tidyUp();
	}
}

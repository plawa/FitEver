package gui.user;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;

import java.awt.Font;

public class UserPanel extends JPanel {

	private static final long serialVersionUID = -8424451595957894544L;
	private JLabel lblValueNameAndSurname;
	private JLabel lblValueStartWeight;
	private JLabel lblValueGoalWeight;
	private JLabel lblValueHeight;
	private JLabel lblValueAge;
	private JLabel lblValueUserObjective;
	private JLabel lblValueFatPercentage;
	private JLabel lblValueSex;
	private User userDisplaying;
	private JLabel lblValueBmi;
	private JLabel lblValueActualWeight;
	
	public UserPanel(){
		this(new User("name", "surname", new Date(3214122), 'f', 200, 66.9f,
				80.1f, new Integer(10), new Character('s'), (Set) null, new Shadow(), (Set) null));
	}
	
	public UserPanel(User userToMaintain) {
		userDisplaying = userToMaintain;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 25, 0, 20, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 16, 0, 23, 0, 0, 0, 0, 0, 20, 0, 0, 136, 1, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 7.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.gridwidth = 9;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);
		
		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEditUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditUser.setIcon(new ImageIcon(UserPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		toolBar.add(btnEditUser);
		
		JButton btnUpdateStats = new JButton("Update Stats");
		btnUpdateStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStats();
			}
		});
		btnUpdateStats.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdateStats.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdateStats.setIcon(new ImageIcon(UserPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		toolBar.add(btnUpdateStats);
		
		JButton btnGenerateDietPlan = new JButton("Generate Diet Plan");
		btnGenerateDietPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateDietPlan();
			}
		});
		btnGenerateDietPlan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGenerateDietPlan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGenerateDietPlan.setIcon(new ImageIcon(UserPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		toolBar.add(btnGenerateDietPlan);
		
		JButton btnGenerateWorkoutPlan = new JButton("Generate Workout Plan");
		btnGenerateWorkoutPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateWorkoutPlan();
			}
		});
		btnGenerateWorkoutPlan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGenerateWorkoutPlan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGenerateWorkoutPlan.setIcon(new ImageIcon(UserPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
		toolBar.add(btnGenerateWorkoutPlan);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnLogout.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setIcon(new ImageIcon(UserPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/JavaCup32.png")));
		toolBar.add(btnLogout);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setIcon(new ImageIcon(UserPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		toolBar.add(btnExit);
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editUser();

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
		gbc_lblDescriptionName.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionName.gridx = 1;
		gbc_lblDescriptionName.gridy = 2;
		add(lblDescriptionName, gbc_lblDescriptionName);
		
		lblValueNameAndSurname = new JLabel();
		lblValueNameAndSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueNameAndSurname = new GridBagConstraints();
		gbc_lblValueNameAndSurname.anchor = GridBagConstraints.WEST;
		gbc_lblValueNameAndSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueNameAndSurname.gridx = 2;
		gbc_lblValueNameAndSurname.gridy = 2;
		add(lblValueNameAndSurname, gbc_lblValueNameAndSurname);
		
		Component dataColumnStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_dataColumnStrut = new GridBagConstraints();
		gbc_dataColumnStrut.insets = new Insets(0, 5, 5, 5);
		gbc_dataColumnStrut.gridx = 3;
		gbc_dataColumnStrut.gridy = 2;
		add(dataColumnStrut, gbc_dataColumnStrut);
		
		JLabel lblDescriptionBmi = new JLabel("BMI:");
		lblDescriptionBmi.setForeground(Color.GRAY);
		lblDescriptionBmi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionBmi = new GridBagConstraints();
		gbc_lblDescriptionBmi.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionBmi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionBmi.gridx = 4;
		gbc_lblDescriptionBmi.gridy = 2;
		add(lblDescriptionBmi, gbc_lblDescriptionBmi);
		
		lblValueBmi = new JLabel("");
		lblValueBmi.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueBmi = new GridBagConstraints();
		gbc_lblValueBmi.anchor = GridBagConstraints.WEST;
		gbc_lblValueBmi.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueBmi.gridx = 5;
		gbc_lblValueBmi.gridy = 2;
		add(lblValueBmi, gbc_lblValueBmi);
		
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
		
		JLabel lblBmiState = new JLabel("Overall state:");
		lblBmiState.setForeground(Color.GRAY);
		lblBmiState.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblBmiState = new GridBagConstraints();
		gbc_lblBmiState.anchor = GridBagConstraints.WEST;
		gbc_lblBmiState.insets = new Insets(0, 0, 5, 5);
		gbc_lblBmiState.gridx = 4;
		gbc_lblBmiState.gridy = 3;
		add(lblBmiState, gbc_lblBmiState);
		
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
		
		JLabel lblDescriptionFatPercentage = new JLabel("Fat Percentage:");
		lblDescriptionFatPercentage.setForeground(Color.GRAY);
		lblDescriptionFatPercentage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionFatPercentage = new GridBagConstraints();
		gbc_lblDescriptionFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionFatPercentage.gridx = 1;
		gbc_lblDescriptionFatPercentage.gridy = 6;
		add(lblDescriptionFatPercentage, gbc_lblDescriptionFatPercentage);
		
		lblValueFatPercentage = new JLabel();
		lblValueFatPercentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueFatPercentage = new GridBagConstraints();
		gbc_lblValueFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblValueFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueFatPercentage.gridx = 2;
		gbc_lblValueFatPercentage.gridy = 6;
		add(lblValueFatPercentage, gbc_lblValueFatPercentage);
		
		JLabel lblDescriptionStartWeight = new JLabel("Start Weight:");
		lblDescriptionStartWeight.setForeground(Color.GRAY);
		lblDescriptionStartWeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionStartWeight = new GridBagConstraints();
		gbc_lblDescriptionStartWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionStartWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionStartWeight.gridx = 1;
		gbc_lblDescriptionStartWeight.gridy = 7;
		add(lblDescriptionStartWeight, gbc_lblDescriptionStartWeight);
		
		lblValueStartWeight = new JLabel();
		lblValueStartWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueStartWeight = new GridBagConstraints();
		gbc_lblValueStartWeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueStartWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueStartWeight.gridx = 2;
		gbc_lblValueStartWeight.gridy = 7;
		add(lblValueStartWeight, gbc_lblValueStartWeight);
		
		JLabel lblDescriptionActualWeight = new JLabel("Actual Weight:");
		lblDescriptionActualWeight.setForeground(Color.GRAY);
		lblDescriptionActualWeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDescriptionActualWeight = new GridBagConstraints();
		gbc_lblDescriptionActualWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionActualWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionActualWeight.gridx = 4;
		gbc_lblDescriptionActualWeight.gridy = 7;
		add(lblDescriptionActualWeight, gbc_lblDescriptionActualWeight);
		
		lblValueActualWeight = new JLabel("");
		lblValueActualWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValueActualWeight = new GridBagConstraints();
		gbc_lblValueActualWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueActualWeight.gridx = 5;
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
		refreshContent();

	}
	
	protected void exit() {
		new DatabaseController().tidyUp();
		System.exit(0);
	}

	protected void generateWorkoutPlan() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
	}

	protected void generateDietPlan() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
	}

	protected void editUser() {
		EditUserDialog editDialog = new EditUserDialog(userDisplaying);
		editDialog.setVisible(true);
		refreshContent();
	}

	protected void updateStats() {
		UpdateUserStatsDialog updateStatsDlg = new UpdateUserStatsDialog(userDisplaying);
		updateStatsDlg.setLocationRelativeTo(this);
		updateStatsDlg.setVisible(true);
		refreshContent();
	}

	private void logout(){
		//this.
	}
	
	protected void refreshContent() {
		lblValueNameAndSurname.setText(userDisplaying.getName() + " " + userDisplaying.getSurname());
		lblValueSex.setText(userDisplaying.getSexString());
		lblValueAge.setText(String.format("%d years", userDisplaying.calculateAge()));
		lblValueHeight.setText(String.format("%d cm", userDisplaying.getHeight()));
		lblValueStartWeight.setText(String.format("%.1f kg", userDisplaying.getStartWeight()));
		lblValueGoalWeight.setText(String.format("%.1f kg", userDisplaying.getGoalWeight()));
		lblValueFatPercentage.setText(String.format("%d %%", userDisplaying.getFatPercentage()));
		lblValueUserObjective.setText(userDisplaying.getUserObjectiveString());
		setBmiLabelFormatted(userDisplaying.calculateBMI());
		lblValueActualWeight.setText(String.format("%.1f kg", userDisplaying.getActualWeight()));
	}
	
	private void setBmiLabelFormatted(float bmi){
		lblValueBmi.setText(String.format("%.2f", bmi));
		if(bmi >= 25.0f || bmi < 18.5f)
			lblValueBmi.setForeground(Color.RED);
		else
			lblValueBmi.setForeground(new Color(0, 120, 0));
	}
	

}

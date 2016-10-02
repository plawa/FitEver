package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.entities.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	
	
	public UserPanel(User userToMaintain) {
		userDisplaying = userToMaintain;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 25, 0, 0, 0, 10, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 16, 0, 23, 0, 0, 0, 0, 0, 20, 0, 0, 136, 1, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Component topStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_topStrut = new GridBagConstraints();
		gbc_topStrut.insets = new Insets(0, 0, 5, 5);
		gbc_topStrut.gridx = 1;
		gbc_topStrut.gridy = 0;
		add(topStrut, gbc_topStrut);
		
		Component leftStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_leftStrut = new GridBagConstraints();
		gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftStrut.gridx = 0;
		gbc_leftStrut.gridy = 1;
		add(leftStrut, gbc_leftStrut);
		
		JLabel lblDescriptionName = new JLabel("Name:");
		GridBagConstraints gbc_lblDescriptionName = new GridBagConstraints();
		gbc_lblDescriptionName.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionName.gridx = 1;
		gbc_lblDescriptionName.gridy = 1;
		add(lblDescriptionName, gbc_lblDescriptionName);
		
		lblValueNameAndSurname = new JLabel();
		GridBagConstraints gbc_lblValueNameAndSurname = new GridBagConstraints();
		gbc_lblValueNameAndSurname.anchor = GridBagConstraints.WEST;
		gbc_lblValueNameAndSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueNameAndSurname.gridx = 2;
		gbc_lblValueNameAndSurname.gridy = 1;
		add(lblValueNameAndSurname, gbc_lblValueNameAndSurname);
		
		JLabel lblDescriptionSex = new JLabel("Sex:");
		GridBagConstraints gbc_lblDescriptionSex = new GridBagConstraints();
		gbc_lblDescriptionSex.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionSex.gridx = 1;
		gbc_lblDescriptionSex.gridy = 2;
		add(lblDescriptionSex, gbc_lblDescriptionSex);
		
		lblValueSex = new JLabel();
		GridBagConstraints gbc_lblValueSex = new GridBagConstraints();
		gbc_lblValueSex.anchor = GridBagConstraints.WEST;
		gbc_lblValueSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueSex.gridx = 2;
		gbc_lblValueSex.gridy = 2;
		add(lblValueSex, gbc_lblValueSex);
		
		JLabel lblDescriptionAge = new JLabel("Age:");
		GridBagConstraints gbc_lblDescriptionAge = new GridBagConstraints();
		gbc_lblDescriptionAge.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionAge.gridx = 1;
		gbc_lblDescriptionAge.gridy = 3;
		add(lblDescriptionAge, gbc_lblDescriptionAge);
		
		lblValueAge = new JLabel("TODO");
		GridBagConstraints gbc_lblValueAge = new GridBagConstraints();
		gbc_lblValueAge.anchor = GridBagConstraints.WEST;
		gbc_lblValueAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueAge.gridx = 2;
		gbc_lblValueAge.gridy = 3;
		add(lblValueAge, gbc_lblValueAge);
		
		JLabel lblDescriptionHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblDescriptionHeight = new GridBagConstraints();
		gbc_lblDescriptionHeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionHeight.gridx = 1;
		gbc_lblDescriptionHeight.gridy = 4;
		add(lblDescriptionHeight, gbc_lblDescriptionHeight);
		
		lblValueHeight = new JLabel();
		GridBagConstraints gbc_lblValueHeight = new GridBagConstraints();
		gbc_lblValueHeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueHeight.gridx = 2;
		gbc_lblValueHeight.gridy = 4;
		add(lblValueHeight, gbc_lblValueHeight);
		
		JLabel lblDescriptionFatPercentage = new JLabel("Fat Percentage:");
		GridBagConstraints gbc_lblDescriptionFatPercentage = new GridBagConstraints();
		gbc_lblDescriptionFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionFatPercentage.gridx = 1;
		gbc_lblDescriptionFatPercentage.gridy = 5;
		add(lblDescriptionFatPercentage, gbc_lblDescriptionFatPercentage);
		
		lblValueFatPercentage = new JLabel();
		GridBagConstraints gbc_lblValueFatPercentage = new GridBagConstraints();
		gbc_lblValueFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblValueFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueFatPercentage.gridx = 2;
		gbc_lblValueFatPercentage.gridy = 5;
		add(lblValueFatPercentage, gbc_lblValueFatPercentage);
		
		JLabel lblDescriptionStartWeight = new JLabel("Start Weight:");
		GridBagConstraints gbc_lblDescriptionStartWeight = new GridBagConstraints();
		gbc_lblDescriptionStartWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionStartWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionStartWeight.gridx = 1;
		gbc_lblDescriptionStartWeight.gridy = 6;
		add(lblDescriptionStartWeight, gbc_lblDescriptionStartWeight);
		
		lblValueStartWeight = new JLabel();
		GridBagConstraints gbc_lblValueStartWeight = new GridBagConstraints();
		gbc_lblValueStartWeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueStartWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueStartWeight.gridx = 2;
		gbc_lblValueStartWeight.gridy = 6;
		add(lblValueStartWeight, gbc_lblValueStartWeight);
		
		JLabel lblDescriptionGoalWeight = new JLabel("Goal Weight:");
		GridBagConstraints gbc_lblDescriptionGoalWeight = new GridBagConstraints();
		gbc_lblDescriptionGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionGoalWeight.gridx = 1;
		gbc_lblDescriptionGoalWeight.gridy = 7;
		add(lblDescriptionGoalWeight, gbc_lblDescriptionGoalWeight);
		
		lblValueGoalWeight = new JLabel();
		GridBagConstraints gbc_lblValueGoalWeight = new GridBagConstraints();
		gbc_lblValueGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblValueGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueGoalWeight.gridx = 2;
		gbc_lblValueGoalWeight.gridy = 7;
		add(lblValueGoalWeight, gbc_lblValueGoalWeight);
		
		JLabel lblDescriptionMainObjective = new JLabel("Main Objective:");
		GridBagConstraints gbc_lblDescriptionMainObjective = new GridBagConstraints();
		gbc_lblDescriptionMainObjective.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionMainObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionMainObjective.gridx = 1;
		gbc_lblDescriptionMainObjective.gridy = 8;
		add(lblDescriptionMainObjective, gbc_lblDescriptionMainObjective);
		
		lblValueUserObjective = new JLabel();
		GridBagConstraints gbc_lblValueUserObjective = new GridBagConstraints();
		gbc_lblValueUserObjective.anchor = GridBagConstraints.WEST;
		gbc_lblValueUserObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblValueUserObjective.gridx = 2;
		gbc_lblValueUserObjective.gridy = 8;
		add(lblValueUserObjective, gbc_lblValueUserObjective);
		
		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditUserDialog editDialog = new EditUserDialog(userDisplaying);
				editDialog.setVisible(true);
				refreshContent();
			}
		});
		GridBagConstraints gbc_btnEditUser = new GridBagConstraints();
		gbc_btnEditUser.anchor = GridBagConstraints.WEST;
		gbc_btnEditUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditUser.gridx = 2;
		gbc_btnEditUser.gridy = 10;
		add(btnEditUser, gbc_btnEditUser);
		refreshContent();

	}
	
	protected void refreshContent() {
		lblValueNameAndSurname.setText(userDisplaying.getName() + " " + userDisplaying.getSurname());
		lblValueSex.setText(userDisplaying.getSexString());
		//TODO lblValueAge.setText("");
		lblValueHeight.setText(Integer.toString(userDisplaying.getHeight()));
		lblValueStartWeight.setText(Float.toString(userDisplaying.getStartWeight()));
		lblValueGoalWeight.setText(Float.toString(userDisplaying.getGoalWeight()));
		lblValueFatPercentage.setText(Integer.toString(userDisplaying.getFatPercentage()));
		lblValueUserObjective.setText(userDisplaying.getUserObjectiveString());
	}


	
}

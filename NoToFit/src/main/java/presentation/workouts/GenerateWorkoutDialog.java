package presentation.workouts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.enums.DifficultyLevel;
import logic.workout.WorkoutGenerationPreferences;

public class GenerateWorkoutDialog extends JDialog {

	private static final long serialVersionUID = 2372123556581471698L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private transient WorkoutGenerationPreferences preferences;
	private JSlider sliderDaysPerWeek;
	private JSlider sliderWorkoutPeriodInDays;
	private JCheckBox chckbxIHaveEquipment;
	private JComboBox<DifficultyLevel> comboBox;

	public GenerateWorkoutDialog() {
		initializeSwingComponents();
	}

	public WorkoutGenerationPreferences getWorkoutGenerationPreferences() {
		return preferences;
	}

	protected void okButtonPressed() {
		retrieveWorkoutGenerationConfigurationFromFields();
		tearDown();
	}

	private void retrieveWorkoutGenerationConfigurationFromFields() {
		preferences = new WorkoutGenerationPreferences();
		preferences.setName(textFieldName.getText());
		preferences.setTrainingDaysPerWeek(sliderDaysPerWeek.getValue());
		preferences.setWorkoutPeriodInWeeks(sliderWorkoutPeriodInDays.getValue());
		preferences.setHasEquipment(chckbxIHaveEquipment.isSelected());
		preferences.setPrefferedDifficulty((DifficultyLevel) comboBox.getSelectedItem());
	}

	private void initializeSwingComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Generate Workout - Workout Properties");
		setBounds(100, 100, 548, 309);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			Component topStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_topStrut = new GridBagConstraints();
			gbc_topStrut.insets = new Insets(0, 0, 5, 5);
			gbc_topStrut.gridx = 3;
			gbc_topStrut.gridy = 0;
			contentPanel.add(topStrut, gbc_topStrut);
		}
		{
			JLabel lblName = new JLabel("New Workout Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.WEST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 1;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setText("My new example workout");
			GridBagConstraints gbc_textFieldName = new GridBagConstraints();
			gbc_textFieldName.gridwidth = 2;
			gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldName.gridx = 2;
			gbc_textFieldName.gridy = 1;
			contentPanel.add(textFieldName, gbc_textFieldName);
			textFieldName.setColumns(10);
		}
		{
			Component leftStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_leftStrut = new GridBagConstraints();
			gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
			gbc_leftStrut.gridx = 0;
			gbc_leftStrut.gridy = 2;
			contentPanel.add(leftStrut, gbc_leftStrut);
		}
		{
			JLabel lblTrainingDaysPer = new JLabel("Training Days per Week:");
			GridBagConstraints gbc_lblTrainingDaysPer = new GridBagConstraints();
			gbc_lblTrainingDaysPer.anchor = GridBagConstraints.WEST;
			gbc_lblTrainingDaysPer.insets = new Insets(0, 0, 5, 5);
			gbc_lblTrainingDaysPer.gridx = 1;
			gbc_lblTrainingDaysPer.gridy = 2;
			contentPanel.add(lblTrainingDaysPer, gbc_lblTrainingDaysPer);
		}
		{
			sliderDaysPerWeek = new JSlider();
			sliderDaysPerWeek.setBorder(null);
			sliderDaysPerWeek.setValue(3);
			sliderDaysPerWeek.setSnapToTicks(true);
			sliderDaysPerWeek.setPaintTicks(true);
			sliderDaysPerWeek.setPaintLabels(true);
			sliderDaysPerWeek.setMajorTickSpacing(1);
			sliderDaysPerWeek.setMinimum(1);
			sliderDaysPerWeek.setMaximum(5);
			GridBagConstraints gbc_sliderDaysPerWeek = new GridBagConstraints();
			gbc_sliderDaysPerWeek.gridwidth = 2;
			gbc_sliderDaysPerWeek.fill = GridBagConstraints.HORIZONTAL;
			gbc_sliderDaysPerWeek.insets = new Insets(0, 0, 5, 5);
			gbc_sliderDaysPerWeek.gridx = 2;
			gbc_sliderDaysPerWeek.gridy = 2;
			contentPanel.add(sliderDaysPerWeek, gbc_sliderDaysPerWeek);
		}
		{
			Component rightStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_rightStrut = new GridBagConstraints();
			gbc_rightStrut.insets = new Insets(0, 0, 5, 0);
			gbc_rightStrut.gridx = 4;
			gbc_rightStrut.gridy = 2;
			contentPanel.add(rightStrut, gbc_rightStrut);
		}
		{
			JLabel lblWorkoutPeriod = new JLabel("Workout Period in Weeks:");
			GridBagConstraints gbc_lblWorkoutPeriod = new GridBagConstraints();
			gbc_lblWorkoutPeriod.anchor = GridBagConstraints.WEST;
			gbc_lblWorkoutPeriod.insets = new Insets(0, 0, 5, 5);
			gbc_lblWorkoutPeriod.gridx = 1;
			gbc_lblWorkoutPeriod.gridy = 3;
			contentPanel.add(lblWorkoutPeriod, gbc_lblWorkoutPeriod);
		}
		{
			sliderWorkoutPeriodInDays = new JSlider();
			sliderWorkoutPeriodInDays.setPaintLabels(true);
			sliderWorkoutPeriodInDays.setMajorTickSpacing(1);
			sliderWorkoutPeriodInDays.setPaintTicks(true);
			sliderWorkoutPeriodInDays.setSnapToTicks(true);
			sliderWorkoutPeriodInDays.setValue(2);
			sliderWorkoutPeriodInDays.setMinimum(1);
			sliderWorkoutPeriodInDays.setMaximum(4);
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.gridwidth = 2;
			gbc_slider.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 2;
			gbc_slider.gridy = 3;
			contentPanel.add(sliderWorkoutPeriodInDays, gbc_slider);
		}
		{
			JLabel lblPreferredDifficulty = new JLabel("Preferred Difficulty:");
			GridBagConstraints gbc_lblPreferredDifficulty = new GridBagConstraints();
			gbc_lblPreferredDifficulty.insets = new Insets(0, 0, 5, 5);
			gbc_lblPreferredDifficulty.anchor = GridBagConstraints.WEST;
			gbc_lblPreferredDifficulty.gridx = 1;
			gbc_lblPreferredDifficulty.gridy = 4;
			contentPanel.add(lblPreferredDifficulty, gbc_lblPreferredDifficulty);
		}
		{
			comboBox = new JComboBox<>();
			comboBox.setModel(new DefaultComboBoxModel<>(DifficultyLevel.values()));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 4;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			chckbxIHaveEquipment = new JCheckBox("I have exercise equipment");
			GridBagConstraints gbc_chckbxIHaveEquipment = new GridBagConstraints();
			gbc_chckbxIHaveEquipment.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxIHaveEquipment.gridx = 3;
			gbc_chckbxIHaveEquipment.gridy = 4;
			contentPanel.add(chckbxIHaveEquipment, gbc_chckbxIHaveEquipment);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e->okButtonPressed());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e->tearDown());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}

}

package gui.workouts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.workout.WorkoutGenerationPreferences;

public class GenerateWorkoutDialog extends JDialog {

	private static final long serialVersionUID = 2372123556581471698L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private WorkoutGenerationPreferences preferences;
	private JSlider sliderDaysPerWeek;
	private JSlider sliderWorkoutPeriodInDays;

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
		preferences.setWorkoutPeriodInDays(sliderWorkoutPeriodInDays.getValue());
	}

	private void initializeSwingComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Generate Workout - Workout Properties");
		setBounds(100, 100, 449, 265);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.WEST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 1;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			textFieldName = new JTextField();
			GridBagConstraints gbc_textFieldName = new GridBagConstraints();
			gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldName.gridx = 2;
			gbc_textFieldName.gridy = 1;
			contentPanel.add(textFieldName, gbc_textFieldName);
			textFieldName.setColumns(10);
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
			sliderDaysPerWeek.setMaximum(7);
			GridBagConstraints gbc_sliderDaysPerWeek = new GridBagConstraints();
			gbc_sliderDaysPerWeek.fill = GridBagConstraints.HORIZONTAL;
			gbc_sliderDaysPerWeek.insets = new Insets(0, 0, 5, 5);
			gbc_sliderDaysPerWeek.gridx = 2;
			gbc_sliderDaysPerWeek.gridy = 2;
			contentPanel.add(sliderDaysPerWeek, gbc_sliderDaysPerWeek);
		}
		{
			JLabel lblWorkoutPeriod = new JLabel("Workout Period:");
			GridBagConstraints gbc_lblWorkoutPeriod = new GridBagConstraints();
			gbc_lblWorkoutPeriod.anchor = GridBagConstraints.WEST;
			gbc_lblWorkoutPeriod.insets = new Insets(0, 0, 5, 5);
			gbc_lblWorkoutPeriod.gridx = 1;
			gbc_lblWorkoutPeriod.gridy = 3;
			contentPanel.add(lblWorkoutPeriod, gbc_lblWorkoutPeriod);
		}
		{
			sliderWorkoutPeriodInDays = new JSlider();
			sliderWorkoutPeriodInDays.setMajorTickSpacing(15);
			sliderWorkoutPeriodInDays.setPaintTicks(true);
			sliderWorkoutPeriodInDays.setSnapToTicks(true);
			sliderWorkoutPeriodInDays.setValue(30);
			sliderWorkoutPeriodInDays.setMinimum(15);
			sliderWorkoutPeriodInDays.setMaximum(60);
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 2;
			gbc_slider.gridy = 3;
			contentPanel.add(sliderWorkoutPeriodInDays, gbc_slider);

			Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();
			sliderLabels.put(15, new JLabel("2 weeks"));
			sliderLabels.put(30, new JLabel("month"));
			sliderLabels.put(60, new JLabel("2 months"));
			sliderWorkoutPeriodInDays.setLabelTable(sliderLabels);
			sliderWorkoutPeriodInDays.setPaintLabels(true);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonPressed();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tearDown();
					}
				});
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

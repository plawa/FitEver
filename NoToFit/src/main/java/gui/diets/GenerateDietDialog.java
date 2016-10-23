package gui.diets;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logic.diet.DayMealSetPreferences;
import logic.diet.DietGenerationPreferences;

public class GenerateDietDialog extends JDialog {

	private static final long serialVersionUID = 1865875837806092179L;
	private static final String MSG_MEAL_COUNT_ERROR = "Meals count is not correct!";
	private final JPanel contentPanel = new JPanel();
	private JSlider sliderDietPeriod;
	private JSlider sliderMealsPerDay;
	private JTextField textField;
	private DietGenerationPreferences newDietPreferences;
	private JSpinner spinnerSupperMealsCount;
	private JSpinner spinnerMainDishesCount;
	private JSpinner spinnerBreakfastMealsCount;

	public GenerateDietDialog() {
		initializeSwingComponents();
	}

	public DietGenerationPreferences getNewDietPreferences() {
		return newDietPreferences;
	}

	protected void okButtonPressed() {
		if (!isMealsCountOK()){
			JOptionPane.showMessageDialog(this, MSG_MEAL_COUNT_ERROR);
			return;
		}
		retrieveDietProperties();
		tearDown();
	}

	private void retrieveDietProperties() {
		newDietPreferences = new DietGenerationPreferences();
		newDietPreferences.setDietName(textField.getText());
		newDietPreferences.setMealsPerDay(sliderMealsPerDay.getValue());
		newDietPreferences.setDietPeriodDays(sliderDietPeriod.getValue());
		
		DayMealSetPreferences dayPreferences = new DayMealSetPreferences();
		dayPreferences.setBreakfastMealsCount((int) spinnerBreakfastMealsCount.getValue());
		dayPreferences.setMainDishMealsCount((int) spinnerMainDishesCount.getValue());
		dayPreferences.setSupperMealsCount((int) spinnerSupperMealsCount.getValue());
		dayPreferences.setCaloriesFactorsByDefault();
		
		newDietPreferences.setDayMealsPref(dayPreferences);
	}

	private boolean isMealsCountOK() {
		int mealsCount = sliderMealsPerDay.getValue();
		int mealCountSum = (int) spinnerBreakfastMealsCount.getValue() + (int) spinnerMainDishesCount.getValue()
				+ (int) spinnerSupperMealsCount.getValue();
		return mealsCount == mealCountSum;
	}

	private void initializeSwingComponents() {
		setBounds(100, 100, 400, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblDietName = new JLabel("Diet Name:");
			GridBagConstraints gbc_lblDietName = new GridBagConstraints();
			gbc_lblDietName.anchor = GridBagConstraints.WEST;
			gbc_lblDietName.insets = new Insets(0, 0, 5, 5);
			gbc_lblDietName.gridx = 1;
			gbc_lblDietName.gridy = 1;
			contentPanel.add(lblDietName, gbc_lblDietName);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblMealsPerDay = new JLabel("Meals Per Day:");
			GridBagConstraints gbc_lblMealsPerDay = new GridBagConstraints();
			gbc_lblMealsPerDay.anchor = GridBagConstraints.WEST;
			gbc_lblMealsPerDay.insets = new Insets(0, 0, 5, 5);
			gbc_lblMealsPerDay.gridx = 1;
			gbc_lblMealsPerDay.gridy = 2;
			contentPanel.add(lblMealsPerDay, gbc_lblMealsPerDay);
		}
		{
			sliderMealsPerDay = new JSlider();
			sliderMealsPerDay.setMajorTickSpacing(1);
			sliderMealsPerDay.setPaintLabels(true);
			sliderMealsPerDay.setPaintTicks(true);
			sliderMealsPerDay.setSnapToTicks(true);
			sliderMealsPerDay.setMaximum(6);
			sliderMealsPerDay.setMinimum(3);
			GridBagConstraints gbc_sliderMealsPerDay = new GridBagConstraints();
			gbc_sliderMealsPerDay.fill = GridBagConstraints.HORIZONTAL;
			gbc_sliderMealsPerDay.insets = new Insets(0, 0, 5, 5);
			gbc_sliderMealsPerDay.gridx = 2;
			gbc_sliderMealsPerDay.gridy = 2;
			contentPanel.add(sliderMealsPerDay, gbc_sliderMealsPerDay);
		}
		{
			JLabel lblBreakfastsPerDay = new JLabel("Breakfasts Per Day:");
			GridBagConstraints gbc_lblBreakfastsPerDay = new GridBagConstraints();
			gbc_lblBreakfastsPerDay.anchor = GridBagConstraints.WEST;
			gbc_lblBreakfastsPerDay.insets = new Insets(0, 0, 5, 5);
			gbc_lblBreakfastsPerDay.gridx = 1;
			gbc_lblBreakfastsPerDay.gridy = 3;
			contentPanel.add(lblBreakfastsPerDay, gbc_lblBreakfastsPerDay);
		}
		{
			spinnerBreakfastMealsCount = new JSpinner();
			spinnerBreakfastMealsCount.setModel(new SpinnerNumberModel(1, 1, 4, 1));
			GridBagConstraints gbc_spinnerBreakfastMealsCount = new GridBagConstraints();
			gbc_spinnerBreakfastMealsCount.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerBreakfastMealsCount.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerBreakfastMealsCount.gridx = 2;
			gbc_spinnerBreakfastMealsCount.gridy = 3;
			contentPanel.add(spinnerBreakfastMealsCount, gbc_spinnerBreakfastMealsCount);
		}
		{
			JLabel lblMainDishesPer = new JLabel("Main Dishes Per Day:");
			GridBagConstraints gbc_lblMainDishesPer = new GridBagConstraints();
			gbc_lblMainDishesPer.anchor = GridBagConstraints.WEST;
			gbc_lblMainDishesPer.insets = new Insets(0, 0, 5, 5);
			gbc_lblMainDishesPer.gridx = 1;
			gbc_lblMainDishesPer.gridy = 4;
			contentPanel.add(lblMainDishesPer, gbc_lblMainDishesPer);
		}
		{
			spinnerMainDishesCount = new JSpinner();
			spinnerMainDishesCount.setModel(new SpinnerNumberModel(1, 1, 4, 1));
			GridBagConstraints gbc_spinnerMainDishesCount = new GridBagConstraints();
			gbc_spinnerMainDishesCount.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerMainDishesCount.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerMainDishesCount.gridx = 2;
			gbc_spinnerMainDishesCount.gridy = 4;
			contentPanel.add(spinnerMainDishesCount, gbc_spinnerMainDishesCount);
		}
		{
			JLabel lblSuppersPerDay = new JLabel("Suppers Per Day:");
			GridBagConstraints gbc_lblSuppersPerDay = new GridBagConstraints();
			gbc_lblSuppersPerDay.anchor = GridBagConstraints.WEST;
			gbc_lblSuppersPerDay.insets = new Insets(0, 0, 5, 5);
			gbc_lblSuppersPerDay.gridx = 1;
			gbc_lblSuppersPerDay.gridy = 5;
			contentPanel.add(lblSuppersPerDay, gbc_lblSuppersPerDay);
		}
		{
			spinnerSupperMealsCount = new JSpinner();
			spinnerSupperMealsCount.setModel(new SpinnerNumberModel(1, 1, 4, 1));
			GridBagConstraints gbc_spinnerSupperMealsCount = new GridBagConstraints();
			gbc_spinnerSupperMealsCount.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerSupperMealsCount.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerSupperMealsCount.gridx = 2;
			gbc_spinnerSupperMealsCount.gridy = 5;
			contentPanel.add(spinnerSupperMealsCount, gbc_spinnerSupperMealsCount);
		}
		{
			JLabel lblDietPeriod = new JLabel("Diet Period:");
			GridBagConstraints gbc_lblDietPeriod = new GridBagConstraints();
			gbc_lblDietPeriod.anchor = GridBagConstraints.WEST;
			gbc_lblDietPeriod.insets = new Insets(0, 0, 5, 5);
			gbc_lblDietPeriod.gridx = 1;
			gbc_lblDietPeriod.gridy = 6;
			contentPanel.add(lblDietPeriod, gbc_lblDietPeriod);
		}
		{
			sliderDietPeriod = new JSlider();
			sliderDietPeriod.setSnapToTicks(true);
			sliderDietPeriod.setPaintTicks(true);
			sliderDietPeriod.setPaintLabels(true);
			sliderDietPeriod.setMajorTickSpacing(1);
			sliderDietPeriod.setValue(7);
			sliderDietPeriod.setMaximum(10);
			sliderDietPeriod.setMinimum(3);
			GridBagConstraints gbc_sliderDietPeriod = new GridBagConstraints();
			gbc_sliderDietPeriod.fill = GridBagConstraints.HORIZONTAL;
			gbc_sliderDietPeriod.insets = new Insets(0, 0, 5, 5);
			gbc_sliderDietPeriod.gridx = 2;
			gbc_sliderDietPeriod.gridy = 6;
			contentPanel.add(sliderDietPeriod, gbc_sliderDietPeriod);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
					public void actionPerformed(ActionEvent e) {
						tearDown();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Generate Diet - Diet Properties");
		setModal(true);
	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}

}

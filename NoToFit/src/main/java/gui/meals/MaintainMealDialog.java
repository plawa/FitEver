package gui.meals;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import database.controller.DatabaseController;
import database.entities.Meal;
import gui.common.DialogMode;
import gui.common.Translator;

public class MaintainMealDialog extends JDialog {

	private static final long serialVersionUID = 7513700313890891626L;
	private static final String MSG_CONNECTION_LOST = "You have probably lost connection to database.";

	private Meal mealMaintained;
	protected DialogMode mode;

	protected JTextField textFieldName;
	protected JTextField textFieldGrammage;
	protected JComboBox<String> comboBoxObjective;
	protected JSpinner spinnerFatPercentage;
	protected JSpinner spinnerProteinPercentage;
	protected JSpinner spinnerCarbohydratesPercentage;
	protected JComboBox<String> comboBoxMealType;
	protected JButton okButton;
	protected JButton cancelButton;

	public MaintainMealDialog() {
		mode = DialogMode.CREATE;
		mealMaintained = new Meal();
		initializeSwingComponents();
	}

	MaintainMealDialog(Meal mealToEdit) {
		mode = DialogMode.EDIT;
		mealMaintained = mealToEdit;
		initializeSwingComponents();
	}

	protected void proceedButtonPressed() {
		retrieveMealAttributesFromFields();
		try {
			performSave();
			tearDown();
		} catch (RuntimeException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, MSG_CONNECTION_LOST, "Error!", 0);
		}
	}

	private void performSave() throws RuntimeException {
		switch (mode) {
		case CREATE:
			DatabaseController.saveEntityToDatabase(mealMaintained);
			break;
		case EDIT:
			DatabaseController.updateEntityToDatabase(mealMaintained);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	protected void retrieveMealAttributesFromFields() {
		mealMaintained.setName(textFieldName.getText());
		mealMaintained.setType(Translator.parseMealTypeStringToChar((String) comboBoxMealType.getSelectedItem()));
		mealMaintained.setGrammage(Integer.parseInt(textFieldGrammage.getText()));
		mealMaintained.setCarbohydratesPercentage((Integer) spinnerCarbohydratesPercentage.getValue());
		mealMaintained.setFatPercentage((Integer) spinnerFatPercentage.getValue());
		mealMaintained.setProteinPercentage((Integer) spinnerProteinPercentage.getValue());
		mealMaintained
				.setObjective(Translator.parseObjectiveStringToChar((String) comboBoxObjective.getSelectedItem()));
	}

	protected void initializeFields() {
		textFieldName.setText(mealMaintained.getName());
		comboBoxMealType.setSelectedItem(Translator.parseMealTypeCharToString(mealMaintained.getType()));
		textFieldGrammage.setText(Integer.toString(mealMaintained.getGrammage()));
		spinnerCarbohydratesPercentage.setValue(mealMaintained.getCarbohydratesPercentage());
		spinnerProteinPercentage.setValue(mealMaintained.getProteinPercentage());
		spinnerFatPercentage.setValue(mealMaintained.getFatPercentage());
		comboBoxObjective.setSelectedItem(Translator.parseObjectiveCharToString(mealMaintained.getObjective()));
	}

	private void initializeSwingComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(mode + " Meal");
		setModal(true);
		setBounds(100, 100, 397, 364);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 70, 179, 49, 59, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 25, 0, 0, 0, 0, 73, 33, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Component verticalStrutTop = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrutTop = new GridBagConstraints();
		gbc_verticalStrutTop.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrutTop.gridx = 2;
		gbc_verticalStrutTop.gridy = 0;
		getContentPane().add(verticalStrutTop, gbc_verticalStrutTop);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);

		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 3;
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 2;
		gbc_textFieldName.gridy = 1;
		getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel lblMealType = new JLabel("Meal Type:");
		GridBagConstraints gbc_lblMealType = new GridBagConstraints();
		gbc_lblMealType.anchor = GridBagConstraints.WEST;
		gbc_lblMealType.insets = new Insets(0, 0, 5, 5);
		gbc_lblMealType.gridx = 1;
		gbc_lblMealType.gridy = 2;
		getContentPane().add(lblMealType, gbc_lblMealType);

		comboBoxMealType = new JComboBox<String>();
		comboBoxMealType
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Breakfast", "Main Dish", "Supper" }));
		GridBagConstraints gbc_comboBoxmealType = new GridBagConstraints();
		gbc_comboBoxmealType.gridwidth = 3;
		gbc_comboBoxmealType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxmealType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxmealType.gridx = 2;
		gbc_comboBoxmealType.gridy = 2;
		getContentPane().add(comboBoxMealType, gbc_comboBoxmealType);

		JLabel lblGrammage = new JLabel("Grammage:");
		GridBagConstraints gbc_lblGrammage = new GridBagConstraints();
		gbc_lblGrammage.anchor = GridBagConstraints.WEST;
		gbc_lblGrammage.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrammage.gridx = 1;
		gbc_lblGrammage.gridy = 3;
		getContentPane().add(lblGrammage, gbc_lblGrammage);

		textFieldGrammage = new JTextField();
		GridBagConstraints gbc_textFieldGrammage = new GridBagConstraints();
		gbc_textFieldGrammage.gridwidth = 3;
		gbc_textFieldGrammage.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGrammage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGrammage.gridx = 2;
		gbc_textFieldGrammage.gridy = 3;
		getContentPane().add(textFieldGrammage, gbc_textFieldGrammage);
		textFieldGrammage.setColumns(10);

		JLabel lblCarbohydratesPercentage = new JLabel("Carbohydrates Percentage (%):");
		GridBagConstraints gbc_lblCarbohydratesPercentage = new GridBagConstraints();
		gbc_lblCarbohydratesPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarbohydratesPercentage.gridx = 1;
		gbc_lblCarbohydratesPercentage.gridy = 4;
		getContentPane().add(lblCarbohydratesPercentage, gbc_lblCarbohydratesPercentage);

		spinnerCarbohydratesPercentage = new JSpinner();
		spinnerCarbohydratesPercentage.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		GridBagConstraints gbc_spinnerCarbohydratesPercentage = new GridBagConstraints();
		gbc_spinnerCarbohydratesPercentage.gridwidth = 3;
		gbc_spinnerCarbohydratesPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerCarbohydratesPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerCarbohydratesPercentage.gridx = 2;
		gbc_spinnerCarbohydratesPercentage.gridy = 4;
		getContentPane().add(spinnerCarbohydratesPercentage, gbc_spinnerCarbohydratesPercentage);

		JLabel lblProteinPercentage = new JLabel("Protein Percentage (%):");
		GridBagConstraints gbc_lblProteinPercentage = new GridBagConstraints();
		gbc_lblProteinPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblProteinPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblProteinPercentage.gridx = 1;
		gbc_lblProteinPercentage.gridy = 5;
		getContentPane().add(lblProteinPercentage, gbc_lblProteinPercentage);

		spinnerProteinPercentage = new JSpinner();
		spinnerProteinPercentage.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		GridBagConstraints gbc_spinnerProteinPercentage = new GridBagConstraints();
		gbc_spinnerProteinPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerProteinPercentage.gridwidth = 3;
		gbc_spinnerProteinPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerProteinPercentage.gridx = 2;
		gbc_spinnerProteinPercentage.gridy = 5;
		getContentPane().add(spinnerProteinPercentage, gbc_spinnerProteinPercentage);

		JLabel lblFatPercentage = new JLabel("Fat Percentage (%):");
		GridBagConstraints gbc_lblFatPercentage = new GridBagConstraints();
		gbc_lblFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatPercentage.gridx = 1;
		gbc_lblFatPercentage.gridy = 6;
		getContentPane().add(lblFatPercentage, gbc_lblFatPercentage);

		spinnerFatPercentage = new JSpinner();
		spinnerFatPercentage.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		GridBagConstraints gbc_spinnerFatPercentage = new GridBagConstraints();
		gbc_spinnerFatPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFatPercentage.gridwidth = 3;
		gbc_spinnerFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerFatPercentage.gridx = 2;
		gbc_spinnerFatPercentage.gridy = 6;
		getContentPane().add(spinnerFatPercentage, gbc_spinnerFatPercentage);

		JLabel lblObjective = new JLabel("Recommended For:");
		GridBagConstraints gbc_lblObjective = new GridBagConstraints();
		gbc_lblObjective.anchor = GridBagConstraints.WEST;
		gbc_lblObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblObjective.gridx = 1;
		gbc_lblObjective.gridy = 7;
		getContentPane().add(lblObjective, gbc_lblObjective);

		comboBoxObjective = new JComboBox<String>();
		comboBoxObjective
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Mass Gain", "Reduction", "Strength" }));
		GridBagConstraints gbc_comboBoxObjective = new GridBagConstraints();
		gbc_comboBoxObjective.gridwidth = 3;
		gbc_comboBoxObjective.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxObjective.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxObjective.gridx = 2;
		gbc_comboBoxObjective.gridy = 7;
		getContentPane().add(comboBoxObjective, gbc_comboBoxObjective);

		Component horizontalStrutLeft = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrutLeft = new GridBagConstraints();
		gbc_horizontalStrutLeft.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrutLeft.gridx = 0;
		gbc_horizontalStrutLeft.gridy = 9;
		getContentPane().add(horizontalStrutLeft, gbc_horizontalStrutLeft);
		{
			okButton = new JButton("Save");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					proceedButtonPressed();
				}
			});
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.insets = new Insets(0, 0, 5, 5);
			gbc_okButton.gridx = 3;
			gbc_okButton.gridy = 9;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 9;
			getContentPane().add(cancelButton, gbc_cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tearDown();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		Component glue = Box.createGlue();
		GridBagConstraints gbc_glue = new GridBagConstraints();
		gbc_glue.insets = new Insets(0, 0, 5, 5);
		gbc_glue.gridx = 5;
		gbc_glue.gridy = 9;
		getContentPane().add(glue, gbc_glue);

		Component horizontalStrutRight = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrutRight = new GridBagConstraints();
		gbc_horizontalStrutRight.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrutRight.gridx = 6;
		gbc_horizontalStrutRight.gridy = 9;
		getContentPane().add(horizontalStrutRight, gbc_horizontalStrutRight);

		Component verticalStrutBottom = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrutBottom = new GridBagConstraints();
		gbc_verticalStrutBottom.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrutBottom.gridx = 2;
		gbc_verticalStrutBottom.gridy = 10;
		getContentPane().add(verticalStrutBottom, gbc_verticalStrutBottom);
		if (mode == DialogMode.EDIT) {
			initializeFields();
		}
	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}

}

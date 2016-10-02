package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import database.controller.DatabaseController;
import database.entities.Meal;

public class AddMealDialog extends JDialog {
	
	private static final long serialVersionUID = 7513700313890891626L;
	private JTextField textFieldName;
	private JTextField textFieldGrammage;
	private JComboBox<String> comboBoxObjective;
	private JSpinner spinnerFatPercentage;
	private JSpinner spinnerProteinPercentage;
	private JSpinner spinnerCarbohydratesPercentage;

	public static void main(String[] args) {
		try {
			AddMealDialog dialog = new AddMealDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AddMealDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New Meal");
		setModal(true);
		setBounds(100, 100, 397, 364);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 70, 179, 49, 59, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 73, 33, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblGrammage = new JLabel("Grammage:");
		GridBagConstraints gbc_lblGrammage = new GridBagConstraints();
		gbc_lblGrammage.anchor = GridBagConstraints.WEST;
		gbc_lblGrammage.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrammage.gridx = 1;
		gbc_lblGrammage.gridy = 2;
		getContentPane().add(lblGrammage, gbc_lblGrammage);
		
		textFieldGrammage = new JTextField();
		GridBagConstraints gbc_textFieldGrammage = new GridBagConstraints();
		gbc_textFieldGrammage.gridwidth = 3;
		gbc_textFieldGrammage.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGrammage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGrammage.gridx = 2;
		gbc_textFieldGrammage.gridy = 2;
		getContentPane().add(textFieldGrammage, gbc_textFieldGrammage);
		textFieldGrammage.setColumns(10);
		
		JLabel lblCarbohydratesPercentage = new JLabel("Carbohydrates Percentage (%):");
		GridBagConstraints gbc_lblCarbohydratesPercentage = new GridBagConstraints();
		gbc_lblCarbohydratesPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarbohydratesPercentage.gridx = 1;
		gbc_lblCarbohydratesPercentage.gridy = 3;
		getContentPane().add(lblCarbohydratesPercentage, gbc_lblCarbohydratesPercentage);
		
		spinnerCarbohydratesPercentage = new JSpinner();
		spinnerCarbohydratesPercentage.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		GridBagConstraints gbc_spinnerCarbohydratesPercentage = new GridBagConstraints();
		gbc_spinnerCarbohydratesPercentage.gridwidth = 3;
		gbc_spinnerCarbohydratesPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerCarbohydratesPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerCarbohydratesPercentage.gridx = 2;
		gbc_spinnerCarbohydratesPercentage.gridy = 3;
		getContentPane().add(spinnerCarbohydratesPercentage, gbc_spinnerCarbohydratesPercentage);
		
		JLabel lblProteinPercentage = new JLabel("Protein Percentage (%):");
		GridBagConstraints gbc_lblProteinPercentage = new GridBagConstraints();
		gbc_lblProteinPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblProteinPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblProteinPercentage.gridx = 1;
		gbc_lblProteinPercentage.gridy = 4;
		getContentPane().add(lblProteinPercentage, gbc_lblProteinPercentage);
		
		spinnerProteinPercentage = new JSpinner();
		spinnerProteinPercentage.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		GridBagConstraints gbc_spinnerProteinPercentage = new GridBagConstraints();
		gbc_spinnerProteinPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerProteinPercentage.gridwidth = 3;
		gbc_spinnerProteinPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerProteinPercentage.gridx = 2;
		gbc_spinnerProteinPercentage.gridy = 4;
		getContentPane().add(spinnerProteinPercentage, gbc_spinnerProteinPercentage);
		
		JLabel lblFatPercentage = new JLabel("Fat Percentage (%):");
		GridBagConstraints gbc_lblFatPercentage = new GridBagConstraints();
		gbc_lblFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatPercentage.gridx = 1;
		gbc_lblFatPercentage.gridy = 5;
		getContentPane().add(lblFatPercentage, gbc_lblFatPercentage);
		
		spinnerFatPercentage = new JSpinner();
		spinnerFatPercentage.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		GridBagConstraints gbc_spinnerFatPercentage = new GridBagConstraints();
		gbc_spinnerFatPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFatPercentage.gridwidth = 3;
		gbc_spinnerFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerFatPercentage.gridx = 2;
		gbc_spinnerFatPercentage.gridy = 5;
		getContentPane().add(spinnerFatPercentage, gbc_spinnerFatPercentage);
		
		JLabel lblObjective = new JLabel("Recommended For:");
		GridBagConstraints gbc_lblObjective = new GridBagConstraints();
		gbc_lblObjective.anchor = GridBagConstraints.WEST;
		gbc_lblObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblObjective.gridx = 1;
		gbc_lblObjective.gridy = 6;
		getContentPane().add(lblObjective, gbc_lblObjective);
		
		comboBoxObjective = new JComboBox<String>();
		comboBoxObjective.setModel(new DefaultComboBoxModel(new String[] {"Mass Gain", "Reduction", "Strength"}));
		GridBagConstraints gbc_comboBoxObjective = new GridBagConstraints();
		gbc_comboBoxObjective.gridwidth = 3;
		gbc_comboBoxObjective.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxObjective.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxObjective.gridx = 2;
		gbc_comboBoxObjective.gridy = 6;
		getContentPane().add(comboBoxObjective, gbc_comboBoxObjective);
		
		Component horizontalStrutLeft = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrutLeft = new GridBagConstraints();
		gbc_horizontalStrutLeft.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrutLeft.gridx = 0;
		gbc_horizontalStrutLeft.gridy = 11;
		getContentPane().add(horizontalStrutLeft, gbc_horizontalStrutLeft);
		{
			JButton okButton = new JButton("Save");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveButtonPressed();
				}
			});
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.insets = new Insets(0, 0, 5, 5);
			gbc_okButton.gridx = 3;
			gbc_okButton.gridy = 11;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 11;
			getContentPane().add(cancelButton, gbc_cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		Component horizontalStrutRight = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrutRight = new GridBagConstraints();
		gbc_horizontalStrutRight.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrutRight.gridx = 5;
		gbc_horizontalStrutRight.gridy = 11;
		getContentPane().add(horizontalStrutRight, gbc_horizontalStrutRight);
		
		Component verticalStrutBottom = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrutBottom = new GridBagConstraints();
		gbc_verticalStrutBottom.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrutBottom.gridx = 2;
		gbc_verticalStrutBottom.gridy = 12;
		getContentPane().add(verticalStrutBottom, gbc_verticalStrutBottom);
	}
	
	private void saveButtonPressed(){

		Meal newMeal = new Meal();
		
		newMeal.setName(textFieldName.getText());
		newMeal.setGramature(Integer.parseInt(textFieldGrammage.getText()));
		newMeal.setCarbohydratesPercentage((Integer) spinnerCarbohydratesPercentage.getValue());
		newMeal.setFatPercentage((Integer) spinnerFatPercentage.getValue());
		newMeal.setProteinPercentage((Integer) spinnerProteinPercentage.getValue());
		newMeal.setObjectiveFromString((String) comboBoxObjective.getSelectedItem());
		
		
		try {
			new DatabaseController().saveEntityToDatabase(newMeal);
			dispose();
		} catch (Exception e){
			e.printStackTrace();
			//JOptionPane.showMessageDialog(this, e.getStackTrace().toString(), "Error!", 2);
		}
	}
	
}

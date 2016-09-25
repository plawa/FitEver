package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AddUserDialog extends JDialog {
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldHeight;
	private JTextField textFieldWeight;
	private JTextField textFieldGoalWeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUserDialog dialog = new AddUserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUserDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New User");
		setModal(true);
		setBounds(100, 100, 353, 364);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 70, 179, 49, 59, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 73, 33, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
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
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.WEST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 2;
		getContentPane().add(lblSurname, gbc_lblSurname);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldSurname = new GridBagConstraints();
		gbc_textFieldSurname.gridwidth = 3;
		gbc_textFieldSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSurname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSurname.gridx = 2;
		gbc_textFieldSurname.gridy = 2;
		getContentPane().add(textFieldSurname, gbc_textFieldSurname);
		textFieldSurname.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex:");
		GridBagConstraints gbc_lblSex = new GridBagConstraints();
		gbc_lblSex.anchor = GridBagConstraints.WEST;
		gbc_lblSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblSex.gridx = 1;
		gbc_lblSex.gridy = 3;
		getContentPane().add(lblSex, gbc_lblSex);
		
		JComboBox comboBoxSex = new JComboBox();
		comboBoxSex.setModel(new DefaultComboBoxModel(new String[] {"male", "female"}));
		GridBagConstraints gbc_comboBoxSex = new GridBagConstraints();
		gbc_comboBoxSex.gridwidth = 3;
		gbc_comboBoxSex.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSex.gridx = 2;
		gbc_comboBoxSex.gridy = 3;
		getContentPane().add(comboBoxSex, gbc_comboBoxSex);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.anchor = GridBagConstraints.WEST;
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfBirth.gridx = 1;
		gbc_lblDateOfBirth.gridy = 4;
		getContentPane().add(lblDateOfBirth, gbc_lblDateOfBirth);
		
		
		//DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		JFormattedTextField frmtdtxtfldDdmmyyyy = new JFormattedTextField(createFormatter("##-##-####"));
		frmtdtxtfldDdmmyyyy.setInputVerifier(createInputVerifier());
		GridBagConstraints gbc_frmtdtxtfldDdmmyyyy = new GridBagConstraints();
		gbc_frmtdtxtfldDdmmyyyy.gridwidth = 3;
		gbc_frmtdtxtfldDdmmyyyy.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldDdmmyyyy.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldDdmmyyyy.gridx = 2;
		gbc_frmtdtxtfldDdmmyyyy.gridy = 4;
		getContentPane().add(frmtdtxtfldDdmmyyyy, gbc_frmtdtxtfldDdmmyyyy);
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.WEST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 5;
		getContentPane().add(lblHeight, gbc_lblHeight);
		
		textFieldHeight = new JTextField();
		GridBagConstraints gbc_textFieldHeight = new GridBagConstraints();
		gbc_textFieldHeight.gridwidth = 3;
		gbc_textFieldHeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHeight.gridx = 2;
		gbc_textFieldHeight.gridy = 5;
		getContentPane().add(textFieldHeight, gbc_textFieldHeight);
		textFieldHeight.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.WEST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 6;
		getContentPane().add(lblWeight, gbc_lblWeight);
		
		textFieldWeight = new JTextField();
		GridBagConstraints gbc_textFieldWeight = new GridBagConstraints();
		gbc_textFieldWeight.gridwidth = 3;
		gbc_textFieldWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWeight.gridx = 2;
		gbc_textFieldWeight.gridy = 6;
		getContentPane().add(textFieldWeight, gbc_textFieldWeight);
		textFieldWeight.setColumns(10);
		
		JLabel lblGoalWeight = new JLabel("Goal Weight:");
		GridBagConstraints gbc_lblGoalWeight = new GridBagConstraints();
		gbc_lblGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoalWeight.gridx = 1;
		gbc_lblGoalWeight.gridy = 7;
		getContentPane().add(lblGoalWeight, gbc_lblGoalWeight);
		
		textFieldGoalWeight = new JTextField();
		GridBagConstraints gbc_textFieldGoalWeight = new GridBagConstraints();
		gbc_textFieldGoalWeight.gridwidth = 3;
		gbc_textFieldGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGoalWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGoalWeight.gridx = 2;
		gbc_textFieldGoalWeight.gridy = 7;
		getContentPane().add(textFieldGoalWeight, gbc_textFieldGoalWeight);
		textFieldGoalWeight.setColumns(10);
		
		JLabel lblFatPercentage = new JLabel("Fat Percentage (%):");
		GridBagConstraints gbc_lblFatPercentage = new GridBagConstraints();
		gbc_lblFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatPercentage.gridx = 1;
		gbc_lblFatPercentage.gridy = 8;
		getContentPane().add(lblFatPercentage, gbc_lblFatPercentage);
		
		JSpinner spinnerFatPercentage = new JSpinner();
		spinnerFatPercentage.setModel(new SpinnerNumberModel(0, 0, 40, 1));
		GridBagConstraints gbc_spinnerFatPercentage = new GridBagConstraints();
		gbc_spinnerFatPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFatPercentage.gridwidth = 3;
		gbc_spinnerFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerFatPercentage.gridx = 2;
		gbc_spinnerFatPercentage.gridy = 8;
		getContentPane().add(spinnerFatPercentage, gbc_spinnerFatPercentage);
		
		JLabel lblMainGoal = new JLabel("Main Goal:");
		GridBagConstraints gbc_lblMainGoal = new GridBagConstraints();
		gbc_lblMainGoal.anchor = GridBagConstraints.WEST;
		gbc_lblMainGoal.insets = new Insets(0, 0, 5, 5);
		gbc_lblMainGoal.gridx = 1;
		gbc_lblMainGoal.gridy = 9;
		getContentPane().add(lblMainGoal, gbc_lblMainGoal);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mass Gain", "Reduction", "Strenght"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 9;
		getContentPane().add(comboBox, gbc_comboBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 11;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
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
		
		Component horizontalStrut1 = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrut1 = new GridBagConstraints();
		gbc_horizontalStrut1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut1.gridx = 5;
		gbc_horizontalStrut1.gridy = 11;
		getContentPane().add(horizontalStrut1, gbc_horizontalStrut1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 12;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
	}
	
	private void saveButtonPressed(){
		
	}
	
	private MaskFormatter createFormatter(String s){
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	    }
	    return formatter;
	}
	
	private InputVerifier createInputVerifier(){
		InputVerifier myInputVerifier = new InputVerifier() {
			private DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
			
			@Override
			public boolean verify(JComponent input) {
				dateFormatter.setLenient(false);
				JFormattedTextField ft = (JFormattedTextField) input;
				String text = ft.getText();
				try {
					dateFormatter.parse(text);
					return true;
				} catch (ParseException e) {
					return false;
				}
			}
		};
		return myInputVerifier;
	}
	
}

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;
import logic.Encrypter;

import javax.swing.JPasswordField;

public class EditUserDialog extends JDialog {
	
	private static final long serialVersionUID = 9182720162758099907L;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldHeight;
	private JTextField textFieldStartWeight;
	private JTextField textFieldGoalWeight;
	private JComboBox<String> comboBoxSex;
	private JComboBox<String> comboBoxUserObjective;
	private JFormattedTextField formatTxtFldDate;
	private JSpinner spinnerFatPercentage;
	private DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditUserDialog dialog = new EditUserDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditUserDialog(User userToEdit) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New User");
		setModal(true);
		setBounds(100, 100, 416, 424);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 70, 179, 49, 59, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 73, 33, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblLogin = new JLabel("Login:");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 1;
		getContentPane().add(lblLogin, gbc_lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setEnabled(false);
		textFieldLogin.setEditable(false);
		GridBagConstraints gbc_textFieldLogin = new GridBagConstraints();
		gbc_textFieldLogin.gridwidth = 3;
		gbc_textFieldLogin.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLogin.gridx = 2;
		gbc_textFieldLogin.gridy = 1;
		getContentPane().add(textFieldLogin, gbc_textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		getContentPane().add(lblName, gbc_lblName);
		
		textFieldName = new JTextField(userToEdit.getName());
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 3;
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 2;
		gbc_textFieldName.gridy = 3;
		getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.WEST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 4;
		getContentPane().add(lblSurname, gbc_lblSurname);
		
		textFieldSurname = new JTextField(userToEdit.getSurname());
		textFieldSurname.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldSurname = new GridBagConstraints();
		gbc_textFieldSurname.gridwidth = 3;
		gbc_textFieldSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSurname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSurname.gridx = 2;
		gbc_textFieldSurname.gridy = 4;
		getContentPane().add(textFieldSurname, gbc_textFieldSurname);
		textFieldSurname.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex:");
		GridBagConstraints gbc_lblSex = new GridBagConstraints();
		gbc_lblSex.anchor = GridBagConstraints.WEST;
		gbc_lblSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblSex.gridx = 1;
		gbc_lblSex.gridy = 5;
		getContentPane().add(lblSex, gbc_lblSex);
		
		comboBoxSex = new JComboBox<String>();
		comboBoxSex.setModel(new DefaultComboBoxModel<String>(new String[] {"male", "female"}));
		GridBagConstraints gbc_comboBoxSex = new GridBagConstraints();
		gbc_comboBoxSex.gridwidth = 3;
		gbc_comboBoxSex.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSex.gridx = 2;
		gbc_comboBoxSex.gridy = 5;
		getContentPane().add(comboBoxSex, gbc_comboBoxSex);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.anchor = GridBagConstraints.WEST;
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfBirth.gridx = 1;
		gbc_lblDateOfBirth.gridy = 6;
		getContentPane().add(lblDateOfBirth, gbc_lblDateOfBirth);
		
		
		//DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		formatTxtFldDate = new JFormattedTextField(createFormatter("##-##-####"));
		formatTxtFldDate.setInputVerifier(createInputVerifier());
		GridBagConstraints gbc_formatTxtFldDate = new GridBagConstraints();
		gbc_formatTxtFldDate.gridwidth = 3;
		gbc_formatTxtFldDate.insets = new Insets(0, 0, 5, 5);
		gbc_formatTxtFldDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_formatTxtFldDate.gridx = 2;
		gbc_formatTxtFldDate.gridy = 6;
		getContentPane().add(formatTxtFldDate, gbc_formatTxtFldDate);
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.WEST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 7;
		getContentPane().add(lblHeight, gbc_lblHeight);
		
		textFieldHeight = new JTextField(userToEdit.getHeight());
		GridBagConstraints gbc_textFieldHeight = new GridBagConstraints();
		gbc_textFieldHeight.gridwidth = 3;
		gbc_textFieldHeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHeight.gridx = 2;
		gbc_textFieldHeight.gridy = 7;
		getContentPane().add(textFieldHeight, gbc_textFieldHeight);
		textFieldHeight.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.WEST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 8;
		getContentPane().add(lblWeight, gbc_lblWeight);
		
		textFieldStartWeight = new JTextField(Float.toString(userToEdit.getStartWeight()));
		GridBagConstraints gbc_textFieldWeight = new GridBagConstraints();
		gbc_textFieldWeight.gridwidth = 3;
		gbc_textFieldWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWeight.gridx = 2;
		gbc_textFieldWeight.gridy = 8;
		getContentPane().add(textFieldStartWeight, gbc_textFieldWeight);
		textFieldStartWeight.setColumns(10);
		
		JLabel lblGoalWeight = new JLabel("Goal Weight:");
		GridBagConstraints gbc_lblGoalWeight = new GridBagConstraints();
		gbc_lblGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoalWeight.gridx = 1;
		gbc_lblGoalWeight.gridy = 9;
		getContentPane().add(lblGoalWeight, gbc_lblGoalWeight);
		
		textFieldGoalWeight = new JTextField(Float.toString(userToEdit.getGoalWeight()));
		GridBagConstraints gbc_textFieldGoalWeight = new GridBagConstraints();
		gbc_textFieldGoalWeight.gridwidth = 3;
		gbc_textFieldGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGoalWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGoalWeight.gridx = 2;
		gbc_textFieldGoalWeight.gridy = 9;
		getContentPane().add(textFieldGoalWeight, gbc_textFieldGoalWeight);
		textFieldGoalWeight.setColumns(10);
		
		JLabel lblFatPercentage = new JLabel("Fat Percentage (%):");
		GridBagConstraints gbc_lblFatPercentage = new GridBagConstraints();
		gbc_lblFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatPercentage.gridx = 1;
		gbc_lblFatPercentage.gridy = 10;
		getContentPane().add(lblFatPercentage, gbc_lblFatPercentage);
		
		spinnerFatPercentage = new JSpinner();
		spinnerFatPercentage.setModel(new SpinnerNumberModel(0, 0, 40, 1));
		GridBagConstraints gbc_spinnerFatPercentage = new GridBagConstraints();
		gbc_spinnerFatPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFatPercentage.gridwidth = 3;
		gbc_spinnerFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerFatPercentage.gridx = 2;
		gbc_spinnerFatPercentage.gridy = 10;
		spinnerFatPercentage.setValue(userToEdit.getFatPercentage());
		getContentPane().add(spinnerFatPercentage, gbc_spinnerFatPercentage);
		
		JLabel lblMainGoal = new JLabel("Main Goal:");
		GridBagConstraints gbc_lblMainGoal = new GridBagConstraints();
		gbc_lblMainGoal.anchor = GridBagConstraints.WEST;
		gbc_lblMainGoal.insets = new Insets(0, 0, 5, 5);
		gbc_lblMainGoal.gridx = 1;
		gbc_lblMainGoal.gridy = 11;
		getContentPane().add(lblMainGoal, gbc_lblMainGoal);
		
		comboBoxUserObjective = new JComboBox<String>();
		comboBoxUserObjective.setModel(new DefaultComboBoxModel<String>(new String[] {"Mass Gain", "Reduction", "Strenght"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 11;
		getContentPane().add(comboBoxUserObjective, gbc_comboBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 13;
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
			gbc_okButton.gridy = 13;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 13;
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
		gbc_horizontalStrut1.gridy = 13;
		getContentPane().add(horizontalStrut1, gbc_horizontalStrut1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 14;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
	}
	
	private void saveButtonPressed(){
		Map<String, Character> sexTranslations = new HashMap<String, Character>();
		sexTranslations.put("male", 'm');
		sexTranslations.put("female", 'f');
		
		Map<String, Character> objectiveTranslations = new HashMap<String, Character>();
		objectiveTranslations.put("Mass Gain", 'm');
		objectiveTranslations.put("Reduction", 'r');
		objectiveTranslations.put("Stength", 'p');
		
		
		String login = textFieldLogin.getText();
		String passwordRaw = passwordField.getText();
		String name = textFieldName.getText();
		String surname = textFieldSurname.getText();
		String sexFull = (String) comboBoxSex.getSelectedItem();
		Character sex = sexTranslations.get(sexFull);
		int height = Integer.parseInt(textFieldHeight.getText());
		float startWeight = Float.parseFloat(textFieldStartWeight.getText());
		float goalWeight = Float.parseFloat(textFieldGoalWeight.getText());
		int fatPercentage = (Integer) spinnerFatPercentage.getValue();
		String userObjectiveFull = (String) comboBoxUserObjective.getSelectedItem();
		Character userObjective = objectiveTranslations.get(userObjectiveFull);
		String dateRaw = formatTxtFldDate.getText();
		Date date = null;
		try { date = dateFormatter.parse(dateRaw); } 
		catch (ParseException e) { e.printStackTrace();	}
		
		Shadow userCredentials = new Shadow();
		userCredentials.setLogin(login);
		userCredentials.setAndEncryptPass(passwordRaw);
		
		User newUser = new User();
		newUser.setName(name);
		newUser.setSurname(surname);
		newUser.setDateOfBirth(date);
		newUser.setSex(sex);
		newUser.setHeight(height);
		newUser.setStartWeight(startWeight);
		newUser.setGoalWeight(goalWeight);
		newUser.setFatPercentage(fatPercentage);
		newUser.setUserObjective(userObjective);
		
		newUser.setShadow(userCredentials);
		userCredentials.setUser(newUser);
		
		try {
			DatabaseController db = new DatabaseController();
			db.saveEntityToDatabase(userCredentials);
			dispose();
		} catch (Exception e){
			e.printStackTrace();
			//JOptionPane.showMessageDialog(this, e.getStackTrace().toString(), "Error!", 2);
		}
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
		return new InputVerifier() {
			
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
	}
	
}

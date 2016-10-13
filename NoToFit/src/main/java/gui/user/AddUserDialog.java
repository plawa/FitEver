package gui.user;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;
import gui.common.GuiTools;
import logic.entitytools.UserTools;

public class AddUserDialog extends JDialog {

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
	private JPasswordField passwordFieldConfirm;

	public static void main(String[] args) {
		try {
			AddUserDialog dialog = new AddUserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public AddUserDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New User");
		setModal(true);
		setBounds(100, 100, 471, 496);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 70, 179, 49, 59, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 73, 33, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Component verticalStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 1;
		getContentPane().add(lblLogin, gbc_lblLogin);

		textFieldLogin = new JTextField();
		GridBagConstraints gbc_textFieldLogin = new GridBagConstraints();
		gbc_textFieldLogin.gridwidth = 3;
		gbc_textFieldLogin.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLogin.gridx = 2;
		gbc_textFieldLogin.gridy = 1;
		getContentPane().add(textFieldLogin, gbc_textFieldLogin);
		textFieldLogin.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		getContentPane().add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		getContentPane().add(passwordField, gbc_passwordField);

		JLabel lblReenterPassword = new JLabel("Re-enter password:");
		lblReenterPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblReenterPassword = new GridBagConstraints();
		gbc_lblReenterPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblReenterPassword.anchor = GridBagConstraints.WEST;
		gbc_lblReenterPassword.gridx = 1;
		gbc_lblReenterPassword.gridy = 3;
		getContentPane().add(lblReenterPassword, gbc_lblReenterPassword);

		passwordFieldConfirm = new JPasswordField();
		GridBagConstraints gbc_passwordFieldConfirm = new GridBagConstraints();
		gbc_passwordFieldConfirm.gridwidth = 3;
		gbc_passwordFieldConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldConfirm.gridx = 2;
		gbc_passwordFieldConfirm.gridy = 3;
		getContentPane().add(passwordFieldConfirm, gbc_passwordFieldConfirm);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		getContentPane().add(lblName, gbc_lblName);

		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 3;
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 2;
		gbc_textFieldName.gridy = 4;
		getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.WEST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 5;
		getContentPane().add(lblSurname, gbc_lblSurname);

		textFieldSurname = new JTextField();
		textFieldSurname.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldSurname = new GridBagConstraints();
		gbc_textFieldSurname.gridwidth = 3;
		gbc_textFieldSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSurname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSurname.gridx = 2;
		gbc_textFieldSurname.gridy = 5;
		getContentPane().add(textFieldSurname, gbc_textFieldSurname);
		textFieldSurname.setColumns(10);

		JLabel lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSex = new GridBagConstraints();
		gbc_lblSex.anchor = GridBagConstraints.WEST;
		gbc_lblSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblSex.gridx = 1;
		gbc_lblSex.gridy = 6;
		getContentPane().add(lblSex, gbc_lblSex);

		comboBoxSex = new JComboBox<String>();
		comboBoxSex.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female" }));
		GridBagConstraints gbc_comboBoxSex = new GridBagConstraints();
		gbc_comboBoxSex.gridwidth = 3;
		gbc_comboBoxSex.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSex.gridx = 2;
		gbc_comboBoxSex.gridy = 6;
		getContentPane().add(comboBoxSex, gbc_comboBoxSex);

		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.anchor = GridBagConstraints.WEST;
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfBirth.gridx = 1;
		gbc_lblDateOfBirth.gridy = 7;
		getContentPane().add(lblDateOfBirth, gbc_lblDateOfBirth);

		formatTxtFldDate = new JFormattedTextField(GuiTools.createFormatterFromPattern("##-##-####"));
		formatTxtFldDate.setInputVerifier(GuiTools.createInputVerifier(dateFormatter));
		GridBagConstraints gbc_formatTxtFldDate = new GridBagConstraints();
		gbc_formatTxtFldDate.gridwidth = 3;
		gbc_formatTxtFldDate.insets = new Insets(0, 0, 5, 5);
		gbc_formatTxtFldDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_formatTxtFldDate.gridx = 2;
		gbc_formatTxtFldDate.gridy = 7;
		getContentPane().add(formatTxtFldDate, gbc_formatTxtFldDate);

		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.WEST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 8;
		getContentPane().add(lblHeight, gbc_lblHeight);

		textFieldHeight = new JTextField();
		GridBagConstraints gbc_textFieldHeight = new GridBagConstraints();
		gbc_textFieldHeight.gridwidth = 3;
		gbc_textFieldHeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHeight.gridx = 2;
		gbc_textFieldHeight.gridy = 8;
		getContentPane().add(textFieldHeight, gbc_textFieldHeight);
		textFieldHeight.setColumns(10);

		JLabel lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.WEST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 9;
		getContentPane().add(lblWeight, gbc_lblWeight);

		textFieldStartWeight = new JTextField();
		GridBagConstraints gbc_textFieldWeight = new GridBagConstraints();
		gbc_textFieldWeight.gridwidth = 3;
		gbc_textFieldWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWeight.gridx = 2;
		gbc_textFieldWeight.gridy = 9;
		getContentPane().add(textFieldStartWeight, gbc_textFieldWeight);
		textFieldStartWeight.setColumns(10);

		JLabel lblGoalWeight = new JLabel("Goal Weight:");
		GridBagConstraints gbc_lblGoalWeight = new GridBagConstraints();
		gbc_lblGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoalWeight.gridx = 1;
		gbc_lblGoalWeight.gridy = 10;
		getContentPane().add(lblGoalWeight, gbc_lblGoalWeight);

		textFieldGoalWeight = new JTextField();
		GridBagConstraints gbc_textFieldGoalWeight = new GridBagConstraints();
		gbc_textFieldGoalWeight.gridwidth = 3;
		gbc_textFieldGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGoalWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGoalWeight.gridx = 2;
		gbc_textFieldGoalWeight.gridy = 10;
		getContentPane().add(textFieldGoalWeight, gbc_textFieldGoalWeight);
		textFieldGoalWeight.setColumns(10);

		JLabel lblFatPercentage = new JLabel("Fat Percentage (%):");
		GridBagConstraints gbc_lblFatPercentage = new GridBagConstraints();
		gbc_lblFatPercentage.anchor = GridBagConstraints.WEST;
		gbc_lblFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatPercentage.gridx = 1;
		gbc_lblFatPercentage.gridy = 11;
		getContentPane().add(lblFatPercentage, gbc_lblFatPercentage);

		spinnerFatPercentage = new JSpinner();
		spinnerFatPercentage.setModel(new SpinnerNumberModel(0, 0, 40, 1));
		GridBagConstraints gbc_spinnerFatPercentage = new GridBagConstraints();
		gbc_spinnerFatPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFatPercentage.gridwidth = 3;
		gbc_spinnerFatPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerFatPercentage.gridx = 2;
		gbc_spinnerFatPercentage.gridy = 11;
		getContentPane().add(spinnerFatPercentage, gbc_spinnerFatPercentage);

		JLabel lblMainGoal = new JLabel("Main Goal:");
		GridBagConstraints gbc_lblMainGoal = new GridBagConstraints();
		gbc_lblMainGoal.anchor = GridBagConstraints.WEST;
		gbc_lblMainGoal.insets = new Insets(0, 0, 5, 5);
		gbc_lblMainGoal.gridx = 1;
		gbc_lblMainGoal.gridy = 12;
		getContentPane().add(lblMainGoal, gbc_lblMainGoal);

		comboBoxUserObjective = new JComboBox<String>();
		comboBoxUserObjective
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Mass Gain", "Reduction", "Strength" }));
		GridBagConstraints gbc_comboBoxUserObjective = new GridBagConstraints();
		gbc_comboBoxUserObjective.gridwidth = 3;
		gbc_comboBoxUserObjective.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUserObjective.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUserObjective.gridx = 2;
		gbc_comboBoxUserObjective.gridy = 12;
		getContentPane().add(comboBoxUserObjective, gbc_comboBoxUserObjective);

		JLabel lblSomatotype = new JLabel("Somatotype:");
		GridBagConstraints gbc_lblSomatotype = new GridBagConstraints();
		gbc_lblSomatotype.anchor = GridBagConstraints.WEST;
		gbc_lblSomatotype.insets = new Insets(0, 0, 5, 5);
		gbc_lblSomatotype.gridx = 1;
		gbc_lblSomatotype.gridy = 13;
		getContentPane().add(lblSomatotype, gbc_lblSomatotype);

		JComboBox<Object> comboBoxSomatotype = new JComboBox<Object>();
		comboBoxSomatotype.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Undefined", "Ectomorphic", "Endomorphic", "Mesomorphic" }));
		GridBagConstraints gbc_comboBoxSomatotype = new GridBagConstraints();
		gbc_comboBoxSomatotype.gridwidth = 3;
		gbc_comboBoxSomatotype.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSomatotype.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSomatotype.gridx = 2;
		gbc_comboBoxSomatotype.gridy = 13;
		getContentPane().add(comboBoxSomatotype, gbc_comboBoxSomatotype);

		JLabel lblLifeStyle = new JLabel("Life Style:");
		GridBagConstraints gbc_lblLifeStyle = new GridBagConstraints();
		gbc_lblLifeStyle.anchor = GridBagConstraints.WEST;
		gbc_lblLifeStyle.insets = new Insets(0, 0, 5, 5);
		gbc_lblLifeStyle.gridx = 1;
		gbc_lblLifeStyle.gridy = 14;
		getContentPane().add(lblLifeStyle, gbc_lblLifeStyle);

		JSlider sliderLifeStyle = new JSlider();
		GridBagConstraints gbc_sliderLifeStyle = new GridBagConstraints();
		gbc_sliderLifeStyle.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderLifeStyle.gridwidth = 3;
		gbc_sliderLifeStyle.insets = new Insets(0, 0, 5, 5);
		gbc_sliderLifeStyle.gridx = 2;
		gbc_sliderLifeStyle.gridy = 14;
		getContentPane().add(sliderLifeStyle, gbc_sliderLifeStyle);

		JLabel lblLifestyleDescription = new JLabel("lifestyle description");
		GridBagConstraints gbc_lblLifestyleDescription = new GridBagConstraints();
		gbc_lblLifestyleDescription.gridwidth = 3;
		gbc_lblLifestyleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblLifestyleDescription.gridx = 2;
		gbc_lblLifestyleDescription.gridy = 15;
		getContentPane().add(lblLifestyleDescription, gbc_lblLifestyleDescription);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 17;
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
			gbc_okButton.gridy = 17;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 17;
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
		gbc_horizontalStrut1.gridy = 17;
		getContentPane().add(horizontalStrut1, gbc_horizontalStrut1);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 18;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
	}

	private void saveButtonPressed() {
		String login = textFieldLogin.getText();
		String passwordRaw = null;
		if (arePasswordsTheSame()){
			passwordRaw = passwordField.getText();
		} else {
			JOptionPane.showMessageDialog(this, "Passwords are not the same.", "Error!", 2);
			return;
		}
		String name = textFieldName.getText();
		String surname = textFieldSurname.getText();
		String sexString = (String) comboBoxSex.getSelectedItem();
		int height = Integer.parseInt(textFieldHeight.getText());
		float startWeight = Float.parseFloat(textFieldStartWeight.getText().replace(',', '.'));
		float goalWeight = Float.parseFloat(textFieldGoalWeight.getText().replace(',', '.'));
		int fatPercentage = (Integer) spinnerFatPercentage.getValue();
		String userObjectiveString = (String) comboBoxUserObjective.getSelectedItem();
		String dateRaw = formatTxtFldDate.getText();
		Date date = new Date(); // present date and time given when parsing fails
		try {
			date = dateFormatter.parse(dateRaw);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Shadow userCredentials = new Shadow();
		userCredentials.setLogin(login);
		userCredentials.encryptAndSetPass(passwordRaw);

		User newUser = new User();
		newUser.setName(name);
		newUser.setSurname(surname);
		newUser.setDateOfBirth(date);
		newUser.setSex(UserTools.parseSexStringToChar(sexString));
		newUser.setHeight(height);
		newUser.setStartWeight(startWeight);
		newUser.setActualWeight(startWeight);
		newUser.setGoalWeight(goalWeight);
		newUser.setFatPercentage(fatPercentage);
		newUser.setUserObjective(UserTools.parseUserObjectiveStringToChar(userObjectiveString));

		newUser.setShadow(userCredentials);
		userCredentials.setUser(newUser);

		try {
			DatabaseController db = new DatabaseController();
			db.saveEntityToDatabase(userCredentials);
			this.setVisible(false);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean arePasswordsTheSame(){
		return passwordField.getText().equals(passwordFieldConfirm.getText());
	}

}

package gui.user;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;
import gui.common.DialogMode;
import gui.common.GuiTools;
import gui.common.Translator;
import logic.utils.Encrypter;

public class MaintainUserDialog extends JDialog {

	private static final String MSG_ERROR_PASSWORDS_INCONSISTENT = "Passwords are not the same.";
	private static final long serialVersionUID = 9182720162758099907L;
	private static final String MSG_SAVE_ERROR = "Error occured while saving data to database.";
	private static final String TOOLTIP_WEIGHT_CHANGE = "You can change actual weight in Update Weight Dialog";
	private static final Object MSG_LOGIN_ALREADY_EXISTS = "User with the same login is already registered.";

	protected User userMaintained;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldHeight;
	private JTextField txtFldStartWeight;
	private JTextField txtFldGoalWeight;
	private JComboBox<String> comboBoxSex;
	private JComboBox<String> comboBoxObjective;
	private JFormattedTextField formatTxtFldDateOfBirth;
	private JSpinner spinnerFatPercentage;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JComboBox<String> comboBoxSomatotype;
	private JSlider sliderLifeStyle;
	private DialogMode mode;
	private JTextArea txtrExampleMessageDescribing;

	public MaintainUserDialog() {
		getContentPane().setFocusable(false);
		mode = DialogMode.CREATE;
		userMaintained = new User();
		initializeView();
	}

	public MaintainUserDialog(User userToEdit) {
		mode = DialogMode.EDIT;
		userMaintained = userToEdit;
		initializeView();
	}

	private void initializeView() {
		initializeFrame();
		initializeLayout();
		initializeSwingComponents();
		if (mode == DialogMode.EDIT) {
			fillSwingComponentsWithUserValues();
			lockImmutableFields();
		}
	}

	private void initializeFrame() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(mode + " User");
		setModal(true);
		setBounds(100, 100, 471, 553);
	}

	private void initializeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 70, 179, 49, 59, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
	}

	private void initializeSwingComponents() {
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

		formatTxtFldDateOfBirth = new JFormattedTextField(GuiTools.getDefaultDateMaskFormatter());
		formatTxtFldDateOfBirth.setInputVerifier(GuiTools.getDefaultDateInputVerifier());
		GridBagConstraints gbc_formatTxtFldDate = new GridBagConstraints();
		gbc_formatTxtFldDate.gridwidth = 3;
		gbc_formatTxtFldDate.insets = new Insets(0, 0, 5, 5);
		gbc_formatTxtFldDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_formatTxtFldDate.gridx = 2;
		gbc_formatTxtFldDate.gridy = 7;
		getContentPane().add(formatTxtFldDateOfBirth, gbc_formatTxtFldDate);

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

		txtFldStartWeight = new JTextField();
		GridBagConstraints gbc_textFieldWeight = new GridBagConstraints();
		gbc_textFieldWeight.gridwidth = 3;
		gbc_textFieldWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWeight.gridx = 2;
		gbc_textFieldWeight.gridy = 9;
		getContentPane().add(txtFldStartWeight, gbc_textFieldWeight);
		txtFldStartWeight.setColumns(10);

		JLabel lblGoalWeight = new JLabel("Goal Weight:");
		GridBagConstraints gbc_lblGoalWeight = new GridBagConstraints();
		gbc_lblGoalWeight.anchor = GridBagConstraints.WEST;
		gbc_lblGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoalWeight.gridx = 1;
		gbc_lblGoalWeight.gridy = 10;
		getContentPane().add(lblGoalWeight, gbc_lblGoalWeight);

		txtFldGoalWeight = new JTextField();
		GridBagConstraints gbc_textFieldGoalWeight = new GridBagConstraints();
		gbc_textFieldGoalWeight.gridwidth = 3;
		gbc_textFieldGoalWeight.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGoalWeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGoalWeight.gridx = 2;
		gbc_textFieldGoalWeight.gridy = 10;
		getContentPane().add(txtFldGoalWeight, gbc_textFieldGoalWeight);
		txtFldGoalWeight.setColumns(10);

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

		comboBoxObjective = new JComboBox<String>();
		comboBoxObjective
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Mass Gain", "Reduction", "Strength" }));
		GridBagConstraints gbc_comboBoxUserObjective = new GridBagConstraints();
		gbc_comboBoxUserObjective.gridwidth = 3;
		gbc_comboBoxUserObjective.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUserObjective.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUserObjective.gridx = 2;
		gbc_comboBoxUserObjective.gridy = 12;
		getContentPane().add(comboBoxObjective, gbc_comboBoxUserObjective);

		JLabel lblSomatotype = new JLabel("Somatotype:");
		GridBagConstraints gbc_lblSomatotype = new GridBagConstraints();
		gbc_lblSomatotype.anchor = GridBagConstraints.WEST;
		gbc_lblSomatotype.insets = new Insets(0, 0, 5, 5);
		gbc_lblSomatotype.gridx = 1;
		gbc_lblSomatotype.gridy = 13;
		getContentPane().add(lblSomatotype, gbc_lblSomatotype);

		comboBoxSomatotype = new JComboBox<String>();
		comboBoxSomatotype.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Undefined", "Ectomorphic", "Endomorphic", "Mesomorphic" }));
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

		txtrExampleMessageDescribing = new JTextArea();
		txtrExampleMessageDescribing.setDisabledTextColor(Color.BLACK);
		txtrExampleMessageDescribing.setText("EXAMPLE MESSAGE DESCRIBING LIFESTYLE");
		txtrExampleMessageDescribing.setOpaque(false);
		txtrExampleMessageDescribing.setFont(new Font("Arial", Font.PLAIN, 12));
		txtrExampleMessageDescribing.setForeground(Color.WHITE);
		txtrExampleMessageDescribing.setEditable(false);
		txtrExampleMessageDescribing.setEnabled(false);
		txtrExampleMessageDescribing.setLineWrap(true);
		GridBagConstraints gbc_txtrExampleMessageDescribing = new GridBagConstraints();
		gbc_txtrExampleMessageDescribing.gridwidth = 3;
		gbc_txtrExampleMessageDescribing.insets = new Insets(0, 0, 5, 5);
		gbc_txtrExampleMessageDescribing.fill = GridBagConstraints.BOTH;
		gbc_txtrExampleMessageDescribing.gridx = 2;
		gbc_txtrExampleMessageDescribing.gridy = 15;
		getContentPane().add(txtrExampleMessageDescribing, gbc_txtrExampleMessageDescribing);

		sliderLifeStyle = new JSlider();
		sliderLifeStyle.setSnapToTicks(true);
		sliderLifeStyle.setPaintTicks(true);
		sliderLifeStyle.setPaintLabels(true);
		sliderLifeStyle.setMinimum(1);
		sliderLifeStyle.setMaximum(5);
		sliderLifeStyle.setMajorTickSpacing(1);
		sliderLifeStyle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int choice = sliderLifeStyle.getValue();
				txtrExampleMessageDescribing.setText(Translator.getLifeStyleDescription(choice));
			}
		});
		sliderLifeStyle.setValue(3);
		GridBagConstraints gbc_sliderLifeStyle = new GridBagConstraints();
		gbc_sliderLifeStyle.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderLifeStyle.gridwidth = 3;
		gbc_sliderLifeStyle.insets = new Insets(0, 0, 5, 5);
		gbc_sliderLifeStyle.gridx = 2;
		gbc_sliderLifeStyle.gridy = 14;
		getContentPane().add(sliderLifeStyle, gbc_sliderLifeStyle);

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
					tearDown();
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

	protected void fillSwingComponentsWithUserValues() {
		textFieldLogin.setText(userMaintained.getShadow().getLogin());
		passwordField.setText("*********");
		passwordFieldConfirm.setText("*********");
		textFieldName.setText(userMaintained.getName());
		textFieldSurname.setText(userMaintained.getSurname());
		comboBoxSex.setSelectedItem(Translator.parseSexCharToString(userMaintained.getSex()));
		formatTxtFldDateOfBirth.setText(GuiTools.parseDateToString(userMaintained.getDateOfBirth()));
		textFieldHeight.setText(Integer.toString(userMaintained.getHeight()));
		txtFldStartWeight.setText(Float.toString(userMaintained.getStartWeight()));
		txtFldGoalWeight.setText(Float.toString(userMaintained.getGoalWeight()));
		spinnerFatPercentage.setValue(userMaintained.getFatPercentage());
		comboBoxObjective.setSelectedItem(Translator.parseObjectiveCharToString(userMaintained.getUserObjective()));
		comboBoxSomatotype.setSelectedItem(Translator.parseSomatotypeIntegerToString(userMaintained.getSomatotype()));
		sliderLifeStyle.setValue(userMaintained.getLifeStyle());
	}

	private void lockImmutableFields() {
		textFieldLogin.setEnabled(false);
		passwordField.setEnabled(false);
		passwordFieldConfirm.setEnabled(false);
		txtFldStartWeight.setEnabled(false);
		txtFldStartWeight.setToolTipText(TOOLTIP_WEIGHT_CHANGE);
	}

	protected void saveButtonPressed() {
		setUserPropertiesFromEnteredValues();
		boolean operationSucceeded = false;

		switch (mode) {
		case CREATE:
			if (!loginNotExists()) {
				JOptionPane.showMessageDialog(this, MSG_LOGIN_ALREADY_EXISTS, "Error!", 2);
				return;
			}
			if (!areEnteredPasswordsTheSame()) {
				JOptionPane.showMessageDialog(this, MSG_ERROR_PASSWORDS_INCONSISTENT, "Error!", 2);
				return;
			}
			assignNewUserCredentialsToMaintainedUser();
			operationSucceeded = saveNewUserToDatabase();
			break;
		case EDIT:
			operationSucceeded = updateUserToDatabase();
		}

		if (operationSucceeded)
			tearDown();
		else
			JOptionPane.showMessageDialog(this, MSG_SAVE_ERROR, "Error!", 2);
	}

	private boolean loginNotExists() {
		String login = textFieldLogin.getText();
		return DatabaseController.getEntitiesByParameter(Shadow.class, "login", login).size() == 0;
	}

	private void setUserPropertiesFromEnteredValues() {
		float startWeight = GuiTools.parseFloatCommaOrDotSep(txtFldStartWeight.getText());
		float goalWeight = GuiTools.parseFloatCommaOrDotSep(txtFldGoalWeight.getText());

		userMaintained.setName(textFieldName.getText());
		userMaintained.setSurname(textFieldSurname.getText());
		userMaintained.setDateOfBirth(GuiTools.parseStringToDate(formatTxtFldDateOfBirth.getText()));
		userMaintained.setSex(Translator.parseSexStringToChar((String) comboBoxSex.getSelectedItem()));
		userMaintained.setHeight(Integer.parseInt(textFieldHeight.getText()));
		userMaintained.setStartWeight(startWeight);
		if (userMaintained.getActualWeight() == 0)
			userMaintained.setActualWeight(startWeight);
		userMaintained.setGoalWeight(goalWeight);
		userMaintained.setFatPercentage((Integer) spinnerFatPercentage.getValue());
		userMaintained
				.setUserObjective(Translator.parseObjectiveStringToChar((String) comboBoxObjective.getSelectedItem()));
		userMaintained.setSomatotype(
				Translator.parseSomatotypeStringToInteger((String) comboBoxSomatotype.getSelectedItem()));
		userMaintained.setLifeStyle(sliderLifeStyle.getValue());
	}

	private void assignNewUserCredentialsToMaintainedUser() {
		Shadow userCredentials = createUserCredentialsFromEnteredValues();
		userMaintained.setShadow(userCredentials);
		userCredentials.setUser(userMaintained);
	}

	private Shadow createUserCredentialsFromEnteredValues() {
		String login = textFieldLogin.getText();
		String encryptedPass = Encrypter.encryptWithMD5(passwordField.getText());

		Shadow userCredentials = new Shadow();
		userCredentials.setLogin(login);
		userCredentials.setEncryptedPass(encryptedPass);
		return userCredentials;
	}

	private boolean saveNewUserToDatabase() {
		try {
			DatabaseController.saveEntityToDatabase(userMaintained.getShadow());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean updateUserToDatabase() {
		try {
			DatabaseController.updateEntityToDatabase(userMaintained);
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean areEnteredPasswordsTheSame() {
		return passwordField.getText().equals(passwordFieldConfirm.getText());
	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}
}

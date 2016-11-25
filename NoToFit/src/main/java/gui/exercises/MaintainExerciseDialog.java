package gui.exercises;

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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.controller.DatabaseController;
import database.entities.Exercise;
import gui.common.DialogMode;
import gui.common.Objective;

public class MaintainExerciseDialog extends JDialog {

	private static final long serialVersionUID = -8160642732810147230L;
	protected JTextField txtFldName;
	protected JComboBox<Objective> comboBoxObjective;
	protected JComboBox<DifficultyLevel> comboBoxDifficulty;
	protected JTextArea textAreaDescription;
	protected JCheckBox chckbxEquipmentRequired;
	protected JButton btnCancel;
	protected JButton btnSave;
	private Exercise exerciseMaintained;
	private DialogMode mode;

	public MaintainExerciseDialog() {
		mode = DialogMode.CREATE;
		this.exerciseMaintained = new Exercise();
		initializeSwingComponents();
	}

	public MaintainExerciseDialog(Exercise exerciseToEdit) {
		mode = DialogMode.EDIT;
		this.exerciseMaintained = exerciseToEdit;
		initializeSwingComponents();
		fillSwingFields();
	}

	protected void saveButtonPressed() {
		retrieveExerciseFromFields();
		switch (mode) {
		case CREATE:
			DatabaseController.saveEntityToDatabase(exerciseMaintained);
			break;
		case EDIT:
			DatabaseController.updateEntityToDatabase(exerciseMaintained);
			break;
		default:
			throw new IllegalArgumentException();
		}
		tearDown();
	}

	private void retrieveExerciseFromFields() {
		exerciseMaintained.setName(txtFldName.getText());
		Objective objective = (Objective) comboBoxObjective.getSelectedItem();
		exerciseMaintained.setObjective(objective.getObjectiveChar());
		DifficultyLevel difficultyLevel = (DifficultyLevel) comboBoxDifficulty.getSelectedItem();
		exerciseMaintained.setDifficultyLevel(difficultyLevel.getLevelNumber());
		exerciseMaintained.setEquipmentRequired(chckbxEquipmentRequired.isSelected());
		exerciseMaintained.setDescription(textAreaDescription.getText());
	}

	private void fillSwingFields() {
		txtFldName.setText(exerciseMaintained.getName());
		Objective objective = Objective.get(exerciseMaintained.getObjective());
		comboBoxObjective.setSelectedItem(objective);
		DifficultyLevel difficulty = DifficultyLevel.get(exerciseMaintained.getDifficultyLevel());
		comboBoxDifficulty.setSelectedItem(difficulty);
		chckbxEquipmentRequired.setSelected(exerciseMaintained.isEquipmentRequired());
		textAreaDescription.setText(exerciseMaintained.getDescription());
	}

	private void initializeSwingComponents() {
		setTitle(mode + " Exercise");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 394, 387);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Component topStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_topStrut = new GridBagConstraints();
		gbc_topStrut.insets = new Insets(0, 0, 5, 5);
		gbc_topStrut.gridx = 3;
		gbc_topStrut.gridy = 0;
		getContentPane().add(topStrut, gbc_topStrut);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);

		txtFldName = new JTextField();
		GridBagConstraints gbc_txtFldName = new GridBagConstraints();
		gbc_txtFldName.gridwidth = 3;
		gbc_txtFldName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldName.gridx = 2;
		gbc_txtFldName.gridy = 1;
		getContentPane().add(txtFldName, gbc_txtFldName);
		txtFldName.setColumns(10);

		Component leftStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_leftStrut = new GridBagConstraints();
		gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftStrut.gridx = 0;
		gbc_leftStrut.gridy = 2;
		getContentPane().add(leftStrut, gbc_leftStrut);

		JLabel lblObjective = new JLabel("Objective:");
		GridBagConstraints gbc_lblObjective = new GridBagConstraints();
		gbc_lblObjective.anchor = GridBagConstraints.WEST;
		gbc_lblObjective.insets = new Insets(0, 0, 5, 5);
		gbc_lblObjective.gridx = 1;
		gbc_lblObjective.gridy = 2;
		getContentPane().add(lblObjective, gbc_lblObjective);

		comboBoxObjective = new JComboBox<Objective>();
		comboBoxObjective.setModel(new DefaultComboBoxModel<Objective>(Objective.values()));
		GridBagConstraints gbc_comboBoxObjective = new GridBagConstraints();
		gbc_comboBoxObjective.gridwidth = 3;
		gbc_comboBoxObjective.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxObjective.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxObjective.gridx = 2;
		gbc_comboBoxObjective.gridy = 2;
		getContentPane().add(comboBoxObjective, gbc_comboBoxObjective);

		JLabel lblDifficulty = new JLabel("Difficulty:");
		GridBagConstraints gbc_lblDifficulty = new GridBagConstraints();
		gbc_lblDifficulty.anchor = GridBagConstraints.WEST;
		gbc_lblDifficulty.insets = new Insets(0, 0, 5, 5);
		gbc_lblDifficulty.gridx = 1;
		gbc_lblDifficulty.gridy = 3;
		getContentPane().add(lblDifficulty, gbc_lblDifficulty);

		comboBoxDifficulty = new JComboBox<DifficultyLevel>();
		comboBoxDifficulty.setModel(new DefaultComboBoxModel<DifficultyLevel>(DifficultyLevel.values()));
		GridBagConstraints gbc_comboBoxDifficulty = new GridBagConstraints();
		gbc_comboBoxDifficulty.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDifficulty.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDifficulty.gridx = 2;
		gbc_comboBoxDifficulty.gridy = 3;
		getContentPane().add(comboBoxDifficulty, gbc_comboBoxDifficulty);

		chckbxEquipmentRequired = new JCheckBox("Equipment Needed");
		GridBagConstraints gbc_chckbxEquipmentRequired = new GridBagConstraints();
		gbc_chckbxEquipmentRequired.gridwidth = 2;
		gbc_chckbxEquipmentRequired.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxEquipmentRequired.gridx = 3;
		gbc_chckbxEquipmentRequired.gridy = 3;
		getContentPane().add(chckbxEquipmentRequired, gbc_chckbxEquipmentRequired);

		JLabel lblDescription = new JLabel("Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 4;
		getContentPane().add(lblDescription, gbc_lblDescription);

		textAreaDescription = new JTextArea();
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
		gbc_textAreaDescription.gridwidth = 3;
		gbc_textAreaDescription.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescription.gridx = 2;
		gbc_textAreaDescription.gridy = 4;
		getContentPane().add(textAreaDescription, gbc_textAreaDescription);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveButtonPressed();
			}
		});
		
		Component glue = Box.createGlue();
		GridBagConstraints gbc_glue = new GridBagConstraints();
		gbc_glue.insets = new Insets(0, 0, 5, 5);
		gbc_glue.gridx = 5;
		gbc_glue.gridy = 5;
		getContentPane().add(glue, gbc_glue);

		Component rightStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_rightStrut = new GridBagConstraints();
		gbc_rightStrut.anchor = GridBagConstraints.EAST;
		gbc_rightStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightStrut.gridx = 6;
		gbc_rightStrut.gridy = 5;
		getContentPane().add(rightStrut, gbc_rightStrut);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 5;
		getContentPane().add(btnSave, gbc_btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tearDown();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 5;
		getContentPane().add(btnCancel, gbc_btnCancel);

		Component bottomStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
		gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
		gbc_bottomStrut.gridx = 3;
		gbc_bottomStrut.gridy = 6;
		getContentPane().add(bottomStrut, gbc_bottomStrut);

	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}
}

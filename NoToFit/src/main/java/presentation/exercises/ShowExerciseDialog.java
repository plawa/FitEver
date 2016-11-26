package presentation.exercises;

import database.entities.Exercise;

public class ShowExerciseDialog extends MaintainExerciseDialog {
	private static final long serialVersionUID = 6725073572860426580L;

	public ShowExerciseDialog(Exercise exerciseToShow) {
		super(exerciseToShow);
		setTitle("ExerciseOverview");
		lockFields();
	}

	private void lockFields() {
		txtFldName.setEditable(false);
		comboBoxObjective.setEnabled(false);
		comboBoxDifficulty.setEnabled(false);
		chckbxEquipmentRequired.setEnabled(false);
		textAreaDescription.setEditable(false);
		btnSave.setVisible(false);
		btnCancel.setText("Close");
	}
	
}

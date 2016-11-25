package gui.meals;

import database.entities.Meal;

public class ShowMealDialog extends MaintainMealDialog {

	private static final long serialVersionUID = 2192525211247612573L;

	public ShowMealDialog(Meal mealToShow) {
		super(mealToShow);
		setTitle("Meal Overview");
		lockFields();
	}

	private void lockFields() {
		spinnerCarbohydratesPercentage.setEnabled(false);
		spinnerFatPercentage.setEnabled(false);
		spinnerProteinPercentage.setEnabled(false);
		textFieldGrammage.setEditable(false);
		textFieldName.setEditable(false);
		comboBoxMealType.setEnabled(false);
		comboBoxObjective.setEnabled(false);
		okButton.setVisible(false);
		cancelButton.setText("Close");
	}
}

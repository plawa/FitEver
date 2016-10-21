package gui.meals;

import javax.swing.JOptionPane;

import database.controller.DatabaseController;
import database.entities.Meal;
import gui.common.Translator;

public class EditMealDialog extends AddMealDialog {
	private static final long serialVersionUID = 4604085912903033345L;

	private static final String MSG_CONNECTION_LOST = "You have probably lost connection to database.";
	private Meal mealToEdit;
	
	public EditMealDialog() {
		super();
	}
	
	public EditMealDialog(Meal mealToMaintain){
		this();
		mealToEdit = mealToMaintain;
		initializeFields();
	}
	
	protected void initializeFields(){
		this.textFieldName.setText(mealToEdit.getName());
		this.comboBoxMealType.setSelectedItem(Translator.parseMealTypeCharToString(mealToEdit.getType()));
		this.textFieldGrammage.setText(Integer.toString(mealToEdit.getGramature()));
		this.spinnerCarbohydratesPercentage.setValue(mealToEdit.getCarbohydratesPercentage());
		this.spinnerProteinPercentage.setValue(mealToEdit.getProteinPercentage());
		this.spinnerFatPercentage.setValue(mealToEdit.getFatPercentage());
		this.comboBoxObjective.setSelectedItem(Translator.parseObjectiveCharToString(mealToEdit.getObjective()));
	}
	 
	protected void proceedButtonPressed(){
		retrieveMealAttributesFromFields(mealToEdit);
		try {
			DatabaseController.updateEntityToDatabase(mealToEdit);
			tearDown();
		} catch (RuntimeException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, MSG_CONNECTION_LOST, "Error!", 0);
		}
	}

}

package gui.meals;

import javax.swing.JOptionPane;

import database.controller.DatabaseController;
import database.entities.Meal;

public class EditMealDialog extends AddMealDialog {

	private static final long serialVersionUID = 4604085912903033345L;
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
		this.textFieldGrammage.setText(Integer.toString(mealToEdit.getGramature()));
	}
	
	protected void proceedButtonPressed(){
		setMealAttributesFromFields(mealToEdit);
		try {
			new DatabaseController().updateEntityToDatabase(mealToEdit);
			tearDown();
		} catch (RuntimeException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "You have probably lost connection to database.", "Error!", 0);
		}
	}

}

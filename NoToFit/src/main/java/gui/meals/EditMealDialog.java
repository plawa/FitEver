package gui.meals;

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
	}
	
	protected void initializeFields(){
		this.textFieldName.setText(mealToEdit.getName());
		this.textFieldGrammage.setText(Integer.toString(mealToEdit.getGramature()));
	}
	

}

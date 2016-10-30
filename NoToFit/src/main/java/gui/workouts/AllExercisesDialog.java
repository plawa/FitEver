package gui.workouts;

import database.controller.DatabaseController;
import database.entities.Exercise;
import gui.meals.AllMealsDialog;

public class AllExercisesDialog extends AllMealsDialog {
	
	protected static final String DLG_TITLE = "Exercises Library";
	private static final long serialVersionUID = -6051480256970284306L;
	private ExercisesTableModel tableModel;

	public AllExercisesDialog() {
		super();
	}

	@Override
	protected void refreshTable() {
		tableModel = new ExercisesTableModel(DatabaseController.getAll(Exercise.class));
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
	}

	@Override
	protected void addButtonPressed() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void editButtonPressed() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void deleteButtonPressed() {
		throw new UnsupportedOperationException();
	}

}

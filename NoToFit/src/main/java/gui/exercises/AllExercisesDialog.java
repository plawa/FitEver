package gui.exercises;

import javax.swing.JOptionPane;

import database.controller.DatabaseController;
import database.entities.Exercise;
import gui.meals.AllMealsDialog;

public class AllExercisesDialog extends AllMealsDialog {

	private static final String TABLE_TOOLTIP = "Open to see more details";
	protected static final String DLG_TITLE = "Exercises Library";
	private static final long serialVersionUID = -6051480256970284306L;
	private ExercisesTableModel tableModel;

	public AllExercisesDialog() {
		super();
		setModal(false);
		setSwingProperties();
	}

	@Override
	protected void refreshTable() {
		tableModel = new ExercisesTableModel(DatabaseController.getAll(Exercise.class));
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(130);
	}

	@Override
	protected void addButtonPressed() {
		MaintainExerciseDialog createExerciseView = new MaintainExerciseDialog();
		createExerciseView.setLocationRelativeTo(this);
		createExerciseView.setVisible(true);
		refreshTable();
	}

	@Override
	protected void editButtonPressed() {
		Exercise selectedExercise = retrieveSelectedExercise();
		MaintainExerciseDialog editExerciseDialog = new MaintainExerciseDialog(selectedExercise);
		editExerciseDialog.setLocationRelativeTo(this);
		editExerciseDialog.setVisible(true);
		refreshTable();

	}

	@Override
	protected void deleteButtonPressed() {
		Exercise selectedExercise = retrieveSelectedExercise();
		int confirmationInput = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		if (confirmationInput == JOptionPane.YES_OPTION) {
			DatabaseController.deleteEntityFromDatabase(selectedExercise);
			refreshTable();
		}
	}

	private Exercise retrieveSelectedExercise() {
		return tableModel.getExerciseAt(table.getSelectedRow());
	}

	private void setSwingProperties() {
		setTitle(DLG_TITLE);
		table.setToolTipText(TABLE_TOOLTIP);
	}

}

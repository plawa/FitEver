package presentation.workouts;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import database.entities.Exercise;
import presentation.exercises.ExercisesTableModel;
import presentation.exercises.ShowExerciseDialog;

public class WorkoutDayTable extends JTable {

	private static final long serialVersionUID = 8043252140730489978L;
	private static final int EXERCISE_NAME_COLUMN_WIDTH = 200;
	private ExercisesTableModel tableModel;

	public WorkoutDayTable(List<Exercise> exercisesList) {
		super();
		setFillsViewportHeight(true);
		tableModel = new ExercisesTableModel(exercisesList);
		setModel(tableModel);
		getColumnModel().getColumn(0).setPreferredWidth(EXERCISE_NAME_COLUMN_WIDTH);
		addMouseListener();		
	}
	
	private void addMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					Exercise chosenExercise = tableModel.getExerciseAt(getSelectedRow());
					ShowExerciseDialog exerciseView = new ShowExerciseDialog(chosenExercise);
					exerciseView.setLocationRelativeTo(WorkoutDayTable.this);
					exerciseView.setVisible(true);
				}
			}
		});
	}

}

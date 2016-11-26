package presentation.workouts;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.entities.Workout;
import logic.enums.Objective;
import presentation.common.GuiTools;

class WorkoutsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8510801416973305236L;
	private List<Workout> workouts;
	final private String[] columnNames = { "Workout Name", "Objective", "Valid From", "Valid To" };

	
	public WorkoutsTableModel(List<Workout> workoutsList) {
		super();
		workouts = workoutsList;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return workouts.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Workout workout = workouts.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return workout.getName();
		case 1:
			return Objective.get(workout.getObjective());			
		case 2:
			return GuiTools.parseDateToString(workout.getValidFrom());
		case 3:
			return GuiTools.parseDateToString(workout.getValidTo());
		default:
			return "Error!";
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex >= 2)
			return Integer.class;
		return String.class; 
	}
	
	public Workout getWorkoutAt(int rowIndex){
		return workouts.get(rowIndex);
	}
	

}

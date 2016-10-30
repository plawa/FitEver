package gui.workouts;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.entities.Workout;

class WorkoutsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8510801416973305236L;
	private List<Workout> workouts;
	final private String[] columnNames = {  };
	
	
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
		// TODO Auto-generated method stub
		return null;
	}

}

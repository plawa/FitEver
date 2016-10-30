package gui.workouts;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.entities.Exercise;

public class ExercisesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1156719073152150069L;
	private List<Exercise> exercises;
	final String[] columnNames = { "Name", "Objective", "Difficulty", "Equipment Required", "Description" };

	public ExercisesTableModel(List<Exercise> exercises) {
		this.exercises = exercises;
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
		return exercises.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Exercise exercise = exercises.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return exercise.getName();
		case 1:
			return exercise.getObjective();
		case 2:
			return exercise.getDifficultyLevel();
		case 3:
			return exercise.isEquipmentRequired();
		case 4:
			return exercise.getDescription();
		default:
			return "Error!";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 3)
			return Boolean.class;
		return String.class;
	}

}

package gui.meals;

import java.util.List;

import database.entities.Meal;
import gui.common.Translator;

class MealsTableModel extends javax.swing.table.AbstractTableModel {

	private static final long serialVersionUID = 7470997421970268078L;
	private List<Meal> meals;
	final String[] columnNames = { "Name", "Grammage (g)", "Carbohydrates (%)", "Protein (%)", "Fat (%)" };
	
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return meals.size();
	}

	public MealsTableModel(List<Meal> meals) {
		super();
		this.meals = meals;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Meal meal = meals.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return meal.getName();
		case 1:
			return meal.getGramature();
		case 2:
			return meal.getCarbohydratesPercentage();
		case 3:
			return meal.getProteinPercentage();
		case 4:
			return meal.getFatPercentage();
		case 5:
			return Translator.parseObjectiveCharToString(meal.getObjective());
		default:
			return "Error";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		case 4:
			return Integer.class;
		case 5:
			return String.class;
		default:
			return String.class;
		}
	}
	
	public Meal getMealAt(int rowIndex){
		return meals.get(rowIndex);
	}

}

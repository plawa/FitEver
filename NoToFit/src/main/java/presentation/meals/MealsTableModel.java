package presentation.meals;

import java.util.List;

import database.entities.Meal;
import presentation.common.Translator;

public class MealsTableModel extends javax.swing.table.AbstractTableModel {

	private static final long serialVersionUID = 7470997421970268078L;
	private List<Meal> meals;
	private static final String[] columnNames = { "Name", "Meal Type", "Grammage (g)", "Carbo (%)", "Protein (%)", "Fat (%)" };

	public MealsTableModel(List<Meal> meals) {
		super();
		this.meals = meals;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return meals.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Meal meal = getMealAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return meal.getName();
		case 1:
			return Translator.parseMealTypeCharToString(meal.getType());
		case 2:
			return meal.getGrammage();
		case 3:
			return meal.getCarbohydratesPercentage();
		case 4:
			return meal.getProteinPercentage();
		case 5:
			return meal.getFatPercentage();
		case 6:
			return Translator.parseObjectiveCharToString(meal.getObjective());
		default:
			return "Error";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex >= 2 && columnIndex <= 5){
			return Integer.class;
		} else {
			return String.class;
		}
	}

	public Meal getMealAt(int rowIndex) {
		return meals.get(rowIndex);
	}

}

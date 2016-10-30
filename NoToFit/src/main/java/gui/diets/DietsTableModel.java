package gui.diets;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.entities.Diet;
import gui.common.GuiTools;

class DietsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7436925452072118635L;
	private List<Diet> diets;
	final private String[] columnNames = { "Diet Name", "kCals/day", "Valid From", "Valid To" };

	public DietsTableModel(List<Diet> diets) {
		super();
		this.diets = diets;
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return diets.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Diet diet = diets.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return diet.getName();
		case 1:
			return diet.getDailyRequirement();
		case 2:
			return GuiTools.parseDateToString(diet.getValidFrom());
		case 3:
			return GuiTools.parseDateToString(diet.getValidTo());
		default:
			return "Error";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex >= 1)
			return Integer.class;
		return String.class; 
	}

	public Diet getDietAt(int rowNumber) {
		return diets.get(rowNumber);
	}

}

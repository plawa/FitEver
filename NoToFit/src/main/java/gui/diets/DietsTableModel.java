package gui.diets;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.entities.Diet;

class DietsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7436925452072118635L;
	private List<Diet> diets;
	final String[] columnNames = { "Diet Name", "Diet Type", "Valid From", "Valid To" };
	
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
			return diet.getObjectiveString();
		case 2:
			return diet.getValidFrom().toString();
		case 3:
			return diet.getValidTo().toString();
		default:
			return "Error";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		/*switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		default:
			return String.class;
		}*/
		return String.class; //only String-type table
	}
	
	public Diet getDietAt(int rowNumber){
		return diets.get(rowNumber);		
	}

}

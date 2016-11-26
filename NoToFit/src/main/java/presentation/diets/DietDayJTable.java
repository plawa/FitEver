package presentation.diets;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import database.entities.Meal;
import presentation.meals.MealsTableModel;
import presentation.meals.ShowMealDialog;

public class DietDayJTable extends JTable {

	private static final long serialVersionUID = 7886074277844124153L;
	private MealsTableModel model;

	public DietDayJTable(List<Meal> dayMeals) {
		super();
		setFillsViewportHeight(true);
		model = new MealsTableModel(dayMeals);
		setModel(model);
		getColumnModel().getColumn(0).setPreferredWidth(220);
		addCustomListener();
	}

	private void addCustomListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					Meal chosenMeal = model.getMealAt(getSelectedRow());
					ShowMealDialog mealView = new ShowMealDialog(chosenMeal);
					mealView.setLocationRelativeTo(DietDayJTable.this);
					mealView.setVisible(true);
				}
			}
		});
	}

}

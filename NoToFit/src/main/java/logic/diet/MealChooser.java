package logic.diet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Meal;
import logic.entitytools.MealTools;

public class MealChooser {

	private static List<Meal> mealsLibrary;

	static{
		updateLibrary();
	}

	private static void updateLibrary() {
		mealsLibrary = new DatabaseController().getAll(Meal.class);
	}

	public static Set<Meal> chooseDayMealSet(int caloriesPerDay, int mealsPerDay) {
		// Set<Meal> mealsAvailable = new HashSet<>(mealsLibrary);
		Set<Meal> dayMealSet = new HashSet<>();
		int caloriesCount = 0;
		for (Meal meal : mealsLibrary) {
			caloriesCount += MealTools.countMealCalories(meal);
			if (caloriesCount > caloriesPerDay)
				break;
			dayMealSet.add(meal);
		}
		return dayMealSet;
	}

	public static void main(String[] args) {
		updateLibrary();
		Set<Meal> testMealList = chooseDayMealSet(2000, 5);
		for (Meal mealSelected : testMealList) {
			System.out.println(mealSelected.getName());
		}
	}

}

package logic.diet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Meal;
import logic.entitytools.MealTools;

public class MealChooser {

	private static List<Meal> mainMealsLibrary;
	private static List<Meal> breakfastsLibrary;
	private static List<Meal> suppersLibrary;

	static {
		updateLibraries();
	}

	private static void updateLibraries() {
		breakfastsLibrary = DatabaseController.getEntityByParameter(Meal.class, "type", "b");
		mainMealsLibrary = DatabaseController.getEntityByParameter(Meal.class, "type", "m");
		suppersLibrary = DatabaseController.getEntityByParameter(Meal.class, "type", "s");
	}

	public static Set<Meal> chooseDayMealSet(int caloriesPerDay, int mealsPerDay) {
		Set<Meal> dayMealSet = new HashSet<Meal>();
		int dietCalories = 0;
		
		for (Meal meal : mainMealsLibrary) {
			int mealCalories = MealTools.countMealCalories(meal);
			dietCalories += mealCalories;
			if (dietCalories > caloriesPerDay) {
				dietCalories -= mealCalories;
				continue;
			}
			dayMealSet.add(meal);
		}
		return dayMealSet;
	}
	
	private static Set<Meal> chooseBreakfastMealSet(int breakfastCalories, int breakfastMealCount){

		Set<Meal> breakfastsChosen = new HashSet<Meal>();
		
		return breakfastsChosen;
	}
	
	private static Set<Meal> chooseOptimalSubset(int itemsCount, int value){
		
		
		
		
		return null;
		
	}

	public static void main(String[] args) {
		updateLibraries();
		Set<Meal> testMealList = chooseDayMealSet(3000, 5);
		for (Meal mealSelected : testMealList) {
			System.out.println(mealSelected.getName());
		}
	}

}

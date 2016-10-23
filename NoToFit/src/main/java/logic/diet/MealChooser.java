package logic.diet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Meal;
import logic.entitytools.MealTools;

public class MealChooser {

	private static List<Meal> mainMealsLibrary;
	private static List<Meal> breakfastsLibrary;
	private static List<Meal> suppersLibrary;
	private final static float caloriesDifferenceToleranceFactor = 0.2f;

	static {
		updateLibraries();
	}

	private static void updateLibraries() {
		breakfastsLibrary = DatabaseController.getEntitiesByParameter(Meal.class, "type", "b");
		mainMealsLibrary = DatabaseController.getEntitiesByParameter(Meal.class, "type", "m");
		suppersLibrary = DatabaseController.getEntitiesByParameter(Meal.class, "type", "s");
	}

	
	public static Set<Meal> chooseDayMealSet(DietDayConfiguration preferences){
		int breakfastMealsCount = preferences.getBreakfastMealsCount();
		int mainMealsCount = preferences.getMainDishMealsCount();
		int supperMealsCount = preferences.getSupperMealsCount();
		
		int dailyCalorieReq = preferences.getDailyCaloriesReq();
				
		int breakfastCalories = Math.round(dailyCalorieReq * preferences.getBreakfastCaloriesFactor());
		int mainDishCalories = Math.round(dailyCalorieReq * preferences.getMainDishCaloriesFactor());
		int supperCalories = Math.round(dailyCalorieReq * preferences.getSupperCaloriesFactor());
		
		Set<Meal> newDayMealSet = new HashSet<>();
		newDayMealSet.addAll(chooseBreakfast(breakfastMealsCount, breakfastCalories));
		newDayMealSet.addAll(chooseMainDish(mainMealsCount, mainDishCalories));
		newDayMealSet.addAll(chooseSupper(supperMealsCount, supperCalories));
		
		return newDayMealSet;
	}
	
	private static List<Meal> chooseBreakfast(int mealCount, int breakfastCalories) {
		return chooseMealSubset(breakfastsLibrary, mealCount, breakfastCalories);
	}
	
	private static List<Meal> chooseMainDish(int mealCount, int mainMealCalories) {
		return chooseMealSubset(mainMealsLibrary, mealCount, mainMealCalories);
	}
	
	private static List<Meal> chooseSupper(int mealCount, int supperCalories) {
		return chooseMealSubset(suppersLibrary, mealCount, supperCalories);
	}

	private static List<Meal> chooseMealSubset(List<Meal> mealsSourceLib, int expectedMealsCount, int calories) {
		int avgCaloriesPerMeal = Math.round(calories/expectedMealsCount);
		int mealCaloriesTolerance = Math.round(caloriesDifferenceToleranceFactor * avgCaloriesPerMeal);
		
		final int mealCaloriesLowerBound = avgCaloriesPerMeal - mealCaloriesTolerance;
		final int mealCaloriesUpperBound = avgCaloriesPerMeal + mealCaloriesTolerance;
		
		List<Meal> chosenMealSet = new ArrayList<>();
		
		while (chosenMealSet.size() < expectedMealsCount) {
			Meal meal = getRandomItem(mealsSourceLib);
			int mealCalories = MealTools.countMealCalories(meal);
			if (mealCalories < mealCaloriesUpperBound && mealCalories > mealCaloriesLowerBound)
				chosenMealSet.add(meal);
		}
		
		return chosenMealSet;
	}

	private static Meal getRandomItem(List<Meal> mealsLib){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(mealsLib.size());
		return mealsLib.get(index);
	}
	
	public static void main(String[] args) {

		PrintStream o = System.out;
		
		for(Meal m : chooseMealSubset(mainMealsLibrary, 5, 1500)){
			o.println(m);
		}
		
		
	}

}

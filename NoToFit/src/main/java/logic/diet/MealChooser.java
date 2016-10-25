package logic.diet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Meal;
import logic.entitytools.MealTools;

public class MealChooser {

	private List<Meal> breakfastsLibrary;
	private List<Meal> mainMealsLibrary;
	private List<Meal> suppersLibrary;
	private final float caloriesDifferenceToleranceFactor;


	public MealChooser() {
		this(0.1f);
	}
	
	public MealChooser(final float caloriesDifferenceToleranceFactor){
		this.caloriesDifferenceToleranceFactor = caloriesDifferenceToleranceFactor;
		initializeCleanLibraries();
	}
	

	private void initializeCleanLibraries() {
		breakfastsLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", "b"));
		mainMealsLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", "m"));
		suppersLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", "s"));
	}

	
	public Set<Meal> chooseDayMealSet(DietDayConfiguration preferences){
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
	
	private Set<Meal> chooseBreakfast(int mealCount, int breakfastCalories) {
		return chooseMealSubset(breakfastsLibrary, mealCount, breakfastCalories);
	}
	
	private Set<Meal> chooseMainDish(int mealCount, int mainMealCalories) {
		return chooseMealSubset(mainMealsLibrary, mealCount, mainMealCalories);
	}
	
	private Set<Meal> chooseSupper(int mealCount, int supperCalories) {
		return chooseMealSubset(suppersLibrary, mealCount, supperCalories);
	}

	private Set<Meal> chooseMealSubset(List<Meal> mealsSourceLib, int expectedMealsCount, int calories) {
		int avgCaloriesPerMeal = Math.round(calories/expectedMealsCount);
		int mealCaloriesTolerance = Math.round(caloriesDifferenceToleranceFactor * avgCaloriesPerMeal);
		
		final int mealCaloriesLowerBound = avgCaloriesPerMeal - mealCaloriesTolerance;
		final int mealCaloriesUpperBound = avgCaloriesPerMeal + mealCaloriesTolerance;
		
		Set<Meal> chosenMealSet = new HashSet<>();
		
		while (chosenMealSet.size() < expectedMealsCount && mealsSourceLib.size() > 0) {
			Meal meal = popRandomItem(mealsSourceLib);
			int mealCalories = MealTools.countMealCalories(meal);
			if ( mealCalories < mealCaloriesUpperBound && mealCalories > mealCaloriesLowerBound )
				chosenMealSet.add(meal);
		}
		return chosenMealSet;
	}

	private Meal popRandomItem(List<Meal> mealsLib){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(mealsLib.size());
		Meal randomMeal = mealsLib.get(index);
		mealsLib.remove(index);
		return randomMeal;
	}

}

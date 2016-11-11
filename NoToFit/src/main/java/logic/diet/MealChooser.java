package logic.diet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Preconditions;

import database.controller.DatabaseController;
import database.entities.Diet;
import database.entities.Dietday;
import database.entities.Meal;
import logic.entitytools.MealTools;

public class MealChooser {

	private static final String MSG_TOO_LESS_MEALS = "There is too less meals in library!";
	private static List<Meal> breakfastsLibrary;
	private static List<Meal> mainMealsLibrary;
	private static List<Meal> suppersLibrary;
	private final float caloriesDifferenceToleranceFactor;
	
	static{
		updateLibraries();
	}
	
	public static void updateLibraries() {
		breakfastsLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", "b"));
		mainMealsLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", "m"));
		suppersLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", "s"));
	}

	public MealChooser() {
		this(0.1f);
	}
	
	public MealChooser(final float caloriesToleranceFactor){
		this.caloriesDifferenceToleranceFactor = caloriesToleranceFactor;
	}
	
	public static boolean isMealsLibraryBigEnough(DietDayConfiguration dayConfig){
		boolean breakfastsOK = dayConfig.getBreakfastMealsCount() <= breakfastsLibrary.size();
		boolean mainMealsOK = dayConfig.getMainDishMealsCount() <= mainMealsLibrary.size();
		boolean supperOK = dayConfig.getSupperMealsCount() <= suppersLibrary.size();
		
		return breakfastsOK && mainMealsOK && supperOK;
	}
	
	public Dietday generateDietDay(DietDayConfiguration preferences, Diet parentDiet){
		Preconditions.checkArgument(isMealsLibraryBigEnough(preferences), MSG_TOO_LESS_MEALS);
		
		Dietday newDietDay = new Dietday();
		
		int breakfastMealsCount = preferences.getBreakfastMealsCount();
		int mainMealsCount = preferences.getMainDishMealsCount();
		int supperMealsCount = preferences.getSupperMealsCount();
		
		int dailyCaloriesReq = preferences.getDailyCaloriesReq();
				
		int breakfastCalories = Math.round(dailyCaloriesReq * preferences.getBreakfastCaloriesFactor());
		int mainDishCalories = Math.round(dailyCaloriesReq * preferences.getMainDishCaloriesFactor());
		int supperCalories = Math.round(dailyCaloriesReq * preferences.getSupperCaloriesFactor());
		
		Set<Meal> newDayMealSet = new HashSet<>();
		newDayMealSet.addAll(chooseBreakfast(breakfastMealsCount, breakfastCalories));
		newDayMealSet.addAll(chooseMainDish(mainMealsCount, mainDishCalories));
		newDayMealSet.addAll(chooseSupper(supperMealsCount, supperCalories));
		
		newDietDay.setMeals(newDayMealSet);
		newDietDay.setDiet(parentDiet);
		newDietDay.setDate(preferences.getDate());
		
		return newDietDay;
	}
	
	private Set<Meal> chooseBreakfast(int mealCount, int breakfastCalories) {
		return chooseMealSubset(new ArrayList<Meal>(breakfastsLibrary), mealCount, breakfastCalories);
	}
	
	private Set<Meal> chooseMainDish(int mealCount, int mainMealCalories) {
		return chooseMealSubset(new ArrayList<Meal>(mainMealsLibrary), mealCount, mainMealCalories);
	}
	
	private Set<Meal> chooseSupper(int mealCount, int supperCalories) {
		return chooseMealSubset(new ArrayList<Meal>(suppersLibrary), mealCount, supperCalories);
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

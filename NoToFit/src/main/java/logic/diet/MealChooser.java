package logic.diet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import database.controller.DatabaseController;
import database.entities.Diet;
import database.entities.Dietday;
import database.entities.Meal;
import database.tools.MealTools;

public class MealChooser {
	private static final String MSG_TOO_LESS_MEALS = "There is too less meals in library!";

	private static final float DEFAULT_DIFFERENCE_FACTOR = 0.1f;

	private static List<Meal> breakfastsLibrary;
	private static List<Meal> mainMealsLibrary;
	private static List<Meal> suppersLibrary;
	private final float caloriesDifferenceToleranceFactor;
	private Random randomGenerator = new Random();

	public MealChooser() {
		this(DEFAULT_DIFFERENCE_FACTOR);
	}

	public MealChooser(final float caloriesToleranceFactor) {
		if (!isMealsLibraryUpToDate()) {
			updateLibraries();
		}
		this.caloriesDifferenceToleranceFactor = caloriesToleranceFactor;
	}

	public static boolean isMealsLibraryBigEnough(DietDayConfiguration dayConfig) {
		boolean breakfastsOK = dayConfig.getBreakfastMealsCount() <= breakfastsLibrary.size();
		boolean mainMealsOK = dayConfig.getMainDishMealsCount() <= mainMealsLibrary.size();
		boolean supperOK = dayConfig.getSupperMealsCount() <= suppersLibrary.size();

		return breakfastsOK && mainMealsOK && supperOK;
	}

	public Dietday generateDietDay(DietDayConfiguration preferences, Diet parentDiet) {
		Preconditions.checkArgument(isMealsLibraryBigEnough(preferences), MSG_TOO_LESS_MEALS);

		Set<Meal> newDayMealSet = new HashSet<>();
		int dailyCaloriesReq = preferences.getDailyCaloriesReq();
		newDayMealSet.addAll(chooseBreakfast(preferences.getBreakfastMealsCount(),
				calculateBreakfastCalories(preferences, dailyCaloriesReq)));
		newDayMealSet.addAll(chooseMainDish(preferences.getMainDishMealsCount(),
				calculateMainDishCalories(preferences, dailyCaloriesReq)));
		newDayMealSet.addAll(chooseSupper(preferences.getSupperMealsCount(),
				calculateSupperCalories(preferences, dailyCaloriesReq)));

		Dietday newDietDay = new Dietday();
		newDietDay.setMeals(newDayMealSet);
		newDietDay.setDiet(parentDiet);
		newDietDay.setDate(preferences.getDate());
		return newDietDay;
	}

	private int calculateSupperCalories(DietDayConfiguration preferences, int dailyCaloriesReq) {
		return Math.round(dailyCaloriesReq * preferences.getSupperCaloriesFactor());
	}

	private int calculateMainDishCalories(DietDayConfiguration preferences, int dailyCaloriesReq) {
		return Math.round(dailyCaloriesReq * preferences.getMainDishCaloriesFactor());
	}

	private int calculateBreakfastCalories(DietDayConfiguration preferences, int dailyCaloriesReq) {
		return Math.round(dailyCaloriesReq * preferences.getBreakfastCaloriesFactor());
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
		int avgCaloriesPerMeal = Math.round(calories / expectedMealsCount);
		int mealCaloriesTolerance = Math.round(caloriesDifferenceToleranceFactor * avgCaloriesPerMeal);

		final int mealCaloriesLowerBound = avgCaloriesPerMeal - mealCaloriesTolerance;
		final int mealCaloriesUpperBound = avgCaloriesPerMeal + mealCaloriesTolerance;

		Set<Meal> chosenMealSet = Sets.newHashSet();
		while (chosenMealSet.size() < expectedMealsCount && mealsSourceLib.size() > 0) {
			Meal meal = getAndRemoveRandomItemFromList(mealsSourceLib);
			int mealCalories = MealTools.countMealCalories(meal); 
			if (mealCalories < mealCaloriesUpperBound && mealCalories > mealCaloriesLowerBound)
				chosenMealSet.add(meal);
		}
		return chosenMealSet;
	}

	private Meal getAndRemoveRandomItemFromList(List<Meal> mealsLib) {
		int randomIndex = randomGenerator.nextInt(mealsLib.size());
		Meal randomMeal = mealsLib.get(randomIndex);
		mealsLib.remove(randomIndex);
		return randomMeal;
	}

	private boolean isMealsLibraryUpToDate() {
		if (breakfastsLibrary == null || mainMealsLibrary == null || suppersLibrary == null) {
			return false;
		}
		int mealsInDBCount = (int) DatabaseController.getRowCount(Meal.class);
		return breakfastsLibrary.size() + mainMealsLibrary.size() + suppersLibrary.size() == mealsInDBCount;
	}

	private void updateLibraries() {
		breakfastsLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", 'b'));
		mainMealsLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", 'm'));
		suppersLibrary = new ArrayList<>(DatabaseController.getEntitiesByParameter(Meal.class, "type", 's'));
	}

}

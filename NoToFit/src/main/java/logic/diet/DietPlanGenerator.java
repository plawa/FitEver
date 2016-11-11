package logic.diet;

import java.util.HashSet;
import java.util.Set;

import database.entities.Diet;
import database.entities.Meal;

public class DietPlanGenerator {

	private final static float caloriesToleranceStep = 0.05f;

	public static Diet generateDiet(DietGenerationPreferences dietConf) {
		Diet generatedDiet = null;
		DietDayConfiguration dayConfiguration = dietConf.getDayMealsPref();
		if (isMealsLibraryBigEnough(dayConfiguration)) {
			generatedDiet = initializeDietPrototype(dietConf);
			int dietDaysCount = dietConf.getDietPeriodInDays();
			int expectedMealsCount = dietDaysCount * dayConfiguration.getMealsPerDayCount();

			Set<Meal> dietMeals;
			MealChooser.updateLibraries();
			float caloriesToleranceFactor = 0.05f;
			do {
				dietMeals = new HashSet<>();
				MealChooser mealChooser = new MealChooser(caloriesToleranceFactor);
				for (int i = 0; i < dietDaysCount; i++) {
					Set<Meal> dietDay = mealChooser.chooseDayMealSet(dayConfiguration);
					dietMeals.addAll(dietDay);
				}
				caloriesToleranceFactor += caloriesToleranceStep;
			} while (dietMeals.size() < expectedMealsCount);
			generatedDiet.setMeals(dietMeals);
		}
		return generatedDiet;
	}

	private static boolean isMealsLibraryBigEnough(DietDayConfiguration dayConfig) {
		return MealChooser.isMealsLibraryBigEnough(dayConfig);
	}

	private static Diet initializeDietPrototype(DietGenerationPreferences dietPreferences) {
		Diet newDiet = new Diet();
		newDiet.setUser(dietPreferences.getUser());
		newDiet.setName(dietPreferences.getDietName());
		newDiet.setValidFrom(dietPreferences.getFirstDietDay());
		newDiet.setValidTo(dietPreferences.getLastDietDay());
		newDiet.setDietDayProperties(dietPreferences.getDayMealsPref());
		return newDiet;
	}
}

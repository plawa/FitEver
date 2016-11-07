package logic.diet;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import database.entities.Diet;
import database.entities.Meal;

public class DietPlanGenerator {

	private final static int ONE_DAY_IN_MILISECONDS = 86400000;
	private final static float toleranceStep = 0.05f;
	

	public static Diet generateDiet(DietGenerationPreferences dietConf) {
		Diet generatedDiet = null;
		DietDayConfiguration dayConfiguration = dietConf.getDayMealsPref();
		if (isMealsLibraryBigEnough(dayConfiguration)) {
			generatedDiet = generateDietPrototype(dietConf);
			int dietDays = dietConf.getDietPeriodDays();
			int expectedMealsCount = dietDays * dayConfiguration.getDayMealsCount();

			Set<Meal> dietMeals;
			MealChooser.updateLibraries();
			float caloriesToleranceFactor = 0.05f;
			do {
				dietMeals = new HashSet<>();
				MealChooser mealChooser = new MealChooser(caloriesToleranceFactor);
				for (int i = 0; i < dietDays; i++) {
					Set<Meal> dietDay = mealChooser.chooseDayMealSet(dayConfiguration);
					dietMeals.addAll(dietDay);
				}
				caloriesToleranceFactor += toleranceStep;
			} while (dietMeals.size() < expectedMealsCount);
			generatedDiet.setMeals(dietMeals);
		}
		return generatedDiet;
	}

	private static boolean isMealsLibraryBigEnough(DietDayConfiguration dayConfig) {
		return MealChooser.isMealsLibraryBigEnough(dayConfig);
	}

	private static Diet generateDietPrototype(DietGenerationPreferences dietPreferences) {
		Diet generatedDiet = new Diet();

		Date now = new Date();
		Date then = new Date(now.getTime() + dietPreferences.getDietPeriodDays() * ONE_DAY_IN_MILISECONDS);

		generatedDiet.setUser(dietPreferences.getUser());
		generatedDiet.setName(dietPreferences.getDietName());
		generatedDiet.setValidFrom(now);
		generatedDiet.setValidTo(then);
		generatedDiet.setDietDayProperties(dietPreferences.getDayMealsPref());

		return generatedDiet;
	}

}

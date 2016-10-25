package logic.diet;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import database.entities.Diet;
import database.entities.Meal;

public class DietPlanGenerator {

	private final static int ONE_DAY_IN_MILISECONDS = 86400000;

	public static Diet generateDiet(DietGenerationPreferences dietConf) {
		DietDayConfiguration dayConf = dietConf.getDayMealsPref();

		Diet generatedDiet = generateDietPrototype(dietConf);
		int dietDays = dietConf.getDietPeriodDays();
		int expectedMealsCount = dietDays*dayConf.getDayMealsCount();
		
		Set<Meal> dietMeals;
		float caloriesToleranceFactor = 0.05f;
		float toleranceStep = 0.05f;
		
		do {
			dietMeals = new HashSet<>();
			MealChooser mealChooser = new MealChooser(caloriesToleranceFactor);
			for (int i = 0; i < dietDays; i++) {
				Set<Meal> dietDay = mealChooser.chooseDayMealSet(dayConf);
				dietMeals.addAll(dietDay);
			}
			caloriesToleranceFactor += toleranceStep;
		} while (dietMeals.size() < expectedMealsCount);

		generatedDiet.setMeals(dietMeals);
		return generatedDiet;
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

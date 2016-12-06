package logic.diet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.entities.Diet;
import database.entities.Dietday;

public class DietPlanGenerator {

	private static final float INITIAL_TOLERANCE_FACTOR = 0f;

	private static final String MSG_TOO_LESS_MEALS = "Diet could not have been generated. Meals library consists of too less meals that match your specific needs.";
	
	private static final  float CALORIES_TOLERANCE_STEP = 0.05f;
	private static final  float CALORIES_TOLERANCE_LIMIT = 1f;

	private DietPlanGenerator(){}
	
	public static Diet generateDiet(DietGenerationPreferences dietConfiguration) {
		final List<DietDayConfiguration> dietDayConfigurations = dietConfiguration.getDayMealsPreferences();
		Diet newDiet = initializeDietPrototype(dietConfiguration);

		Set<Dietday> newDietDays;
		float caloriesToleranceFactor = INITIAL_TOLERANCE_FACTOR;

		boolean isMealsInGeneratedDietCountOK;
		do {
			isMealsInGeneratedDietCountOK = true;
			caloriesToleranceFactor += CALORIES_TOLERANCE_STEP;
			MealChooser mealChooser = new MealChooser(caloriesToleranceFactor);

			newDietDays = new HashSet<>();
			for (DietDayConfiguration dayConfiguration : dietDayConfigurations) {
				if (isMealsLibraryTooLess(dayConfiguration) || toleranceExceeded(caloriesToleranceFactor)) {
					throw new UnsupportedOperationException(MSG_TOO_LESS_MEALS);
				}
				Dietday dietDay = mealChooser.generateDietDay(dayConfiguration, newDiet);
				if (dietDay.getMeals().size() != dayConfiguration.getMealsPerDayCount()) {
					isMealsInGeneratedDietCountOK = false;
				}
				newDietDays.add(dietDay);
			}
		} while (!isMealsInGeneratedDietCountOK);
		newDiet.setDietdays(newDietDays);
		return newDiet;
	}

	private static boolean toleranceExceeded(float caloriesToleranceFactor) {
		return caloriesToleranceFactor >= CALORIES_TOLERANCE_LIMIT;
	}

	private static boolean isMealsLibraryTooLess(DietDayConfiguration dayConfig) {
		return !MealChooser.isMealsLibraryBigEnough(dayConfig);
	}

	private static Diet initializeDietPrototype(DietGenerationPreferences dietPreferences) {
		Diet newDiet = new Diet();
		newDiet.setUser(dietPreferences.getUser());
		newDiet.setName(dietPreferences.getDietName());
		newDiet.setValidFrom(dietPreferences.getFirstDietDay());
		newDiet.setValidTo(dietPreferences.getLastDietDay());
		newDiet.setDailyReq(dietPreferences.getCaloriesRequirementPerDay());
		return newDiet;
	}
}

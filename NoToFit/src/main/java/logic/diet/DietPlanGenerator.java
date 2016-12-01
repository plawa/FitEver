package logic.diet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.entities.Diet;
import database.entities.Dietday;

public class DietPlanGenerator {

	private static final float INITIAL_TOLERANCE_FACTOR = 0f;

	private static final String MSG_TOO_LESS_MEALS = "Diet could not have been generated. Meals library consists of too less meals that match your specific needs.";
	
	private final static float caloriesToleranceStep = 0.05f;
	private final static float caloriesToleranceLimit = 1f;

	public static Diet generateDiet(DietGenerationPreferences dietConfiguration) throws UnsupportedOperationException {
		final List<DietDayConfiguration> dietDayConfigurations = dietConfiguration.getDayMealsPreferences();
		Diet newDiet = initializeDietPrototype(dietConfiguration);

		Set<Dietday> newDietDays;
		float caloriesToleranceFactor = INITIAL_TOLERANCE_FACTOR;

		boolean isMealsInGeneratedDietCountOK;
		do {
			isMealsInGeneratedDietCountOK = true;
			caloriesToleranceFactor += caloriesToleranceStep;
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
		return caloriesToleranceFactor >= caloriesToleranceLimit;
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

package logic.diet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.entities.Diet;
import database.entities.Dietday;

public class DietPlanGenerator {

	private final static float caloriesToleranceStep = 0.05f;

	public static Diet generateDiet(DietGenerationPreferences dietConfiguration) {
		Diet newDiet = null;
		List<DietDayConfiguration> dietDayConfigurations = dietConfiguration.getDayMealsPref();
		if (isMealsLibraryBigEnough(dietDayConfigurations.get(0))) { //TODO IMPROVE
			newDiet = initializeDietPrototype(dietConfiguration);

			Set<Dietday> newDietDays;
			MealChooser.updateLibraries();
			float caloriesToleranceFactor = 0f;
			
			boolean isMealsInGeneratedDietCountOK;
			do {
				isMealsInGeneratedDietCountOK = true;
				caloriesToleranceFactor += caloriesToleranceStep;
				MealChooser mealChooser = new MealChooser(caloriesToleranceFactor);
				
				newDietDays = new HashSet<>();
				for (DietDayConfiguration dayConfiguration : dietDayConfigurations) {
					Dietday dietDay = mealChooser.generateDietDay(dayConfiguration, newDiet);
					if (dietDay.getMeals().size() != dayConfiguration.getMealsPerDayCount()){
						isMealsInGeneratedDietCountOK = false;
					}
					newDietDays.add(dietDay);
				}
			} while (!isMealsInGeneratedDietCountOK);
			newDiet.setDietdays(newDietDays);
		}
		return newDiet;
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
		newDiet.setDailyReq(dietPreferences.getCaloriesRequirementPerDay());
		return newDiet;
	}
}

package logic.diet;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import database.entities.Diet;
import database.entities.Meal;

public class DietPlanGenerator {

	private final static int ONE_DAY_IN_MILISECONDS = 86400000;
	
	public static Diet generateDiet(DietGenerationPreferences preferences) {
		Diet generatedDiet = generateDietPrototype(preferences);

		Set<Meal> dietMeals = new HashSet<>();
		DietDayConfiguration dietDayConf = preferences.getDayMealsPref();
		
		MealChooser mealChooser = new MealChooser();
		int dietDays = preferences.getDietPeriodDays();
		for (int i = 0; i < dietDays; i++) {
			Set<Meal> dietDay = mealChooser.chooseDayMealSet(dietDayConf);
			dietMeals.addAll(dietDay);
		}
		generatedDiet.setMeals(dietMeals);
		return generatedDiet;
	}

	private static Diet generateDietPrototype(DietGenerationPreferences dietPreferences){
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

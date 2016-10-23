package logic.diet;

import java.util.Date;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Diet;
import database.entities.Meal;
import database.entities.User;
 
public class DietPlanGenerator {
	
	private final static int ONE_DAY_IN_MILISECONDS = 86400000;
	
	public static Diet generateDiet(DietGenerationPreferences preferences){
		Diet generatedDiet = new Diet();
		
		//retrieve data
		User user = preferences.getUser();
		String dietName = preferences.getDietName();
		int dietPeriodDays = preferences.getDietPeriodDays();
		DayMealSetPreferences dayPrefs = preferences.getDayMealsPref();
		
		Date now = new Date();
		Date then = new Date(now.getTime() + dietPeriodDays*ONE_DAY_IN_MILISECONDS);
			
		int dailyCaloriesRequired = EnergyRequirementCalculator.performCalculation(user);
		dayPrefs.setDailyCaloriesReq(dailyCaloriesRequired);
		
		generatedDiet.setUser(user);
		generatedDiet.setName(dietName);
		generatedDiet.setValidFrom(now);
		generatedDiet.setValidTo(then);
		generatedDiet.setDailyRequirement(dailyCaloriesRequired);
		
		for (int i = 0; i < dietPeriodDays; i++){
			Set<Meal> dietDay = MealChooser.chooseDayMealSet(dayPrefs);
			generatedDiet.getMeals().addAll(dietDay);
		}
		
		return generatedDiet;
	}
	
	
	public static void main(String[] args) {
		User ja = DatabaseController.getEntityByID(User.class, 13);
		
		DietGenerationPreferences prefs = new DietGenerationPreferences();
		prefs.setDietPeriodDays(6);
		prefs.setDietName("Dieta testowa");
		prefs.setMealsPerDay(5);
		prefs.setUser(ja);
		
		Diet mojaDieta = generateDiet(prefs);
		
		for (Meal meal : mojaDieta.getMeals()){
			System.out.println(meal.getName());
		}
		
	}

}

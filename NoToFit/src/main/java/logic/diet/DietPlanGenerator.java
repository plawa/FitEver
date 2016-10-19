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
		int dietPeriodDays = preferences.getDierPeriodDays();
		
		Date now = new Date();
		Date then = new Date(now.getTime() + dietPeriodDays*ONE_DAY_IN_MILISECONDS);
	
		generatedDiet.setUser(user);
		generatedDiet.setName(dietName);
		generatedDiet.setValidFrom(now); //valid from now
		generatedDiet.setValidTo(then);
		
		int caloriesDailyRequirement = EnergyRequirementCalculator.performCalculation(user);
		for (int i = 0; i < dietPeriodDays; i++){
			Set<Meal> dietDay = MealChooser.chooseDayMealSet(caloriesDailyRequirement, 3);
			generatedDiet.getMeals().addAll(dietDay);
		}
		
		return generatedDiet;
	}
	
	
	public static void main(String[] args) {
		User ja = DatabaseController.getEntityByID(User.class, 13);
		
		DietGenerationPreferences prefs = new DietGenerationPreferences();
		prefs.setDietPeriodDays(2);
		prefs.setDietName("Dieta testowa");
		prefs.setMealsPerDay(3);
		prefs.setUser(ja);
		
		Diet mojaDieta = generateDiet(prefs);
		
		for (Meal meal : mojaDieta.getMeals()){
			System.out.println(meal.getName());
		}
		
	}

}

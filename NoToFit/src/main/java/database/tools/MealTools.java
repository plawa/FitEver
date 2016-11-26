package database.tools;

import database.controller.DatabaseController;
import database.entities.Meal;

public class MealTools {

	private static final float CALORIES_IN_ONE_GRAM_OF_CARBOHYDRATES = 4.5f;
	private static final float CALORIES_IN_ONE_GRAM_OF_PROTEINS = 4.5f;
	private static final float CALORIES_IN_ONE_GRAM_OF_FAT = 9f;
	
	public static int countMealCalories(Meal meal){
		int grammage = meal.getGrammage();
		int carboCalories = Math.round(grammage * CALORIES_IN_ONE_GRAM_OF_CARBOHYDRATES *
				meal.getCarbohydratesPercentage()/100f);
		int proteinCalories = Math.round(grammage * CALORIES_IN_ONE_GRAM_OF_PROTEINS *
				meal.getProteinPercentage()/100f);
		int fatCalories = Math.round(grammage * CALORIES_IN_ONE_GRAM_OF_FAT *
				meal.getFatPercentage()/100f);
		return carboCalories + proteinCalories + fatCalories;
	}
	
	public static Meal copyMealShallow(Meal sourceMeal) {
		Meal newMeal = new Meal();
		newMeal.setName(sourceMeal.getName());
		newMeal.setObjective(sourceMeal.getObjective());
		newMeal.setGrammage(sourceMeal.getGrammage());
		newMeal.setCarbohydratesPercentage(sourceMeal.getCarbohydratesPercentage());
		newMeal.setProteinPercentage(sourceMeal.getProteinPercentage());
		newMeal.setFatPercentage(sourceMeal.getFatPercentage());
		return newMeal;
	}
	
	public static void main(String[] args) {
		Meal testMeal = DatabaseController.getEntityByID(Meal.class, 1);
		System.out.print(MealTools.countMealCalories(testMeal));
	}

}

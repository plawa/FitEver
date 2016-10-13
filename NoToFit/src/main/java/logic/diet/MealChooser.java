package logic.diet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Meal;

public class MealChooser {

	private Random randomGenerator;
	private List<Meal> mealsLibrary;
	
	public MealChooser(){
		mealsLibrary = new DatabaseController().getAll(Meal.class);
		randomGenerator = new Random();
	}
	
	public List<Meal> chooseDayMealSet(int caloriesPerDay, int mealsPerDay){
		Set<Meal> mealsAvailable = new HashSet<>(mealsLibrary);
		List<Meal> dayMealSet = new ArrayList<>();
		//int randomIndex = randomGenerator.nextInt(dayMealSet.size());
		int caloriesCount = 0;
		for (Meal meal : mealsAvailable){
			caloriesCount += 0; //TODO: implement calories counting tool for meal
			if (caloriesCount > caloriesPerDay)
				break;
			
			dayMealSet.add(meal);
		}
		return dayMealSet;
	}
	
	
	public static void main(String[] args) {
		
	}
	
	

}

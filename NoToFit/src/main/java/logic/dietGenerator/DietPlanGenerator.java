package logic.dietGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import database.controller.DatabaseController;
import database.entities.Diet;
import database.entities.Meal;
import database.entities.User;

public class DietPlanGenerator {

	
	public static void main(String[] args) {
		DatabaseController db = new DatabaseController();

		//getting User
		User user = db.getEntityByID(User.class, 11);
		
		//setting Diet
		Diet newDiet = new Diet();
		DateFormat myDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    	Date dateFrom = null;
    	Date dateTo = null;
		try {
			dateFrom = myDateFormatter.parse("09-10-2016");
			dateTo = myDateFormatter.parse("16-10-2016");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newDiet.setName("Dieta przykładowa");
		newDiet.setUser(user);
		newDiet.setValidFrom(dateFrom);
		newDiet.setValidTo(dateTo);
		newDiet.setObjective('r');
		
		
		//getting Meal
		Meal mealOne = db.getEntityByID(Meal.class, 1);
		Meal mealTwo = db.getEntityByID(Meal.class, 2);
		
		//setting Relation Diet-Meal
		newDiet.getMeals().add(mealOne);
		newDiet.getMeals().add(mealTwo);
		

		db.saveEntityToDatabase(newDiet);

		/*
		Set<Meal> meals = db.getEntityByID(Diet.class, 1).getMeals();
		System.out.println(meals.size());
		*/
	}

}

package logic.dietGenerator;

import database.controller.DatabaseController;

public class DietPlanGenerator {

	
	public static void main(String[] args) {
		DatabaseController db = new DatabaseController();
	/*	//getting User
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
		Meal meal = db.getEntityByID(Meal.class, 4);
		
		//setting Relation Diet-Meal
		DietMeal relationTest = new DietMeal();
		relationTest.setDiet(newDiet);
		relationTest.setMeal(meal);
		
		Set<DietMeal> dietMeals = new HashSet<DietMeal>();
		dietMeals.add(relationTest);
		
		newDiet.setDietMeals(dietMeals);
		
		db.saveEntityToDatabase(newDiet);*/
	}

}

package logic.diet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import database.controller.DatabaseController;
import database.entities.Meal;
import logic.entitytools.MealTools;

public class MealChooser {

	private static List<Meal> mainMealsLibrary;
	private static List<Meal> breakfastsLibrary;
	private static List<Meal> suppersLibrary;

	static {
		updateLibraries();
	}

	private static void updateLibraries() {
		breakfastsLibrary = DatabaseController.getEntityByParameter(Meal.class, "type", "b");
		mainMealsLibrary = DatabaseController.getEntityByParameter(Meal.class, "type", "m");
		suppersLibrary = DatabaseController.getEntityByParameter(Meal.class, "type", "s");
	}

	public static Set<Meal> chooseDayMealSet(int caloriesPerDay, int mealsPerDay) {
		Set<Meal> dayMealSet = new HashSet<Meal>();
		int dietCalories = 0;

		for (Meal meal : mainMealsLibrary) {
			int mealCalories = MealTools.countMealCalories(meal);
			dietCalories += mealCalories;
			if (dietCalories > caloriesPerDay) {
				dietCalories -= mealCalories;
				continue;
			}
			dayMealSet.add(meal);
		}
		return dayMealSet;
	}

	private static Set<Meal> chooseBreakfastMealSet(int breakfastCalories, int breakfastMealCount) {

		Set<Meal> breakfastsChosen = new HashSet<Meal>();

		return breakfastsChosen;
	}

	private static Set<Meal> chooseOptimalSubset(int itemsCount, int value) {

		return null;

	}

	static Tuple max(Tuple cas1, Tuple cas2) {
		return (cas1.kCalsAvailable < cas2.kCalsAvailable) ? cas1 : cas2;
	}

	static int knapSack(final int kCalsInitiallyAvailable, List<Meal> mealsLib, int n) {
		
		Stack<Tuple> st = new Stack<>();
		st.push(new Tuple(kCalsInitiallyAvailable, n));	
		
		while (!st.empty()) {
			Tuple cas = st.pop();
			if (cas.n == 0 || cas.kCalsAvailable == 0){
				return kCalsInitiallyAvailable - cas.kCalsAvailable;
			}

			int kCal = MealTools.countMealCalories(mealsLib.get(cas.n - 1));
			if (kCal > cas.kCalsAvailable) {
				Tuple newCas = new Tuple(cas.kCalsAvailable, cas.n - 1);
				st.push(newCas);
			} else {
				Tuple cas1 = new Tuple(cas.kCalsAvailable - kCal, cas.n - 1);
				Tuple cas2 = new Tuple(cas.kCalsAvailable, cas.n - 1);
				
				st.push(max(cas1, cas2));
				//iter.add(max(cas1, cas2));
			}
		}
		return kCalsInitiallyAvailable;
	}

	public static void main(String[] args) {
		System.out.println(knapSack(4541, mainMealsLibrary, mainMealsLibrary.size()));
		 //Set<Meal> testMealList = chooseDayMealSet(3000, 5);
		// for (Meal mealSelected : testMealList) {
		// System.out.println(mealSelected.getName());	
		//}
	}

	public static class Tuple {

		public Integer kCalsAvailable;
		public Integer n;

		public Tuple(Integer kCalsAvailable, Integer n) {
			this.kCalsAvailable = kCalsAvailable;
			this.n = n;
		}
	}

}

package logic.diet;

public class DayMealSetPreferences {

	private int breakfastMealsCount;
	private int mainDishMealsCount;
	private int supperMealsCount;
	
	private int dailyCaloriesReq;

	private float breakfastCaloriesFactor;
	private float mainDishCaloriesFactor;
	private float supperCaloriesFactor;

	public DayMealSetPreferences() {
		
	}

	public void initializeWithDefaultValues() {
		setDailyCaloriesReq(2500);
		
		breakfastMealsCount = 1;
		mainDishMealsCount = 1;
		supperMealsCount = 1;

		setCaloriesFactorsByDefault();
	}

	public void setCaloriesFactorsByDefault() {
		float allMealsCount = breakfastMealsCount + mainDishMealsCount + supperMealsCount;

		breakfastCaloriesFactor = breakfastMealsCount / allMealsCount;
		mainDishCaloriesFactor = mainDishMealsCount / allMealsCount;
		supperCaloriesFactor = supperMealsCount / allMealsCount;
	}

	public int getDailyCaloriesReq() {
		return dailyCaloriesReq;
	}

	public void setDailyCaloriesReq(int dailyCaloriesReq) {
		this.dailyCaloriesReq = dailyCaloriesReq;
	}

	public int getBreakfastMealsCount() {
		return breakfastMealsCount;
	}

	public void setBreakfastMealsCount(int breakfastMealsCount) {
		this.breakfastMealsCount = breakfastMealsCount;
	}

	public int getMainDishMealsCount() {
		return mainDishMealsCount;
	}

	public void setMainDishMealsCount(int mainDishMealsCount) {
		this.mainDishMealsCount = mainDishMealsCount;
	}

	public int getSupperMealsCount() {
		return supperMealsCount;
	}

	public void setSupperMealsCount(int supperMealsCount) {
		this.supperMealsCount = supperMealsCount;
	}

	public float getBreakfastCaloriesFactor() {
		return breakfastCaloriesFactor;
	}

	public void setBreakfastCaloriesFactor(float breakfastCaloriesFactor) {
		this.breakfastCaloriesFactor = breakfastCaloriesFactor;
	}

	public float getMainDishCaloriesFactor() {
		return mainDishCaloriesFactor;
	}

	public void setMainDishCaloriesFactor(float mainDishCaloriesFactor) {
		this.mainDishCaloriesFactor = mainDishCaloriesFactor;
	}

	public float getSupperCaloriesFactor() {
		return supperCaloriesFactor;
	}

	public void setSupperCaloriesFactor(float supperCaloriesFactor) {
		this.supperCaloriesFactor = supperCaloriesFactor;
	}

}

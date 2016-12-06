package logic.diet;

import java.util.Date;

public class DietDayConfiguration {

	private int dailyCaloriesReq;
	
	private int breakfastMealsCount;
	private int mainDishMealsCount;
	private int supperMealsCount;

	private float breakfastCaloriesWeight;
	private float mainDishCaloriesWeight;
	private float supperCaloriesWeight;

	private Date date;
	
	public DietDayConfiguration() {
		this(new Date());
	}

	public DietDayConfiguration(Date date) {
		this.setDate(date);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCaloriesWeightsByDefault() {
		float allMealsCount = (float)breakfastMealsCount + mainDishMealsCount + supperMealsCount; //should be float due to floating point calculations below

		breakfastCaloriesWeight = breakfastMealsCount / allMealsCount;
		mainDishCaloriesWeight = mainDishMealsCount / allMealsCount;
		supperCaloriesWeight = supperMealsCount / allMealsCount;
	}

	public int getMealsPerDayCount(){
		return breakfastMealsCount + mainDishMealsCount + supperMealsCount;
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
		return breakfastCaloriesWeight;
	}

	public void setBreakfastCaloriesFactor(float breakfastCaloriesFactor) {
		this.breakfastCaloriesWeight = breakfastCaloriesFactor;
	}

	public float getMainDishCaloriesFactor() {
		return mainDishCaloriesWeight;
	}

	public void setMainDishCaloriesFactor(float mainDishCaloriesFactor) {
		this.mainDishCaloriesWeight = mainDishCaloriesFactor;
	}

	public float getSupperCaloriesFactor() {
		return supperCaloriesWeight;
	}

	public void setSupperCaloriesFactor(float supperCaloriesFactor) {
		this.supperCaloriesWeight = supperCaloriesFactor;
	}

}

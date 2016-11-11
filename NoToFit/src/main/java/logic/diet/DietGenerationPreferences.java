package logic.diet;

import java.util.Date;
import java.util.List;

import database.entities.User;

public class DietGenerationPreferences {

	private static final int ONE_DAY_IN_MILISECONDS = 86400000;
	
	private User user;
	private String dietName;
	private int dietPeriodInDays;
	private int caloriesRequirementPerDay;
	private Date firstDietDay;
	private List<DietDayConfiguration> dayMealsPref;

	
	public DietGenerationPreferences() {
		setFirstDietDay(new Date());
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (user == null)
			throw new IllegalArgumentException("user must not be null!");
		this.user = user;
	}

	public String getDietName() {
		return dietName;
	}

	public void setDietName(String dietName) {
		if (dietName.length() > 30)
			throw new IllegalArgumentException("dietName String is too long!");
		this.dietName = dietName;
	}

	public int getCaloriesRequirementPerDay() {
		return caloriesRequirementPerDay;
	}

	public void setCaloriesRequirementPerDay(int caloriesRequirementPerDay) {
		this.caloriesRequirementPerDay = caloriesRequirementPerDay;
	}

	public int getDietPeriodInDays() {
		return dietPeriodInDays;
	}

	public void setDietPeriodDays(int dietPeriodDays) {
		this.dietPeriodInDays = dietPeriodDays;
	}

	public List<DietDayConfiguration> getDayMealsPref() {
		return dayMealsPref;
	}

	public void setDayMealsPref(List<DietDayConfiguration> dayMealsPref) {
		this.dayMealsPref = dayMealsPref;
	}

	public Date getFirstDietDay() {
		return firstDietDay;
	}

	private void setFirstDietDay(Date firstDietDay) {
		this.firstDietDay = firstDietDay;
	}
	
	public Date getLastDietDay(){
		long inMiliseconds = getFirstDietDay().getTime() + dietPeriodInDays * ONE_DAY_IN_MILISECONDS;
		return new Date(inMiliseconds);
	}
}

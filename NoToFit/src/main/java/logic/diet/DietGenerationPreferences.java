package logic.diet;

import database.entities.User;

public class DietGenerationPreferences {

	private User user;
	private String dietName;
	private int dietPeriodDays;
	private DietDayConfiguration dayMealsPref;

	
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

	public int getDietPeriodDays() {
		return dietPeriodDays;
	}

	public void setDietPeriodDays(int dietPeriodDays) {
		this.dietPeriodDays = dietPeriodDays;
	}

	public DietDayConfiguration getDayMealsPref() {
		return dayMealsPref;
	}

	public void setDayMealsPref(DietDayConfiguration dayMealsPref) {
		this.dayMealsPref = dayMealsPref;
	}
}

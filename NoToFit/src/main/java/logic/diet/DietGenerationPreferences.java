package logic.diet;

import java.util.Date;

import database.entities.User;

public class DietGenerationPreferences {

	private static final int ONE_DAY_IN_MILISECONDS = 86400000;
	
	private User user;
	private String dietName;
	private int dietPeriodInDays;
	private DietDayConfiguration dayMealsPref;
	private Date firstDietDay;

	
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

	public int getDietPeriodInDays() {
		return dietPeriodInDays;
	}

	public void setDietPeriodDays(int dietPeriodDays) {
		this.dietPeriodInDays = dietPeriodDays;
	}

	public DietDayConfiguration getDayMealsPref() {
		return dayMealsPref;
	}

	public void setDayMealsPref(DietDayConfiguration dayMealsPref) {
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

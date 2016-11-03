package logic.workout;

import java.util.Date;

import com.google.common.base.Preconditions;

import database.entities.User;
import gui.exercises.DifficultyLevel;

public class WorkoutGenerationPreferences {

	private final static int ONE_DAY_IN_MILISECONDS = 86400000;
	private static final int DAYS_IN_WEEK_COUNT = 7;
	
	private User user;
	private String name;
	private int workoutDaysPerWeek;
	private int workoutPeriodInWeeks;
	private DifficultyLevel prefferedDifficulty;
	private boolean hasEquipment;
	private Date firstDayDate;

	public WorkoutGenerationPreferences() {
		firstDayDate = new Date(); //start from now
	}
	
	public Date[] getDatesForWorkoutDays() {
		Preconditions.checkArgument(workoutDaysPerWeek != 0 && workoutPeriodInWeeks != 0);
		
		int workoutDaysCount = workoutDaysPerWeek * workoutPeriodInWeeks;

		long firstDayInMiliseconds = getFirstDayDate().getTime();
		long lastDayInMiliseconds = getLastDayDate().getTime(); 
		long workoutDaysInterval = (lastDayInMiliseconds - firstDayInMiliseconds) / workoutDaysCount;

		Date[] daysDates = new Date[workoutDaysPerWeek * workoutPeriodInWeeks];
		for (int i = 0; i < workoutDaysCount; i++) {
			daysDates[i] = new Date(firstDayInMiliseconds + i * workoutDaysInterval);
		}
		return daysDates;
	}
	
	public Date getFirstDayDate(){
		return firstDayDate;
	}
	
	public Date getLastDayDate(){
		long workoutPeriodInDays = DAYS_IN_WEEK_COUNT * workoutPeriodInWeeks;
		long inMiliseconds = getFirstDayDate().getTime() + workoutPeriodInDays * ONE_DAY_IN_MILISECONDS;
		return new Date(inMiliseconds);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTrainingDaysPerWeek() {
		return workoutDaysPerWeek;
	}

	public void setTrainingDaysPerWeek(int trainingDaysPerWeek) {
		this.workoutDaysPerWeek = trainingDaysPerWeek;
	}

	public int getWorkoutPeriodInWeeks() {
		return workoutPeriodInWeeks;
	}

	public void setWorkoutPeriodInWeeks(int workoutPeriodInDays) {
		this.workoutPeriodInWeeks = workoutPeriodInDays;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DifficultyLevel getPrefferedDifficulty() {
		return prefferedDifficulty;
	}

	public void setPrefferedDifficulty(DifficultyLevel prefferedDifficulty) {
		this.prefferedDifficulty = prefferedDifficulty;
	}

	public boolean hasUserEquipment() {
		return hasEquipment;
	}

	public void setHasEquipment(boolean hasEquipment) {
		this.hasEquipment = hasEquipment;
	}
	
	public static void main(String[] args) {
		WorkoutGenerationPreferences wgp = new WorkoutGenerationPreferences();
		wgp.setTrainingDaysPerWeek(3);
		wgp.setWorkoutPeriodInWeeks(4);
		
		for (Date d : wgp.getDatesForWorkoutDays()){
			System.out.println(d);
		}
	}

}

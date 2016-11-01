package logic.workout;

public class WorkoutGenerationPreferences {

	private String name;
	private int trainingDaysPerWeek;
	private int workoutPeriodInDays;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTrainingDaysPerWeek() {
		return trainingDaysPerWeek;
	}
	public void setTrainingDaysPerWeek(int trainingDaysPerWeek) {
		this.trainingDaysPerWeek = trainingDaysPerWeek;
	}
	public int getWorkoutPeriodInDays() {
		return workoutPeriodInDays;
	}
	public void setWorkoutPeriodInDays(int workoutPeriodInDays) {
		this.workoutPeriodInDays = workoutPeriodInDays;
	}
	
	
}

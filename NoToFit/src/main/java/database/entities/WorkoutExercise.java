package database.entities;
// Generated 2016-09-29 20:51:33 by Hibernate Tools 5.2.0.Beta1

/**
 * WorkoutExercise generated by hbm2java
 */
public class WorkoutExercise implements java.io.Serializable {

	private WorkoutExerciseId id;
	private Exercise exercise;
	private Workout workout;
	private int orderNumber;

	public WorkoutExercise() {
	}

	public WorkoutExercise(WorkoutExerciseId id, Exercise exercise, Workout workout, int orderNumber) {
		this.id = id;
		this.exercise = exercise;
		this.workout = workout;
		this.orderNumber = orderNumber;
	}

	public WorkoutExerciseId getId() {
		return this.id;
	}

	public void setId(WorkoutExerciseId id) {
		this.id = id;
	}

	public Exercise getExercise() {
		return this.exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Workout getWorkout() {
		return this.workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public int getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}

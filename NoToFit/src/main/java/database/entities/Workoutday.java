package database.entities;
// Generated 2016-10-30 15:10:58 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Workoutday extends Entity {

	private static final long serialVersionUID = 1275169139490146634L;
	private Integer id;
	private Workout workout;
	private Date date;
	private Set<Exercise> exercises = new HashSet<Exercise>(0);


	public Workoutday() {
	}
	
	public Workoutday(Workout workout) {
		this.workout = workout;
	}

	public Workoutday(Date date) {
		this.date = date;
	}

	public Workoutday(Workout workout, Set<Exercise> exercises) {
		this.workout = workout;
		this.exercises = exercises;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Workout getWorkout() {
		return this.workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Exercise> getExercises() {
		return this.exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}

}

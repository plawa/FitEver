package database.entities;
// Generated 2016-11-27 00:30:46 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Workoutday extends Entity {

	private static final long serialVersionUID = 1275169139490146634L;
	private Integer id;
	private Workout workout;
	private Date date;
	private Set<Exercise> exercises = new HashSet<>(0);

	public Workoutday() {
	}

	public Workoutday(Date date) {
		this.date = date;
	}

	@Override
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
		return this.date;
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

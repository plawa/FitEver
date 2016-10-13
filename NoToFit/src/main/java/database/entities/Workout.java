package database.entities;
// Generated 2016-09-29 20:51:33 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Workout generated by hbm2java
 */
public class Workout implements java.io.Serializable {

	private static final long serialVersionUID = 9209194375975264761L;
	private Integer id;
	private User user;
	private Date validFrom;
	private Set<Exercise> exercises = new HashSet<>(0);

	public Workout() {
	}

	public Workout(User user, Date validFrom) {
		this.user = user;
		this.validFrom = validFrom;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Set<Exercise> getExercises() {
		return this.exercises;
	}

	public void setExercises(Set<Exercise> workoutExercises) {
		this.exercises = workoutExercises;
	}

}

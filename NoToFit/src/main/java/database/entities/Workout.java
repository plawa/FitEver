package database.entities;
// Generated 2016-11-27 00:30:46 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Workout generated by hbm2java
 */
public class Workout extends Entity {

	private static final long serialVersionUID = 5479860479872562447L;
	private Integer id;
	private User user;
	private String name;
	private Date validFrom;
	private Date validTo;
	private Character objective;
	private Set<Workoutday> workoutdays = new HashSet<Workoutday>(0);

	public Workout() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Character getObjective() {
		return this.objective;
	}

	public void setObjective(Character objective) {
		this.objective = objective;
	}

	public Set<Workoutday> getWorkoutdays() {
		return this.workoutdays;
	}

	public void setWorkoutdays(Set<Workoutday> workoutdays) {
		this.workoutdays = workoutdays;
	}

}

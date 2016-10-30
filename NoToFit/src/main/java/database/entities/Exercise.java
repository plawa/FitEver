package database.entities;
// Generated 2016-10-30 15:10:58 by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Exercise generated by hbm2java
 */
public class Exercise extends Entity {

	private Integer id;
	private String name;
	private String description;
	private char objective;
	private boolean requiresEquipment;
	private Integer difficultyLevel;
	private Set<Workoutday> workoutdays = new HashSet<Workoutday>(0);

	public Exercise() {
	}

	public Exercise(String name, char objective, boolean requiresEquipment) {
		this.name = name;
		this.objective = objective;
		this.requiresEquipment = requiresEquipment;
	}

	public Exercise(String name, String description, char objective, boolean requiresEquipment, Integer difficultyLevel,
			Set<Workoutday> workoutdays) {
		this.name = name;
		this.description = description;
		this.objective = objective;
		this.requiresEquipment = requiresEquipment;
		this.difficultyLevel = difficultyLevel;
		this.workoutdays = workoutdays;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getObjective() {
		return this.objective;
	}

	public void setObjective(char objective) {
		this.objective = objective;
	}

	public boolean isRequiresEquipment() {
		return this.requiresEquipment;
	}

	public void setRequiresEquipment(boolean requiresEquipment) {
		this.requiresEquipment = requiresEquipment;
	}

	public Integer getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Set<Workoutday> getWorkoutdays() {
		return this.workoutdays;
	}

	public void setWorkoutdays(Set<Workoutday> workoutdays) {
		this.workoutdays = workoutdays;
	}

}

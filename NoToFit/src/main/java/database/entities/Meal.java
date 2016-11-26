package database.entities;
// Generated 2016-11-11 15:44:57 by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

import database.tools.MealTools;

/**
 * Meal generated by hbm2java
 */
public class Meal extends Entity {

	private static final long serialVersionUID = -5478353296080109577L;
	private Integer id;
	private String name;
	private char objective;
	private char type;
	private int grammage;
	private int carbohydratesPercentage;
	private int proteinPercentage;
	private int fatPercentage;
	private Set<Dietday> dietdays = new HashSet<Dietday>();

	public Meal() {
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

	public char getObjective() {
		return this.objective;
	}

	public void setObjective(char objective) {
		this.objective = objective;
	}

	public char getType() {
		return this.type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getGrammage() {
		return this.grammage;
	}

	public void setGrammage(int grammage) {
		this.grammage = grammage;
	}

	public int getCarbohydratesPercentage() {
		return this.carbohydratesPercentage;
	}

	public void setCarbohydratesPercentage(int carbohydratesPercentage) {
		this.carbohydratesPercentage = carbohydratesPercentage;
	}

	public int getProteinPercentage() {
		return this.proteinPercentage;
	}

	public void setProteinPercentage(int proteinPercentage) {
		this.proteinPercentage = proteinPercentage;
	}

	public int getFatPercentage() {
		return this.fatPercentage;
	}

	public void setFatPercentage(int fatPercentage) {
		this.fatPercentage = fatPercentage;
	}

	public Set<Dietday> getDietdays() {
		return this.dietdays;
	}

	public void setDietdays(Set<Dietday> dietdays) {
		this.dietdays = dietdays;
	}

	/* MANUAL CODE REGION */ 
	
	@Override
	public String toString() {
		return getName() + ", kCal: " + MealTools.countMealCalories(this);
	}
	
}

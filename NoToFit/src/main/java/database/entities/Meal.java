package database.entities;
// Generated 2016-09-29 20:51:33 by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Meal generated by hbm2java
 */
public class Meal extends Entity {

	private static final long serialVersionUID = -3460371620499533617L;
	private Integer id;
	private String name;
	private char objective;
	private int gramature;
	private int carbohydratesPercentage;
	private int proteinPercentage;
	private int fatPercentage;
	private Set<Diet> diets = new HashSet<Diet>(0);
	
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
	
	public int getGramature() {
		return this.gramature;
	}

	public void setGramature(int gramature) {
		this.gramature = gramature;
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

	public Set<Diet> getDiets() {
		return this.diets;
	} 
 
	public void setDiets(Set<Diet> dietMeals) {
		this.diets = dietMeals;
	}
	
}

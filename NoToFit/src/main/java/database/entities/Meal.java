package database.entities;
// Generated 2016-09-25 00:14:06 by Hibernate Tools 5.2.0.Beta1

/**
 * Meal generated by hbm2java
 */
public class Meal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4343540118304154575L;
	private Integer id;
	private String name;
	private String objective;
	private int gramature;
	private int carbohydratesPercentage;
	private int proteinPercentage;
	private int fatPercentage;

	public Meal() {
	}

	public Meal(String name, String objective, int gramature, int carbohydratesPercentage, int proteinPercentage,
			int fatPercentage) {
		this.name = name;
		this.objective = objective;
		this.gramature = gramature;
		this.carbohydratesPercentage = carbohydratesPercentage;
		this.proteinPercentage = proteinPercentage;
		this.fatPercentage = fatPercentage;
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

	public String getObjective() {
		return this.objective;
	}

	public void setObjective(String objective) {
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

}
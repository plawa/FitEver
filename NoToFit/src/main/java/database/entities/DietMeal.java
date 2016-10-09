package database.entities;
// Generated 2016-09-29 20:51:33 by Hibernate Tools 5.2.0.Beta1

public class DietMeal extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7645787240053240386L;
	private DietMealId id;
	private Diet diet;
	private Meal meal;
	private Integer orderNumber;

	public DietMeal() {
	}

	public DietMeal(DietMealId id, Diet diet, Meal meal) {
		this.id = id;
		this.diet = diet;
		this.meal = meal;
	}

	public DietMeal(DietMealId id, Diet diet, Meal meal, Integer orderNumber) {
		this.id = id;
		this.diet = diet;
		this.meal = meal;
		this.orderNumber = orderNumber;
	}

	public DietMealId getId() {
		return this.id;
	}

	public void setId(DietMealId id) {
		this.id = id;
	}

	public Diet getDiet() {
		return this.diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	public Meal getMeal() {
		return this.meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

}

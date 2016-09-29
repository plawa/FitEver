package database.entities;
// Generated 2016-09-29 16:40:14 by Hibernate Tools 5.2.0.Beta1

/**
 * DietMealId generated by hbm2java
 */
public class DietMealId implements Entity {

	private int dietId;
	private int mealId;

	public DietMealId() {
	}

	public DietMealId(int dietId, int mealId) {
		this.dietId = dietId;
		this.mealId = mealId;
	}

	public int getDietId() {
		return this.dietId;
	}

	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public int getMealId() {
		return this.mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DietMealId))
			return false;
		DietMealId castOther = (DietMealId) other;

		return (this.getDietId() == castOther.getDietId()) && (this.getMealId() == castOther.getMealId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getDietId();
		result = 37 * result + this.getMealId();
		return result;
	}

}

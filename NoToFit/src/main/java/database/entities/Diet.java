package database.entities;
// Generated 2016-09-29 20:51:33 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Diet generated by hbm2java
 */
public class Diet extends Entity {

	private static final long serialVersionUID = -2334508004434775181L;
	private Integer id;
	private String name;
	private User user;
	private int mealIds;
	private Date validFrom;
	private Date validTo;
	private Character objective;
	private Set<?> dietMeals = new HashSet<Object>(0);
	private static BiMap<Character, String> objectiveTranslations;

	public Diet(){
		initializeMap();
	}
	
	public Diet(String name, User user, int mealIds, Date validFrom) {
		this();
		this.setName(name);
		this.user = user;
		this.mealIds = mealIds;
		this.validFrom = validFrom;
	}

	public Diet(String name, User user, int mealIds, Date validFrom, Set<?> dietMeals) {
		this();
		this.setName(name);
		this.user = user;
		this.mealIds = mealIds;
		this.validFrom = validFrom;
		this.dietMeals = dietMeals;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getMealIds() {
		return this.mealIds;
	}

	public void setMealIds(int mealIds) {
		this.mealIds = mealIds;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Character getObjective() {
		return objective;
	}
	
	public String getObjectiveString() {
		return parseObjectiveCharToString(objective);
	}

	public void setObjective(Character objective) {
		this.objective = objective;
	}
	public void setObjectiveFromString(String objectiveString) {
		this.objective = parseObjectiveStringToChar(objectiveString);
	}

	public Set<?> getDietMeals() {
		return this.dietMeals;
	}

	public void setDietMeals(Set<?> dietMeals) {
		this.dietMeals = dietMeals;
	}
	
	private void initializeMap(){		
		objectiveTranslations = HashBiMap.create();
		objectiveTranslations.put('m', "Mass Gain");
		objectiveTranslations.put('r', "Reduction");
		objectiveTranslations.put('s', "Strength");
	}
	
	private String parseObjectiveCharToString(Character objectiveChar){
		return objectiveTranslations.getOrDefault(objectiveChar, "Error!");
	}
	
	private Character parseObjectiveStringToChar(String objectiveString){
		return objectiveTranslations.inverse().getOrDefault(objectiveString, 'e');
	}

}

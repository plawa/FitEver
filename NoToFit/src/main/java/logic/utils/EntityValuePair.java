package logic.utils;

import database.entities.Entity;

public class EntityValuePair implements Comparable<EntityValuePair> {

	public Entity entity;
	public Integer value;

	public EntityValuePair(Entity entity, Integer value) {
		this.entity = entity;
		this.value = value;
	}

	@Override
	public int compareTo(EntityValuePair other) {
		if(value > other.value)
			return -1;
		if(value < other.value)
			return 1;
		return 0;
	}

}

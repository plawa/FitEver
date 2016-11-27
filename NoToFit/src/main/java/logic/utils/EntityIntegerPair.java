package logic.utils;

import database.entities.Entity;

public class EntityIntegerPair implements Comparable<EntityIntegerPair> {

	public Entity entity;
	public Integer value;

	public EntityIntegerPair(Entity entity, Integer value) {
		this.entity = entity;
		this.value = value;
	}

	@Override
	public int compareTo(EntityIntegerPair other) {
		if(value > other.value)
			return -1;
		if(value < other.value)
			return 1;
		return 0;
	}

}

package logic.utils;

import database.entities.Entity;

public class EntityIntegerPair implements Comparable<EntityIntegerPair> {

	private Entity entity;
	private Integer value;

	public EntityIntegerPair(Entity entity, Integer value) {
		this.entity = entity;
		this.value = value;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public int compareTo(EntityIntegerPair other) {
		if (value > other.value) {
			return -1;
		}
		if (value < other.value) {
			return 1;
		}
		return 0;
	}

}

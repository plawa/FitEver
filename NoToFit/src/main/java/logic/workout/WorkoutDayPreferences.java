package logic.workout;

import logic.enums.DifficultyLevel;
import logic.enums.Objective;

public class WorkoutDayPreferences {

	private Objective objective;
	private DifficultyLevel difficultyLevel;
	private boolean isEquipmentRequired;
	
	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public boolean isEquipmentRequired() {
		return isEquipmentRequired;
	}

	public void setEquipmentRequired(boolean isEquipmentRequired) {
		this.isEquipmentRequired = isEquipmentRequired;
	}

}

package logic.workout;

import gui.common.Objective;
import gui.exercises.DifficultyLevel;

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

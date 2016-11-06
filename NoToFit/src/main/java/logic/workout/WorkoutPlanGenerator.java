package logic.workout;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import database.entities.Workout;
import database.entities.Workoutday;
import gui.common.Objective;

public class WorkoutPlanGenerator {

	public static Workout generateWorkout(WorkoutGenerationPreferences workoutPreferences) {
		Workout newWorkout = initializeWorkoutPrototype(workoutPreferences);
		Set<Workoutday> generateWorkoutDaysSet = generateWorkoutDaysSet(newWorkout, workoutPreferences);
		if (generateWorkoutDaysSet != null) {
			newWorkout.setWorkoutdays(generateWorkoutDaysSet);
			return newWorkout;
		} else {
			return null;
		}
	}

	private static Set<Workoutday> generateWorkoutDaysSet(Workout workout, WorkoutGenerationPreferences preferences) {
		int daysToGenerateCount = preferences.getTrainingDaysPerWeek() * preferences.getWorkoutPeriodInWeeks();

		Set<Workoutday> workoutDaysSet = null;
		ExerciseChooser dayGenerator = new ExerciseChooser(buildWorkoutDayPreferences(preferences));
		if (dayGenerator.isExercisesLibraryBigEnough()) {
			workoutDaysSet = new HashSet<>();
			Date[] workoutDayDates = preferences.getDatesForWorkoutDays();
			for (int i = 0; i < daysToGenerateCount; i++) {
				Workoutday day = dayGenerator.generateWorkoutDay(workoutDayDates[i]);
				day.setWorkout(workout);
				workoutDaysSet.add(day);
			}
		}
		return workoutDaysSet;
	}

	private static WorkoutDayPreferences buildWorkoutDayPreferences(WorkoutGenerationPreferences workoutPreferences) {
		WorkoutDayPreferences dayPreferences = new WorkoutDayPreferences();
		dayPreferences.setObjective(Objective.get(workoutPreferences.getUser().getUserObjective()));
		dayPreferences.setEquipmentRequired(workoutPreferences.hasUserEquipment());
		dayPreferences.setDifficultyLevel(workoutPreferences.getPrefferedDifficulty());
		return dayPreferences;
	}

	private static Workout initializeWorkoutPrototype(WorkoutGenerationPreferences workoutPreferences) {
		Workout newWorkout = new Workout();
		newWorkout.setValidFrom(workoutPreferences.getFirstDayDate());
		newWorkout.setValidTo(workoutPreferences.getLastDayDate());
		newWorkout.setName(workoutPreferences.getName());
		newWorkout.setUser(workoutPreferences.getUser());
		newWorkout.setObjective(workoutPreferences.getUser().getUserObjective());
		return newWorkout;
	}

}

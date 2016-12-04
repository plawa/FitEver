package logic.workout;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Exercise;
import database.entities.Workoutday;
import logic.enums.DifficultyLevel;
import logic.enums.Objective;
import logic.utils.EntityIntegerPair;

public class ExerciseChooser {

	private static final int REDUCTION_EXERCISES_COUNT = 10;
	private static final int MASS_GAIN_EXERCISES_COUNT = 8;
	private static final int POINTS_FOR_EQUIPMENT_MATCH = 2;
	private static final int POINTS_FOR_OBJECTIVE_MATCH = 4;

	private int exercisesPerDayCount;
	private DifficultyLevel difficulty;
	private Objective objective;
	private boolean hasUserEquipment;

	private static List<Exercise> exercisesLib;

	public ExerciseChooser(WorkoutDayPreferences preferences) {
		if (exercisesLib == null) {
			updateLibrary();
		}
		exercisesPerDayCount = getExercisesPerDayCountByObjective(preferences.getObjective());
		difficulty = preferences.getDifficultyLevel();
		objective = preferences.getObjective();
		hasUserEquipment = preferences.isEquipmentRequired();
	}

	public boolean isExercisesLibraryBigEnough() {
		return exercisesPerDayCount <= exercisesLib.size();
	}

	public Workoutday generateWorkoutDay(Date date) {
		Workoutday day = new Workoutday(date);
		day.setExercises(chooseBestMatchedExercises());
		return day;
	}

	private Set<Exercise> chooseBestMatchedExercises() {
		PriorityQueue<EntityIntegerPair> exercisesSortedByMatchRank = retrieveExercisesRanked();
		Set<Exercise> resultSet = new HashSet<>();
		for (int i = 0; i < exercisesPerDayCount; i++) {
			resultSet.add((Exercise) exercisesSortedByMatchRank.poll().entity);
		}
		return resultSet;
	}

	private PriorityQueue<EntityIntegerPair> retrieveExercisesRanked() {
		PriorityQueue<EntityIntegerPair> exercisesSortedByMatchRank = new PriorityQueue<>();
		for (Exercise currentExercise : exercisesLib) {
			int exerciseRate = rateExercise(currentExercise);
			exercisesSortedByMatchRank.add(new EntityIntegerPair(currentExercise, exerciseRate));
		}
		return exercisesSortedByMatchRank;
	}

	private int rateExercise(Exercise exercise) {
		int points = new Random().nextInt(4) - 2; // to provide more diversity of training days
		boolean isAbleToExercise = hasUserEquipment || !exercise.isRequiresEquipment();
		boolean objectiveMatches = objective.getCharID() == exercise.getObjective();
		int difficultyLevelDifference = Math.abs(difficulty.getNumber() - exercise.getDifficultyLevel());

		if (isAbleToExercise) {
			points += POINTS_FOR_EQUIPMENT_MATCH;
		}
		if (objectiveMatches) {
			points += POINTS_FOR_OBJECTIVE_MATCH;
		}
		points -= difficultyLevelDifference;
		return points;
	}

	private int getExercisesPerDayCountByObjective(Objective objective) {
		switch (objective) {
		case MassGain:
			return MASS_GAIN_EXERCISES_COUNT;
		case Reduction:
			return REDUCTION_EXERCISES_COUNT;
		default:
			return 0;
		}
	}

	private void updateLibrary() {
		exercisesLib = DatabaseController.getAll(Exercise.class);
	}

}

package logic.workout;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.base.Preconditions;

import database.controller.DatabaseController;
import database.entities.Exercise;
import database.entities.Workoutday;
import gui.common.Objective;
import gui.exercises.DifficultyLevel;
import logic.utils.EntityValuePair;

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

	static {
		updateLibrary();
	}
	
	public static void updateLibrary() {
		exercisesLib = DatabaseController.getAll(Exercise.class);
	}
	
	public ExerciseChooser(WorkoutDayPreferences preferences) {
		exercisesPerDayCount = getExercisesPerDayCountByObjective(preferences.getObjective());
		difficulty = preferences.getDifficultyLevel();
		objective = preferences.getObjective();
		hasUserEquipment = preferences.isEquipmentRequired();
	}
	
	public boolean isExercisesLibraryBigEnough(){
		return exercisesPerDayCount <= exercisesLib.size();
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

	public Workoutday generateWorkoutDay(Date date) {
		Workoutday day = new Workoutday(date);
		day.setExercises(chooseBestMatchedExercises());
		return day;
	}

	private Set<Exercise> chooseBestMatchedExercises() {
		Preconditions.checkArgument(isExercisesLibraryBigEnough());
		PriorityQueue<EntityValuePair> exercisesSortedByMatchRank = getRankedExercisesSorted();
		Set<Exercise> resultSet = new HashSet<>();
		for(int i = 0; i < exercisesPerDayCount; i++){
			resultSet.add((Exercise) exercisesSortedByMatchRank.poll().entity);
		}
		return resultSet;
	}

	private PriorityQueue<EntityValuePair> getRankedExercisesSorted() {
		PriorityQueue<EntityValuePair> exercisesSortedByMatchRank = new PriorityQueue<EntityValuePair>();
		for (Exercise currentExercise : exercisesLib) {
			int exerciseRate = rateExercise(currentExercise);
			exercisesSortedByMatchRank.add(new EntityValuePair(currentExercise, exerciseRate));
		}
		return exercisesSortedByMatchRank;
	}

	private int rateExercise(Exercise exercise) {
		int points = 0;
		boolean isAbleToExercise = hasUserEquipment || !exercise.isEquipmentRequired();
		boolean objectiveMatches = objective.getObjectiveChar() == exercise.getObjective();
		int difficultyLevelDifference = Math.abs(difficulty.getLevelNumber() - exercise.getDifficultyLevel());

		if (isAbleToExercise)
			points += POINTS_FOR_EQUIPMENT_MATCH;
		if (objectiveMatches)
			points += POINTS_FOR_OBJECTIVE_MATCH;
		points -= difficultyLevelDifference;

		return points;
	}
}
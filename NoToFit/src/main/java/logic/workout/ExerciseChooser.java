package logic.workout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import database.controller.DatabaseController;
import database.entities.Exercise;
import database.entities.Workoutday;
import gui.common.Objective;
import gui.exercises.DifficultyLevel;
import logic.TupleInt;

public class ExerciseChooser {

	private static final int POINTS_FOR_EQUIPMENT_MATCH = 2;
	private static final int POINTS_FOR_OBJECTIVE_MATCH = 4;
	private int exercisesPerDayCount;
	private DifficultyLevel difficulty;
	private Objective objective;
	private boolean hasUserEquipment;

	private List<Exercise> exercisesLib;

	public ExerciseChooser(WorkoutDayPreferences preferences) {
		exercisesPerDayCount = getExercisesPerDayCountByObjective(preferences.getObjective());
		difficulty = preferences.getDifficultyLevel();
		objective = preferences.getObjective();
		hasUserEquipment = preferences.isEquipmentRequired();
	}

	private int getExercisesPerDayCountByObjective(Objective objective) {
		switch (objective) {
		case MassGain:
			return 9;
		case Reduction:
			return 12;
		default:
			return 0;
		}
	}

	public Workoutday generateWorkoutDay() {
		initializeLibrary();
		chooseBestMatchedExercises();
		Workoutday day = new Workoutday();

		Set<Exercise> exercisesForDay = new HashSet<>();

		return null;
	}

	private void initializeLibrary() {
		exercisesLib = DatabaseController.getAll(Exercise.class);
	}

	private List<Exercise> chooseBestMatchedExercises() {
		PriorityQueue<TupleInt> bestMatchedExercises = new PriorityQueue<>();
		for (int libIndex = 0; libIndex < exercisesLib.size(); libIndex++) {
			Exercise currentExercise = exercisesLib.get(libIndex);
			Integer exerciseRate = rateExercise(currentExercise);
			bestMatchedExercises.add(new TupleInt(exerciseRate, libIndex));
			System.out.println(String.format("%s: %d", exercisesLib.get(libIndex).getName(), exerciseRate));
		}
		List<Exercise> exercisesChosen = new ArrayList<>();
		for (int i = 0; i < exercisesPerDayCount; i++) {
			Exercise ex = exercisesLib.get(bestMatchedExercises.poll().b);
			exercisesChosen.add(ex);
		}
		return exercisesChosen;
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

	public static void main(String[] args) {
		WorkoutDayPreferences preferences = new WorkoutDayPreferences();
		preferences.setDifficultyLevel(DifficultyLevel.Easy);
		preferences.setObjective(Objective.MassGain);
		preferences.setEquipmentRequired(true);

		ExerciseChooser ec = new ExerciseChooser(preferences);

		ec.initializeLibrary();
		ec.chooseBestMatchedExercises();

		/*
		 * for (Exercise ex : ) { System.out.println(ex.getName()); }
		 */
	}
}

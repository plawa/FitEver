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
		PriorityQueue<TupleInt> bestItems = new PriorityQueue<>();
		for (int libIndex = 0; libIndex < exercisesLib.size(); libIndex++) {
			Exercise currentExercise = exercisesLib.get(libIndex);
			Integer exerciseRate = rateExercise(currentExercise);
			bestItems.add(new TupleInt(exerciseRate, libIndex));
		}
		List<Exercise> exercisesChosen = new ArrayList<>();
		for (int i = 0; i < exercisesPerDayCount; i++){
			Exercise ex = exercisesLib.get(bestItems.poll().b);
			exercisesChosen.add(ex);
		}
		return exercisesChosen;
	}

	private int rateExercise(Exercise exercise) {
		int points = 0;
		boolean isAbleToExercise = hasUserEquipment || !exercise.isEquipmentRequired();
		boolean doesObjectiveMatch = objective.getObjectiveChar() == exercise.getObjective();
		int difficultyLevelDifference = Math.abs(difficulty.getLevelNumber() - exercise.getDifficultyLevel());

		points += isAbleToExercise ? 1 : -10;
		points += doesObjectiveMatch ? 10 : -10;
		points -= difficultyLevelDifference;

		return points;
	}

	public static void main(String[] args) {
		WorkoutDayPreferences preferences = new WorkoutDayPreferences();
		preferences.setDifficultyLevel(DifficultyLevel.Easy);
		preferences.setObjective(Objective.MassGain);
		preferences.setEquipmentRequired(false);

		ExerciseChooser ec = new ExerciseChooser(preferences);

		ec.initializeLibrary();
		
		for (Exercise ex :  ec.chooseBestMatchedExercises()){
			System.out.println(ex.getName());
		}
	}
}

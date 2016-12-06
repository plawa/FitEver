package database.tools;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import database.controller.DatabaseController;
import database.entities.User;
import logic.enums.Objective;

public class UserTools {

	private UserTools(){}
	
	public static float calculateBMI(final User user) {
		float userActualWeight = retrieveActualWeight(user);
		float heightMeters = user.getHeight() / 100f;

		return userActualWeight / (heightMeters * heightMeters);
	}

	public static int calculateAge(final User user) {
		LocalDate birthDate = LocalDate.fromDateFields(user.getDateOfBirth());
		Period age = Period.fieldDifference(birthDate, LocalDate.now());
		return age.getYears();
	}

	public static float retrieveActualWeight(final User user) {
		return DatabaseController.getUserWeight(user, true);
	}

	public static float retrieveInitialWeight(final User user) {
		return DatabaseController.getUserWeight(user, false);
	}

	public static Objective getUserObjective(User user) {
		if (retrieveActualWeight(user) > user.getGoalWeight()) {
			return Objective.Reduction;
		}
		return Objective.MassGain;
	}
}

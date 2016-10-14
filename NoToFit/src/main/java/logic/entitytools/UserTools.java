package logic.entitytools;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import database.entities.User;

public class UserTools {
	
	public static float calculateBMI(final User user){
		float heightMeters = user.getHeight() / 100f;
		return user.getActualWeight() / (heightMeters*heightMeters);
	}
	
	public static int calculateAge(final User user) {
		LocalDate birthDate = LocalDate.fromDateFields(user.getDateOfBirth());
		Period age = Period.fieldDifference(birthDate, LocalDate.now());
		return age.getYears();
	}	
}

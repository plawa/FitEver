package logic.entitytools;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import database.entities.User;

public class UserTools {
	
	private static BiMap<Character, String> sexTranslations;
	private static BiMap<Character, String> objectiveTranslations;

	static {
		sexTranslations = HashBiMap.create();
		sexTranslations.put('m', "Male");
		sexTranslations.put('f', "Female");
		
		objectiveTranslations = HashBiMap.create();
		objectiveTranslations.put('m', "Mass Gain");
		objectiveTranslations.put('r', "Reduction");
		objectiveTranslations.put('s', "Strength");
	}

	public static float calculateBMI(User user){
		float heightMeters = user.getHeight() / 100f;
		return user.getActualWeight() / (heightMeters*heightMeters);
	}
	
	public static int calculateAge(User user) {
		LocalDate birthDate = LocalDate.fromDateFields(user.getDateOfBirth());
		Period age = Period.fieldDifference(birthDate, LocalDate.now());
		return age.getYears();
	}	
	
	public static String parseUserObjectiveCharToString(Character objectiveChar){
		return objectiveTranslations.getOrDefault(objectiveChar, "Error!");
	}
	
	public static Character parseUserObjectiveStringToChar(String objectiveString){
		return objectiveTranslations.inverse().getOrDefault(objectiveString, 'e');
	}
	
	public static String parseSexCharToString(Character sexChar){
		return sexTranslations.getOrDefault(sexChar, "Error!");
	}
	
	public static Character parseSexStringToChar(String sexString){
		return sexTranslations.inverse().getOrDefault(sexString, 'e');
	}


}

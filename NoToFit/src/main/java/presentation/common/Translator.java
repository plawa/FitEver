package presentation.common;

import java.util.HashMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class Translator {

	private static final String MSG_ERROR = "Error!";
	private static final BiMap<Character, String> sexTranslations;
	private static final BiMap<Character, String> objectiveTranslations;
	private static final HashMap<Integer, String> lifeStyleDescriptions;
	private static final BiMap<Character, String> mealTypeTranslations;

	private static final String DESC_LIFESTYLE_1 = "Mostly you sit in front of your computer all day.";
	private static final String DESC_LIFESTYLE_2 = "Your job is sitting in front of the computer,"
			+ "but you also like to go sometimes for a short walk.";
	private static final String DESC_LIFESTYLE_3 = "Your job may be sitting, but your leave your car"
			+ " at every occasion to take a trip with your bicycle.";
	private static final String DESC_LIFESTYLE_4 = "You play sport few days a week.";
	private static final String DESC_LIFESTYLE_5 = "You are a sportsman/sportswoman.";

	static {
		sexTranslations = HashBiMap.create();
		sexTranslations.put('m', "Male");
		sexTranslations.put('f', "Female");

		objectiveTranslations = HashBiMap.create();
		objectiveTranslations.put('m', "Mass Gain");
		objectiveTranslations.put('r', "Reduction");
		objectiveTranslations.put('s', "Strength");

		lifeStyleDescriptions = new HashMap<>();
		lifeStyleDescriptions.put(1, DESC_LIFESTYLE_1);
		lifeStyleDescriptions.put(2, DESC_LIFESTYLE_2);
		lifeStyleDescriptions.put(3, DESC_LIFESTYLE_3);
		lifeStyleDescriptions.put(4, DESC_LIFESTYLE_4);
		lifeStyleDescriptions.put(5, DESC_LIFESTYLE_5);

		mealTypeTranslations = HashBiMap.create();
		mealTypeTranslations.put('b', "Breakfast");
		mealTypeTranslations.put('m', "Main Dish");
		mealTypeTranslations.put('s', "Supper");
	}

	private Translator(){}
	
	public static String parseSexCharToString(Character sexChar) {
		return sexTranslations.getOrDefault(sexChar, MSG_ERROR);
	}

	public static Character parseSexStringToChar(String sexString) {
		return sexTranslations.inverse().getOrDefault(sexString, 'e');
	}

	public static String parseObjectiveCharToString(Character objectiveChar) {
		return objectiveTranslations.getOrDefault(objectiveChar, MSG_ERROR);
	}

	public static Character parseObjectiveStringToChar(String objectiveString) {
		return objectiveTranslations.inverse().getOrDefault(objectiveString, 'e');
	}

	public static String getLifeStyleDescription(Integer lifeStyleInt) {
		return lifeStyleDescriptions.getOrDefault(lifeStyleInt, MSG_ERROR);
	}

	public static String parseMealTypeCharToString(Character mealType) {
		return mealTypeTranslations.getOrDefault(mealType, MSG_ERROR);
	}

	public static Character parseMealTypeStringToChar(String mealType) {
		return mealTypeTranslations.inverse().getOrDefault(mealType, 'e');
	}

}

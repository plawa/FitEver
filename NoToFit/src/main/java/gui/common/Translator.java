package gui.common;

import java.util.HashMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class Translator {

	private static final BiMap<Character, String> sexTranslations;
	private static final BiMap<Character, String> objectiveTranslations;
	private static final BiMap<Integer, String> somatotypeTranslations;
	private static final HashMap<Integer, String> lifeStyleDescriptions;
	
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

		somatotypeTranslations = HashBiMap.create();
		somatotypeTranslations.put(1, "Ectomorphic");
		somatotypeTranslations.put(2, "Mesomorphic");
		somatotypeTranslations.put(3, "Endomorphic");
		
		lifeStyleDescriptions = new HashMap<>();
		lifeStyleDescriptions.put(1, DESC_LIFESTYLE_1);
		lifeStyleDescriptions.put(2, DESC_LIFESTYLE_2);
		lifeStyleDescriptions.put(3, DESC_LIFESTYLE_3);
		lifeStyleDescriptions.put(4, DESC_LIFESTYLE_4);
		lifeStyleDescriptions.put(5, DESC_LIFESTYLE_5);
	}

	public static String parseSexCharToString(Character sexChar) {
		return sexTranslations.getOrDefault(sexChar, "Error!");
	}

	public static Character parseSexStringToChar(String sexString) {
		return sexTranslations.inverse().getOrDefault(sexString, 'e');
	}

	public static String parseObjectiveCharToString(Character objectiveChar) {
		return objectiveTranslations.getOrDefault(objectiveChar, "Error!");
	}

	public static Character parseObjectiveStringToChar(String objectiveString) {
		return objectiveTranslations.inverse().getOrDefault(objectiveString, 'e');
	}

	public static String parseSomatotypeIntegerToString(Integer somatotypeInteger) {
		return somatotypeTranslations.getOrDefault(somatotypeInteger, "Error!");
	}
	
	public static Integer parseSomatotypeStringToInteger(String somatotypeString) {
		return somatotypeTranslations.inverse().getOrDefault(somatotypeString, 0);
	}
	
	public static String getLifeStyleDescription(Integer lifeStyleInt){
		return lifeStyleDescriptions.getOrDefault(lifeStyleInt, "Error!");
	}
	
	
}

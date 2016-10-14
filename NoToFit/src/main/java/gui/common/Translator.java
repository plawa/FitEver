package gui.common;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class Translator {
	
	private static final BiMap<Character, String> sexTranslations;
	private static final BiMap<Character, String> objectiveTranslations;

	static {
		sexTranslations = HashBiMap.create();
		sexTranslations.put('m', "Male");
		sexTranslations.put('f', "Female");
		
		objectiveTranslations = HashBiMap.create();
		objectiveTranslations.put('m', "Mass Gain");
		objectiveTranslations.put('r', "Reduction");
		objectiveTranslations.put('s', "Strength");
	}
	
	public static String parseObjectiveCharToString(Character objectiveChar){
		return objectiveTranslations.getOrDefault(objectiveChar, "Error!");
	}
	
	public static Character parseObjectiveStringToChar(String objectiveString){
		return objectiveTranslations.inverse().getOrDefault(objectiveString, 'e');
	}
	
	public static String parseSexCharToString(Character sexChar){
		return sexTranslations.getOrDefault(sexChar, "Error!");
	}
	
	public static Character parseSexStringToChar(String sexString){
		return sexTranslations.inverse().getOrDefault(sexString, 'e');
	}


}

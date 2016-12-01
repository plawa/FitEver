package logic.diet;

import database.entities.User;
import database.tools.UserTools; 


public class EnergyRequirementCalculator {

	public static int performCalculation(User user) {
		float weight = UserTools.retrieveActualWeight(user);
		int height = user.getHeight();
		int age = UserTools.calculateAge(user);
		char sex = user.getSex();
		float physicalActivityFactor = 1f + user.getLifeStyle()*0.2f;
		float somatypeFactor = translateSomatypeToFactor(user.getSomatotype());
		
		float bmr = calculateBasicMetabolismRequirement(weight, height, age, sex);
		float dailyReq = calculateDailyEnergyRequirement(bmr, physicalActivityFactor);
		
		return Math.round(dailyReq + somatypeFactor*dailyReq);
	}

	private static float calculateBasicMetabolismRequirement(float weight, int height, int age, Character sex) {
		switch (sex){
		case 'm':
			return 66.5f + 13.7f*weight + 5f*height - 6.8f*age;
		case 'f':
			return 655f + 9.6f*weight + 1.85f*height - 4.7f*age;
		default:
			throw new IllegalArgumentException("Unknown sex identifier.");
		}
	}
	
	private static float calculateDailyEnergyRequirement(float bmr, float physicalActivityFactor) {
		return bmr*physicalActivityFactor;		
	}

	private static float translateSomatypeToFactor(int somatypeIntValue) {
		switch(somatypeIntValue){
		case 1:
			return 0.2f;	//ectomorphic
		case 2:
			return 0.15f;	//mesomorphic (value currently not in use)
		case 3:
			return 0.1f;	//endomorphic
		default:
			throw new IllegalArgumentException("Unknown somatotype code.");
		}
	}

}

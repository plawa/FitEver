package gui.common;

public enum Objective {
	Undefined('u', "Undefined"),
	MassGain('m', "Mass Gain"),
	Reduction('r', "Mass Reduction");
	
	private char objectiveChar;
	private String objectiveName;
	
	private Objective(char objectiveChar, String objectiveName){
		this.objectiveChar = objectiveChar;
		this.objectiveName = objectiveName;
	}
	
	public char getObjectiveChar(){
		return objectiveChar;
	}
	
	public static Objective get(char objectiveChar){
		switch(objectiveChar){
		case 'm': return MassGain;			
		case 'r': return Reduction;
		default: return Undefined;
		}
	}
	
	@Override
	public String toString() {
		return this.objectiveName;
	}
}

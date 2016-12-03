package logic.enums;

public enum Objective {
	Undefined('u', "Undefined"), MassGain('m', "Mass Gain"), Reduction('r', "Mass Reduction");

	private char charID;
	private String objectiveName;

	private Objective(char objectiveChar, String objectiveName) {
		this.charID = objectiveChar;
		this.objectiveName = objectiveName;
	}

	public char getCharID() {
		return charID;
	}

	public static Objective getByChar(Character objectiveChar) {
		if (objectiveChar != null) {
			switch (objectiveChar) {
			case 'm':
				return MassGain;
			case 'r':
				return Reduction;
			}
		}
		return Undefined;
	}

	@Override
	public String toString() {
		return this.objectiveName;
	}
}

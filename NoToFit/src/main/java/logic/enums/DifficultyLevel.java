package logic.enums;

public enum DifficultyLevel {
	Undefined(0), Easy(1), Medium(2), Hard(3);

	private int levelNo;

	private DifficultyLevel(int levelNo) {
		this.levelNo = levelNo;
	}

	public int getLevelNumber() {
		return levelNo;
	}

	public static DifficultyLevel getByNumber(int levelNo) {
		switch (levelNo) {
		case 1:
			return Easy;
		case 2:
			return Medium;
		case 3:
			return Hard;
		default:
			return Undefined;
		}
	}
}

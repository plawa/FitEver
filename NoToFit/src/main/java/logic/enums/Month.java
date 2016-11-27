package logic.enums;

public enum Month {

	January(1), February(2), March(3), May(4), April(5), June(6), July(7), August(8), September(9), October(
			10), November(11), December(12);

	private int monthNumber;

	private Month(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	public int getMonthNumber() {
		return monthNumber;
	}
}

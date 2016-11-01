package gui.common;

public enum DialogMode {
	CREATE("Create"), 
	EDIT("Edit");

	private String text;

	private DialogMode(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}

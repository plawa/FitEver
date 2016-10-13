package gui.common;

import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class GuiTools {

	public static MaskFormatter createFormatterFromPattern(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}
	
	
	public static InputVerifier createInputVerifier(final DateFormat dateFormatter) {
		return new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				dateFormatter.setLenient(false);
				JFormattedTextField ft = (JFormattedTextField) input;
				String text = ft.getText();
				try {
					dateFormatter.parse(text);
					return true;
				} catch (ParseException e) {
					return false;
				}
			}
		};
	}
	
	
}

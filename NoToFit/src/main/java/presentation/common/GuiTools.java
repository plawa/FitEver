package presentation.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class GuiTools {
	
	private final static String DATE_MASK_PATTERN = "##-##-####";
	private final static String DATE_FORMAT = "dd-MM-yyyy";
	private final static DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);	
	
	public static String parseDateToString(Date date){	
		return dateFormatter.format(date);
	}
	
	public static Date parseStringToDate(String dateString){
		Date date = new Date(0); //1970 year set to return if parsing fails
		try {
			 date = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static float parseFloatCommaOrDotSep(String floatCommaOrDotSeparated){
		return Float.parseFloat(floatCommaOrDotSeparated.replace(',', '.'));
	}

	public static MaskFormatter getDefaultDateMaskFormatter(){
		return createDateMaskFormatterFromPattern(DATE_MASK_PATTERN);
	}
	
	public static MaskFormatter createDateMaskFormatterFromPattern(String pattern) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(pattern);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}
	
	public static InputVerifier getDefaultDateInputVerifier(){
		return createDateInputVerifier(dateFormatter);
	}
	
	public static InputVerifier createDateInputVerifier(final DateFormat dateFormatter) {
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

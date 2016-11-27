package presentation.common;

import java.util.Calendar;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class DatePicker extends JDatePickerImpl {
	private static final long serialVersionUID = -1581428859181146266L;

	public DatePicker() {
		super(new JDatePanelImpl(new UtilDateModel()));
		getJFormattedTextField().setValue(Calendar.getInstance());
	}

}

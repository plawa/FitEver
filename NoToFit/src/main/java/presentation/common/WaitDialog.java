package presentation.common;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class WaitDialog extends JDialog {

	private static final long serialVersionUID = -1984606567886986803L;
	private static final String DLG_TITLE = "Please wait...";
	private static final String DEFAULT_MSG = "Please wait until current operation is done.";

	public WaitDialog() {
		this(DEFAULT_MSG);
	}

	public WaitDialog(String message) {
		initializeSwingComponents();
		setMessage(message);
		//setAlwaysOnTop(true);
	}

	public void setMessage(String message) {
		JLabel lblDescription = new JLabel(message);
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 0;
		getContentPane().add(lblDescription, gbc_lblDescription);
	}

	private void initializeSwingComponents() {
		setTitle(DLG_TITLE);
		setType(Type.POPUP);
		setBounds(100, 100, 360, 113);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 1;
		getContentPane().add(progressBar, gbc_progressBar);
	}
}

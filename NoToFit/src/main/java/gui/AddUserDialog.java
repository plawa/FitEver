package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;

public class AddUserDialog extends JDialog {
	private JTextField textFieldName;
	private JTextField textFieldSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUserDialog dialog = new AddUserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUserDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add New User");
		setModal(true);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 70, 158, 0, 49, 59, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 25, 0, 0, 73, 33, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);
		
		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 2;
		gbc_textFieldName.gridy = 1;
		getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 2;
		getContentPane().add(lblSurname, gbc_lblSurname);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldSurname = new GridBagConstraints();
		gbc_textFieldSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSurname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSurname.gridx = 2;
		gbc_textFieldSurname.gridy = 2;
		getContentPane().add(textFieldSurname, gbc_textFieldSurname);
		textFieldSurname.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 6;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		{
			JButton okButton = new JButton("Save");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savePressed();
				}
			});
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.insets = new Insets(0, 0, 5, 5);
			gbc_okButton.gridx = 4;
			gbc_okButton.gridy = 6;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 5;
			gbc_cancelButton.gridy = 6;
			getContentPane().add(cancelButton, gbc_cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		Component horizontalStrut1 = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_horizontalStrut1 = new GridBagConstraints();
		gbc_horizontalStrut1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut1.gridx = 6;
		gbc_horizontalStrut1.gridy = 6;
		getContentPane().add(horizontalStrut1, gbc_horizontalStrut1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 7;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
	}
	
	private void savePressed(){
		
	}
}

package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.common.collect.Lists;

public class CoreConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 2651039040831221995L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDbconnection;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JCheckBox chckbxCreateDatabaseIf;

	public CoreConfigurationDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Configure System Database Connection");
		setBounds(100, 100, 504, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblDatabaseConnectionString = new JLabel("Database Connection String:");
			GridBagConstraints gbc_lblDatabaseConnectionString = new GridBagConstraints();
			gbc_lblDatabaseConnectionString.anchor = GridBagConstraints.WEST;
			gbc_lblDatabaseConnectionString.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatabaseConnectionString.gridx = 1;
			gbc_lblDatabaseConnectionString.gridy = 1;
			contentPanel.add(lblDatabaseConnectionString, gbc_lblDatabaseConnectionString);
		}
		{
			txtDbconnection = new JTextField();
			txtDbconnection.setText("jdbc:mysql://localhost/fitever");
			GridBagConstraints gbc_txtDbconnection = new GridBagConstraints();
			gbc_txtDbconnection.insets = new Insets(0, 0, 5, 5);
			gbc_txtDbconnection.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDbconnection.gridx = 2;
			gbc_txtDbconnection.gridy = 1;
			contentPanel.add(txtDbconnection, gbc_txtDbconnection);
			txtDbconnection.setColumns(10);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.WEST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 1;
			gbc_lblUsername.gridy = 2;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			txtUsername = new JTextField();
			txtUsername.setText("root");
			GridBagConstraints gbc_txtUsername = new GridBagConstraints();
			gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUsername.gridx = 2;
			gbc_txtUsername.gridy = 2;
			contentPanel.add(txtUsername, gbc_txtUsername);
			txtUsername.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.anchor = GridBagConstraints.WEST;
			gbc_lblPassword.gridx = 1;
			gbc_lblPassword.gridy = 3;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			txtPassword = new JTextField();
			txtPassword.setText("root");
			GridBagConstraints gbc_txtPassword = new GridBagConstraints();
			gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
			gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPassword.gridx = 2;
			gbc_txtPassword.gridy = 3;
			contentPanel.add(txtPassword, gbc_txtPassword);
			txtPassword.setColumns(10);
		}
		{
			chckbxCreateDatabaseIf = new JCheckBox(
					"create database if not exists (works only in case you have sufficient rights)");
			GridBagConstraints gbc_chckbxCreateDatabaseIf = new GridBagConstraints();
			gbc_chckbxCreateDatabaseIf.gridwidth = 2;
			gbc_chckbxCreateDatabaseIf.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxCreateDatabaseIf.gridx = 1;
			gbc_chckbxCreateDatabaseIf.gridy = 4;
			contentPanel.add(chckbxCreateDatabaseIf, gbc_chckbxCreateDatabaseIf);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e->tearDown());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e->System.exit(1));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}

	private void tearDown() {
		setVisible(false);
		dispose();
	}

	public List<String> retrieveConfiguration() {
		String dbConnection = txtDbconnection.getText();
		if (chckbxCreateDatabaseIf.isSelected()) {
			dbConnection += "?createDatabaseIfNotExist=true";
		}
		return Lists.newArrayList(dbConnection, txtUsername.getText(), txtPassword.getText());
	}

}

package gui;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class UsersPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UsersPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{225, 0, 10, 0, 64, 117, 0, 0};
		gridBagLayout.rowHeights = new int[]{16, 23, 0, 0, 136, 1, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAddUserModalDialog();
			}
		});
		
		JLabel lblManagment = new JLabel("Managment");
		GridBagConstraints gbc_lblManagment = new GridBagConstraints();
		gbc_lblManagment.insets = new Insets(0, 0, 5, 5);
		gbc_lblManagment.gridx = 5;
		gbc_lblManagment.gridy = 1;
		add(lblManagment, gbc_lblManagment);
		lblManagment.setLabelFor(btnAddUser);
		GridBagConstraints gbc_btnAddUser = new GridBagConstraints();
		gbc_btnAddUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddUser.gridx = 5;
		gbc_btnAddUser.gridy = 2;
		add(btnAddUser, gbc_btnAddUser);
		
		JButton btnEditUser = new JButton("Edit User");
		GridBagConstraints gbc_btnEditUser = new GridBagConstraints();
		gbc_btnEditUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditUser.gridx = 5;
		gbc_btnEditUser.gridy = 3;
		add(btnEditUser, gbc_btnEditUser);

	}
	
	private void handleAddUserModalDialog(){
		AddUserDialog dialog = new AddUserDialog();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
}

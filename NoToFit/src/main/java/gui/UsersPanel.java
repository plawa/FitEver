package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsersPanel extends JPanel {

	private static final long serialVersionUID = -8424451595957894544L;

	/**
	 * Create the panel.
	 */
	public UsersPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{225, 0, 10, 0, 64, 125, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{16, 23, 0, 0, 136, 1, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAddUserModalDialog();
			}
		});
		
		JLabel lblManagement = new JLabel("Management");
		GridBagConstraints gbc_lblManagement = new GridBagConstraints();
		gbc_lblManagement.insets = new Insets(0, 0, 5, 5);
		gbc_lblManagement.gridx = 5;
		gbc_lblManagement.gridy = 1;
		add(lblManagement, gbc_lblManagement);
		lblManagement.setLabelFor(btnAddUser);
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
		AddUserDialog myAddUserDialog = new AddUserDialog();
		myAddUserDialog.setLocationRelativeTo(this);
		myAddUserDialog.setVisible(true);
	}
}

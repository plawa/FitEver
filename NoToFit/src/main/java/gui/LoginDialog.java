package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hibernate.service.spi.ServiceException;

import database.entities.User;
import gui.user.AddUserDialog;
import logic.Login;
import net.miginfocom.swing.MigLayout;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 8472433868284888754L;

	private User authorizedUser;
	private final JPanel contentPanel = new JPanel();
	private JDialog myAddUserDialog;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private Login loginLogic;

	public static void main(String[] args) {
		LoginDialog dialog = new LoginDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		super((Frame) null, true);
		try {
			LoginDialog.this.loginLogic = new Login();
		} catch (ServiceException exc) {
			JOptionPane.showMessageDialog(LoginDialog.this, "Unable to connect database.", "Error!", 0);
			System.exit(1);
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("NoToFit Login");
		setType(Type.POPUP);
		setResizable(false);
		setBounds(100, 100, 450, 242);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 10, 76, 171, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 10, 14, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUsername = new JLabel("Username:");
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.anchor = GridBagConstraints.WEST;
			gbc_lblUsername.gridx = 1;
			gbc_lblUsername.gridy = 1;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			textFieldUsername = new JTextField();
			GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
			gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldUsername.gridx = 2;
			gbc_textFieldUsername.gridy = 1;
			contentPanel.add(textFieldUsername, gbc_textFieldUsername);
			textFieldUsername.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.anchor = GridBagConstraints.WEST;
			gbc_lblPassword.gridx = 1;
			gbc_lblPassword.gridy = 2;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 5);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 2;
			gbc_passwordField.gridy = 2;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new MigLayout("", "[115px][grow][57px][65px]", "[23px]"));
			{
				JButton btnCreateNewUser = new JButton("Create New User");
				btnCreateNewUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						myAddUserDialog = new AddUserDialog();
						myAddUserDialog.setLocationRelativeTo(contentPanel);
						myAddUserDialog.setVisible(true);
					}
				});
				buttonPane.add(btnCreateNewUser, "cell 0 0,alignx left,aligny center");
			}
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String username = textFieldUsername.getText();
						String passwordRaw = passwordField.getText();
						LoginDialog.this.authorizedUser = LoginDialog.this.loginLogic.performLogin(username,
								passwordRaw);

						if (LoginDialog.this.authorizedUser != null) {
							LoginDialog.this.setVisible(false);
							LoginDialog.this.dispose();
						} else {
							JOptionPane.showMessageDialog(LoginDialog.this, "Username or password incorrect.", "Error!",
									2);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton, "cell 2 0,alignx left,aligny center");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, "cell 3 0,alignx left,aligny center");
			}
		}
		setLocationRelativeTo(null);		
	}

	public User getAuthorizedUser() {
		dispose();
		return authorizedUser;
	}

}

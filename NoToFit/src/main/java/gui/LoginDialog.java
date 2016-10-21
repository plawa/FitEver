package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
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
import gui.user.MaintainUserDialog;
import logic.Login;
import net.miginfocom.swing.MigLayout;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 8472433868284888754L;
	private static final String LBL_MSG_IMAGE_NOT_FOUND = "Error! Image not found.";
	private static final String HEADER_IMAGE_PATH = "images\\login_image.png";
	private static final String MSG_LOGIN_DENIED = "Username or password incorrect.";
	private static final String MSG_DATABASE_ERROR = "Unable to reach database.";

	private User authorizedUser = null;
	private final JPanel contentPanel = new JPanel();
	private JDialog myAddUserDialog;
	private JTextField txtFldLogin;
	private JPasswordField passFld;
	private JLabel lblImageLabel;
	private JButton okButton;

	public LoginDialog() {
		super((Frame) null, true);
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setResizable(false);
		initializeFrame();
		initializeLayout();
		initializeSwingComponents();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initializeFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				tearDown();
				System.exit(1);
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("NoToFit Login");
		setBounds(100, 100, 450, 360);
	}

	private void initializeSwingComponents() {
		{
			lblImageLabel = loadHeaderImageLabel();
			GridBagConstraints gbc_lblImageLabel = new GridBagConstraints();
			gbc_lblImageLabel.gridwidth = 4;
			gbc_lblImageLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblImageLabel.gridx = 0;
			gbc_lblImageLabel.gridy = 0;
			contentPanel.add(lblImageLabel, gbc_lblImageLabel);
		}
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
			txtFldLogin = new JTextField();
			GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
			gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldUsername.gridx = 2;
			gbc_textFieldUsername.gridy = 1;
			contentPanel.add(txtFldLogin, gbc_textFieldUsername);
			txtFldLogin.setColumns(10);
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
			passFld = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 5);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 2;
			gbc_passwordField.gridy = 2;
			contentPanel.add(passFld, gbc_passwordField);
		}
		{
			Component bottomStrut = Box.createVerticalStrut(0);
			bottomStrut.setForeground(Color.WHITE);
			GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
			gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
			gbc_bottomStrut.gridx = 2;
			gbc_bottomStrut.gridy = 3;
			contentPanel.add(bottomStrut, gbc_bottomStrut);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new MigLayout("", "[115px][grow][57px][65px]", "[23px]"));
			{
				JButton btnCreateNewUser = new JButton("Create New User");
				btnCreateNewUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						myAddUserDialog = new MaintainUserDialog();
						myAddUserDialog.setLocationRelativeTo(contentPanel);
						myAddUserDialog.setVisible(true);
					}
				});
				buttonPane.add(btnCreateNewUser, "cell 0 0,alignx left,aligny center");
			}
			{
				okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						loginButtonPressed();
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
						tearDown();
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, "cell 3 0,alignx left,aligny center");
			}
		}
	}

	private void initializeLayout() {
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 10, 76, 171, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 10, 14, 0, 13, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
	}

	public User getAuthorizedUser() {
		tearDown();
		return authorizedUser;
	}

	private void loginButtonPressed() {
		try {
			authorizedUser = Login.performLogin(txtFldLogin.getText(), passFld.getText());
		} catch (ServiceException exc) {
			exc.printStackTrace();
			JOptionPane.showMessageDialog(LoginDialog.this, MSG_DATABASE_ERROR, "Error!", 2);
			return;
		}
		if (authorizedUser == null)
			JOptionPane.showMessageDialog(LoginDialog.this, MSG_LOGIN_DENIED, "Error!", 2);
		else
			tearDown();
	}

	private JLabel loadHeaderImageLabel() {
		try {
			BufferedImage image = ImageIO.read(new File(HEADER_IMAGE_PATH));
			return new JLabel(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
			return new JLabel(LBL_MSG_IMAGE_NOT_FOUND);
		}
	}

	private void tearDown() {
		setVisible(false);
		dispose();
	}
}

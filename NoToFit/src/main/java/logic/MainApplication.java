package logic;

import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class MainApplication {

	public static void main(String[] args) {
		LoginDialog myLoginDialog = new LoginDialog();
		myLoginDialog.setVisible(true);
		User loggedUser = myLoginDialog.getAuthorizedUser();
		new MainFrame(loggedUser);
	}
}
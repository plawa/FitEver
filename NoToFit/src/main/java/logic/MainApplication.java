package logic;

import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class MainApplication {

	public static void main(String[] args) {
		// while (true) {
		User loggedUser = new LoginDialog().getAuthorizedUser();
		new MainFrame(loggedUser);
		// }
	}
}

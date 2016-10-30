package logic;

import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class MainApplication {

	private static final String DATABASE_CONTROLLER_BIN_PATH = "database.controller.DatabaseController";

	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName(DATABASE_CONTROLLER_BIN_PATH); //force controller class initialization

		
		User loggedUser = new LoginDialog().getAuthorizedUser();
		new MainFrame(loggedUser);
		
	}
}

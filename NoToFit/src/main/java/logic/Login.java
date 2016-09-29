package logic;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;

public class Login {

	private DatabaseController myDatabaseController;

	public Login() {
		myDatabaseController = new DatabaseController();
	}

	public User performLogin(String login, String passwordRaw) {
		Shadow credentialsFound = myDatabaseController.getShadowEntityByLogin(login);
		if (credentialsFound != null) {
			Shadow credentialsEntered = new Shadow();
			credentialsEntered.setLogin(login);
			credentialsEntered.setAndEncryptPass(passwordRaw);
			if (credentialsEntered.equals(credentialsFound))
				myDatabaseController.startTransaction();
				User loggedUser = credentialsFound.getUser();
				//myDatabaseController.commitTransaction();
				return loggedUser;
		}
		return null;
	}

	public static void main(String[] args) {
		Login loginTest = new Login();
		User result = loginTest.performLogin("pidanciwo", "andrzej123");
		System.out.print(result.getName());
	}

}

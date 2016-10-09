package logic;

import org.hibernate.service.spi.ServiceException;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;

public class Login {

	private DatabaseController myDatabaseController;

	public Login() throws ServiceException {
		myDatabaseController = new DatabaseController();
	}

	public User performLogin(String login, String passwordRaw) {
		Shadow credentialsInDatabase = myDatabaseController.getShadowEntityByLogin(login);
		if (credentialsInDatabase != null) {
			Shadow credentialsEntered = new Shadow();
			credentialsEntered.setLogin(login);
			credentialsEntered.encryptAndSetPass(passwordRaw);
			if (credentialsEntered.equals(credentialsInDatabase))
				return credentialsInDatabase.getUser();
		}
		return null;
	}

}

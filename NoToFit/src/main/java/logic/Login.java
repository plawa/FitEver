package logic;

import database.controller.DatabaseController;
import database.entities.Shadow;
import database.entities.User;
import logic.utils.Encrypter;

public class Login {

	public static User performLogin(String login, String passwordRaw) {
		
		Shadow credentialsInDatabase = DatabaseController.getShadowEntityByLogin(login);
		
		if (credentialsInDatabase != null) {
			Shadow credentialsEntered = new Shadow();
			credentialsEntered.setLogin(login);
			credentialsEntered.setEncryptedPass(Encrypter.encryptWithMD5(passwordRaw));
			if (credentialsEntered.equals(credentialsInDatabase))
				return credentialsInDatabase.getUser();
		}
		return null;
	}
	
	

}

package logic;

import database.controller.DatabaseController;
import database.entities.Shadow;

public class Login {

	private DatabaseController myDatabaseController;
	
	public Login() {
		myDatabaseController = new DatabaseController();
	}

	public boolean performLogin(String login, String passwordRaw){
		Shadow credentialsFound = myDatabaseController.getShadowEntityByLogin(login);
		
		Shadow credentialsEntered = new Shadow();
		credentialsEntered.setLogin(login);
		credentialsEntered.setAndEncryptPass(passwordRaw);
		
		if(credentialsEntered.equals(credentialsFound))
			return true;
		else
			return false;
	}

	
	public static void main(String[] args) {
		Login loginTest = new Login();
		boolean result = loginTest.performLogin("pidanciwo", "andrzej123");
		System.out.print(result);
	}

}

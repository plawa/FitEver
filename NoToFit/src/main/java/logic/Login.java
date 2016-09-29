package logic;

import java.util.List;

import database.controller.DatabaseController;
import database.entities.Shadow;

public class Login {

	private Encrypter myEncrypter;
	private DatabaseController myDatabaseController;
	
	public Login() {
		myEncrypter = new Encrypter();
		myDatabaseController = new DatabaseController();
	}

	public boolean performLogin(String username, String pass){
		/*Shadow account = myDatabaseController.getEntityByID(Shadow.class, username);
		Shadow myCredentials = new Shadow(username, pass);
		for(Shadow account){
			
		}*/
		return false;
	}

	
	public static void main(String[] args) {
		
	}

}

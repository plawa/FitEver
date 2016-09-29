package logic;

import database.entities.Shadow;

public class Login {

	private Encrypter myEncrypter;
	
	public Login() {
		myEncrypter = new Encrypter();
	}

	public boolean performLogin(String username, String pass){
		Shadow myCredentials = new Shadow(username, pass);
		
		return false;
	}

	
	public static void main(String[] args) {
		
	}

}

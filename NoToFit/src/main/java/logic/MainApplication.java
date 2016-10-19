package logic;

import javax.swing.JOptionPane;

import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class MainApplication {

	private static final String DATABASE_CONTROLLER_BIN_PATH = "database.controller.DatabaseController";

	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName(DATABASE_CONTROLLER_BIN_PATH); //force controller class initialization
		// while (true) {
		try{
		User loggedUser = new LoginDialog().getAuthorizedUser();
		new MainFrame(loggedUser);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.toString());
		}
		// }
	}
}

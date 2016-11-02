package logic;

import javax.swing.JOptionPane;

import org.hibernate.service.spi.ServiceException;

import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class MainApplication {

	private static final String MSG_DATABASE_ERROR = "Error with database connection!";
	private static final Object MSG_FATAL_ERROR = "Fatal error occured!";
	private static final String DATABASE_CONTROLLER_BIN_PATH = "database.controller.DatabaseController";

	public static void main(String[] args) throws ClassNotFoundException {
		try{
			Class.forName(DATABASE_CONTROLLER_BIN_PATH); //force controller class initialization
		
			User loggedUser = new LoginDialog().getAuthorizedUser();
			new MainFrame(loggedUser);
		} catch (ServiceException e){
			JOptionPane.showMessageDialog(null, MSG_DATABASE_ERROR);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, MSG_FATAL_ERROR);
		}
	}
}

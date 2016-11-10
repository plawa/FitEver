package logic;

import javax.swing.JOptionPane;

import database.controller.DatabaseController;
import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class ApplicationLauncher {

	private static final String MSG_DATABASE_ERROR = "Error with database connection!";
	private static final String MSG_FATAL_ERROR = "Fatal error occured!";

	private static Thread databaseInitializationThread = initializeDatabaseInitializationThread();
	private static Thread applicationInterfaceThread = initializeApplicationInterfaceThread();

	public static void main() throws InterruptedException {
		databaseInitializationThread.start();
		applicationInterfaceThread.start();
		
		databaseInitializationThread.join();
		applicationInterfaceThread.join();
	}

	private static Thread initializeDatabaseInitializationThread() {
		return new Thread() {
			@Override
			public void run() {
				DatabaseController.tryToInitializeSessionFactory();
			}
		};
	}

	private static Thread initializeApplicationInterfaceThread() {
		return new Thread() {
			@Override
			public void run() {
				try {
					User loggedUser = new LoginDialog().getAuthorizedUser();
					new MainFrame(loggedUser);
				} catch (NoClassDefFoundError e) {
					JOptionPane.showMessageDialog(null, MSG_DATABASE_ERROR);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, MSG_FATAL_ERROR);
				}
			}
		};
	}

}

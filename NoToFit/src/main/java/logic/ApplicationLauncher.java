package logic;

import javax.swing.JOptionPane;

import database.controller.DatabaseController;
import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class ApplicationLauncher {

	private static final String MSG_DATABASE_ERROR = "Error with database connection!";
	private static final String MSG_FATAL_ERROR = "Fatal error occured!";

	private Thread databaseInitializationThread = initializeDatabaseInitializationThread();
	private Thread applicationInterfaceThread = initializeApplicationInterfaceThread();

	public static void main(String[] args) {
		new ApplicationLauncher().launchApplication();
	}

	private void launchApplication() {
		databaseInitializationThread.start();
		applicationInterfaceThread.start();
		try {
			databaseInitializationThread.join();
			applicationInterfaceThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Thread initializeDatabaseInitializationThread() {
		return new Thread() {
			@Override
			public void run() {
				DatabaseController.tryToInitializeSessionFactory();
			}
		};
	}

	private Thread initializeApplicationInterfaceThread() {
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

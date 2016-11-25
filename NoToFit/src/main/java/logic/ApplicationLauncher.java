package logic;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.hibernate.service.spi.ServiceException;

import database.controller.DatabaseController;
import database.entities.User;
import gui.LoginDialog;
import gui.MainFrame;

public class ApplicationLauncher {

	private static final String MSG_DATABASE_ERROR = "Error initializing database connection!";
	private static final String MSG_FATAL_ERROR = "Fatal error occured! Please contact your local support.";
	protected static final String DLG_ERROR_TITLE = "Error!";

	private Thread databaseInitThread = initializeDatabaseInitializationThread();
	private Thread guiThread = initializeApplicationInterfaceThread();
	private Thread.UncaughtExceptionHandler exceptionHandler = initializeExceptionHandlerThread();

	private void launchApplication() {
		databaseInitThread.setUncaughtExceptionHandler(exceptionHandler);
		guiThread.setUncaughtExceptionHandler(exceptionHandler);
		databaseInitThread.start();
		guiThread.start();
		try {
			databaseInitThread.join();
			guiThread.join();
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
				User loggedUser = new LoginDialog().getAuthorizedUser();
				new MainFrame(loggedUser);
			}
		};
	}

	private Thread.UncaughtExceptionHandler initializeExceptionHandlerThread() {
		return new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable exception) {
				exception.printStackTrace();
				if (exception instanceof ServiceException) {
					SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, MSG_DATABASE_ERROR, DLG_ERROR_TITLE, 0));
				} else {
					SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, MSG_FATAL_ERROR, DLG_ERROR_TITLE, 0));
				}
			}
		};
	}

	public static void main(String[] args) {
		new ApplicationLauncher().launchApplication();
	}

}

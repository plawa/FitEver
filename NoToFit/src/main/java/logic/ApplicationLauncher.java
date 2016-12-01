package logic;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.hibernate.service.spi.ServiceException;

import database.controller.DatabaseController;
import database.entities.User;
import presentation.LoginDialog;
import presentation.MainFrame;

public class ApplicationLauncher {

	private static final String MSG_DATABASE_ERROR = "Error initializing database connection!";
	private static final String MSG_FATAL_ERROR = "Fatal error occured! Please contact your local support.";
	protected static final String DLG_ERROR_TITLE = "Error!";

	private Thread databaseInitThread = initializeDatabaseInitializationThread();
	private Thread guiThread = initializeApplicationInterfaceThread();
	private UncaughtExceptionHandler exceptionHandler = initializeExceptionHandlerThread();

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
		return new Thread(() -> DatabaseController.tryToInitializeSessionFactory());
	}

	private Thread initializeApplicationInterfaceThread() {
		return new Thread(() -> {
			User loggedUser = new LoginDialog().getAuthorizedUser();
			new MainFrame(loggedUser);
		});
	}

	private UncaughtExceptionHandler initializeExceptionHandlerThread() {
		return new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable exception) {
				String message;
				if (exception instanceof ServiceException) {
					message = MSG_DATABASE_ERROR;
				} else {
					message = MSG_FATAL_ERROR;
				}
				exception.printStackTrace();
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, message, DLG_ERROR_TITLE, 0));
			}
		};
	}

	public static void main(String[] args) {
		new ApplicationLauncher().launchApplication();
	}

}

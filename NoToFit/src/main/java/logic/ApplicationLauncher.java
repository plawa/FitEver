package logic;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.hibernate.service.spi.ServiceException;

import database.controller.DatabaseController;
import database.entities.User;
import presentation.CoreConfigurationDialog;
import presentation.LoginDialog;
import presentation.MainFrame;

public class ApplicationLauncher {

	private static final String CONFIG_FILE_PATH = "database-config.txt";
	private static final String MSG_DATABASE_ERROR = "Error initializing database connection! If you want to setup new database configuration, simply remove "
			+ CONFIG_FILE_PATH + " from application directory.";
	private static final String MSG_FATAL_ERROR = "Fatal error occured! Please contact your local support.";
	protected static final String DLG_ERROR_TITLE = "Error!";

	private Thread databaseInitThread = initializeDatabaseInitializationThread();
	private Thread guiThread = initializeApplicationInterfaceThread();
	private UncaughtExceptionHandler exceptionHandler = initializeExceptionHandlerThread();

	private void launchApplication() {
		databaseInitThread.setUncaughtExceptionHandler(exceptionHandler);
		guiThread.setUncaughtExceptionHandler(exceptionHandler);
		configureDatabase();
		databaseInitThread.start();
		guiThread.start();
		try {
			databaseInitThread.join();
			guiThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private void configureDatabase() {
		try {
			Path configFile = Paths.get(CONFIG_FILE_PATH);
			if (!configFile.toFile().exists()) {
				createConfigFile(configFile);
			}
			List<String> configLines = Files.readAllLines(configFile);
			DatabaseController.setDatabaseConnectionConfiguration(configLines.get(0), configLines.get(1),
					configLines.get(2));
		} catch (Exception e) {
			e.printStackTrace();
			SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, MSG_FATAL_ERROR, DLG_ERROR_TITLE, 0));
		}
	}

	private void createConfigFile(Path configFile) throws IOException {
		List<String> configuration = new CoreConfigurationDialog().retrieveConfiguration();
		Files.write(configFile, configuration, Charset.forName("UTF-8"));
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
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				e.printStackTrace();
				String message = (e instanceof ServiceException) ? MSG_DATABASE_ERROR : MSG_FATAL_ERROR;
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, message, DLG_ERROR_TITLE, 0));
			}
		};
	}

	public static void main(String[] args) {
		new ApplicationLauncher().launchApplication();
	}

}

package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import database.controller.DatabaseController;
import database.entities.User;
import gui.diets.DietsPanel;
import gui.user.UserPanel;
import gui.workouts.WorkoutsPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3253794944742197441L;
	
	private static final String NOTOFIT_VERSION_HEADER = "NoToFit v0.0.1";

	private User userLogged;
	private ImageIcon userIcon;
	private ImageIcon mealIcon;
	private ImageIcon exerciseIcon;
	private JTabbedPane tabbedPane;

	// FOR DEBUGGING PURPOSES ONLY
	public MainFrame() {
		this(new User());
	}

	public MainFrame(User authorizedUser) {
		if (authorizedUser == null) {
			tidyUp();
		}
		userLogged = authorizedUser;
		initializeInterface();
		setVisible(true);
	}

	public void switchUser() {
		setVisible(false);
		userLogged = new LoginDialog().getAuthorizedUser();
		if (userLogged != null) {
			SwingUtilities.invokeLater(() -> getContentPane().remove(0));
			buildTabbedPane();
		}
		setVisible(true);
	}

	private void initializeInterface() {
		createFrame();
		loadTabbedPaneIcons();
		buildTabbedPane();
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(NOTOFIT_VERSION_HEADER);
		setBounds(100, 100, 863, 550);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
	}

	private void loadTabbedPaneIcons() {
		userIcon = new ImageIcon(getClass().getResource("/images/user_icon.png"));
		mealIcon = new ImageIcon(getClass().getResource("/images/meal_icon.png"));
		exerciseIcon = new ImageIcon(getClass().getResource("/images/exercise_icon.png"));
	}

	private void buildTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		refreshTabs();
		getContentPane().add(tabbedPane);
	}

	private void refreshTabs() {
		tabbedPane.addTab("", userIcon, new UserPanel(userLogged));
		tabbedPane.addTab("", mealIcon, new DietsPanel(userLogged));
		tabbedPane.addTab("", exerciseIcon, new WorkoutsPanel(userLogged));
	}

	public void tidyUp() {
		DatabaseController.tidyUp();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame.this.dispatchEvent(new WindowEvent(MainFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
}

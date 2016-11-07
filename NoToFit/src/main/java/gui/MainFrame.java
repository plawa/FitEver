package gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

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

	// FOR DEBUGGING PURPOSES ONLY
	public MainFrame() {
		this(new User());
	}

	public MainFrame(User authorizedUser) {
		if (authorizedUser == null) {
			System.exit(0);
		}
		this.userLogged = authorizedUser;
		initializeInterface();
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
	}

	private void loadTabbedPaneIcons() {
		userIcon = new ImageIcon(getClass().getResource("/images/user_icon.png"));
		mealIcon = new ImageIcon(getClass().getResource("/images/meal_icon.png"));
		exerciseIcon = new ImageIcon(getClass().getResource("/images/exercise_icon.png"));
	}

	private void buildTabbedPane() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.addTab("", userIcon, new UserPanel(userLogged));
		tabbedPane.addTab("", mealIcon, new DietsPanel(userLogged));
		tabbedPane.addTab("", exerciseIcon, new WorkoutsPanel(userLogged));
		getContentPane().add(tabbedPane);
	}
}

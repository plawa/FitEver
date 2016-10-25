package gui;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import database.entities.User;
import gui.diets.DietsPanel;
import gui.exercises.ExercisesPanel;
import gui.user.UserPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3253794944742197441L;
	private User userLogged;
	private ImageIcon userIcon;
	private ImageIcon mealIcon;
	private ImageIcon exerciseIcon;

	public MainFrame() {
		this(new User());
	}

	public MainFrame(User authorizedUser) {
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
		setTitle("NoToFit 0.0.1");
		setBounds(100, 100, 778, 550);
	}

	private void loadTabbedPaneIcons() {
		userIcon = new ImageIcon(getClass().getResource("/images/user_icon.png"));
		mealIcon = new ImageIcon(getClass().getResource("/images/meal_icon.png"));
		exerciseIcon = new ImageIcon(getClass().getResource("/images/exercise_icon.png"));
	}

	private void buildTabbedPane() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		UserPanel usersPanel = new UserPanel(userLogged);
		usersPanel.setToolTipText("Overview Panel");
		GridBagLayout gridBagLayout = (GridBagLayout) usersPanel.getLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 42, 0 };
		tabbedPane.addTab("", userIcon, usersPanel);
		DietsPanel dietsPanel = new DietsPanel(userLogged);
		dietsPanel.setToolTipText("Diet Panel");
		tabbedPane.addTab("", mealIcon, dietsPanel);
		ExercisesPanel exercisesPanel = new ExercisesPanel();
		exercisesPanel.setToolTipText("Workout Panel");
		tabbedPane.addTab("", exerciseIcon, exercisesPanel);
		getContentPane().add(tabbedPane);
	}
}

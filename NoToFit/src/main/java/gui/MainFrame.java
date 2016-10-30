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
	//	GridBagLayout gridBagLayout = (GridBagLayout) usersPanel.getLayout();
		//gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 42, 0 };
		tabbedPane.addTab("", userIcon, new UserPanel(userLogged));
		tabbedPane.addTab("", mealIcon, new DietsPanel(userLogged));
		tabbedPane.addTab("", exerciseIcon, new WorkoutsPanel(userLogged));
		getContentPane().add(tabbedPane);
	}
}

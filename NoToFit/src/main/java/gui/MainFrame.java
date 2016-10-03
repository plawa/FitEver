package gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import database.entities.Shadow;
import database.entities.User;
import gui.exercises.ExercisesPanel;
import gui.meals.MealsPanel;
import gui.user.UserPanel;

public class MainFrame {

	private JFrame frmNoToFit;
	private User userLogged;
	private ImageIcon userIcon;
	private ImageIcon mealIcon;
	private ImageIcon exerciseIcon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmNoToFit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame(){
		this(new User("name", "surname", new Date(3214122), 'f', 200, 66.9f,
			80.1f, new Integer(10), new Character('s'), (Set) null, new Shadow(), (Set) null));
	}
	
	public MainFrame(User authorizedUser) {
		this.userLogged = authorizedUser;
		initializeInterface();
	}
	
	private void initializeInterface() {
		createFrame();
		loadIcons();
		prepareTabbedPane();
		frmNoToFit.setVisible(true);
	}
	
	private void createFrame(){
		frmNoToFit = new JFrame();
		frmNoToFit.setTitle("NoToFit 0.0.1");
<<<<<<< HEAD
		frmNoToFit.setBounds(100, 100, 778, 550);
=======
		frmNoToFit.setBounds(100, 100, 590, 550);
>>>>>>> refs/remotes/origin/master
		frmNoToFit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void loadIcons(){
		userIcon = new ImageIcon("images\\user_icon.png");		
		mealIcon = new ImageIcon("images\\meal_icon.png");
		exerciseIcon = new ImageIcon("images\\exercise_icon.png");
	}
	private void prepareTabbedPane(){
<<<<<<< HEAD
		frmNoToFit.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		UserPanel usersPanel = new UserPanel(userLogged);
		GridBagLayout gridBagLayout = (GridBagLayout) usersPanel.getLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 42, 0};
		tabbedPane.addTab("Overview", userIcon, usersPanel);
		tabbedPane.addTab("Diet", mealIcon, new MealsPanel());
		tabbedPane.addTab("Workout", exerciseIcon, new ExercisesPanel());
=======
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 550, 450);
		tabbedPane.addTab("Your Stats", userIcon, new UsersPanel());
		tabbedPane.addTab("Meals", mealIcon, new MealsPanel());
		tabbedPane.addTab("Exercises", exerciseIcon, new ExercisesPanel());
>>>>>>> refs/remotes/origin/master
		frmNoToFit.getContentPane().add(tabbedPane);
	}
}

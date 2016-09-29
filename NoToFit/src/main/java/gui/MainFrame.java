package gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame {

	private JFrame frmNoToFit;
	private LoginDialog myLoginDialog;
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
	public MainFrame() {
		
		initialize();
	}

	private boolean login(){
		myLoginDialog = new LoginDialog(frmNoToFit, true);
		myLoginDialog.setVisible(true);
		return false;
		
	}
	
	private void initialize() {
		createFrame();
		login();
		loadIcons();
		prepareTabbedPane();
	}
	
	private void createFrame(){
		frmNoToFit = new JFrame();
		frmNoToFit.setTitle("NoToFit 0.0.1");
		frmNoToFit.setBounds(100, 100, 778, 550);
		frmNoToFit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void loadIcons(){
		userIcon = new ImageIcon("images\\user_icon.png");		
		mealIcon = new ImageIcon("images\\meal_icon.png");
		exerciseIcon = new ImageIcon("images\\exercise_icon.png");
	}
	private void prepareTabbedPane(){
		frmNoToFit.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		UsersPanel usersPanel = new UsersPanel();
		GridBagLayout gridBagLayout = (GridBagLayout) usersPanel.getLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 42, 0};
		tabbedPane.addTab("Your Stats", userIcon, usersPanel);
		tabbedPane.addTab("Meals", mealIcon, new MealsPanel());
		tabbedPane.addTab("Exercises", exerciseIcon, new ExercisesPanel());
		frmNoToFit.getContentPane().add(tabbedPane);
	}
}

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class MainFrame {

	private JFrame frmNoToFit;
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createFrame();
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

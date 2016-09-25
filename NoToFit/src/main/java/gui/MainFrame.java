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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 639, 288);
		tabbedPane.addTab("Test Panel", userIcon, new UsersPanel());
		frmNoToFit.getContentPane().add(tabbedPane);
	}
	
	private void createFrame(){
		frmNoToFit = new JFrame();
		frmNoToFit.setTitle("NoToFit 0.0.1");
		frmNoToFit.setBounds(100, 100, 675, 349);
		frmNoToFit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNoToFit.getContentPane().setLayout(null);
	}
	private void loadIcons(){
		userIcon = new ImageIcon("images\\user_icon.png");		
		mealIcon = new ImageIcon("images\\contact-outline.png");
		exerciseIcon = new ImageIcon("images\\contact-outline.png");
	}
}

package gui.workouts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import database.entities.Exercise;
import database.entities.Workout;
import database.entities.Workoutday;
import gui.common.GuiTools;

public class WorkoutOverviewDialog extends JDialog {

	private static final long serialVersionUID = 4775756922675353668L;
	private final JPanel contentPanel = new JPanel();
	private Workout workoutDisplaying;
	
	
	public WorkoutOverviewDialog() {
		this(new Workout());
	}
	
	public WorkoutOverviewDialog(Workout workout){
		workoutDisplaying = workout;
		initializeSwingComponents();
		refreshWorkoutDetails();
	}

	private void refreshWorkoutDetails() {
		// TODO Auto-generated method stub
		
	}

	private JTabbedPane initializeWorkoutDaysTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		for(Workoutday workoutDay : workoutDisplaying.getWorkoutdays()){
			String dateHeader = GuiTools.parseDateToString(workoutDay.getDate());
			JPanel newPanel = createWorkoutDayPanel(workoutDay.getExercises());
			tabbedPane.add(dateHeader, newPanel);
		}
		return tabbedPane;
	}
	
	private JPanel createWorkoutDayPanel(Set<Exercise> dayExercisesSet){
		JPanel workoutDayPanel = new JPanel();
		workoutDayPanel.setLayout(new BorderLayout());
		
		JScrollPane newScrollPane = new JScrollPane();
		newScrollPane.setViewportView(createWorkoutDayTable(dayExercisesSet));

		workoutDayPanel.add(newScrollPane);
		return workoutDayPanel;
	}
	
	
	private WorkoutDayTable createWorkoutDayTable(Set<Exercise> exercisesList) {
		return new WorkoutDayTable(new ArrayList<>(exercisesList));
	}

	private void initializeSwingComponents() {
		setTitle("Workout Overview");
		setBounds(100, 100, 800, 419);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name: ");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 1;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			JLabel lblValidFrom = new JLabel("Valid From:");
			GridBagConstraints gbc_lblValidFrom = new GridBagConstraints();
			gbc_lblValidFrom.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidFrom.gridx = 4;
			gbc_lblValidFrom.gridy = 1;
			contentPanel.add(lblValidFrom, gbc_lblValidFrom);
		}
		{
			JLabel lblValidTo = new JLabel("Valid To:");
			GridBagConstraints gbc_lblValidTo = new GridBagConstraints();
			gbc_lblValidTo.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidTo.gridx = 4;
			gbc_lblValidTo.gridy = 2;
			contentPanel.add(lblValidTo, gbc_lblValidTo);
		}
		{
			JTabbedPane tabbedPane = initializeWorkoutDaysTabbedPane();
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.gridwidth = 5;
			gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.gridx = 1;
			gbc_tabbedPane.gridy = 4;
			contentPanel.add(tabbedPane, gbc_tabbedPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tearDown();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void tearDown() {
		setVisible(false);
		dispose();
	}

}

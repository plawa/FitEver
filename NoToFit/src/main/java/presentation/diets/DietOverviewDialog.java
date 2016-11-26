package presentation.diets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import database.entities.Diet;
import database.entities.Dietday;
import database.entities.Meal;
import presentation.common.GuiTools;

public class DietOverviewDialog extends JDialog {

	private static final long serialVersionUID = -6225856914630687435L;
	private final JPanel contentPanel = new JPanel();
	private final Diet dietDisplaying;
	private JLabel lblNameValue;
	private JLabel lblCaloriesValue;
	private JLabel lblValidFromValue;
	private JLabel lblValidToValue;

	public DietOverviewDialog() {
		this(new Diet());
	}

	public DietOverviewDialog(Diet dietToShow) {
		dietDisplaying = dietToShow;
		initializeSwingComponents();
		refreshDietDetails();
	}

	private void refreshDietDetails() {
		lblNameValue.setText(dietDisplaying.getName());
		lblCaloriesValue.setText(String.format("%d", dietDisplaying.getDailyReq()));
		lblValidFromValue.setText(GuiTools.parseDateToString(dietDisplaying.getValidFrom()));
		lblValidToValue.setText(GuiTools.parseDateToString(dietDisplaying.getValidTo()));
	}

	private JTabbedPane initializeDietDaysTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		List<Dietday> dietDaysSorted = new ArrayList<>(dietDisplaying.getDietdays());
		
		dietDaysSorted.sort(new Comparator<Dietday>() {
			@Override
			public int compare(Dietday o1, Dietday o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		
		for (Dietday day : dietDaysSorted) {						
			tabbedPane.add(GuiTools.parseDateToString(day.getDate()), createDietDayJPanel(day));
		}
		return tabbedPane;
	}

	private JPanel createDietDayJPanel(Dietday dietDay) {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BorderLayout());

		JScrollPane newScrollPane = new JScrollPane();
		newScrollPane.setViewportView(createDietDayJTable(dietDay.getMeals()));

		newPanel.add(newScrollPane);

		return newPanel;
	}

	private DietDayJTable createDietDayJTable(Set<Meal> dietDayMeals) {
		return new DietDayJTable(new ArrayList<>(dietDayMeals));
	}

	private void initializeSwingComponents() {
		setModal(true);
		setTitle("Diet Overview");
		setBounds(100, 100, 675, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNameDescription = new JLabel("Diet Name: ");
			lblNameDescription.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblNameDescription = new GridBagConstraints();
			gbc_lblNameDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblNameDescription.anchor = GridBagConstraints.WEST;
			gbc_lblNameDescription.gridx = 1;
			gbc_lblNameDescription.gridy = 1;
			contentPanel.add(lblNameDescription, gbc_lblNameDescription);
		}
		{
			lblNameValue = new JLabel("example name");
			lblNameValue.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNameValue = new GridBagConstraints();
			gbc_lblNameValue.anchor = GridBagConstraints.WEST;
			gbc_lblNameValue.insets = new Insets(0, 0, 5, 5);
			gbc_lblNameValue.gridx = 2;
			gbc_lblNameValue.gridy = 1;
			contentPanel.add(lblNameValue, gbc_lblNameValue);
		}
		{
			JLabel lblValidFrom = new JLabel("Valid From:");
			lblValidFrom.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblValidFrom = new GridBagConstraints();
			gbc_lblValidFrom.anchor = GridBagConstraints.WEST;
			gbc_lblValidFrom.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidFrom.gridx = 4;
			gbc_lblValidFrom.gridy = 1;
			contentPanel.add(lblValidFrom, gbc_lblValidFrom);
		}
		{
			lblValidFromValue = new JLabel("10.10.2016");
			lblValidFromValue.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblValidFromValue = new GridBagConstraints();
			gbc_lblValidFromValue.anchor = GridBagConstraints.WEST;
			gbc_lblValidFromValue.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidFromValue.gridx = 5;
			gbc_lblValidFromValue.gridy = 1;
			contentPanel.add(lblValidFromValue, gbc_lblValidFromValue);
		}
		{
			JLabel lblCaloriesDescription = new JLabel("Calories Per Day:");
			lblCaloriesDescription.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblCaloriesDescription = new GridBagConstraints();
			gbc_lblCaloriesDescription.anchor = GridBagConstraints.WEST;
			gbc_lblCaloriesDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblCaloriesDescription.gridx = 1;
			gbc_lblCaloriesDescription.gridy = 2;
			contentPanel.add(lblCaloriesDescription, gbc_lblCaloriesDescription);
		}
		{
			lblCaloriesValue = new JLabel("example owner");
			lblCaloriesValue.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblCaloriesValue = new GridBagConstraints();
			gbc_lblCaloriesValue.anchor = GridBagConstraints.WEST;
			gbc_lblCaloriesValue.insets = new Insets(0, 0, 5, 5);
			gbc_lblCaloriesValue.gridx = 2;
			gbc_lblCaloriesValue.gridy = 2;
			contentPanel.add(lblCaloriesValue, gbc_lblCaloriesValue);
		}
		{
			JLabel lblValidTo = new JLabel("Valid To:");
			lblValidTo.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblValidTo = new GridBagConstraints();
			gbc_lblValidTo.anchor = GridBagConstraints.WEST;
			gbc_lblValidTo.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidTo.gridx = 4;
			gbc_lblValidTo.gridy = 2;
			contentPanel.add(lblValidTo, gbc_lblValidTo);
		}
		{
			lblValidToValue = new JLabel("15.10.2016");
			lblValidToValue.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblValidToValue = new GridBagConstraints();
			gbc_lblValidToValue.anchor = GridBagConstraints.WEST;
			gbc_lblValidToValue.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidToValue.gridx = 5;
			gbc_lblValidToValue.gridy = 2;
			contentPanel.add(lblValidToValue, gbc_lblValidToValue);
		}

		JTabbedPane tabbed = initializeDietDaysTabbedPane();

		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 5;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 4;
		contentPanel.add(tabbed, gbc_tabbedPane);
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

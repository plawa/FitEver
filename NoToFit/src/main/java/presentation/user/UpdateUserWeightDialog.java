package presentation.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Hashtable;

import javax.persistence.PersistenceException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import org.hibernate.service.spi.ServiceException;

import database.controller.DatabaseController;
import database.entities.User;
import database.entities.Weighthistory;
import database.entities.WeighthistoryId;
import presentation.common.DatePicker;

public class UpdateUserWeightDialog extends JDialog {
	private static final long serialVersionUID = 2665625473604154239L;
	private static final float SLIDER_VALUE_FACTOR = 0.1f;

	private static final String MSG_SAVE_ERROR = "An error occured! Unable to store data to the database.";
	private static final Object MSG_DUPLICATE_ENTRY = "Entry for given date and user already exists!";

	private User userToMaintain;
	private float oldWeight;
	private float newWeight;
	private Weighthistory savedWeightEntry = null;

	private final JPanel contentPanel = new JPanel();
	private JSlider slider;
	private JLabel lblValueDifference;
	private JLabel lblValueNewWeight;
	private DatePicker datePicker;

	public UpdateUserWeightDialog(User user, float oldWeight) {
		this.userToMaintain = user;
		this.oldWeight = oldWeight;
		initializeFrame();
		initializeLayout();
		initializeSwingComponents();
	}

	private void initializeSwingComponents() {
		{
			Component topStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_topStrut = new GridBagConstraints();
			gbc_topStrut.insets = new Insets(0, 0, 5, 5);
			gbc_topStrut.gridx = 1;
			gbc_topStrut.gridy = 0;
			contentPanel.add(topStrut, gbc_topStrut);
		}
		{
			JLabel lblDescriptionOldWeight = new JLabel("Old Weight:");
			lblDescriptionOldWeight.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblDescriptionOldWeight = new GridBagConstraints();
			gbc_lblDescriptionOldWeight.anchor = GridBagConstraints.WEST;
			gbc_lblDescriptionOldWeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescriptionOldWeight.gridx = 1;
			gbc_lblDescriptionOldWeight.gridy = 1;
			contentPanel.add(lblDescriptionOldWeight, gbc_lblDescriptionOldWeight);
		}
		{
			JLabel lblValueOldWeight = new JLabel("");
			lblValueOldWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblValueOldWeight.setText(String.format("%.1f kg", oldWeight));
			GridBagConstraints gbc_lblValueOldWeight = new GridBagConstraints();
			gbc_lblValueOldWeight.anchor = GridBagConstraints.WEST;
			gbc_lblValueOldWeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueOldWeight.gridx = 2;
			gbc_lblValueOldWeight.gridy = 1;
			contentPanel.add(lblValueOldWeight, gbc_lblValueOldWeight);
		}
		{
			JLabel lblDescriptionDifference = new JLabel("Mass Gain:");
			lblDescriptionDifference.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblDescriptionDifference = new GridBagConstraints();
			gbc_lblDescriptionDifference.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescriptionDifference.gridx = 5;
			gbc_lblDescriptionDifference.gridy = 1;
			contentPanel.add(lblDescriptionDifference, gbc_lblDescriptionDifference);
		}
		{
			lblValueDifference = new JLabel("");
			lblValueDifference.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblValueDifference = new GridBagConstraints();
			gbc_lblValueDifference.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueDifference.gridx = 6;
			gbc_lblValueDifference.gridy = 1;
			contentPanel.add(lblValueDifference, gbc_lblValueDifference);
		}
		{
			JLabel lblDescriptionNewWeight = new JLabel("New Weight:");
			lblDescriptionNewWeight.setForeground(Color.GRAY);
			GridBagConstraints gbc_lblDescriptionNewWeight = new GridBagConstraints();
			gbc_lblDescriptionNewWeight.anchor = GridBagConstraints.WEST;
			gbc_lblDescriptionNewWeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescriptionNewWeight.gridx = 1;
			gbc_lblDescriptionNewWeight.gridy = 2;
			contentPanel.add(lblDescriptionNewWeight, gbc_lblDescriptionNewWeight);
		}
		{
			lblValueNewWeight = new JLabel("");
			lblValueNewWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblValueNewWeight = new GridBagConstraints();
			gbc_lblValueNewWeight.anchor = GridBagConstraints.WEST;
			gbc_lblValueNewWeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueNewWeight.gridx = 2;
			gbc_lblValueNewWeight.gridy = 2;
			contentPanel.add(lblValueNewWeight, gbc_lblValueNewWeight);
		}
		{
			Component leftStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_leftStrut = new GridBagConstraints();
			gbc_leftStrut.insets = new Insets(0, 0, 5, 5);
			gbc_leftStrut.gridx = 0;
			gbc_leftStrut.gridy = 4;
			contentPanel.add(leftStrut, gbc_leftStrut);
		}
		{

			slider = new JSlider();
			slider.addChangeListener(e -> updateDifferenceLabel());
			slider.setPaintTicks(true);

			slider.setMinimum(-20);
			slider.setMaximum(20);
			slider.setValue(0);
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider.gridwidth = 6;
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 1;
			gbc_slider.gridy = 4;

			Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();
			sliderLabels.put(0, new JLabel("0"));
			sliderLabels.put(20, new JLabel("+"));
			sliderLabels.put(-20, new JLabel("-"));
			slider.setLabelTable(sliderLabels); 
			slider.setPaintLabels(true);

			contentPanel.add(slider, gbc_slider);
		}
		{
			Component rightStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_rightStrut = new GridBagConstraints();
			gbc_rightStrut.insets = new Insets(0, 0, 5, 0);
			gbc_rightStrut.gridx = 7;
			gbc_rightStrut.gridy = 4;
			contentPanel.add(rightStrut, gbc_rightStrut);
		}
		{
			JLabel lblPickDate = new JLabel("Pick date: ");
			GridBagConstraints gbc_lblPickDate = new GridBagConstraints();
			gbc_lblPickDate.anchor = GridBagConstraints.EAST;
			gbc_lblPickDate.insets = new Insets(0, 0, 0, 5);
			gbc_lblPickDate.gridx = 3;
			gbc_lblPickDate.gridy = 5;
			contentPanel.add(lblPickDate, gbc_lblPickDate);
		}
		{
			datePicker = new DatePicker();
			GridBagConstraints gbc_datePicker = new GridBagConstraints();
			gbc_datePicker.gridwidth = 3;
			gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
			gbc_datePicker.insets = new Insets(0, 0, 0, 5);
			gbc_datePicker.gridx = 4;
			gbc_datePicker.gridy = 5;
			contentPanel.add(datePicker, gbc_datePicker);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(e -> updateButtonPressed());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> tearDown());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void initializeLayout() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 4.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
	}

	private void initializeFrame() {
		setModal(true);
		setTitle("Update Actual User Weight");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 284);
	}

	protected void updateButtonPressed() {
		Calendar dateInput = (Calendar) datePicker.getJFormattedTextField().getValue();
		Weighthistory newHistoryEntry = new Weighthistory(new WeighthistoryId(userToMaintain, dateInput.getTime()),
				newWeight);
		try {
			DatabaseController.saveEntityToDatabase(newHistoryEntry);
			tearDown();
			savedWeightEntry = newHistoryEntry;
		} catch (ServiceException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, MSG_SAVE_ERROR);
		} catch (PersistenceException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, MSG_DUPLICATE_ENTRY);
		}
	}

	private void tearDown() {
		setVisible(false);
		dispose();
	}

	private void updateDifferenceLabel() {
		newWeight = oldWeight + slider.getValue() * SLIDER_VALUE_FACTOR;
		lblValueNewWeight.setText(String.format("%.1f kg", newWeight));
		float differencePercentage = 100f * newWeight / oldWeight - 100f;
		lblValueDifference.setText(String.format("%.2f %%", differencePercentage));
	}

	public Weighthistory getNewSavedWeightEntry() {
		return savedWeightEntry;
	}

}

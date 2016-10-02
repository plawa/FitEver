package gui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import database.controller.DatabaseController;
import database.entities.User;

public class UpdateUserStatsDialog extends JDialog {

	private static final long serialVersionUID = 2665625473604154239L;
	private final JPanel contentPanel = new JPanel();
	private User userToMaintain;
	private JSlider slider;
	private DatabaseController db;
	private JLabel lblValueDifference;
	private JLabel lblValueNewWeight;
	private float newWeight;

	public UpdateUserStatsDialog(User user) {
		db = new DatabaseController();
		userToMaintain = user;
		setModal(true);
		setTitle("Update Actual User Weight");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
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
			lblValueOldWeight.setText(String.format("%.1f kg", userToMaintain.getActualWeight()));
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
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					newWeight = userToMaintain.getActualWeight() + slider.getValue()*0.1f;
					lblValueNewWeight.setText(String.format("%.1f kg", newWeight));
					float differencePercentage = 100f * newWeight / userToMaintain.getActualWeight() - 100f;
					lblValueDifference.setText(String.format("%.2f %%", differencePercentage)); 
				}
			});
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(1);

			slider.setMinimum(-20);
			slider.setMaximum(20);
			slider.setValue(0);
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider.gridwidth = 6;
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 1;
			gbc_slider.gridy = 4;
			
	        Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();
	        sliderLabels.put(0, new JLabel("0"));
	        sliderLabels.put(20, new JLabel("+"));
	        sliderLabels.put(-20, new JLabel("-"));
	        slider.setLabelTable(sliderLabels);
			
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
			Component bottomStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_bottomStrut = new GridBagConstraints();
			gbc_bottomStrut.insets = new Insets(0, 0, 0, 5);
			gbc_bottomStrut.gridx = 1;
			gbc_bottomStrut.gridy = 5;
			contentPanel.add(bottomStrut, gbc_bottomStrut);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						proceedUpdate();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UpdateUserStatsDialog.this.setVisible(false);
						UpdateUserStatsDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void proceedUpdate() {
		userToMaintain.setActualWeight(newWeight);
		db.updateEntityToDatabase(userToMaintain);
		setVisible(false);
		dispose();
	}

}

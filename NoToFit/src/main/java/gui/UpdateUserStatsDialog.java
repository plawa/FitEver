package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.transaction.UserTransaction;

import database.entities.User;

import java.awt.GridBagLayout;
import javax.swing.JSlider;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window.Type;

public class UpdateUserStatsDialog extends JDialog {

	private static final long serialVersionUID = 2665625473604154239L;
	private final JPanel contentPanel = new JPanel();
	private User userToMaintain;

	public UpdateUserStatsDialog(User user) {
		userToMaintain = user;
		setModal(true);
		setTitle("Update Actual User Weight");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 204);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JSlider slider = new JSlider();
			slider.setSnapToTicks(true);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.setMinimum(Math.round(userToMaintain.getActualWeight() * 0.9f));
			slider.setMaximum(Math.round(userToMaintain.getActualWeight() * 1.1f));
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider.gridwidth = 5;
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 1;
			gbc_slider.gridy = 3;
			contentPanel.add(slider, gbc_slider);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

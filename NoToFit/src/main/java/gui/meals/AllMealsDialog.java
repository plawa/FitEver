package gui.meals;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.controller.DatabaseController;
import database.entities.Meal;

public class AllMealsDialog extends JDialog {

	private static final long serialVersionUID = 8721491617478602101L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private MealsTableModel tableModel;

	public static void main(String[] args) {
		try {
			AllMealsDialog dialog = new AllMealsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AllMealsDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Meals Library");
		setBounds(100, 100, 660, 478);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JToolBar toolBar = new JToolBar();
			toolBar.setRollover(true);
			toolBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
			toolBar.setOrientation(SwingConstants.VERTICAL);
			toolBar.setFloatable(false);
			GridBagConstraints gbc_toolBar = new GridBagConstraints();
			gbc_toolBar.insets = new Insets(0, 0, 5, 5);
			gbc_toolBar.anchor = GridBagConstraints.NORTH;
			gbc_toolBar.gridx = 2;
			gbc_toolBar.gridy = 1;
			contentPanel.add(toolBar, gbc_toolBar);
			{
				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addMeal();
					}
				});
				btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
				btnAdd.setIcon(new ImageIcon(
						AllMealsDialog.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
				toolBar.add(btnAdd);
			}
			{
				JButton btnEdit = new JButton("Edit");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						editMeal();
					}
				});
				btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
				btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnEdit.setIcon(new ImageIcon(
						AllMealsDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
				toolBar.add(btnEdit);
			}
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						deleteMeal();
					}
				});
				btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
				btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnDelete.setIcon(new ImageIcon(
						AllMealsDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
				toolBar.add(btnDelete);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				refreshTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JButton closeButton = new JButton("Close");
			GridBagConstraints gbc_closeButton = new GridBagConstraints();
			gbc_closeButton.insets = new Insets(0, 0, 5, 5);
			gbc_closeButton.gridx = 2;
			gbc_closeButton.gridy = 3;
			contentPanel.add(closeButton, gbc_closeButton);
			closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tearDown();
				}
			});
		}
		setVisible(true);
	}

	protected void deleteMeal() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex != -1) {
			int confirmationInput = JOptionPane.showConfirmDialog(contentPanel, "Are you sure to delete?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (confirmationInput == JOptionPane.YES_OPTION) {
				Meal mealToDelete = tableModel.getMealAt(rowIndex);
				DatabaseController.deleteEntityFromDatabase(mealToDelete);
				refreshTable();
			}
		} else {
			JOptionPane.showMessageDialog(contentPanel, "No row selected!", "Error", 0);
		}
	}

	protected void editMeal() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex != -1) {
			Meal mealToEdit = tableModel.getMealAt(rowIndex);
			EditMealDialog editMealDlg = new EditMealDialog(mealToEdit);
			editMealDlg.setLocationRelativeTo(this);
			editMealDlg.setVisible(true);
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(contentPanel, "No row selected!", "Error", 0);
		}
	}

	protected void addMeal() {
		AddMealDialog addMealDlg = new AddMealDialog();
		addMealDlg.setLocationRelativeTo(this);
		addMealDlg.setVisible(true);
		refreshTable();
	}

	protected void refreshTable() {
		tableModel = new MealsTableModel(DatabaseController.getAll(Meal.class));
		table.setModel(tableModel);
	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}
}

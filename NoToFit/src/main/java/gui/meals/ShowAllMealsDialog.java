package gui.meals;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import database.controller.DatabaseController;
import database.entities.Meal;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.enterprise.inject.Default;
import javax.swing.ImageIcon;
import java.awt.Component;

public class ShowAllMealsDialog extends JDialog {

	private static final long serialVersionUID = 8721491617478602101L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private MealsTableModel tableModel;


	public static void main(String[] args) {
		try {
			ShowAllMealsDialog dialog = new ShowAllMealsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ShowAllMealsDialog() {
		tableModel = new MealsTableModel(new DatabaseController().getAll(Meal.class));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Meals Library");
		setBounds(100, 100, 660, 478);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JToolBar toolBar = new JToolBar();
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
				btnAdd.setIcon(new ImageIcon(ShowAllMealsDialog.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
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
				btnEdit.setIcon(new ImageIcon(ShowAllMealsDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
				toolBar.add(btnEdit);
			}
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
				btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnDelete.setIcon(new ImageIcon(ShowAllMealsDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
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
				table.setModel(tableModel );
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
	}

	protected void editMeal() {
		Meal mealToEdit = tableModel.getMealAt(table.getSelectedRow());
		EditMealDialog editMealDlg = new EditMealDialog(mealToEdit);
		editMealDlg.setLocationRelativeTo(this);
		editMealDlg.setVisible(true);
	}

	protected void addMeal() {
		AddMealDialog addMealDlg = new AddMealDialog();
		addMealDlg.setLocationRelativeTo(this);
		addMealDlg.setVisible(true);
	}

	protected void tearDown() {
		setVisible(false);
		dispose();
	}
	
	protected DefaultTableModel getTableModelWithAllMealEntities(){
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] {
				"Name", "Grammage (g)", "Carbohydrates (%)", "Protein (%)", "Fat (%)"
			});
		List<Meal> allMeals = new DatabaseController().getAll(Meal.class);
		for (Meal meal : allMeals){
			Object [] rowData = new Object [] { 
				meal.getName(),
				meal.getGramature(),
				meal.getCarbohydratesPercentage(),
				meal.getProteinPercentage(),
				meal.getFatPercentage()					
			};
			tableModel.addRow(rowData);
		}
		return tableModel;
	}
}

package presentation.charts;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

public class BarChartFX {

	private String title = "Undefined Title";
	private ObservableList<PieChart.Data> pieChartData;

	public BarChartFX() {
		this("No title given");
	}
	
	public BarChartFX(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ObservableList<PieChart.Data> getPieChartData() {
		return pieChartData;
	}

	public void setPieChartData(ObservableList<PieChart.Data> pieChartData) {
		this.pieChartData = pieChartData;
	}

	public Scene getScene() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> chart = new BarChart<String, Number>(xAxis, yAxis);
		xAxis.setLabel("Country");
		yAxis.setLabel("Value");

		Scene scene = new Scene(new Group(), Color.TRANSPARENT);
		chart.setTitle(title);

		((Group) scene.getRoot()).getChildren().add(chart);
		return scene;
	}

}
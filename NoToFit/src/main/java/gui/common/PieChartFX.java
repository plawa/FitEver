package gui.common;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

public class PieChartFX {

	private String title = "Undefined Title";
	private ObservableList<PieChart.Data> pieChartData;

	public PieChartFX() {

	}

	public PieChartFX(String title) {
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
		Scene scene = new Scene(new Group(), Color.TRANSPARENT);
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle(title);

		((Group) scene.getRoot()).getChildren().add(chart);
		return scene;
	}

}
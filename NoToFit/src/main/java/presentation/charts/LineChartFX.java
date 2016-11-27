package presentation.charts;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class LineChartFX {

	private String xlabelName;
	private String title;

	public LineChartFX() {
		this("No title given", "No label name given");
	}

	public LineChartFX(String title, String xlabelName) {
		this.title = title;
		this.xlabelName = xlabelName;
	}

	public Scene getScene() {
		// defining the axes
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel(xlabelName);
		final NumberAxis yAxis = new NumberAxis();
		
		// creating the chart
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle(title);
		lineChart.setLegendVisible(false);
		
		// defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName("My portfolio");
		// populating the series with data
		series.getData().add(new XYChart.Data(1, 23));
		series.getData().add(new XYChart.Data(2, 14));
		series.getData().add(new XYChart.Data(3, 15));
		series.getData().add(new XYChart.Data(4, 24));
		series.getData().add(new XYChart.Data(5, 34));
		series.getData().add(new XYChart.Data(6, 36));
		series.getData().add(new XYChart.Data(7, 22));
		series.getData().add(new XYChart.Data(8, 45));
		series.getData().add(new XYChart.Data(9, 43));
		series.getData().add(new XYChart.Data(10, 17));
		series.getData().add(new XYChart.Data(11, 29));
		series.getData().add(new XYChart.Data(12, 25));

		lineChart.getData().add(series);

		Scene scene = new Scene(new Group(), Color.TRANSPARENT);

		((Group) scene.getRoot()).getChildren().add(lineChart);
		return scene;
	}

}
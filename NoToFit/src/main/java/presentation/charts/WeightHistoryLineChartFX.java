package presentation.charts;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import database.entities.Weighthistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;

public class WeightHistoryLineChartFX {

	private String xlabelName;
	private String title;
	private ObservableList<Series<Number, Number>> allSeries;
	private LineChart<Number, Number> lineChart;

	public WeightHistoryLineChartFX() {
		this("", "");
	}

	public WeightHistoryLineChartFX(String title, String xlabelName) {
		allSeries = FXCollections.observableArrayList();
		this.title = title;
		this.xlabelName = xlabelName;
	}

	public void addWeightHistorySeries(String seriesName, Collection<Weighthistory> weightHistoryEntries) {
		final ObservableList<XYChart.Data<Number, Number>> newSeriesData = FXCollections.observableArrayList();
		for (Weighthistory weightEntry : weightHistoryEntries) {
			newSeriesData.add(new XYChart.Data<>(weightEntry.getDate().getDate(), weightEntry.getWeight()));
		}
		allSeries.add(new XYChart.Series<>("seria", newSeriesData));
	}

	public void updateWeightHistorySeriesWithNewEntry(Weighthistory newWeightHistoryEntry) {
		allSeries.get(0).getData()
				.add(new XYChart.Data<>(newWeightHistoryEntry.getDate().getDate(), newWeightHistoryEntry.getWeight()));
	}

	public Scene createScene() {
		final NumberAxis xAxis = new NumberAxis(1, 31, 1);
		xAxis.setLabel(xlabelName);
		xAxis.setMinorTickVisible(false);

		List<Data<Number, Number>> sorted = allSeries.get(0).getData().sorted(new Comparator<Data<Number, Number>>() {
			@Override
			public int compare(Data<Number, Number> o1, Data<Number, Number> o2) {
				if (o1.getYValue().floatValue() > o2.getYValue().floatValue()) {
					return 1;
				}
				if (o1.getYValue().floatValue() == o2.getYValue().floatValue()) {
					return 0;
				}
				return -1;
			}
		});
		float lowestWeight = sorted.get(0).getYValue().floatValue() - 0.2f;
		float highestWeight = sorted.get(sorted.size() - 1).getYValue().floatValue() + 0.2f;

		final NumberAxis yAxis = new NumberAxis(lowestWeight, highestWeight, 0.1f);
		yAxis.setLabel("Weight (kg)");

		lineChart = new LineChart<>(xAxis, yAxis, allSeries);

		lineChart.setTitle(title);
		lineChart.setLegendVisible(false);
		lineChart.setCreateSymbols(false);
		lineChart.setAnimated(true);

		Scene scene = new Scene(new Group(), Color.TRANSPARENT);

		((Group) scene.getRoot()).getChildren().add(lineChart);
		return scene;
	}

}
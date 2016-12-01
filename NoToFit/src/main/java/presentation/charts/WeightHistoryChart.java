package presentation.charts;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Preconditions;

import database.entities.Weighthistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;

public class WeightHistoryChart {
	private static final String Y_AXIS_TITLE = "Weight (kg)";
	private static final String X_AXIS_TITLE = "Day of Month";
	private static final String SERIES_TITLE = "Series";

	private static final float INITIAL_LOWER_BOUND = 50f;
	private static final float INITIAL_UPPER_BOUND = 80f;

	private ObservableList<Series<Number, Number>> allSeries;
	private LineChart<Number, Number> lineChart;

	public WeightHistoryChart() {
		allSeries = FXCollections.observableArrayList();
	}

	public void setWeightHistorySeries(String seriesName, Collection<Weighthistory> weightHistoryEntries) {
		cleanChart();
		allSeries.add(new Series<>(SERIES_TITLE, convertCollectionToChartDataObservableList(weightHistoryEntries)));
		refreshYAxisRangle();
	}

	private ObservableList<Data<Number, Number>> convertCollectionToChartDataObservableList(
			Collection<Weighthistory> weightHistoryEntries) {
		final ObservableList<Data<Number, Number>> newSeriesData = FXCollections.observableArrayList();
		for (Weighthistory weightEntry : weightHistoryEntries) {
			newSeriesData.add(new Data<>(weightEntry.getId().getDate().getDate(), weightEntry.getWeight()));
		}
		return newSeriesData;
	}

	public void addSinglePointToCurrentSeries(Weighthistory newWeightHistoryEntry) {
		Preconditions.checkNotNull(newWeightHistoryEntry);
		Preconditions.checkArgument(!allSeries.isEmpty());
		Preconditions.checkArgument(!allSeries.get(0).getData().isEmpty());

		allSeries.get(0).getData()
				.add(new Data<>(newWeightHistoryEntry.getId().getDate().getDate(), newWeightHistoryEntry.getWeight()));
		refreshYAxisRangle();
	//	recalculateYAxisBounds();
	}

	public Scene createScene() {
		final NumberAxis xAxis = initializeXAxis();
		final NumberAxis yAxis = intializeYAxis();
		lineChart = initializeLineChart(xAxis, yAxis);

		return buildSceneObject();
	}

	private Scene buildSceneObject() {
		Scene scene = new Scene(new Group(), Color.TRANSPARENT);
		((Group) scene.getRoot()).getChildren().add(lineChart);
		return scene;
	}

	private LineChart<Number, Number> initializeLineChart(final NumberAxis xAxis, final NumberAxis yAxis) {
		LineChart<Number, Number> newLineChart = new LineChart<>(xAxis, yAxis, allSeries);
		newLineChart.setLegendVisible(false);
		newLineChart.setCreateSymbols(false);
		newLineChart.setHorizontalGridLinesVisible(true);
		newLineChart.setPrefHeight(350f);
		return newLineChart;
	}

	private NumberAxis initializeXAxis() {
		final NumberAxis xAxis = new NumberAxis(1, 31, 1);
		xAxis.setLabel(X_AXIS_TITLE);
		xAxis.setMinorTickVisible(false);
		return xAxis;
	}

	private NumberAxis intializeYAxis() {
		final NumberAxis yAxis = new NumberAxis(INITIAL_LOWER_BOUND, INITIAL_UPPER_BOUND, 0.5f);
		yAxis.setLabel(Y_AXIS_TITLE);
		yAxis.setMinorTickVisible(false);
		return yAxis;
	}

//	public void recalculateYAxisBounds() {
//		float lowestWeight = INITIAL_LOWER_BOUND;
//		float highestWeight = INITIAL_UPPER_BOUND;
//
//		List<Data<Number, Number>> sorted = getDataSortedByWeight();
//		if (sorted != null && !sorted.isEmpty()) {
//			lowestWeight = sorted.get(0).getYValue().floatValue() - 0.2f;
//			highestWeight = sorted.get(sorted.size() - 1).getYValue().floatValue() + 0.2f;
//		}
//		refreshYAxisRangle(lowestWeight, highestWeight);
//	}

	private List<Data<Number, Number>> getDataSortedByWeight() {
		if (allSeries.isEmpty() || allSeries.get(0).getData().isEmpty()) {
			return null;
		}
		List<Data<Number, Number>> sorted = allSeries.get(0).getData().sorted(new Comparator<Data<Number, Number>>() {
			@Override
			public int compare(Data<Number, Number> o1, Data<Number, Number> o2) {
				return (o1.getYValue().floatValue() > o2.getYValue().floatValue()) ? 1 : -1;
			}
		});
		return sorted;
	}

	private void refreshYAxisRangle() {
		NumberAxis numberAxis = (NumberAxis) lineChart.getYAxis();
		numberAxis.setAutoRanging(true);
	}

	private void cleanChart() {
		while (allSeries.size() != 0) {
			allSeries.remove(0);
		}
	}

}
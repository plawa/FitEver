package presentation.charts;

import java.util.Collection;

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

	private static final long serialVersionUID = -1581878849641943340L;
	private static final String Y_AXIS_TITLE = "Weight (kg)";
	private static final String X_AXIS_TITLE = "Day of Month";
	private static final String SERIES_TITLE = "Series";

	private static final float INITIAL_LOWER_BOUND = 50f;
	private static final float INITIAL_UPPER_BOUND = 80f;

	private ObservableList<Series<Number, Number>> allSeries;

	public WeightHistoryChart() {
		allSeries = FXCollections.observableArrayList();
	}

	public void setWeightHistorySeries(Collection<Weighthistory> weightHistories) {
		removeAllSeries();
		allSeries.add(
				new Series<>(SERIES_TITLE, convertWeightHistoryCollectionToChartDataObservableList(weightHistories)));
	}

	private ObservableList<Data<Number, Number>> convertWeightHistoryCollectionToChartDataObservableList(
			Collection<Weighthistory> weightHistoryEntries) {
		final ObservableList<Data<Number, Number>> newSeriesData = FXCollections.observableArrayList();
		for (Weighthistory weightEntry : weightHistoryEntries) {
			newSeriesData.add(new Data<>(weightEntry.getId().getDate().getDate(), weightEntry.getWeight()));
		}
		return newSeriesData;
	}

	public void addSinglePointToCurrentSeries(Weighthistory newWeight) {
		Preconditions.checkNotNull(newWeight);
		Preconditions.checkArgument(!allSeries.isEmpty());

		allSeries.get(0).getData().add(new Data<>(newWeight.getId().getDate().getDate(), newWeight.getWeight()));
	}

	public Scene createScene() {
		LineChart<Number, Number> lineChart = initializeLineChart(initializeXAxis(), intializeYAxis());
		return buildSceneObjectWith(lineChart);
	}

	private Scene buildSceneObjectWith(LineChart<Number, Number> lineChart) {
		Scene scene = new Scene(new Group(), Color.TRANSPARENT);
		((Group) scene.getRoot()).getChildren().add(lineChart);
		return scene;
	}

	private LineChart<Number, Number> initializeLineChart(final NumberAxis xAxis, final NumberAxis yAxis) {
		LineChart<Number, Number> newLineChart = new LineChart<>(xAxis, yAxis, allSeries);
		newLineChart.setLegendVisible(false);
		newLineChart.setCreateSymbols(true);
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
		yAxis.setAutoRanging(true);
		return yAxis;
	}

	private void removeAllSeries() {
		while (!allSeries.isEmpty()) {
			allSeries.remove(0);
		}
	}

}
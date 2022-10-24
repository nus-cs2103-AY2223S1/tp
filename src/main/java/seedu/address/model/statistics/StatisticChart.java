package seedu.address.model.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class StatisticChart {

    private final PieChart chart;

    public StatisticChart() {
        ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("NIL", 1));
        this.chart = new PieChart(pieChartData);
    }

    public PieChart getChart() {
        return this.chart;
    }

    public void fillData(StatisticDataList data) {
        ;
    }
}

package seedu.address.model.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class StatisticChart {

    private final PieChart chart;
    private final StatisticDataList data;

    public StatisticChart(StatisticDataList data) {
        this.data = data;
        this.chart = new PieChart(data.asUnmodifiableObservableList());
    }

    public PieChart getChart() {
        return this.chart;
    }

}

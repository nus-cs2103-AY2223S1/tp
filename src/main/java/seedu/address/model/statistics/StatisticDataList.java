package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class StatisticDataList {


    private final ObservableList<StatisticData> chartDataList = FXCollections.observableArrayList();
    private final ObservableList<Data> unmodifiableChartDataList =
            FXCollections.unmodifiableObservableList(this.toPieChartData());


    public void add(StatisticData toAdd) {
        requireNonNull(toAdd);
        this.chartDataList.add(toAdd);
    }

    public ObservableList<Data> asUnmodifiableObservableList() {
        return unmodifiableChartDataList;
    }

    public ObservableList<StatisticData> getChartDataList() {
        return this.chartDataList;
    }

    public ObservableList<Data> toPieChartData() {
        ObservableList<Data> pieChartData = FXCollections.observableArrayList();
        for (StatisticData statData : this.chartDataList) {
            pieChartData.add(statData.getData());
        }
        return pieChartData;
    }
}

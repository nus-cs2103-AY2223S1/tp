package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatisticDataList {


    private final ObservableList<StatisticData> chartDataList = FXCollections.observableArrayList();
    private final ObservableList<StatisticData> unmodifiableChartDataList = FXCollections.unmodifiableObservableList(chartDataList);


    public void add(StatisticData toAdd) {
        requireNonNull(toAdd);
        if (isDuplicateData(toAdd)) {
            ;
        }
        this.chartDataList.add(toAdd);
    }

    public boolean isDuplicateData(StatisticData toCheck) {
        requireNonNull(toCheck);
        return chartDataList.stream().anyMatch(toCheck::isSameTitle);
    }

    public ObservableList<StatisticData> asUnmodifiableObservableList() {
        return unmodifiableChartDataList;
    }

    public ObservableList<StatisticData> getChartDataList() {
        return this.chartDataList;
    }
}

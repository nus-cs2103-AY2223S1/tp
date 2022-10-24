package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import seedu.address.model.statistics.exceptions.DuplicateDataException;

/**
 * A list of {@code StatisticData} that enforces that no 2 StatisticData points have the same name
 * to avoid confusing charts. As such, adding uses StatisticData#isSameName(StatisticData) in order
 * to ensure the StatisticData being added is unique in terms of name.
 *
 * Supports a minimal set of list operations.
 */
public class StatisticDataList {

    //ObservableList of StatisticData points.
    private final ObservableList<StatisticData> chartDataList = FXCollections.observableArrayList();

    //unmodifiable ObservableList<PieChart.Data> that is outputted to set the statistics
    private final ObservableList<Data> unmodifiableChartDataList =
            FXCollections.unmodifiableObservableList(this.toPieChartData());


    /**
     * Adds {@code toAdd} into the ObservableList of StatisticData points.
     * {@code toAdd} cannot be null and cannot have the same name as an existing StatisticData point.
     */
    public void add(StatisticData toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDataException();
        }
        this.chartDataList.add(toAdd);
    }

    /**
     * Returns true if {@code toCheck} shares the same name as a StatisticData point in the list,
     * otherwise returns false.
     */
    public boolean contains(StatisticData toCheck) {
        requireNonNull(toCheck);
        return chartDataList.stream().anyMatch(toCheck::isSameName);
    }

    public ObservableList<Data> asUnmodifiableObservableList() {
        return unmodifiableChartDataList;
    }

    public ObservableList<StatisticData> getChartDataList() {
        return this.chartDataList;
    }

    /**
     * Converts the ObservableList of StatisticData to an ObservableList of PieChart.data.
     */
    public ObservableList<Data> toPieChartData() {
        ObservableList<Data> pieChartData = FXCollections.observableArrayList();
        for (StatisticData statData : this.chartDataList) {
            pieChartData.add(statData.getData());
        }
        return pieChartData;
    }
}

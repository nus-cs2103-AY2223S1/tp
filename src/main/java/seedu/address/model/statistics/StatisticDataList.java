package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import seedu.address.model.statistics.exceptions.DuplicateDataException;

/**
 * A list of {@code StatisticData} that enforces that no 2 StatisticData points have the same name
 * as the charts require unique data points. As such, adding uses StatisticData#isSameName(StatisticData) in order
 * to ensure the StatisticData being added is unique in terms of name.
 *
 * Supports a minimal set of list operations.
 */
public class StatisticDataList {

    //ObservableList of StatisticData points.
    private final ObservableList<StatisticData> chartDataList = FXCollections.observableArrayList();

    /**
     * Adds {@code toAdd} into the ObservableList of StatisticData points.
     * {@code toAdd} cannot be null and cannot have the same name as an existing StatisticData point.
     */
    public void addStatistic(StatisticData toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDataException();
        }
        this.chartDataList.add(toAdd);
    }

    /**
     * Increments {@code value} of StatisticData point by 1 with the same name as specified, else add
     * a new StatisticData point with the specified name.
     */
    public void addToStatistic(String name) {
        for (StatisticData data : chartDataList) {
            if (data.getName().equals(name)) {
                data.updateValueByOne();
                return;
            }
        }
        this.addStatistic(new StatisticData(name, 1.0));
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
        return FXCollections.unmodifiableObservableList(this.toPieChartData());
    }

    public ObservableList<StatisticData> getChartDataList() {
        return this.chartDataList;
    }

    /**
     * Returns true if the StatisticDataList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.chartDataList.size() == 0;
    }

    /**
     * Converts the ObservableList of StatisticData to an ObservableList of PieChart.Data.
     */
    public ObservableList<Data> toPieChartData() {
        ObservableList<Data> pieChartData = FXCollections.observableArrayList();
        for (StatisticData statData : this.chartDataList) {
            pieChartData.add(statData.getData());
        }
        return pieChartData;
    }
}

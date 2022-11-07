package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import seedu.address.model.statistics.exceptions.DuplicateDataException;

/**
 * A list of {@code StatisticData} that enforces that no 2 StatisticData points have the same name
 * as the charts require unique data points. As such, adding uses StatisticData#isSameStatisticData(StatisticData)
 * in order to ensure the StatisticData being added is unique in terms of name.
 *
 * Supports a minimal set of list operations.
 */
public class StatisticDataList {
    //ObservableList of StatisticData points
    private final ObservableList<StatisticData> chartDataList = FXCollections.observableArrayList();
    /**
     * Returns true if the StatisticDataList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.chartDataList.size() == 0;
    }

    /**
     * Returns true if {@code toCheck} shares the same name as a StatisticData point in the list.
     */
    public boolean contains(StatisticData toCheck) {
        requireNonNull(toCheck);
        return chartDataList.stream().anyMatch(toCheck::isSameStatisticData);
    }
    public ObservableList<StatisticData> getChartDataList() {
        return this.chartDataList;
    }
    /**
     * Adds a StatisticData point to the ObservableList.
     * The StatisticData point must not already exist in the list.
     * @param toAdd StatisticData point to be added to ObservableList.
     */
    public void addStatistic(StatisticData toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDataException();
        }
        this.chartDataList.add(toAdd);
    }

    /**
     * Increments {@code value} of StatisticData point with name {@code name} by 1.
     * If StatisticData point with name {@code name} does not exist,
     * adds a new StatisticData point with the specified name to the list.
     */
    public void incrementStatistic(String name) {
        for (StatisticData data : chartDataList) {
            if (data.getName().equals(name)) {
                data.updateValueByOne();
                return;
            }
        }
        this.addStatistic(new StatisticData(name, 1.0));
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

    public ObservableList<Data> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(this.toPieChartData());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof StatisticDataList) {
            return this.getChartDataList()
                    .equals(((StatisticDataList) other).getChartDataList());
        }
        return false;
    }
}

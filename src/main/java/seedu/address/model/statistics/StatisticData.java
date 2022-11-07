package seedu.address.model.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.scene.chart.PieChart.Data;

/**
 * Represents a data point in the Statistical Data for a PieChart.
 * Has a String {@code name} and double {@code value} as it is a piechart datapoint.
 * Guarrantees: Immutable, data points are not null.
 */
public class StatisticData {
    public final Data chartData;
    /**
     * Constructs a {@code StatisticData} with the given {@code name} and {@code value}.
     * {@code name} and {@code value} must not be null.
     */
    public StatisticData(String name, Double value) {
        requireAllNonNull(name, value);
        this.chartData = new Data(name, value);
    }

    public String getName() {
        return this.chartData.getName();
    }

    public double getValue() {
        return this.chartData.getPieValue();
    }

    public Data getData() {
        return this.chartData;
    }

    /**
     * Returns true if both StatisticData points have the same name.
     */
    public boolean isSameStatisticData(StatisticData otherData) {
        //short-circuit if same object
        if (otherData == this) {
            return true;
        }
        return otherData != null
                && otherData.getName().equals(getName());
    }

    /**
     * Increments {@code value} by 1, to facilitate generating of statistics.
     */
    public void updateValueByOne() {
        double initialValue = this.getValue();
        this.chartData.setPieValue(initialValue + 1);
    }

    @Override
    public boolean equals(Object other) {
        //short-circuit if same object
        if (this == other) {
            return true;
        }
        if (other instanceof StatisticData) {
            StatisticData otherData = (StatisticData) other;
            if (this.getName().equals(otherData.getName()) & this.getValue() == otherData.getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}

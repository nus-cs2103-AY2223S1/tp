package seedu.address.model.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.scene.chart.PieChart.Data;

public class StatisticData {

    private final Data chartData;

    public StatisticData(String name, double value) {
        requireAllNonNull(name, value);
        this.chartData = new Data(name, value);
    }

    public boolean isSameTitle(StatisticData otherData) {
        if (otherData == this) {
            return true;
        }
        return otherData != null
                && otherData.getName().equals(getName());
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
}

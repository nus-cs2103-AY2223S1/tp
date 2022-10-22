package seedu.address.model.statistics;

public enum StatisticChartType{

    PIE_CHART("pie"), //NA is the default gender option
    BAR_CHART("bar"),
    LINE_CHART("line");

    private String chartType;


    StatisticChartType(String chartType) {
        this.chartType = chartType;
    }

    @Override
    public String toString() {
        return this.chartType;
    }
}

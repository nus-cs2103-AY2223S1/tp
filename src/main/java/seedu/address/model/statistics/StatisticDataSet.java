package seedu.address.model.statistics;

import java.util.ArrayList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data ;

public class StatisticDataSet {

    private ArrayList<Integer> xData;
    private ArrayList<Integer> yData;
    private final Data chartData;

    private StatisticDataSet(ArrayList<Integer> xData, ArrayList<Integer> yData) {
        this.xData = xData;
        this.yData = yData;
        this.chartData = this.processRawData();
    }

    public static StatisticDataSet createDataSet(ArrayList<Integer> xData, ArrayList<Integer> yData) {
        return new StatisticDataSet(xData, yData);
    }

    public static boolean validateData(ArrayList<Integer> data) {
        return true;
    }

    private Data processRawData() {
        return new Data<>();
    }
}

package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Creates JavaFX charts.
 */
public class ChartUtil {

    // private static final double DEFAULT_TICK_UNIT = 5.0;

    /**
     * Creates a JavaFX BarChart with the given title, axis labels and data points.
     */
    public static BarChart<String, Number> createBarChart(String title, String xLabel, String yLabel,
                                                          Map<String, Number> results, Map<String, Number> maxResult) {
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);
        ArrayList<String> categories = new ArrayList<>();
        for (String string: results.keySet()) {
            categories.add(string);
        }
        xAxis.setCategories(FXCollections.<String>observableArrayList(categories));
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);
        // Disable auto-ranging so that we can configure our own tick units
        yAxis.setAutoRanging(false);

        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle(title);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Your Score");
        for (Map.Entry<String, Number> entry : results.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(series);

        XYChart.Series<String, Number> seriesResult = new XYChart.Series<>();
        seriesResult.setName("Maximum Score");
        for (Map.Entry<String, Number> entry : maxResult.entrySet()) {
            seriesResult.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(seriesResult);

        return barChart;
    }
}

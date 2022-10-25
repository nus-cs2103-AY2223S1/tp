package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * A UI component that displays the line graph.
 */
public class EntryLineChart extends UiPart<Region> {
    private static final String FXML = "EntryLineChart.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.address.ui.EntryLineChart.class);

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private LineChart<String, Number> lineChart;

    /**
     * Creates an {@code EntryLineChart} with the supplied data.
     * @param lineChartData
     */
    public EntryLineChart(XYChart.Series<String, Number> lineChartData) {
        super(FXML);

        yAxis.setLabel("Amount");
        xAxis.setLabel("Date");

        lineChartData.getData().sort(Comparator.comparing(XYChart.Data::getXValue));

        lineChart.getData().clear();
        lineChart.getData().add(lineChartData);
    }
}

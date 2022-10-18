package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * A UI component that displays the line graph
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

    public EntryLineChart(XYChart.Series<String, Number> lineChartData) {
        super(FXML);

        yAxis.setLabel("Amount");
        xAxis.setLabel("Date");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<String, Number>("04-10-2022", 100.00));
        series.getData().add(new XYChart.Data<String, Number>("05-10-2022", 120.00));
        series.getData().add(new XYChart.Data<String, Number>("06-10-2022", 90.00));
        lineChart.getData().clear();
        lineChart.getData().add(lineChartData);
    }
}

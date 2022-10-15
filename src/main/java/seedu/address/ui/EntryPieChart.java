package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * An UI component that displays the PieChart
 */
public class EntryPieChart extends UiPart<Region> {

    private static final String FXML = "EntryPieChart.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.address.ui.EntryPieChart.class);

    @FXML
    private PieChart pieChart;

    /**
     * Creates an {@code EntryPieChart} with specified data
     */
    public EntryPieChart(ObservableList<PieChart.Data> pieChartData) {
        super(FXML);
        pieChart.setData(pieChartData);
    }
}

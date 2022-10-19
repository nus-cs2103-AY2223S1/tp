package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entry.EntryType;

/**
 * Panel containing the pie chart diagram.
 */
public class GraphPanel extends UiPart<Region> {
    private static final String FXML = "GraphPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.address.ui.GraphPanel.class);
    private EntryPieChart pieChart;
    private EntryLineChart lineChart;

    @FXML
    private Label chartTitle;

    @FXML
    private StackPane graphPlaceholder;

    /**
     * TODO: Edit image to pie chart
     * Creates a {@code GraphPanel} with a default pie chart image
     */
    public GraphPanel(EntryType entryType, ObservableList<PieChart.Data> pieChartData) {
        super(FXML);

        this.pieChart = new EntryPieChart(pieChartData);

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            chartTitle.setText("Expenses");
            break;
        case INCOME:
            chartTitle.setText("Income");
            break;
        default:
            break;
        }
        graphPlaceholder.getChildren().add(pieChart.getRoot());

    }

    /**
     * Creates a {@code GraphPanel} with a line chart image
     */
    public GraphPanel(EntryType entryType, XYChart.Series<String, Number> lineGraphData) {
        super(FXML);

        this.lineChart = new EntryLineChart(lineGraphData);

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            chartTitle.setText("Expenses");
            break;
        case INCOME:
            chartTitle.setText("Income");
            break;
        default:
            break;
        }
        graphPlaceholder.getChildren().add(lineChart.getRoot());
    }

}


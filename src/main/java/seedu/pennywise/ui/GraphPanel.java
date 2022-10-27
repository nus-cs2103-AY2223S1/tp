package seedu.pennywise.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.pennywise.commons.core.LogsCenter;
import seedu.pennywise.model.entry.EntryType;

/**
 * Panel containing the pie chart diagram.
 */
public class GraphPanel extends UiPart<Region> {
    private static final String FXML = "GraphPanel.fxml";
    private static final String EXPENSE_CHART_TITLE = "Expenses";
    private static final String INCOME_CHART_TITLE = "Income";
    private final Logger logger = LogsCenter.getLogger(seedu.pennywise.ui.GraphPanel.class);
    private EntryPieChart pieChart;
    private EntryLineChart lineChart;
    private final NoEntryFound noEntryFound = new NoEntryFound();

    @FXML
    private Label chartTitle;

    @FXML
    private StackPane graphPlaceholder;

    /**
     * Creates a {@code GraphPanel} with a default pie chart image.
     */
    public GraphPanel(EntryType entryType, ObservableList<PieChart.Data> pieChartData) {
        super(FXML);
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            chartTitle.setText(EXPENSE_CHART_TITLE);
            break;
        case INCOME:
            chartTitle.setText(INCOME_CHART_TITLE);
            break;
        default:
            break;
        }

        if (pieChartData.size() == 0) {
            graphPlaceholder.getChildren().add(noEntryFound.getRoot());
            return;
        }

        this.pieChart = new EntryPieChart(pieChartData);
        graphPlaceholder.getChildren().add(pieChart.getRoot());

    }

    /**
     * Creates a {@code GraphPanel} with a line chart image.
     */
    public GraphPanel(EntryType entryType, XYChart.Series<String, Number> lineGraphData) {
        super(FXML);
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            chartTitle.setText(EXPENSE_CHART_TITLE);
            break;
        case INCOME:
            chartTitle.setText(INCOME_CHART_TITLE);
            break;
        default:
            break;
        }

        if (lineGraphData.getData().size() == 0) {
            graphPlaceholder.getChildren().add(noEntryFound.getRoot());
            return;
        }

        this.lineChart = new EntryLineChart(lineGraphData);
        graphPlaceholder.getChildren().add(lineChart.getRoot());
    }

}


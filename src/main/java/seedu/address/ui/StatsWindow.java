package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;


/**
 * Controller for a stats page
 */
public class StatsWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(StatsWindow.class);
    private static final String FXML = "StatsWindow.fxml";

    @FXML
    private PieChart chart;

    /**
     * Creates a new StatsWindow.
     *
     * @param root Stage to use as the root of the StatsWindow.
     */
    public StatsWindow(Stage root) {
        super(FXML, root);
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        chart.setData(data);
        chart.setTitle("Statistics");
    }

    /**
     * Creates a new StatsWindow.
     */
    public StatsWindow() {
        this(new Stage());
    }

    /**
     * Replaces the current data used to generate the {@code PieChart} with the new data specified.
     */
    //Solution adapted from: https://stackoverflow.com/questions/36276805/how-to-wrap-text-of-a-javafx-chart-legend
    public void setPieChart(ObservableList<PieChart.Data> newData) {
        chart.setData(newData);
        for (Node node : chart.lookupAll(".chart-legend-item")) {
            if (node instanceof Label) {
                Label legendElement = (Label) node;
                legendElement.setWrapText(true);
                legendElement.setManaged(true);
                legendElement.setPrefWidth(100);
            }
        }
    }

    /**
     * Shows the stats window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing the statistics page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

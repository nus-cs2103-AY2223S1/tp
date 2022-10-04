package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the pie chart diagram.
 */
public class PieChartPanel extends UiPart<Region> {
    private static final String FXML = "PieChartPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.address.ui.PieChartPanel.class);

    @FXML
    private Label pieChartTitle;

    /**
     * TODO: Edit image to pie chart
     * Creates a {@code PieChartPanel} with a default pie chart image
     */
    public PieChartPanel() {
        super(FXML);
        // TODO: Edit to title of time period of pie chart
        pieChartTitle.setText("PennyWise");
    }

}


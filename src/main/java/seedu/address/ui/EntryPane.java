package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing all entries
 */
public class EntryPane {
    private static final String FXML = "EntryPane.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.address.ui.EntryPane.class);

    @FXML
    private Tab expenses;

    @FXML
    private Tab income;


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

package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class FilterStatusDisplay extends UiPart<Region> {
    private static final String FXML = "FilterStatusDisplay.fxml";

    @FXML
    private Label filterStatusDisplay;
    public FilterStatusDisplay() {
        super(FXML);
    }
    public void setFilterStatus(String filterStatus) {
        requireNonNull(filterStatus);
        filterStatusDisplay.setText(filterStatus);
    }
}

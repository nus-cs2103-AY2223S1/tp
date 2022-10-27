package seedu.pennywise.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Section that is displayed in place of a chart when no entry is found.
 */
public class NoEntryFound extends UiPart<Region> {
    private static final String FXML = "NoEntryFound.fxml";
    private static final String NO_ENTRY = "No entry found.";

    @FXML
    private Label noEntryText;

    /**
     * Creates a {@code NoEntryFound} section with the default text.
     */
    public NoEntryFound() {
        super(FXML);
        noEntryText.setText(NO_ENTRY);
    }

}

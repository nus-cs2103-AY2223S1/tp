package seedu.waddle.ui;

import javafx.scene.layout.Region;

/**
 * List panel ui
 */
public abstract class ListPanel extends UiPart<Region> {
    /**
     * Creates a {@code ListPanel} with the given {@code FXML}.
     */
    public ListPanel(String fxml) {
        super(fxml);
    }
}

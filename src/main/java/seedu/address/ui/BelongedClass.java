package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class BelongedClass extends UiPart<Region> {
    private static final String FXML = "BelongedClass.fxml";
    @FXML
    protected Label name;

    /**
     * Creates a {@code BelongedClass} with the given name to display.
     */
    public BelongedClass(String className) {
        super(FXML);
        name.setText(className);
    }
}

package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the property list.
 */
public class PropertyStatusBarFooter extends UiPart<Region> {

    private static final String FXML = "PropertyStatusBarFooter.fxml";

    @FXML
    private Label savePropertyLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public PropertyStatusBarFooter(Path savePropertyLocation) {
        super(FXML);
        savePropertyLocationStatus.setText(Paths.get(".").resolve(savePropertyLocation).toString());
    }
}

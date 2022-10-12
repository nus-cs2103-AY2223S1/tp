package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.item.AbstractContainerItem;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label currGroupStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(String.format("Save Location: %s", Paths.get(".").resolve(saveLocation).toString()));
        updateFooter(null);
    }

    public void updateFooter(AbstractContainerItem o) {
        if (o == null) {
            currGroupStatus.setText("/");
            return;
        }
        currGroupStatus.setText(o.getFullPathName());
    }

}

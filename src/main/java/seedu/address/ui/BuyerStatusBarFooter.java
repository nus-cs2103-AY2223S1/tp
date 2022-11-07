package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the buyer list.
 */
public class BuyerStatusBarFooter extends UiPart<Region> {

    private static final String FXML = "BuyerStatusBarFooter.fxml";

    @FXML
    private Label saveBuyerLocationStatus;

    /**
     * Creates a {@code BuyerStatusBarFooter} with the given {@code Path}.
     */
    public BuyerStatusBarFooter(Path saveBuyerLocation) {
        super(FXML);
        saveBuyerLocationStatus.setText(Paths.get(".").resolve(saveBuyerLocation).toString());
    }

}

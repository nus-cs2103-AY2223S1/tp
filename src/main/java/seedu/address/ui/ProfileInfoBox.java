package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ProfileInfoBox extends UiPart<Region> {
    private static final String FXML = "ProfileInfoBox.fxml";

    @FXML
    private VBox box;

    public ProfileInfoBox() {
        super(FXML);
    }
}

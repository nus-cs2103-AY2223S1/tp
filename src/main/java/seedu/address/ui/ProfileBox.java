package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


public class ProfileBox extends UiPart<Region> {
    private static final String FXML = "ProfileBox.fxml";

    @FXML
    private HBox box;

    public ProfileBox() {
        super(FXML);
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ProfileDisplay extends UiPart<Region> {

    private static final String FXML = "ProfileDisplay.fxml";
    public static final String USER_PROFILE = "user profile";

    @FXML
    private TextField profileDisplay;

    public ProfileDisplay() {
        super(FXML);
    }

    public void setUser() {
        profileDisplay.setText(USER_PROFILE);
    }

}

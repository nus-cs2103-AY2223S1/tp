package seedu.address.ui;

import seedu.address.model.person.Person;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ProfileDisplay extends UiPart<Region> {

    private static final String FXML = "ProfileDisplay.fxml";
    public static final String USER_PROFILE = "user profile";

    @FXML
    private TextField profileDisplay;

    public ProfileDisplay() {
        super(FXML);
        profileDisplay.setText(USER_PROFILE);
    }

}

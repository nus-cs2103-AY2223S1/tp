package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class UserProfile extends UiPart<Region> {

    private static final String FXML = "UserProfile.fxml";
    public static final String USER_PROFILE = "user profile";

    @FXML
    private TextField userProfile;

    public UserProfile() {
        super(FXML);
        userProfile.setText(USER_PROFILE);
    }

}

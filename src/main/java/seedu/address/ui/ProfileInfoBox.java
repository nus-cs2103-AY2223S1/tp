package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ProfileInfoBox extends UiPart<Region> {
    private static final String FXML = "ProfileInfoBox.fxml";

    @FXML
    private VBox box;

    @FXML
    private Label name;

    @FXML
    private Label title;

    @FXML
    private Label github;

    @FXML
    private Label timezone;

    public ProfileInfoBox() {
        super(FXML);
        name.setText("name");
        title.setText("title");
        github.setText("github");
        timezone.setText("time zone");
    }
}

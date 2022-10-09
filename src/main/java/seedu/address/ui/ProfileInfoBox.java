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

    public ProfileInfoBox(String name, String title, String github, String timezone) {
        super(FXML);
        this.name.setText(name);
        this.title.setText(title);
        this.github.setText(github);
        this.timezone.setText(timezone);
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Container for the basic information related to a Contact
 */
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

    /**
     * Initialises a ProfileInfoBox.
     *
     * @param name The Contact's name
     * @param title The Contact's title
     * @param github The Contact's GitHub handle
     * @param timezone The time zone the Contact is in
     */
    public ProfileInfoBox(String name, String title, String github, String timezone) {
        super(FXML);
        this.name.setText(name);
        this.title.setText(title);
        this.github.setText(github);
        this.timezone.setText(timezone);
    }
}

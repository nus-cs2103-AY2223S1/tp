package seedu.condonery.ui;

import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import seedu.condonery.model.client.Client;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";
    private static final String DEFAULT_PROPERTY_IMAGE = "/images/ClientDisplay.png";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PropertyDirectory level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a {@code ClientCode} with the given {@code Person} and index to display.
     */
    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(StringUtils.abbreviate(client.getName().fullName, 140));
        address.setText(StringUtils.abbreviate(client.getAddress().value, 140));
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(StringUtils.abbreviate(tag.tagName, 15))));
        displayPicture.setClip(new Circle(40, 40, 40));
        Path imagePath = client.getImagePath();
        if (imagePath != null) {
            File file = new File(client.getImagePath().toString());
            if (file.exists()) {
                Image img = new Image(file.toURI().toString());
                displayPicture.setImage(img);
            } else {
                Image img = new Image(this.getClass().getResourceAsStream(DEFAULT_PROPERTY_IMAGE));
                displayPicture.setImage(img);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}

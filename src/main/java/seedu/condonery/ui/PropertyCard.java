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
import seedu.condonery.model.property.Property;

/**
 * An UI component that displays information of a {@code Property}.
 */
public class PropertyCard extends UiPart<Region> {

    private static final String FXML = "PropertyListCard.fxml";
    private static final String DEFAULT_PROPERTY_IMAGE = "/images/PropertyDisplay.png";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PropertyDirectory level 4</a>
     */

    public final Property property;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label price;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label propertyType;
    @FXML
    private Label propertyStatus;
    /**
     * Creates a {@code PropertyCode} with the given {@code Property} and index to display.
     */
    public PropertyCard(Property property, int displayedIndex) {
        super(FXML);
        this.property = property;
        id.setText(displayedIndex + ". ");
        name.setText(StringUtils.abbreviate(property.getName().fullName, 140));
        address.setText(StringUtils.abbreviate(property.getAddress().value, 140));
        price.setText("$" + property.getPrice().value);
        property.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(StringUtils.abbreviate(tag.tagName, 15))));
        displayPicture.setClip(new Circle(40, 40, 40));
        propertyType.setText(property.getPropertyTypeEnum().toString());
        propertyStatus.setText(property.getPropertyStatusEnum().toString());
        Path imagePath = property.getImagePath();
        if (imagePath != null) {
            File file = new File(property.getImagePath().toString());
            if (file.exists()) {
                Image img = new Image(file.toURI().toString());
                displayPicture.setImage(img);
            } else {
                Image img = new Image(this.getClass().getResourceAsStream(DEFAULT_PROPERTY_IMAGE));
                displayPicture.setImage(img);
            }
        } else {
            Image img = new Image(this.getClass().getResourceAsStream(DEFAULT_PROPERTY_IMAGE));
            displayPicture.setImage(img);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PropertyCard)) {
            return false;
        }

        // state check
        PropertyCard card = (PropertyCard) other;
        return id.getText().equals(card.id.getText())
                && property.equals(card.property);
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Property;

/**
 * An UI component that displays information of a {@code Buyer}.
 */
public class PropertyCard extends UiPart<Region> {

    private static final String FXML = "PropertyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Property property;

    @FXML
    private HBox cardPane;
    @FXML
    private Label propertyName;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private Label address;
    @FXML
    private Label description;
    @FXML
    private Label ownerName;
    @FXML
    private Label ownerPhone;
    @FXML
    private Label characteristics;

    /**
     * Creates a {@code PropertyCard} with the given {@code Property} and index to display.
     */
    public PropertyCard(Property property, int displayedIndex) {
        super(FXML);
        this.property = property;
        id.setText(displayedIndex + ". ");
        propertyName.setText(property.getPropertyName().fullName);
        price.setText(property.getPrice().value);
        address.setText(property.getAddress().value);
        description.setText(property.getDescription().value);
        characteristics.setText("Characteristics: " + property
                .getCharacteristics().map(Characteristics::toString)
                .orElse("Not Specified"));
        ownerName.setText("Owner Name: " + property.getOwnerName());
        ownerPhone.setText("Owner Phone: " + property.getOwnerPhone());
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

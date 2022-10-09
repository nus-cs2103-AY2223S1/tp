package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Card containing an address and its associated icon.
 */
public class ContactAddressCard extends UiPart<Region> {
    private static final String FXML = "ContactAddressCard.fxml";

    @FXML
    private HBox box;

    @FXML
    private ImageView icon;

    @FXML
    private Label address;

    /**
     * Initialises a ContactAddressCard.
     *
     * @param iconName The name of the icon associated to the address
     * @param address The Contact's address for a particular platform
     */
    public ContactAddressCard(String iconName, String address) {
        super(FXML);
        Image img = new Image(this.getClass().getResourceAsStream("/images/" + iconName + ".png"));
        this.icon.setImage(img);
        this.address.setText(address);
    }
}

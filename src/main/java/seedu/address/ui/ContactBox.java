package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * Container for all the contact addresses of the Contact.
 */
public class ContactBox extends UiPart<Region> {
    private static final String FXML = "ContactBox.fxml";

    @FXML
    private ImageView contactLogo;

    @FXML
    private Label contactLabel;

    /**
     * Initialises a ContactListBox.
     *
     * @param type The type of contact
     * @param name The name of the contact
     */
    public ContactBox(String type, String name) {
        super(FXML);

        contactLogo.setImage(new Image(this.getClass().getResourceAsStream("/images/contact/" + type + ".png")));
        contactLabel.setText(name);
    }
}

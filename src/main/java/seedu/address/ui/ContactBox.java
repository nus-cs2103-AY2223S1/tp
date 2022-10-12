package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.model.person.contact.Contact;

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
     * @param contact Contact
     */
    public ContactBox(Contact contact) {
        super(FXML);

        String type = contact.getContactType().toString().toLowerCase();
        contactLogo.setImage(new Image(this.getClass().getResourceAsStream("/images/contact/" + type + ".png")));
        contactLabel.setText(contact.toString());
    }
}

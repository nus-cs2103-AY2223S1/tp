package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
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
    private Hyperlink contactLabel;

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

        Alert a = new Alert(Alert.AlertType.ERROR);
        contactLabel.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(contact.getLink()));
                } catch (IOException ex) {
                    a.setContentText("Sorry, browser couldn't be opened.");
                    a.show();
                } catch (URISyntaxException ex) {
                    a.setContentText("Sorry, url is invalid");
                    a.show();
                }
            }
        });
    }
}

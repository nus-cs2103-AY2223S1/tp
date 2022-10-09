package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ContactAddressCard extends UiPart<Region> {
    private static final String FXML = "ContactAddressCard.fxml";

    @FXML
    private HBox box;

    public ContactAddressCard() {
        super(FXML);
    }
}

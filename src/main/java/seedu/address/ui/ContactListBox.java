package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ContactListBox extends UiPart<Region> {
    private static final String FXML = "ContactListBox.fxml";

    @FXML
    private HBox box;

    public ContactListBox() {
        super(FXML);
    }
}

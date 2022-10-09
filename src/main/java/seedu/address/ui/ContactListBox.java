package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ContactListBox extends UiPart<Region> {
    private static final String FXML = "ContactListBox.fxml";

    @FXML
    private HBox box;

    @FXML
    private HBox emailAddressContainer;

    @FXML
    private HBox telegramHandleContainer;

    @FXML
    private HBox twitterHandleContainer;

    public ContactListBox(String email, String telegram, String twitter) {
        super(FXML);

        ContactAddressCard emailAddr = new ContactAddressCard("email", email);
        HBox.setHgrow(emailAddr.getRoot(), Priority.ALWAYS);
        emailAddressContainer.getChildren().add(emailAddr.getRoot());

        ContactAddressCard teleHandle = new ContactAddressCard("telegram", telegram);
        HBox.setHgrow(teleHandle.getRoot(), Priority.ALWAYS);
        telegramHandleContainer.getChildren().add(teleHandle.getRoot());

        ContactAddressCard twitterHandle = new ContactAddressCard("twitter", twitter);
        HBox.setHgrow(twitterHandle.getRoot(), Priority.ALWAYS);
        twitterHandleContainer.getChildren().add(twitterHandle.getRoot());

    }
}

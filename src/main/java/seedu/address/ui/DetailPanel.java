package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import seedu.address.model.person.Person;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailPanel extends MainPanel {

    private static final String FXML = "DetailPanel.fxml";

    @FXML
    private HBox profileBoxContainer;

    @FXML
    private HBox contactListBoxContainer;

    @FXML
    private ContactListBox contactListBox;

    @FXML
    private Label contactHeader;

    /**
     * Initialises the DetailPanel.
     *
     * @param person The person whose contact details are to be displayed.
     */
    public DetailPanel(Person person) {
        super(FXML);
        contactHeader.setText("Contact");
        ProfileBox profileBox = new ProfileBox("default",
                                         "name",
                                          "title",
                                        "github",
                                      "timezone");
        HBox.setHgrow(profileBox.getRoot(), Priority.ALWAYS);
        profileBoxContainer.getChildren().add(profileBox.getRoot());

        ContactListBox contactListBox = new ContactListBox("email",
                                                           "telegram",
                                                           "twitter");
        HBox.setHgrow(contactListBox.getRoot(), Priority.ALWAYS);
        contactListBoxContainer.getChildren().add(contactListBox.getRoot());
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Detail;
    }
}

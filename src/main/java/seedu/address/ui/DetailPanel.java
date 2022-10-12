package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.ContactType;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailPanel extends MainPanel {

    private static final String FXML = "DetailPanel.fxml";

    @FXML
    private Circle profileImageContainer;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label timezoneLabel;

    @FXML
    private HBox contactBoxContainer;

    /**
     * Initialises the DetailPanel.
     *
     * @param person The person whose contact details are to be displayed.
     */
    public DetailPanel(Person person) {
        super(FXML);

        nameLabel.setText(person.getName().toString());

        // These are mock data, to be implemented in future
        Image placeholder = new Image(this.getClass().getResourceAsStream("/images/user_placeholder.png"));
        profileImageContainer.setFill(new ImagePattern(placeholder));
        roleLabel.setText("DevOps Engineer");
        timezoneLabel.setText("Local Time: 10.00 am (UTC+8)");

        List<ContactBox> contactBoxList = new ArrayList<ContactBox>(
                person.getContacts()
                        .values().stream()
                        .map(ContactBox::new)
                        .collect(Collectors.toList()));

        contactBoxContainer.getChildren().addAll(contactBoxList.stream()
                .map(ContactBox::getRoot)
                .collect(Collectors.toList()));
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Detail;
    }
}

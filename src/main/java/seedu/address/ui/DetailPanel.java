package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.model.person.Person;

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

        List<ContactBox> contactBoxList = new ArrayList<>();
        contactBoxList.add(new ContactBox("telegram", "hello-world"));
        contactBoxList.add(new ContactBox("email", "hello-world@gmail.com"));
        contactBoxList.add(new ContactBox("slack", "hello-world"));

        for (ContactBox contact : contactBoxList) {
            contactBoxContainer.getChildren().add(contact.getRoot());
        }
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Detail;
    }
}

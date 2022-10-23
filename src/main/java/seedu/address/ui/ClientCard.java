package seedu.address.ui;

import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane products;
    @FXML
    private VBox vbox;
    @FXML
    private VBox meetingsBox;
    @FXML
    private Label numMeetings;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().get().toString());
        email.setText(client.getEmail().get().toString());
        client.getProducts().stream()
                .sorted(Comparator.comparing(product -> product.productName))
                .forEach(product -> products.getChildren().add(new Label(product.productName)));
        if (client.hasMeeting()) {
            List<Meeting> clientMeetings = client.getMeetings();
            numMeetings.setText(Integer.toString(clientMeetings.size()));
            for (Meeting meeting : clientMeetings) {
                String meetingSummary = String.format("•  %s, %s - %s", meeting.getMeetingDate(),
                        meeting.getMeetingTime(), meeting.getDescription());
                Label label = new Label(meetingSummary);
                label.getStyleClass().add("cell_small_label");
                meetingsBox.getChildren().add(label);
            }
        } else {
            meetingsBox.getChildren().clear();
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}

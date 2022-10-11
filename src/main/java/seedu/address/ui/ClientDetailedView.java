package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * GridPane containing a detailed view of a client.
 */
public class ClientDetailedView extends UiPart<Region> {
    private static final String FXML = "ClientDetailedView.fxml";

    public final Client client;

    private final Logger logger = LogsCenter.getLogger(ClientDetailedView.class);


    @FXML
    private Label clientName;

    @FXML
    private Label phone;

    @FXML
    private Label email;

    @FXML
    private Label nextMeeting;

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ClientDetailedView} with the given {@code Client}.
     */
    public ClientDetailedView(Client client) {
        super(FXML);
        this.client = client;
        clientName.setText(client.getName().toString());
        phone.setText(client.getPhone().toString());
        email.setText(client.getEmail().toString());
        if (client.hasMeeting()) {
            Meeting meeting = client.getMeeting();
            nextMeeting.setText(meeting.getMeetingDate().toString() + ", " + meeting.getMeetingTime().toString());
        } else {
            nextMeeting.setText("There are no upcoming meetings.");
        }
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}

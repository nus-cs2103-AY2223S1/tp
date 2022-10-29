package seedu.address.ui;

import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * An UI component that displays information of an {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private FlowPane attendees;
    @FXML
    private HBox dateTime;
    @FXML
    private HBox duration;
    @FXML
    private Label endDateTime;
    @FXML
    private FlowPane tags;

    /**
     * Creates an {@code EventCode} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);

        ImageView durationIcon = new ImageView("/images/duration_icon.png");
        durationIcon.setFitHeight(15);
        durationIcon.setFitWidth(15);
        Label durationLabel = new Label(
                DateTime.getDifferenceString(event.getStartDateTime(), event.getEndDateTime()));

        ImageView dateTimeIcon = new ImageView("/images/dateTime_icon.png");
        dateTimeIcon.setFitHeight(15);
        dateTimeIcon.setFitWidth(15);
        String dateString = String.format("%s - %s",
                event.getStartDateTime().toString(),
                event.getEndDateTime().toString());
        Label dateTimeLabel = new Label(dateString);

        this.event = event;
        id.setText(displayedIndex + ". ");
        title.setText(event.getTitle().title);
        duration.getChildren().add(durationIcon);
        duration.getChildren().add(durationLabel);
        dateTime.getChildren().add(dateTimeIcon);
        dateTime.getChildren().add(dateTimeLabel);

        event.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        List<Profile> attendeesList = event.getAttendeesList();
        attendeesList.stream()
                .forEach(attendee -> {
                    int index = attendeesList.indexOf(attendee) + 1;
                    attendees.getChildren().add(new Label(
                            index + ". " + attendee.getName() + " ("
                            + attendee.getPhone() + ")"));
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";
    private final Meeting meeting;

    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        description.setText(meeting.getDescription().toString());
        date.setText(meeting.getMeetingDate().toString());
        time.setText(meeting.getMeetingTime().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}

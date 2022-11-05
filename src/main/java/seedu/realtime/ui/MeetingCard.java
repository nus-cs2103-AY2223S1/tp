package seedu.realtime.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.realtime.model.meeting.Meeting;



/**
 * A UI component that displays information of a {@code meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "meetingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/realTime-level4/issues/336">The issue on realTime level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label dateTime;
    @FXML
    private Label client;
    @FXML
    private Label listingId;

    /**
     * Creates a {@code meetingCode} with the given {@code meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        name.setText(meeting.getClient().fullName);
        listingId.setText(meeting.getListing().value);
        dateTime.setText(meeting.getdateTime().toString());

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
        return client.getText().equals(card.client.getText())
                && meeting.equals(card.meeting);
    }
}


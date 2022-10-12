package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCondensedView extends UiPart<Region> {

    private static final String FXML = "MeetingCondensedView.fxml";
    private final Meeting meeting;

    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} to display.
     */
    public MeetingCondensedView(Meeting meeting) {
        super(FXML);
        this.meeting = meeting;
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
        if (!(other instanceof MeetingCondensedView)) {
            return false;
        }

        // state check
        MeetingCondensedView card = (MeetingCondensedView) other;
        return meeting.equals(card.meeting);
    }
}

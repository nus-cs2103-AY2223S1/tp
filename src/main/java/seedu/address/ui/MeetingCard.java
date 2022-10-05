package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";

    public final Meeting meeting;

    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label time;

    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        description.setText(meeting.getDescription().toString());
        date.setText(meeting.getMeetingDate().toString());
        time.setText(meeting.getMeetingTime().toString());
    }

}

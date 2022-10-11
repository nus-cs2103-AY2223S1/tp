package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;

/**
 * Panel containing a detailed view of a meeting.
 */
public class MeetingDetailedView extends UiPart<Region> {
    private static final String FXML = "MeetingDetailedView.fxml";

    public final Meeting meeting;

    private final Logger logger = LogsCenter.getLogger(MeetingDetailedView.class);


    @FXML
    private Label clientName;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label date;

    @FXML
    private Label time;

    @FXML
    private Label description;

    /**
     * Creates a {@code MeetingDetailedView} with the given {@code Meeting}.
     */
    public MeetingDetailedView(Meeting meeting) {
        super(FXML);
        this.meeting = meeting;
        clientName.setText(meeting.getClientName().toString());
        phoneNumber.setText(meeting.getClientPhone().toString());
        date.setText(meeting.getMeetingDate().toString());
        time.setText(meeting.getMeetingTime().toString());
        description.setText(meeting.getDescription().toString());
    }
}

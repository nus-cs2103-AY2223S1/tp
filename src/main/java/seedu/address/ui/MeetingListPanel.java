package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Panel containing the list of meetings.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);

    @FXML
    private ListView<Meeting> meetingListView;

    @FXML
    private Label numMeetings;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList}.
     */
    public MeetingListPanel(ObservableList<Meeting> meetingList) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());
        numMeetings.setText(numMeetingsString(meetingList));
        meetingList.addListener((ListChangeListener<? super Meeting>)
                c -> numMeetings.setText(numMeetingsString(meetingList)));
    }

    /**
     * Returns a string denoting the number of records for {@code Meeting} currently shown in the {@code meetingList}.
     */
    private String numMeetingsString(ObservableList<Meeting> meetingList) {
        if (meetingList.size() == 1) {
            return "1 record";
        } else {
            return meetingList.size() + " records";
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(meeting, getIndex() + 1).getRoot());
            }
        }
    }

}

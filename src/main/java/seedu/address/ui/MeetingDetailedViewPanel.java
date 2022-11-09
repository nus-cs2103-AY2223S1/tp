package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * Panel containing the list of meetings.
 */
public class MeetingDetailedViewPanel extends UiPart<Region> {
    private static final String FXML = "MeetingDetailedViewPanel.fxml";

    @FXML
    private ListView<Meeting> detailedMeetingListView;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList}.
     */
    public MeetingDetailedViewPanel(ObservableList<Meeting> detailedMeetingList) {
        super(FXML);
        detailedMeetingListView.setItems(detailedMeetingList);
        detailedMeetingListView.setCellFactory(listView -> new MeetingDetailedViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingDetailedView}.
     */
    class MeetingDetailedViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingDetailedView(meeting).getRoot());
            }
        }
    }

}

package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;

public interface ReadOnlyMeetingList {
    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();
}

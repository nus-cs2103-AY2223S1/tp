package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING_TYPICAL_1;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;


public class MeetingListTest {
    private final MeetingList meetingList = new MeetingList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), meetingList.getMeetingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMeetingList_replacesData() {
        MeetingList newData = getTypicalMeetingList();
        meetingList.resetData(newData);
        assertEquals(newData, meetingList);
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInMeetingList_returnsFalse() {
        assertFalse(meetingList.hasMeeting(MEETING_TYPICAL_1));
    }

    @Test
    public void hasMeeting_meetingInMeetingList_returnsTrue() {
        meetingList.addMeeting(MEETING_TYPICAL_1);
        assertTrue(meetingList.hasMeeting(MEETING_TYPICAL_1));
    }

    @Test
    public void getMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> meetingList.getMeetingList().remove(0));
    }

    /**
     * A stub ReadOnlyMeetingList whose meetings list can violate interface constraints.
     */
    private static class MeetingListStub implements ReadOnlyMeetingList {
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        MeetingListStub(Collection<Meeting> meetings) {
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }

    }
}



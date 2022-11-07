package seedu.address.testutil;

import seedu.address.model.MeetingList;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building MeetingList objects.
 * Example usage: <br>
 *     {@code MeetingList ml = new MeetingListBuilder().withMeeting("MEETING_TYPICAL_1", "MEETING_TYPICAL_2").build();}
 */
public class MeetingListBuilder {
    private MeetingList meetingList;

    public MeetingListBuilder() {
        meetingList = new MeetingList();
    }

    public MeetingListBuilder(MeetingList meetingList) {
        this.meetingList = meetingList;
    }

    /**
     * Adds a new {@code Meeting} to the {@code MeetingList} that we are building.
     */
    public MeetingListBuilder withMeeting(Meeting meeting) {
        meetingList.addMeeting(meeting);
        return this;
    }

    public MeetingList build() {
        return meetingList;
    }
}

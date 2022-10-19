package seedu.address.model.util;

import seedu.address.model.meeting.Meeting;

/**
 * Functional interface used by FindMeeting to pass methods as a parameter
 */
public interface FindMeetingFunctionalInterface {
    String getField(Meeting m);
}

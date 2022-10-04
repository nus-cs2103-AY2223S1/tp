package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.person.Client;

@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
class JsonAdaptedMeeting {

    private final Client client;
    private final Description description;
    private final MeetingDate meetingDate;
    private final MeetingTime meetingTime;

    JsonAdaptedMeeting(Meeting meeting) {
        client = meeting.getClient();
        description = meeting.getDescription();
        meetingDate = meeting.getMeetingDate();
        meetingTime = meeting.getMeetingTime();
    }
}

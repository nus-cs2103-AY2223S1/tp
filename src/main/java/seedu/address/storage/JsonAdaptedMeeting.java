package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.person.Client;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
class JsonAdaptedMeeting {

    private final Client client;
    private final String description;
    private final String meetingDate;
    private final String meetingTime;

    JsonAdaptedMeeting(Meeting meeting) {
        client = meeting.getClient();
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingTime = meeting.getMeetingTime().toString();
    }

    public Meeting toModelType() {
        Description modelDescription = new Description(description);
        MeetingDate modelMeetingDate = new MeetingDate(LocalDate.of(2022, 10, 04));
        MeetingTime modelMeetingTime = new MeetingTime(LocalTime.of(10,10));
        return new Meeting(client, modelDescription, modelMeetingDate, modelMeetingTime);
    }

}

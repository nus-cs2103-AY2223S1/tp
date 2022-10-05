package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.client.Client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Jackson-friendly version of {@link seedu.address.model.meeting.Meeting}.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
class JsonAdaptedMeeting {

    private final Client client;
    private final String description;
    private final String meetingDate;
    private final String meetingTime;

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    JsonAdaptedMeeting(Meeting meeting) {
        client = meeting.getClient();
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingTime = meeting.getMeetingTime().toString();
    }

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("client") Client client, @JsonProperty("phone") String description,
                             @JsonProperty("email") String meetingDate, @JsonProperty("address") String meetingTime) {
        this.client = client;
        this.description = description;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        Description modelDescription = new Description(description);
        MeetingDate modelMeetingDate = new MeetingDate(LocalDate.of(2022, 10, 04));
        MeetingTime modelMeetingTime = new MeetingTime(LocalTime.of(10,10));
        return new Meeting(client, modelDescription, modelMeetingDate, modelMeetingTime);
    }

}

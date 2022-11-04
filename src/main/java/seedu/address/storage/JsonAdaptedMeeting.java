package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;

/**
 * Jackson-friendly version of {@link seedu.address.model.meeting.Meeting}.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
class JsonAdaptedMeeting {

    @JsonProperty
    private String description;
    private String meetingDate;
    private String meetingStartTime;
    private String meetingEndTime;

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting meeting) {
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingStartTime = meeting.getMeetingStartTime().toString();
        meetingEndTime = meeting.getMeetingEndTime().toString();
    }

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("description") String description,
                              @JsonProperty("meetingStartTime") String meetingStartTime,
                              @JsonProperty("meetingEndTime") String meetingEndTime,
                              @JsonProperty("meetingDate") String meetingDate) {
        this.description = description;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
        this.meetingDate = meetingDate;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType(Client client) throws IllegalValueException {
        Description modelDescription = new Description(description);
        MeetingDate modelMeetingDate = new MeetingDate(ParserUtil.parseDate(meetingDate, "meeting"));
        MeetingTime modelMeetingStartTime = ParserUtil.parseTime(meetingStartTime);
        MeetingTime modelMeetingEndTime = ParserUtil.parseTime(meetingEndTime);
        return new Meeting(client, modelDescription, modelMeetingDate, modelMeetingStartTime, modelMeetingEndTime);
    }

}

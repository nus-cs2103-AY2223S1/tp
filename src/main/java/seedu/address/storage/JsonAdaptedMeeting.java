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

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

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
        Description modelDescription = validateAndGetDescription();
        MeetingDate modelMeetingDate = validateAndGetMeetingDate();
        MeetingTime modelMeetingStartTime = validateAndGetStartTime();
        MeetingTime modelMeetingEndTime = validateAndGetEndTIme();

        return new Meeting(client, modelDescription, modelMeetingDate, modelMeetingStartTime, modelMeetingEndTime);
    }

    private Description validateAndGetDescription() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    private MeetingDate validateAndGetMeetingDate() throws IllegalValueException {
        if (meetingDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingDate.class.getSimpleName()));
        }
        if (!MeetingDate.isValidMeetingDate(meetingDate)) {
            throw new IllegalValueException(MeetingDate.MESSAGE_CONSTRAINTS);
        }
        return new MeetingDate(ParserUtil.parseDate(meetingDate, "meeting"));
    }

    private MeetingTime validateAndGetStartTime() throws IllegalValueException {
        if (meetingStartTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Meeting Start Time"));
        }
        if (!MeetingTime.isValidMeetingTime(meetingStartTime)) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        return ParserUtil.parseTime(meetingStartTime);
    }

    private MeetingTime validateAndGetEndTIme() throws IllegalValueException {
        if (meetingEndTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Meeting End Time"));
        }
        if (!MeetingTime.isValidMeetingTime(meetingEndTime)) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        return ParserUtil.parseTime(meetingEndTime);
    }

}

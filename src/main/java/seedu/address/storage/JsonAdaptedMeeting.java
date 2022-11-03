package seedu.address.storage;

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
    JsonAdaptedMeeting(Meeting meeting) {
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingStartTime = meeting.getMeetingStartTime().toString();
        meetingEndTime = meeting.getMeetingEndTime().toString();
    }

    /**
     * Converts a given {@code Meeting} and {@code JsonAdaptedClient} into this class for Jackson use.
     * @param meeting
     * @param adaptedClient
     */
    JsonAdaptedMeeting(Meeting meeting, JsonAdaptedClient adaptedClient) {
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingStartTime = meeting.getMeetingStartTime().toString();
        meetingEndTime = meeting.getMeetingEndTime().toString();
    }

    /**
     * Default constructor for {@code JsonAdaptedMeeting}
     */
    public JsonAdaptedMeeting() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingStartTime = meetingTime;
    }

    public String getMeetingTime() {
        return meetingStartTime;
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

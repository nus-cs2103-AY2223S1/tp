package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import seedu.address.commons.exceptions.IllegalValueException;
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

    private JsonAdaptedClient client;
    @JsonProperty
    private String description;
    private String meetingDate;
    private String meetingTime;

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    JsonAdaptedMeeting(Meeting meeting) {
        client = new JsonAdaptedClient(meeting.getClient(), this);
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingTime = meeting.getMeetingTime().toString();
    }

    /**
     * Converts a given {@code Meeting} and {@code JsonAdaptedClient} into this class for Jackson use.
     * @param meeting
     * @param adaptedClient
     */
    JsonAdaptedMeeting(Meeting meeting, JsonAdaptedClient adaptedClient) {
        client = adaptedClient;
        description = meeting.getDescription().toString();
        meetingDate = meeting.getMeetingDate().toString();
        meetingTime = meeting.getMeetingTime().toString();
    }

    /**
     * Default constructor for {@code JsonAdaptedMeeting}
     */
    public JsonAdaptedMeeting() {
    }

    public void setClient(JsonAdaptedClient client) {
        this.client = client;
    }

    public JsonAdaptedClient getClient() {
        return client;
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
        this.meetingTime = meetingTime;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType(Client client) throws IllegalValueException {
        Description modelDescription = new Description(description);
        MeetingDate modelMeetingDate = new MeetingDate(LocalDate.of(2022, 10, 04));
        MeetingTime modelMeetingTime = new MeetingTime(LocalTime.of(10, 10));
        return new Meeting(client, modelDescription, modelMeetingDate, modelMeetingTime);
    }

}

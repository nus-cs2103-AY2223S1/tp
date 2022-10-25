package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.MeetingTime;

/**
 * Jackson-friendly version of {@link MeetingTime}.
 */
class JsonAdaptedMeetingTime {

    private final String meetingTimeName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code meetingTimeName}.
     */
    @JsonCreator
    public JsonAdaptedMeetingTime(String meetingTimeName) {
        this.meetingTimeName = meetingTimeName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedMeetingTime(MeetingTime source) {
        meetingTimeName = source.value;
    }

    @JsonValue
    public String getMeetingTimeName() {
        return meetingTimeName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code MeetingTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public MeetingTime toModelType() throws IllegalValueException {
        if (!MeetingTime.isValidMeetingTime(meetingTimeName)) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        return new MeetingTime(meetingTimeName);
    }
}

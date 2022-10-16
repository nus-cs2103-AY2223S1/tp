package seedu.address.storage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MeetingList;
import seedu.address.model.ReadOnlyMeetingList;
import seedu.address.model.meeting.Meeting;

/**
 * An Immutable Meeting that is serializable to JSON format.
 */
@JsonRootName(value = "meetingList")
class JsonSerializableMeetingList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMeeting} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMeetingList(@JsonProperty("meetings") List<JsonAdaptedMeeting> meetings) {
        this.meetings.addAll(meetings);
    }

    /**
     * Converts a given {@code ReadOnlyMeeting} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMeeting}.
     */
    public JsonSerializableMeetingList(ReadOnlyMeetingList source) {
        meetings.addAll(source.getMeetingList().stream().map(JsonAdaptedMeeting::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MeetingList toModelType() throws IllegalValueException, ParseException {
        MeetingList meetingList = new MeetingList();
        for (JsonAdaptedMeeting jsonAdaptedMeeting : meetings) {
            Meeting meeting = jsonAdaptedMeeting.toModelType();
            if (meetingList.hasMeeting(meeting)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            meetingList.addMeeting(meeting);
        }
        return meetingList;
    }

}

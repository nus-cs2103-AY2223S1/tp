package seedu.address.storage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final String meetingDescription;
    private final String meetingDateAndTime;
    private final String meetingLocation;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("meetingDescription") String meetingDescription,
            @JsonProperty("meetingDateAndTime") String meetingDateAndTime,
            @JsonProperty("meetingLocation") String meetingLocation) {
        this.persons.addAll(persons);
        this.meetingDescription = meetingDescription;
        this.meetingDateAndTime = meetingDateAndTime;
        this.meetingLocation = meetingLocation;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        persons.addAll(source.getPersonToMeet().asUnmodifiableObservableList()
                .stream().map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        meetingDescription = source.getDescription();
        meetingDateAndTime = source.getDateAndTime();
        meetingLocation = source.getLocation();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Meeting toModelType() throws IllegalValueException, ParseException {
        final ArrayList<Person> person = new ArrayList<>();
        for (JsonAdaptedPerson p : persons) {
            person.add(p.toModelType());
        }

        if (meetingDescription == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        if (meetingDateAndTime == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        if (meetingLocation == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        return new Meeting(person, meetingDescription, meetingDateAndTime, meetingLocation);
    }

}

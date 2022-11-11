package longtimenosee.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import longtimenosee.commons.exceptions.IllegalValueException;
import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.Duration;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Name;

/**
 * Constructs a {@code JsonAdaptedEvent} with the given person details.
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";
    private final String description;
    private final String personName;
    private final String date;
    private final String duration; //Notice that the duration includes both start and end time
    //duration = hh:mm__hh:mm, (start)__(end)
    /**
     * Constructs a {@code JsonAdaptedPerson} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description") String description,
                            @JsonProperty("personName") String personName,
                            @JsonProperty("date") String date,
                            @JsonProperty("duration") String duration) {
        this.description = description;
        this.personName = personName;
        this.date = date;
        this.duration = duration;
    }
    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        description = source.getDescription().retrieveDescription();
        personName = source.getPersonName().fullName;
        date = source.getDate().value;
        duration = source.getDuration().value;
    }
    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (personName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Description.isValidDescription(personName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(personName);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidFormat(date)) {
            throw new IllegalValueException(Date.MESSAGE_FORMAT_CONSTRAINTS);
        }
        if (!Date.isValidRange(date)) {
            throw new IllegalValueException(Date.RANGE_FORMAT_CONSTRAINTS);
        }
        if (!Date.isValidYear(date)) {
            throw new IllegalValueException(Date.YEAR_RANGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Duration.class.getSimpleName()));
        }
        if (!Duration.isValidStartAndEnd(duration)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS);
        }
        if (!Duration.isValidFormat(duration)) {
            throw new IllegalValueException(Duration.FORMAT_CONSTRAINTS);
        }
        final Duration modelDuration = new Duration(duration);
        return new Event(modelDescription, modelName, modelDate, modelDuration);
    }

}

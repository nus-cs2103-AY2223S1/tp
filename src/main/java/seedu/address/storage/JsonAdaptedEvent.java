package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;

/**
 * Jackson-friendly version of Event
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing";

    private final String eventTitle;

    private final String date;

    private final String time;

    private final String purpose;

    /**
     * Constructs a JsonAdaptedEvent with the given Event information
     * @param eventTitle
     * @param date
     * @param time
     * @param purpose
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventTitle") String eventTitle, @JsonProperty("date") String date,
                            @JsonProperty("time") String time, @JsonProperty("purpose") String purpose) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
    }

    /**
     * Converts a given Event into this class for use by Jackson.
     */
    public JsonAdaptedEvent(Event event) {
        this.eventTitle = event.getEventTitle();
        this.date = event.getDate();
        this.time = event.getTime();
        this.purpose = event.getPurpose();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's Event object
     *
     * @throws IllegalValueException if any data constraints were violated in the adapted person
     */
    public Event toModelType() throws IllegalValueException {
        if (this.eventTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Event Title"));
        }
        if (this.date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }
        if (this.time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Time"));
        }
        if (this.purpose == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Purpose"));
        }
        return new Event(this.eventTitle, this.date, this.time, this.purpose);
    }

}

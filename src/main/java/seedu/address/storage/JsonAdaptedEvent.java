package seedu.address.storage;

//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;

public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing";

    private final String eventTitle;

    private final String date;

    private final String time;

    private final String purpose;

    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventTitle") String eventTitle, @JsonProperty("date") String date,
                            @JsonProperty("time") String time, @JsonProperty("purpose") String purpose) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
    }

    public JsonAdaptedEvent(Event event) {
        this.eventTitle = event.getEventTitle();
        this.date = event.getDate();
        this.time = event.getTime();
        this.purpose = event.getPurpose();
    }

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

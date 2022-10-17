package seedu.address.storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.UidList;
import seedu.address.model.person.Uid;

/**
 * Jackson-friendly version of Event
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing";

    private final String eventTitle;

    private final String date;

    private final String time;

    private final String purpose;
    private final List<JsonAdaptedUid> uids = new ArrayList<>();

    /**
     * Constructs a JsonAdaptedEvent with the given Event information
     * @param eventTitle
     * @param date
     * @param time
     * @param purpose
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventTitle") String eventTitle, @JsonProperty("date") String date,
                            @JsonProperty("time") String time, @JsonProperty("purpose") String purpose,
                            @JsonProperty("uids") List<JsonAdaptedUid> uids) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
        if (uids != null) {
            this.uids.addAll(uids);
        }
    }

    /**
     * Converts a given Event into this class for use by Jackson.
     */
    public JsonAdaptedEvent(Event event) {
        this.eventTitle = event.getEventTitle();
        this.date = event.getDate();
        this.time = event.getTime();
        this.purpose = event.getPurpose();
        Iterator<Uid> iter = event.getUids().iterator();
        while (iter.hasNext()) {
            uids.add(new JsonAdaptedUid(iter.next()));
        }
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
        final UidList eventUids = new UidList();
        for (JsonAdaptedUid uid : uids) {
            eventUids.add(uid.toModelType());
        }
        return new Event(this.eventTitle, this.date, this.time, this.purpose, eventUids);
    }

}

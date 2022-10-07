package seedu.travelr.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.event.Event;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    private final String eventTitle;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedEvent(String tagName) {
        this.eventTitle = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventTitle = source.title;
    }

    @JsonValue
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Event toModelType() throws IllegalValueException {
        if (!Event.isValidTagName(eventTitle)) {
            throw new IllegalValueException(Event.MESSAGE_CONSTRAINTS);
        }
        return new Event(eventTitle, EVENT_DESCRIPTION_PLACEHOLDER);
    }

}

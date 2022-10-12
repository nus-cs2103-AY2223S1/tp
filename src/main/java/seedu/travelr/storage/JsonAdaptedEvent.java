package seedu.travelr.storage;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Description;
import seedu.travelr.model.trip.Title;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    private final String title;

    private final String description;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        title = source.getTitle().fullTitle;
        description = source.getDescription().value;

    }

    @JsonValue
    public String getEventTitle() {
        return title;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Event toModelType() throws IllegalValueException {
        if (!Event.isValidEventTitle(title)) {
            throw new IllegalValueException(Event.MESSAGE_CONSTRAINTS);
        }
        return new Event(new Title(title), new Description(EVENT_DESCRIPTION_PLACEHOLDER));
    }

}

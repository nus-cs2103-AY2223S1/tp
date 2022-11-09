package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.ITimesAvailable;
import seedu.address.model.person.TimeInterval;

/**
 * Jackson-friendly version of {@link TimeInterval}.
 */
class JsonAdaptedTimeInterval {
    private final String timeInterval;

    /**
     * Constructs a {@code JsonAdaptedTimeInterval} with the given {@code timeInterval}.
     */
    @JsonCreator
    public JsonAdaptedTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     * Converts a given {@code socials} into this class for Jackson use.
     */
    public JsonAdaptedTimeInterval(ITimesAvailable source) {
        timeInterval = source.toString();
    }

    @JsonValue
    public String getTimeInterval() {
        return timeInterval;
    }

    /**
     * Converts this Jackson-friendly adapted time intervals
     * object into the model's {@code ITimesAvailable} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted time intervals.
     */
    public ITimesAvailable toModelType() throws IllegalValueException {
        if (!TimeInterval.isValidTimeInterval(timeInterval)) {
            throw new IllegalValueException(TimeInterval.getTimeIntervalConstraints());
        }
        return ParserUtil.parseTimeInterval(timeInterval);
    }
}

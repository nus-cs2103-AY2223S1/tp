package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Duration;

/**
 * Jackson-friendly version of {@link Duration}.
 */
public class JsonAdaptedDuration {
    private final String durationDesc;
    /**
     * Constructs a {@code JsonAdaptedDuration} with the given {@code durationDesc}.
     */
    @JsonCreator
    public JsonAdaptedDuration(String durationDesc) {
        this.durationDesc = durationDesc;
    }

    /**
     * Converts a given {@code Duration} into this class for Jackson use.
     */
    public JsonAdaptedDuration(Duration source) {
        durationDesc = source.toString();
    }

    @JsonValue
    public String getDurationDesc() {
        return durationDesc;
    }
    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Duration} object.
     */
    public Duration toModelType() {
        return new Duration(durationDesc);
    }
}

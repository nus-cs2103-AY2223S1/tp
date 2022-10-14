package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Duration;

public class JsonAdaptedDuration {
    private final String durationDesc;

    @JsonCreator
    public JsonAdaptedDuration(String tagName) {
        this.durationDesc = tagName;
    }


    public JsonAdaptedDuration(Duration source) {
        durationDesc = source.toString();
    }

    @JsonValue
    public String getDurationDesc() {
        return durationDesc;
    }

    public Duration toModelType() {
        return new Duration(durationDesc);
    }
}

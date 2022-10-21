package jarvis.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link TimePeriod}.
 */
public class JsonAdaptedTimePeriod {
    private final String start;
    private final String end;

    /**
     * Constructs a {@code JsonAdaptedTimePeriod} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTimePeriod(@JsonProperty("start") String start, @JsonProperty("end") String end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code TimePeriod} into this class for Jackson use.
     */
    public JsonAdaptedTimePeriod(TimePeriod source) {
        this.start = source.getStart().toString();
        this.end = source.getEnd().toString();
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code TimePeriod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted time period.
     */
    public TimePeriod toModelType() throws IllegalValueException {
        return new TimePeriod(LocalDateTime.parse(start), LocalDateTime.parse(end));
    }
}

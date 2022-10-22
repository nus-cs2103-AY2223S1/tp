package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.module.Lesson;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    private final String type;
    private final String module;
    private final String day;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given {@code modCode}.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("type") String type, @JsonProperty("module") String module,
                             @JsonProperty("day") String day, @JsonProperty("startTime") String startTime,
                             @JsonProperty("endTime") String endTime) {
        this.type = type;
        this.module = module;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        assert !source.equals(null) : "lesson to add should not be null";
        type = source.getType();
        module = source.getModule();
        day = String.format("%d", source.getDay());
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Lesson toModelType() throws IllegalValueException {
        return ParserUtil.parseLesson(type, module, day, startTime, endTime);
    }

}

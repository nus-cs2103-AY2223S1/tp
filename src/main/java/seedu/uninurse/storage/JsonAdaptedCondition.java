package seedu.uninurse.storage;


import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.condition.Condition;

/**
 * Jackson-friendly version of {@link Condition}.
 */
public class JsonAdaptedCondition {

    private final String condition;

    /**
     * Constructs a {@code JsonAdaptedCondition} with the given {@code condition}.
     */
    @JsonCreator
    public JsonAdaptedCondition(String condition) {
        requireNonNull(condition);
        this.condition = condition;
    }

    /**
     * Converts a given {@code Condition} into this class for Jackson use.
     */
    public JsonAdaptedCondition(Condition source) {
        requireNonNull(source);
        condition = source.condition;
    }

    @JsonValue
    public String getCondition() {
        return condition;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Condition toModelType() throws IllegalValueException {
        if (!Condition.isValidCondition(condition)) {
            throw new IllegalValueException(Condition.MESSAGE_CONSTRAINTS);
        }
        return new Condition(condition);
    }
}

package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Priority;

/**
 * Jackson-friendly version of {@link Priority}.
 */
class JsonAdaptedTag {

    private final String specifiedPriority;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.specifiedPriority = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Priority source) {
        specifiedPriority = source.toString();
    }

    @JsonValue
    public String getPriority() {
        return specifiedPriority;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Priority toModelType() throws IllegalValueException {
        if (!Priority.isValidPriority(specifiedPriority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(specifiedPriority);
    }

}

package seedu.trackascholar.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.trackascholar.commons.exceptions.IllegalValueException;
import seedu.trackascholar.model.major.Major;

/**
 * Jackson-friendly version of {@link Major}.
 */
class JsonAdaptedMajor {

    private final String majorName;

    /**
     * Constructs a {@code JsonAdaptedMajor} with the given {@code majorName}.
     */
    @JsonCreator
    public JsonAdaptedMajor(String majorName) {
        this.majorName = majorName;
    }

    /**
     * Converts a given {@code Major} into this class for Jackson use.
     */
    public JsonAdaptedMajor(Major source) {
        majorName = source.majorName;
    }

    @JsonValue
    public String getMajorName() {
        return majorName;
    }

    /**
     * Converts this Jackson-friendly adapted major object into the model's {@code Major} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted major.
     */
    public Major toModelType() throws IllegalValueException {
        if (!Major.isValidMajorName(majorName)) {
            throw new IllegalValueException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(majorName);
    }

}

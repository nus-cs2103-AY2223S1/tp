package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Interest;

/**
 * Jackson-friendly version of {@link Interest}.
 */
class JsonAdaptedInterest {

    private final String interestName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code interestName}.
     */
    @JsonCreator
    public JsonAdaptedInterest(String interestName) {
        this.interestName = interestName;
    }

    /**
     * Converts a given {@code Interest} into this class for Jackson use.
     */
    public JsonAdaptedInterest(Interest source) {
        interestName = source.interestName;
    }

    @JsonValue
    public String getInterestName() {
        return interestName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Interest} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Interest toModelType() throws IllegalValueException {
        if (!Interest.isValidInterest(interestName)) {
            throw new IllegalValueException(Interest.MESSAGE_CONSTRAINTS);
        }
        return new Interest(interestName);
    }

}

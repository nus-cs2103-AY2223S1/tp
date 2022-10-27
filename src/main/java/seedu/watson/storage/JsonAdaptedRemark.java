package seedu.watson.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.watson.commons.exceptions.IllegalValueException;
import seedu.watson.model.person.Remark;

/**
 * Jackson-friendly version of {@link Remark}.
 */
public class JsonAdaptedRemark {

    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedRemark} with the given {@code remark}.
     */
    @JsonCreator
    public JsonAdaptedRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Converts a given {@code Remark} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        remark = source.value;
    }

    @JsonValue
    public String getRemark() {
        return remark;
    }

    /**
     * Converts this Jackson-friendly adapted remark object into the model's {@code Remark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted remark.
     */
    public Remark toModelType() throws IllegalValueException {
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(remark);
    }

}

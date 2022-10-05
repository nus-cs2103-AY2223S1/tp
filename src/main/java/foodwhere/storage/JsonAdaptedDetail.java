package foodwhere.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.detail.Detail;

/**
 * Jackson-friendly version of {@link Detail}.
 */
class JsonAdaptedDetail {

    private final String detail;

    /**
     * Constructs a {@code JsonAdaptedDetail} with the given {@code detail}.
     */
    @JsonCreator
    public JsonAdaptedDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Converts a given {@code Detail} into this class for Jackson use.
     */
    public JsonAdaptedDetail(Detail source) {
        detail = source.detail;
    }

    @JsonValue
    public String getDetail() {
        return detail;
    }

    /**
     * Converts this Jackson-friendly adapted detail object into the model's {@code Detail} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Detail toModelType() throws IllegalValueException {
        if (!Detail.isValidDetail(detail)) {
            throw new IllegalValueException(Detail.MESSAGE_CONSTRAINTS);
        }
        return new Detail(detail);
    }

}

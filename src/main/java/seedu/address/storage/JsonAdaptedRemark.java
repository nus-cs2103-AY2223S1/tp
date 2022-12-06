package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;

/**
 * Jackson-friendly version of {@link Remark}.
 */
class JsonAdaptedRemark {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Remark's %s field is missing!";

    private final String text;

    /**
     * Constructs a {@code JsonAdaptedRemark} with the given remark details.
     */
    @JsonCreator
    public JsonAdaptedRemark(@JsonProperty("text") String text) {
        this.text = text;
    }

    /**
     * Converts a given {@code Remark} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        text = source.getText().value;
    }

    /**
     * Converts this Jackson-friendly adapted remark object into the model's {@code Remark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted remark.
     */
    public Remark toModelType() throws IllegalValueException {

        if (text == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Text.class.getSimpleName()));
        }
        if (!Text.isValidText(text)) {
            throw new IllegalValueException(Text.MESSAGE_CONSTRAINTS);
        }
        final Text modelText = new Text(text);

        return new Remark(modelText);
    }

}

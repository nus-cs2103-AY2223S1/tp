package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Uid;

/**
 * Jackson-friendly version of {@link Uid}.
 */
public class JsonAdaptedUid {
    private final String uid;

    /**
     * Constructs a {@code JsonAdaptedUid} with the given {@code uidName}.
     */
    @JsonCreator
    public JsonAdaptedUid(String uid) {
        this.uid = uid;
    }
    /**
     * Converts a given {@code Uid} into this class for Jackson use.
     */
    public JsonAdaptedUid(Uid source) {
        uid = source.getUid();
    }

    @JsonValue
    public String getUid() {
        return uid;
    }

    /**
     * Converts this Jackson-friendly adapted uid object into the model's {@code uid} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted uid.
     */
    public Uid toModelType() throws IllegalValueException {
        if (uid == null || !Uid.isValidUid(uid)) {
            throw new IllegalValueException(Uid.MESSAGE_CONSTRAINTS);
        }
        return new Uid(uid);
    }
}

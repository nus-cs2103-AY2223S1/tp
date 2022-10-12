package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Mod;

/**
 * Jackson-friendly version of {@link Mod}.
 */
class JsonAdaptedMod {

    private final String modName;

    /**
     * Constructs a {@code JsonAdaptedMod} with the given {@code modName}.
     */
    @JsonCreator
    public JsonAdaptedMod(String modName) {
        this.modName = modName;
    }

    /**
     * Converts a given {@code Mod} into this class for Jackson use.
     */
    public JsonAdaptedMod(Mod source) {
        modName = source.modName;
    }

    @JsonValue
    public String getModName() {
        return modName;
    }

    /**
     * Converts this Jackson-friendly adapted mod object into the model's {@code Mod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted mod.
     */
    public Mod toModelType() throws IllegalValueException {
        if (!Mod.isValidModName(modName)) {
            throw new IllegalValueException(Mod.MESSAGE_CONSTRAINTS);
        }
        return new Mod(modName);
    }

}

package seedu.masslinkers.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.masslinkers.commons.exceptions.IllegalValueException;
import seedu.masslinkers.model.student.Mod;

//@@author jonasgwt
/**
 * Jackson-friendly version of {@link Mod}.
 */
class JsonAdaptedMod {

    private final String modName;
    private boolean hasTaken;

    /**
     * Constructs a {@code JsonAdaptedMod} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedMod(@JsonProperty("modName") String modName,
                          @JsonProperty("hasTaken") boolean hasTaken) {
        this.modName = modName;
        this.hasTaken = hasTaken;
    }

    /**
     * Converts a given {@code Mod} into this class for Jackson use.
     */
    public JsonAdaptedMod(Mod source) {
        modName = source.getModName();
        hasTaken = source.getModStatus();
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
        return new Mod(modName, hasTaken);
    }

}

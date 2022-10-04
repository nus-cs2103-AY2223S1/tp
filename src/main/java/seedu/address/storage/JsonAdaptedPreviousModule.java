package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.PreviousModule;

/**
 * Jackson-friendly version of {@link PreviousModule}.
 */
class JsonAdaptedPreviousModule {

    private final String modCode;

    /**
     * Constructs a {@code JsonAdaptedPreviousModule} with the given {@code modCode}.
     */
    @JsonCreator
    public JsonAdaptedPreviousModule(String modCode) {
        this.modCode = modCode;
    }

    /**
     * Converts a given {@code PreviousModule} into this class for Jackson use.
     */
    public JsonAdaptedPreviousModule(PreviousModule source) {
        modCode = source.moduleName;
    }

    @JsonValue
    public String getModName() {
        return modCode;
    }

    /**
     * Converts this Jackson-friendly adapted previous module object into the model's {@code PreviousModule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PreviousModule toModelType() throws IllegalValueException {
        if (!Module.isValidModuleName(modCode)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        return new PreviousModule(modCode);
    }

}

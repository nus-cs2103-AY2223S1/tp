package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.CurrentModule;

/**
 * Jackson-friendly version of {@link CurrentModule}.
 */
class JsonAdaptedCurrentModule {

    private final String modCode;

    /**
     * Constructs a {@code JsonAdaptedCurrentModule} with the given {@code modCode}.
     */
    @JsonCreator
    public JsonAdaptedCurrentModule(String modCode) {
        this.modCode = modCode;
    }

    /**
     * Converts a given {@code CurrentModule} into this class for Jackson use.
     */
    public JsonAdaptedCurrentModule(CurrentModule source) {
        modCode = source.moduleName;
    }

    @JsonValue
    public String getModName() {
        return modCode;
    }

    /**
     * Converts this Jackson-friendly adapted current module object into the model's {@code CurrentModule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public CurrentModule toModelType() throws IllegalValueException {
        if (!Module.isValidModuleName(modCode)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        return new CurrentModule(modCode);
    }

}

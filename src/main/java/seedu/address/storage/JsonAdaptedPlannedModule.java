package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.PlannedModule;

/**
 * Jackson-friendly version of {@link PlannedModule}.
 */
class JsonAdaptedPlannedModule {

    private final String modCode;

    /**
     * Constructs a {@code JsonAdaptedPlannedModule} with the given {@code modCode}.
     */
    @JsonCreator
    public JsonAdaptedPlannedModule(String modCode) {
        this.modCode = modCode;
    }

    /**
     * Converts a given {@code PlannedModule} into this class for Jackson use.
     */
    public JsonAdaptedPlannedModule(PlannedModule source) {
        modCode = source.moduleName;
    }

    @JsonValue
    public String getModName() {
        return modCode;
    }

    /**
     * Converts this Jackson-friendly adapted previous module object into the model's {@code PlannedModule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PlannedModule toModelType() throws IllegalValueException {
        if (!Module.isValidModuleName(modCode)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        return new PlannedModule(modCode);
    }

}

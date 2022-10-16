package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ModuleCode;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
public class JsonAdaptedModuleCode {
    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCode}.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        this.moduleCode = source.value;
    }

    @JsonValue
    public String getModuleCode() {
        return this.moduleCode;
    }

    /**
     * Converts this Jackson-friendly adapted moduleCode object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted moduleCode.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidModuleCodeName(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }
}

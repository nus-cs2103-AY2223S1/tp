package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    private final String moduleName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedModule(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        moduleName = source.moduleName;
    }

    @JsonValue
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Module toModelType() throws IllegalValueException {
        if (!Module.isValidModule(moduleName)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        return new Module(moduleName);
    }

}

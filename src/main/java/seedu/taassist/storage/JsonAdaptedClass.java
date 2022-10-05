package seedu.taassist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Json-friendly version of {@link ModuleClass}.
 */
class JsonAdaptedClass {

    private final String className;

    /**
     * Constructs a {@code JsonAdaptedClass} with the given {@code className}.
     */
    @JsonCreator
    public JsonAdaptedClass(String className) {
        this.className = className;
    }

    /**
     * Converts a given {@code Class} into this class for Jackson use.
     */
    public JsonAdaptedClass(ModuleClass source) {
        className = source.className;
    }

    @JsonValue
    public String getClassName() {
        return className;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ModuleClass toModelType() throws IllegalValueException {
        if (!ModuleClass.isValidModuleClassName(className)) {
            throw new IllegalValueException(ModuleClass.MESSAGE_CONSTRAINTS);
        }
        return new ModuleClass(className);
    }

}

package seedu.address.model.module;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
        "Module Codes should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String EMPTY_MODULE_CODE = "";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String moduleCode;
    private String moduleTitle;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param moduleCodeInput A valid module code.
     */
    public ModuleCode(String moduleCodeInput) {
        checkArgument(isValidModuleCode(moduleCodeInput), MESSAGE_CONSTRAINTS);
        moduleCode = moduleCodeInput;
        moduleTitle = null;
    }

    /**
     * Sets the title of the module based on the moduleCode.
     *
     * @param moduleTitle List of NUS modules from Json file.
     */
    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    /**
     * Gets the title of the module.
     *
     * @return The title of the module.
     */
    public String getModuleTitle() {
        return this.moduleTitle;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (moduleCode == null) {
            return EMPTY_MODULE_CODE;
        }
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof ModuleCode
            && moduleCode.equalsIgnoreCase(((ModuleCode) other).moduleCode));
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }
}

package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.nusmodules.NusModule;

/**
 * Represents a Module's code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
        "Module Codes should only contain alphanumeric characters and spaces, and it should not be blank";

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
        requireNonNull(moduleCodeInput);
        checkArgument(isValidModuleCode(moduleCodeInput), MESSAGE_CONSTRAINTS);
        moduleCode = moduleCodeInput.toUpperCase();
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
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid NUS module code.
     */
    public boolean isNusModule(NusModule nusModule) {
        return nusModule.getModuleCode().equalsIgnoreCase(moduleCode);
    }

    @Override
    public String toString() {
        return moduleCode + "\n" + moduleTitle;
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

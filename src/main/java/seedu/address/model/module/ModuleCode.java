package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * ModuleCode represents the module code of the module.
 */
public class ModuleCode {
    public static final String MODULE_CODE_CONSTRAINTS = "Module code should be at least 6 "
            + "characters long, with first two characters "
            + "being alphabetical characters";
    public static final String VALIDATION_REGEX = "^[A-Za-z]{2}[A-Za-z0-9]*";

    public final String moduleCode;

    /**
     * The constructor of the ModuleCode class which stores
     * the module code of the module.
     *
     * @param moduleCode The module code of the module.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MODULE_CODE_CONSTRAINTS);
        this.moduleCode = moduleCode;
    }

    public static boolean isValidModuleCode(String testModuleCode) {
        return testModuleCode.matches(VALIDATION_REGEX) && testModuleCode.length() >= 6;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof ModuleCode
                && moduleCode.equalsIgnoreCase((((ModuleCode) obj).moduleCode)));
    }

    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

}

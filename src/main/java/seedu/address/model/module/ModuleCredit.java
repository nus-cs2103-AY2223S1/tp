package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * ModuleCredit class represents the number of module credit of the module.
 */
public class ModuleCredit {
    public static final String MODULE_CREDIT_CONSTRAINTS =
            "The module credit of a module should be a integer from 0 to 45";

    public final int moduleCredit;

    /**
     * The constructor of the ModuleCredit class. Sets the
     * number of module credit of the module.
     *
     * @param moduleCredit The number of module credit the class has.
     */
    public ModuleCredit(int moduleCredit) {
        requireNonNull(moduleCredit);
        checkArgument(isValidModuleCredit(moduleCredit), MODULE_CREDIT_CONSTRAINTS);
        this.moduleCredit = moduleCredit;
    }

    public static boolean isValidModuleCredit(int moduleCredit) {
        return moduleCredit >= 0 && moduleCredit <= 45;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ModuleCredit
                && moduleCredit == ((ModuleCredit) other).moduleCredit);
    }

    @Override
    public int hashCode() {
        return moduleCredit;
    }

    @Override
    public String toString() {
        return Integer.toString(moduleCredit);
    }
}




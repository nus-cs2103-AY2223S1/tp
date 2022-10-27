package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * ModuleName class represents the name of the module.
 */
public class ModuleName {
    public static final String MODULE_NAME_CONSTRAINTS =
            "The name of the module should not be empty and should be less than 80 characters";

    public final String moduleName;

    /**
     * The constructor of the ModuleName class. Sets the
     * string name of the module.
     *
     * @param moduleName The string name of the module.
     */
    public ModuleName(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MODULE_NAME_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    public static boolean isValidModuleName(String moduleName) {
        return moduleName.length() > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ModuleName
                && moduleName.equalsIgnoreCase((((ModuleName) other).moduleName)));
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

    @Override
    public String toString() {
        return moduleName;
    }
}



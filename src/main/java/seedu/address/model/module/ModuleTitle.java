package seedu.address.model.module;

/**
 * Represents a {@code Module}'s title (optional field) in the address book.
 * Guarantees: immutable.
 */
public class ModuleTitle {

    public final String value;

    /**
     * Constructs a {@code ModuleTitle}.
     *
     * @param moduleTitle A valid {@code ModuleTitle}.
     */
    public ModuleTitle(String moduleTitle) {
        value = moduleTitle;
    }

    /**
     * Returns the module title as a {@code String} in upper case.
     */
    public String getModuleTitleAsUpperCaseString() {
        return value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTitle // instanceof handles nulls
                && value.equals(((ModuleTitle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.taassist.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;

/**
 * Represents a Class in TA-Assist.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleClassName(String)}
 */
public class ModuleClass {

    public static final String MESSAGE_CONSTRAINTS = "Class names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String className;

    /**
     * Constructs a {@code Tag}.
     *
     * @param className A valid tag name.
     */
    public ModuleClass(String className) {
        requireNonNull(className);
        checkArgument(isValidModuleClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidModuleClassName(String test) { // TODO: Ensure that class exists
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleClass // instanceof handles nulls
                && className.equals(((ModuleClass) other).className)); // state check
    }

    //TODO: fix
    public boolean isSameModuleClass(ModuleClass otherModuleClass) {
        return this.className.equals(otherModuleClass.className);
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + className + ']';
    }

}

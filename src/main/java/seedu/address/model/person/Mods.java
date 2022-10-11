package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a  Person's module code in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidMods(String)}
 */
public class Mods {

    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should contain only alphanumeric characters and should not be blank.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\S+";

    public final String value;

    /**
     * Constructs a {@code Mod}.
     *
     * @param module A valid module code.
     */
    public Mods(String module) {
        requireNonNull(module);
        checkArgument(isValidMods(module), MESSAGE_CONSTRAINTS);
        this.value = module;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidMods(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mods // instanceof handles nulls
                && value.equals(((Mods) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

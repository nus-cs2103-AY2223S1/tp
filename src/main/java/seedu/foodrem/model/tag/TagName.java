package seedu.foodrem.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Represents a Tag's name in the FoodRem.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TagName {
    public static final String MESSAGE_CONSTRAINTS =
            "The tag name should only contain alphanumeric characters, spaces and the following symbols "
                    + "[]{}()-+*=.,_'\"^$?@!#%&:;";

    private final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TagName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns {@code true} if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(StringUtil.VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagName
                && fullName.equals(((TagName) other).fullName));
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}

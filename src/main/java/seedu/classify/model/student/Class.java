package seedu.classify.model.student;

import static java.util.Objects.requireNonNull;

import seedu.classify.commons.util.AppUtil;

/**
 * Represents a Class that a student belongs to.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassName(String)}
 */
public class Class {
    public static final String MESSAGE_CONSTRAINTS =
            "Class names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String className;

    /**
     * Constructs a {@code Class}.
     *
     * @param className A valid class name.
     */
    public Class(String className) {
        requireNonNull(className);
        AppUtil.checkArgument(isValidClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid class name.
     */
    public static boolean isValidClassName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.className;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && this.className.equals(((Class) other).className)); // state check
    }

    @Override
    public int hashCode() {
        return this.className.hashCode();
    }

}

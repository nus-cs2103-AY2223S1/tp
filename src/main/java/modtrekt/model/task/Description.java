package modtrekt.model.task;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tasks's description in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description implements Comparable<Description> {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should not be blank";

    /*
     * The first character of the task must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{ASCII}][\\p{ASCII} ]*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public int compareTo(Description that) {
        return this.description.compareTo(that.description);
    }
}

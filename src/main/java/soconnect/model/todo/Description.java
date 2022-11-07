package soconnect.model.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a {@code Todo}'s description in the {@code TodoList}.
 * Guarantees: is valid as declared in {@link #isValidDescription(String)}.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description can take any values, but it should not be blank.";

    /**
     * The first character of the {@code Description} must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param description A valid {@code Description}.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid {@code Description}.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Description // instanceof handles nulls
            && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

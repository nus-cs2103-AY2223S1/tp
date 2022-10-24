package swift.model.task;

import static java.util.Objects.requireNonNull;
import static swift.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters, spaces, and special " +
                    "characters ($&+,:;=?@#|'<>.\\-^*()%!)";

    /*
     * Only alphanumeric characters, spaces, and special characters ($&+,:;=?@#|'<>.\-^*()%!)
     * are allowed.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}$&+,:;=?@#|'<>.\\\\-^*()%! ]*$";

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
        return other == this
                || (other instanceof Description
                && description.equals(((Description) other).description));
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

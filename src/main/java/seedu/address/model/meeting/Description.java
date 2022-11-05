package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Meeting's description in MyInsuRec.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions can consist of any valid character except newlines.";
    public static final String VALIDATION_REGEX = "(.*?)";
    private final String details;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        details = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return details;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Description
                && details.equals(((Description) other).details));
    }
}

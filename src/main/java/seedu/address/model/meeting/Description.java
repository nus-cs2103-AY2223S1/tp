package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Meeting's description in MyInsuRec.
 */
public class Description {
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

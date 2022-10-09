package seedu.address.model.iteration;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Iteration's description in the address book.
 * Guarantees: immutable; any description is valid
 */
public class IterationDescription {

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public IterationDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof IterationDescription)) {
            return false;
        }

        IterationDescription otherDescription = (IterationDescription) other;
        return otherDescription.description.equals(description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

package seedu.modquik.model.consultation;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Consultation's description in ModQuik.
 * Guarantees: immutable;
 */
public class ConsultationDescription {

    public final String description;

    /**
     * Constructs a {@code ConsultationDescription}.
     *
     * @param description A valid description.
     */
    public ConsultationDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationDescription // instanceof handles nulls
                && description.equals(((ConsultationDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

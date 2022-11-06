package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Consultation's description in ModQuik.
 * Guarantees: immutable;
 */
public class ConsultationDescription {
    public static final String MESSAGE_CONSTRAINTS =
        "Description can take any values, and it should not be blank.";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String description;

    /**
     * Constructs a {@code consultationName}.
     *
     * @param description A valid description.
     */
    public ConsultationDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidConsultationDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidConsultationDescription(String test) {
        return test.matches(VALIDATION_REGEX);
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

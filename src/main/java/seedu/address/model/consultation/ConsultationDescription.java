package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Consultation's description in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class ConsultationDescription {
    public static final String MESSAGE_CONSTRAINTS =
        "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;

    /**
     * Constructs a {@code consultationName}.
     *
     * @param description A valid description.
     */
    public ConsultationDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid name.
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
                || (other instanceof ConsultationDescription // instanceof handles nulls
                && description.equals(((ConsultationDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

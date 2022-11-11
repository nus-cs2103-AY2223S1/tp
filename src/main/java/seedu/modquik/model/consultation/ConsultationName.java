package seedu.modquik.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.util.AppUtil.checkArgument;

/**
 * Represents a Consultation's name (name of the student who book the consultation in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ConsultationName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names can take any values, and it should not be blank.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String fullName;

    /**
     * Constructs a {@code consultationName}.
     *
     * @param name A valid name.
     */
    public ConsultationName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationName // instanceof handles nulls
                && fullName.equals(((ConsultationName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}

package friday.model.person;

import java.time.LocalDate;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidConsultation(String)}
 */
public class Consultation {

    public static final String MESSAGE_CONSTRAINTS = "Desired dates for Mastery Checks should be in the format: YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    public LocalDate value;

    /**
     * Constructs an {@code Consultation}.
     *
     * @param desiredDate A valid email address.
     */
    public Consultation(LocalDate desiredDate) {
        requireNonNull(desiredDate);
        checkArgument(isValidConsultation(desiredDate.toString()), MESSAGE_CONSTRAINTS);
        value = desiredDate;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidConsultation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("Consultation: %s", value.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Consultation // instanceof handles nulls
                && value.equals(((Consultation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

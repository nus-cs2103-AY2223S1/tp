package friday.model.student;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Student's consultations in FRIDAY.
 * Guarantees: immutable; is valid as declared in {@link #isValidConsultation(String)}
 */
public class Consultation implements Comparable<Consultation> {

    public static final String MESSAGE_CONSTRAINTS = "Desired dates for consultation should be valid dates in the "
        + "format: YYYY-MM-DD, and the year should be between 1900 - 2999";
    public static final String VALIDATION_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    // The empty value for when there is no consultation date attached to a student
    public static final Consultation EMPTY_CONSULTATION = new Consultation();

    private LocalDate value;

    /**
     * Constructs an {@code Consultation}.
     *
     * @param desiredDate A valid date.
     */
    public Consultation(LocalDate desiredDate) {
        requireNonNull(desiredDate);
        checkArgument(isValidConsultation(desiredDate.toString()), MESSAGE_CONSTRAINTS);
        value = desiredDate;
    }

    /**
     * Constructs a {@code Consultation} for the empty instance.
     */
    private Consultation() {
        value = LocalDate.of(0001, 01, 01);
    }

    /**
     * Returns if a given string is a valid consultation.
     */
    public static boolean isValidConsultation(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid consultation or empty.
     *
     * Only to be used when converting JSON to Student in JsonAdaptedStudent.
     */
    public static boolean isValidOrEmptyJson(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("0001-01-01");
    }

    /**
     * Returns true if the given consultation is the empty value.
     */
    public boolean isEmpty() {
        return this == EMPTY_CONSULTATION;
    }

    /**
     * Returns the value of the given Consultation.
     *
     * @return value
     */
    public LocalDate getValue() {
        return value;
    }

    @Override
    public String toString() {
        String str = isEmpty() ? "" : value.toString();
        return str;
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

    @Override
    public int compareTo(Consultation consultation) {
        if (this.equals(consultation)) {
            return 0;
        } else if (this.isEmpty()) {
            return 1;
        } else if (consultation.isEmpty()) {
            return -1;
        }
        return this.value.compareTo(consultation.value);
    }

}

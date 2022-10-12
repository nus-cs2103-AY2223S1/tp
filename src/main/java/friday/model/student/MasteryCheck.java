package friday.model.student;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Student's desired date for their Mastery Check.
 */
public class MasteryCheck {

    public static final String MESSAGE_CONSTRAINTS = "Desired dates for Mastery Check should be in the format:"
            + "YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    // The empty value for when there is no mastery check date attached to a student
    public static final MasteryCheck EMPTY_MASTERYCHECK = new MasteryCheck();

    private LocalDate value;

    /**
     * Constructs an {@code MasteryCheck}.
     *
     * @param desiredDate The student's desired date for Mastery Check.
     */
    public MasteryCheck(LocalDate desiredDate) {
        requireNonNull(desiredDate);
        checkArgument(isValidMasteryCheck(desiredDate.toString()), MESSAGE_CONSTRAINTS);
        value = desiredDate;
    }

    /**
     * Constructs a {@code MasteryCheck} for the empty instance.
     */
    private MasteryCheck() {
        value = LocalDate.of(0001, 01, 01);
    }

    /**
     * Returns true if a given string is a valid Mastery Check.
     */
    public static boolean isValidMasteryCheck(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid Mastery Check or empty.
     *
     * Only to be used when converting JSON to Student in JsonAdaptedPerson.
     */
    public static boolean isValidOrEmpty(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("0001-01-01");
    }

    /**
     * Returns true if the given Mastery Check is the empty value.
     */
    public boolean isEmpty() {
        return this == EMPTY_MASTERYCHECK;
    }

    /**
     * Returns the value of the given Mastery Check.
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
                || (other instanceof MasteryCheck // instanceof handles nulls
                && value.equals(((MasteryCheck) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

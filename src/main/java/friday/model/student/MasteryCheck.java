package friday.model.student;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Student's desired date for their Mastery Check.
 */
public class MasteryCheck implements Comparable<MasteryCheck> {

    public static final String MESSAGE_CONSTRAINTS = "Desired dates for Mastery Check should be valid dates in the "
        + "format: YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    // The empty value for when there is no mastery check date attached to a student
    public static final MasteryCheck EMPTY_MASTERYCHECK = new MasteryCheck();

    private LocalDate value;
    private boolean isPassed;

    /**
     * Constructs an {@code MasteryCheck} with isPassed set to false by default.
     *
     * @param desiredDate The student's desired date for Mastery Check.
     */
    public MasteryCheck(LocalDate desiredDate) {
        requireNonNull(desiredDate);
        checkArgument(isValidMasteryCheck(desiredDate.toString()), MESSAGE_CONSTRAINTS);
        this.value = desiredDate;
        this.isPassed = false;
    }

    /**
     * Constructs an {@code MasteryCheck} with the given isPassed value.
     *
     * @param desiredDate The student's desired date for Mastery Check.
     * @param isPassed True if Mastery Check has already been passed, false otherwise.
     */
    public MasteryCheck(LocalDate desiredDate, boolean isPassed) {
        requireNonNull(desiredDate);
        checkArgument(isValidMasteryCheck(desiredDate.toString()), MESSAGE_CONSTRAINTS);
        this.value = desiredDate;
        this.isPassed = isPassed;
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
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid Mastery Check or empty.
     *
     * Only to be used when converting JSON to Student in JsonAdaptedStudent.
     */
    public static boolean isValidOrEmptyJson(String test) {
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

    /**
     * Returns true if the given Mastery Check has been marked as done, and false otherwise.
     *
     * @return isPassed
     */
    public boolean getIsPassed() {
        return isPassed;
    }

    /**
     * Marks the given Mastery Check as done.
     */
    public void markAsPassed() {
        this.isPassed = true;
    }

    /**
     * Unmarks the given Mastery Check.
     */
    public void unmark() {
        this.isPassed = false;
    }

    public boolean canPass() {
        return this.value.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        String value = isEmpty() ? "" : this.value.toString();
        String isDone = this.isPassed ? " (Passed)" : "";
        return value + isDone;
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

    @Override
    public int compareTo(MasteryCheck masteryCheck) {
        if (this.equals(masteryCheck)) {
            return 0;
        } else if (this.isEmpty()) {
            return 1;
        } else if (masteryCheck.isEmpty()) {
            return -1;
        }
        return this.value.compareTo(masteryCheck.value);
    }

}

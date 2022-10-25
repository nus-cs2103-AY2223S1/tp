package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job ID the Person applied to in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final int LENGTH_LIMIT = 10;

    public static final String MESSAGE_LENGTH_LIMIT_EXCEEDED = "After trimming leading and trailing whitespaces, and "
            + "replacing multiple spaces with a single space, "
            + "Job IDs can only be of length max " + LENGTH_LIMIT;

    public static final String MESSAGE_CONSTRAINTS =
            "Job ID should be upper case letters and/or numeric, and it should not be blank or spaces only";

    public static final String VALIDATION_REGEX = "[A-Z0-9]+";

    public final String value;

    /**
     * Constructs an {@code Id}.
     *
     * @param id A valid job ID.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if the length of a given string is within the length limit
     */
    public static boolean isWithinLengthLimit(String test) {
        return test.length() <= LENGTH_LIMIT;
    }

    /**
     * Returns true if a given string is a valid job ID.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.job.Id // instanceof handles nulls
                && value.equals(((seedu.address.model.job.Id) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

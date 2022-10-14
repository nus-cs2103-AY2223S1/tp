package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent a project deadline in HR PRO Max++.
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format YYYY-MM-DD and should not be blank";

    /*
     * The date should be in YYYY-MM-DD format.
     *
     */
    public static final String VALIDATION_REGEX =
            "^((2000|2400|2800|(2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
            + "|^(((2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    public final String deadline;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param deadline A valid deadline address.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = deadline;
    }

    /**
     * Returns if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return deadline.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}

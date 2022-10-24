package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job title the Person applied to in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title {

    public static final int LENGTH_LIMIT = 100;

    public static final String MESSAGE_LENGTH_LIMIT_EXCEEDED = "After trimming leading and trailing whitespaces, and "
            + "replacing multiple spaces with a single space, "
            + "Job titles can only be of length max " + LENGTH_LIMIT;

    public static final String MESSAGE_CONSTRAINTS =
            "Job title should be alphanumeric or spaces or some allowed punctuations "
            + "- # , : & ( ) \" ' / [ ]";

    public static final String VALIDATION_REGEX = "[A-Za-z0-9\\-#,:&()\"'/\\[\\]][A-Za-z0-9 \\-#,:&()\"'/\\[\\]]*";

    public final String value;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A valid job title.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        value = title;
    }

    /**
     * Returns true if the length of a given string is within the length limit
     */
    public static boolean isWithinLengthLimit(String test) {
        return test.length() <= LENGTH_LIMIT;
    }

    /**
     * Returns true if a given string is a valid job title.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.job.Title // instanceof handles nulls
                && value.equals(((seedu.address.model.job.Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

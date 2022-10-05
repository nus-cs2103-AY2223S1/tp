package foodwhere.model.detail;

import static foodwhere.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;


/**
 * Represents a Detail in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDetail(String)}
 */
public class Detail {

    public static final String MESSAGE_CONSTRAINTS = "Details should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String detail;

    /**
     * Constructs a {@code Detail}.
     *
     * @param detail A valid detail of stall.
     */
    public Detail(String detail) {
        requireNonNull(detail);
        checkArgument(isValidDetail(detail), MESSAGE_CONSTRAINTS);
        this.detail = detail;
    }

    /**
     * Returns true if a given string is a valid detail.
     */
    public static boolean isValidDetail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Detail // instanceof handles nulls
                && detail.equals(((Detail) other).detail)); // state check
    }

    @Override
    public int hashCode() {
        return detail.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + detail + ']';
    }

}

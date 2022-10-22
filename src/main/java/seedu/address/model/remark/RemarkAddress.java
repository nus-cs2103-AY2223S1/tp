package seedu.address.model.remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemarkAddress(String)}
 */
public class RemarkAddress {

    public static final String MESSAGE_CONSTRAINTS = "RemarkAddresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code RemarkAddress}.
     *
     * @param address A valid address.
     */
    public RemarkAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidRemarkAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid address.
     */
    public static boolean isValidRemarkAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkAddress // instanceof handles nulls
                && value.equals(((RemarkAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

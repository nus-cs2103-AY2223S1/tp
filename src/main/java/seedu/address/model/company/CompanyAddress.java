package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyAddress(String)}
 */
public class CompanyAddress {

    public static final String MESSAGE_CONSTRAINTS = "CompanyAddresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code CompanyAddress}.
     *
     * @param address A valid address.
     */
    public CompanyAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidCompanyAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid address.
     */
    public static boolean isValidCompanyAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyAddress // instanceof handles nulls
                && value.equals(((CompanyAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

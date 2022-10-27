package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an AddressBookFile in the file system.
 * Guarantees: is valid as declared in {@link #isValidAddressBookFile(String)}
 */
public class AddressBookFile {

    public static final String MESSAGE_CONSTRAINTS = "AddressBookFile can take any pattern of "
        + "alphanumeric characters including '_', '-', and '.'";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\w\\-.]+$";

    public final String value;

    /**
     * Constructs an {@code AddressBookFile}.
     *
     * @param file A valid file.
     */
    public AddressBookFile(String file) {
        requireNonNull(file);
        checkArgument(isValidAddressBookFile(file), MESSAGE_CONSTRAINTS);
        value = file;
    }

    /**
     * Returns true if a given string is a valid file.
     */
    public static boolean isValidAddressBookFile(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddressBookFile // instanceof handles nulls
            && value.equals(((AddressBookFile) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

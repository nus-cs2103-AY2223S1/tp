package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

// @@author craeyeons-reused
// Reused from tutorial-2 of week 6, adding-command-tutorial
// https://github.com/se-edu/addressbook-level3/tree/tutorial-add-remark

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values";

    public static final String VALIDATION_REGEX = ".*";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
// @@author craeyeons-reused

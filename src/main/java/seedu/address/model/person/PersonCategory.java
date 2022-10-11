package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's type in the Address Book.
 * There are only three types of people - Buyer, Deliverer, and Supplier.
 */
public class PersonCategory {

    public static final String MESSAGE_CONSTRAINTS =
            "PersonCategory should only contain alphanumeric characters, it is case sensitive and it should not " +
                    "be blank. It can only be one of the following types: 'Buyer', 'Deliverer' and 'Supplier'.";

    /*
     * The first character of the category must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code PersonCategory}.
     *
     * @param personCategory A valid person category.
     */
    public PersonCategory(String personCategory) {
        requireNonNull(personCategory);
        checkArgument(isValidPersonCategory(personCategory), MESSAGE_CONSTRAINTS);
        value = personCategory;
    }

    /**
     * Returns true if a given string is a valid person type.
     */
    public static boolean isValidPersonCategory(String test) {
        boolean isValidCategory = test.equals("Buyer") || test.equals("Deliverer") || test.equals("Supplier")
                || test.equals("Test");

        return test.matches(VALIDATION_REGEX) && isValidCategory;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonCategory // instanceof handles nulls
                && value.equals(((PersonCategory) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

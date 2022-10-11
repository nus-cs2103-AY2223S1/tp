package seedu.address.model.person;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's type in the Address Book.
 * There are only three types of people - Buyer, Deliverer, and Supplier.
 */
public enum PersonCategory {
    BUYER("Buyer"),
    DELIVERER("Deliverer"),
    SUPPLIER("Supplier");

    public static final String MESSAGE_CONSTRAINTS =
            "PersonCategory should only contain alphanumeric characters, it is case sensitive and it should not "
                    + "be blank. It can only be one of the following types: 'Buyer', 'Deliverer' and 'Supplier'.";

    /*
     * The first character of the category must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs a {@code PersonCategory}.
     *
     * @param personCategory A valid person category.
     */
    PersonCategory(String personCategory) {
        value = personCategory;
    }

    /**
     * Converts a string representation to the actual enum value
     *
     * @param personCategory Either BUYER, DELIVERER, or SUPPLIER
     */
    public static PersonCategory getFromString(String personCategory) {
        checkArgument(isValidPersonCategory(personCategory), MESSAGE_CONSTRAINTS);
        return Arrays.stream(PersonCategory.class.getEnumConstants())
                .filter(x -> x.toString().equals(personCategory))
                .findFirst().orElse(PersonCategory.BUYER);
    }

    /**
     * Returns true if a given string is a valid person type.
     */
    public static boolean isValidPersonCategory(String test) {
        boolean isValidPersonCategory =
                Arrays.stream(PersonCategory.class.getEnumConstants()).map(x -> x.value).anyMatch(x -> x.equals(test));

        return test.matches(VALIDATION_REGEX) && isValidPersonCategory;
    }

    @Override
    public String toString() {
        return value;
    }
}

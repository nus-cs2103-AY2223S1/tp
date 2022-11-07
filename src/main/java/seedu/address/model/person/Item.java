package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Supplier's item in the person list.
 * Guarantees: immutable; is valid as declared in {@link #isValidItem(String)}
 */
public class Item {

    public static final String MESSAGE_CONSTRAINTS = "Item can take any values, "
            + "but it should not be blank or numbers only";

    /*
     * The first character of the item must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(?![0-9]+$)[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Item}.
     *
     * @param item A valid item.
     */
    public Item(String item) {
        requireNonNull(item);
        checkArgument(isValidItem(item), MESSAGE_CONSTRAINTS);
        value = item;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidItem(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Item // instanceof handles nulls
                && value.equals(((Item) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

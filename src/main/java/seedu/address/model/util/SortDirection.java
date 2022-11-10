package seedu.address.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a direction for the sort command.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortDirection(String)} (String)}
 */
public class SortDirection {
    public static final SortDirection INCREASING = new SortDirection("+");
    public static final SortDirection DECREASING = new SortDirection("-");


    public static final String MESSAGE_CONSTRAINTS =
        "Sort direction should be one of '+' or '-'";
    public static final String VALIDATION_REGEX = "[+-]";
    public final Boolean isIncreasing;

    /**
     * Constructs a {@code SortDirection} from the user input.
     *
     * @param direction A valid direction (+/-).
     */
    public SortDirection(String direction) {
        requireNonNull(direction);
        checkArgument(isValidSortDirection(direction), MESSAGE_CONSTRAINTS);
        isIncreasing = direction.strip().compareTo("+") == 0;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidSortDirection(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return isIncreasing ? "increasing" : "decreasing";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortDirection // instanceof handles nulls
            && isIncreasing.equals(((SortDirection) other).isIncreasing)); // state check
    }

    @Override
    public int hashCode() {
        return isIncreasing.hashCode();
    }

    public boolean isIncreasing() {
        return isIncreasing;
    }
}

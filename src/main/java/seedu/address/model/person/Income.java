package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's income in the FinBook.
 */
public class Income {

    public static final String MESSAGE_CONSTRAINTS =
            "Income should start with $ followed by numbers and should be at least 1 digit long."
                    + "\nExample: $1, $0 is still considered valid";
    public static final String VALIDATION_REGEX = "^[$]\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code Income}.
     *
     * @param income A valid income value.
     */
    public Income(String income) {
        requireNonNull(income);
        checkArgument(isValidIncome(income), MESSAGE_CONSTRAINTS);
        value = income;
    }

    public int toInt() {
        return Integer.parseInt(value.replace("$", ""));
    }

    /**
     * Returns true if a given string is a valid income.
     */
    public static boolean isValidIncome(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Income // instanceof handles nulls
                && value.equals(((Income) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

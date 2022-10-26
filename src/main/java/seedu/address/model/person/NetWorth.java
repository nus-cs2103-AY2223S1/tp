package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.ui.PersonProfile;

/**
 * Represents a Person's net worth in the address book.
 */
public class NetWorth {

    public static final String EMPTY_NETWORTH = "";
    public static final String MESSAGE_CONSTRAINTS =
            "Net worth should only contain numbers and net worth should be at least 4 digits";
    public static final String VALIDATION_REGEX = "[$][1-9]\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param netWorth A valid net worth.
     */
    public NetWorth(String netWorth) {
        requireNonNull(netWorth);
        checkArgument(isValidNetWorth(netWorth), MESSAGE_CONSTRAINTS);
        value = netWorth;
    }

    /**
     * Returns value of net worth.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns value of net worth if not null else, EMPTY_DISPLAY_VALUE.
     */
    public String getDisplayValue() {
        return value == EMPTY_NETWORTH ? PersonProfile.EMPTY_DISPLAY_VALUE : value;
    }

    /**
     * Returns true if a given string is a valid networth.
     */
    public static boolean isValidNetWorth(String test) {
        return test.equals(EMPTY_NETWORTH) || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NetWorth // instanceof handles nulls
                && value.equals(((NetWorth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's reward points in bobaBot.
 * Guarantees: immutable; is valid as declared in {@link #isValidReward(String)}
 */
public class Reward {

    public static final String MESSAGE_CONSTRAINTS = "Reward points can take any values, and it should not be blank";

    /*
     * The first character of the reward must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param reward A valid address.
     */
    public Reward(String reward) {
        requireNonNull(reward);
        checkArgument(isValidReward(reward), MESSAGE_CONSTRAINTS);
        int integerValue = Integer.valueOf(reward);
        value = String.valueOf(integerValue);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidReward(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reward // instanceof handles nulls
                && value.equals(((Reward) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

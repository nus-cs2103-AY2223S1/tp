package seedu.boba.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.util.AppUtil.checkArgument;

import java.nio.charset.StandardCharsets;

/**
 * Represents a Customer's reward points in bobaBot.
 * Guarantees: immutable; is valid as declared in {@link #isValidReward(String)}
 */
public class Reward {

    public static final String MESSAGE_CONSTRAINTS = "Reward points can take any NON-NEGATIVE values"
            + ", and it should not be blank";
    public static final String MESSAGE_MAX_INTEGER = "Reward points must be POSITIVE integers and"
            + " CANNOT take any values more than 2147483647 (Maximum integer value)";
    public static final String MESSAGE_MAX_EXCEEDED = "Reward points exceeded 2147483647 (Maximum integer value)!";
    public static final String MESSAGE_NEGATIVE = "Reward points will be NEGATIVE after decrement. Please check again!";

    /*
     * The first character of the reward must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;
    public final String displayValue;

    /**
     * Constructs an {@code Reward}.
     *
     * @param reward A valid reward.
     */
    public Reward(String reward) {
        requireNonNull(reward);
        checkArgument(isValidReward(reward), MESSAGE_CONSTRAINTS);
        int integerValue = Integer.valueOf(reward);
        checkArgument(integerValue >= 0, MESSAGE_CONSTRAINTS);
        value = String.valueOf(integerValue);

        // @@author tanwencong-reused
        // Reused from https://stackoverflow.com/questions/22872484/javafx-how-can-i-display-emoji#:~:text=Follow%20these%20steps%20to%20add%20Emoji%20as%20a%20text%3A
        // with minor modifications
        // Generate reward emoji
        byte[] emojiByteCode = new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x8E, (byte) 0x81};
        String emoji = new String(emojiByteCode, StandardCharsets.UTF_8);
        // @@author

        displayValue = emoji + " " + value;
    }

    /**
     * Returns true if a given string is a valid reward.
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

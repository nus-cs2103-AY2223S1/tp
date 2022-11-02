package seedu.boba.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.util.AppUtil.checkArgument;

import java.nio.charset.StandardCharsets;

/**
 * Represents a Customer's birthday in bobaBot.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthdayMonth(String)}
 */
public class BirthdayMonth {

    public static final String MESSAGE_CONSTRAINTS = "Birthday month should not be blank. It should be between 1 - 12.";

    /*
     * The first character of the birthdayMonth must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String[] monthStrings = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
        "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    public final String value;
    public final String monthString;

    /**
     * Constructs a {@code BirthdayMonth}.
     *
     * @param birthdayMonth A valid birthdayMonth.
     */
    public BirthdayMonth(String birthdayMonth) {
        requireNonNull(birthdayMonth);
        checkArgument(isValidBirthdayMonth(birthdayMonth), MESSAGE_CONSTRAINTS);
        int integerValue = Integer.valueOf(birthdayMonth);

        assert integerValue > 0 && integerValue < 13;

        // @@author tanwencong-reused
        // Reused from https://stackoverflow.com/questions/22872484/javafx-how-can-i-display-emoji#:~:text=Follow%20these%20steps%20to%20add%20Emoji%20as%20a%20text%3A
        // with minor modifications
        // Generate birthday cake emoji
        byte[] emojiByteCode = new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x8E, (byte) 0x82};
        String emoji = new String(emojiByteCode, StandardCharsets.UTF_8);
        // @@author

        monthString = emoji + " " + monthStrings[integerValue - 1];
        value = String.valueOf(integerValue);
    }

    /**
     * Returns true if a given string is a valid birthdayMonth.
     */
    public static boolean isValidBirthdayMonth(String test) {
        return test.matches(VALIDATION_REGEX) && isNumeric(test)
            && Integer.valueOf(test) > 0 && Integer.valueOf(test) < 13;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof BirthdayMonth // instanceof handles nulls
            && value.equals(((BirthdayMonth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

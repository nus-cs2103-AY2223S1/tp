package seedu.boba.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.util.AppUtil.checkArgument;

import java.nio.charset.StandardCharsets;

/**
 * Represents a Customer's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be EXACTLY 8 digits long";
    public static final String VALIDATION_REGEX = "\\d{8,}";
    public final String value;
    public final String displayValue;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;

        // @@author tanwencong-reused
        // Reused from https://stackoverflow.com/questions/22872484/javafx-how-can-i-display-emoji
        // with minor modifications
        // Generate phone emoji
        byte[] emojiByteCode = new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x93, (byte) 0xB1};
        String emoji = new String(emojiByteCode, StandardCharsets.UTF_8);
        // @@author

        displayValue = emoji + " " + value;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() == 8;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

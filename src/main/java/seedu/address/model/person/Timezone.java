package seedu.address.model.person;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Represents a Software Engineer's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimezone(String)}
 */
public class Timezone {
    public static final String MESSAGE_CONSTRAINTS =
        "Timezone should be a number with sign (+18 to -18), and it should not be blank";

    public static final String VALIDATION_REGEX = "[+-][\\d]{1,2}";
    public final String timezone;

    /**
     * Constructs an {@code Role}.
     *
     * @param timezone A valid timezone.
     */
    public Timezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidTimezone(String timezoneString) {
        if (!timezoneString.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            int offset = Integer.valueOf(timezoneString);

            return offset <= 18 && offset >= -18;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    @Override
    public String toString() {
        ZoneOffset currentUtcOffSet = ZoneId.systemDefault().getRules().getOffset(Instant.now());
        ZoneOffset timezoneUtcOffSet = ZoneOffset.ofHours(Integer.valueOf(timezone));

        // Seconds to Hours
        int difference = (currentUtcOffSet.getTotalSeconds() - timezoneUtcOffSet.getTotalSeconds()) / 3600;

        if (difference == 0) {
            return "same time as you";
        }

        return Math.abs(difference)
                + ((Math.abs(difference) <= 1) ? " hour" : " hours")
                + (difference > 0 ? " behind" : " ahead");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timezone // instanceof handles nulls
                && this.timezone.equals(((Timezone) other).timezone)); // state check
    }

    @Override
    public int hashCode() {
        return this.timezone.hashCode();
    }

}

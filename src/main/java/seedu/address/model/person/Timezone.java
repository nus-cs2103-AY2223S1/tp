package seedu.address.model.person;

/**
 * Represents a Software Engineer's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimezone(String)}
 */
public class Timezone {
    public static final String MESSAGE_CONSTRAINTS =
        "Timezone should be a number with sign, and it should not be blank";

    public static final String VALIDATION_REGEX = "[+-][\\d]";
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
        return  timezoneString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.timezone;
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

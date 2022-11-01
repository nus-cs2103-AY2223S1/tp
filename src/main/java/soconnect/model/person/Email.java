package soconnect.model.person;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the SoConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}.
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";
    public static final String MESSAGE_EMAIL_TOO_LONG =
            "The Email is too long. Keep it within 45 characters, including whitespaces.";
    public static final int CHARACTER_LIMIT = 45;
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*"
            + DOMAIN_PART_REGEX + "\\."
            + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLength(email), MESSAGE_EMAIL_TOO_LONG);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string does not exceed the character limit.
     *
     * @param text The given string.
     * @return True if it does not exceed the character limit. False if otherwise.
     */
    public static boolean isValidLength(String text) {
        return text.length() > CHARACTER_LIMIT ? false : true;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Compares this Email to another Email.
     *
     * @param other The other Email object.
     * @return      -1 if this object is lesser, 0 if they are equal, 1 otherwise.
     */
    public int compareTo(Email other) {
        return value.compareToIgnoreCase(other.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

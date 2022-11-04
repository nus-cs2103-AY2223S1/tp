package seedu.address.model.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Profile's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters. Moreover, special characters may not be used consecutively.\n"
            + "2. Emails must begin and end with an alphabet or number.\n"
            + "3. This is followed by a '@' and then a domain name. The domain name must one of the following "
            + "NUS email domains:\n"
            + "    - u.nus.edu\n"
            + "    - u.duke.nus.edu\n"
            + "    - nus.edu.sg\n"
            + "    - comp.nus.edu.sg\n"
            + "    - u.yale-nus.edu.sg";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";

    private static final String DOMAIN_STUDENT_EMAIL = "u(\\.duke)?\\.nus\\.edu";
    private static final String DOMAIN_STAFF_EMAIL = "(comp\\.)?nus\\.edu\\.sg";
    private static final String DOMAIN_YALE_NUS_EMAIL = "u\\.yale-nus\\.edu\\.sg";
    private static final String DOMAIN_REGEX = "(" + DOMAIN_STUDENT_EMAIL + "|" + DOMAIN_STAFF_EMAIL + "|"
            + DOMAIN_YALE_NUS_EMAIL + ")$";

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
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.toLowerCase().equals(((Email) other).value.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

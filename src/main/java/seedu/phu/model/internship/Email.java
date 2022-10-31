package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.util.AppUtil.checkArgument;

/**
 * Represents a Internship's email in the internship book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {
    public static final String DEFAULT_VALUE = "NA";
    public static final int MAX_LENGTH = 254;
    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - have at least 2 domain labels, with the last label being the top level domain label.\n"
            + "    - have the top level domain label at least 2 characters long.\n"
            + "    - have the top level domain label ends with an alphabet.\n"
            + "    - have the top level domain label separated from the other domain labels by a period.\n"
            + "    - have each domain label start and end with alphanumeric characters.\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.\n"
            + "3. Emails must not exceed 254 characters.";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String ALPHABET = "[a-zA-Z]+";
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";

    // At least two chars, first char must be alphabet
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + ")+" + ALPHABET + "$";
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.){1,}" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email internship.
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
        return (test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH) || test.equals(DEFAULT_VALUE);
    }

    @Override
    public String toString() {
        return value;
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

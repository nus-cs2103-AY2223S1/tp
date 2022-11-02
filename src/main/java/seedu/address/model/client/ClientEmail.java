package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the project book.
 */
public class ClientEmail {
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

    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    private String email;

    /**
     * Constructs a Client Email.
     *
     * @param email A valid email address.
     */
    public ClientEmail(String email) {
        requireNonNull(email);
        checkArgument(isValidClientEmail(email), MESSAGE_CONSTRAINTS);
        this.email = email;
    }

    /**
     * Represents an Empty ClientEmail.
     */
    public static class EmptyEmail extends ClientEmail {
        public static final ClientEmail EMPTY_EMAIL = new EmptyEmail();
        public EmptyEmail() {
            super("cs2103@nus.edu");
        }

        @Override
        public String toString() {
            return "";
        }

        @Override
        public String uiRepresentation() {
            return "No email set";
        }

    }

    /**
     * Checks if this Client Email is empty.
     * @return true if the Client Email is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientEmail // instanceof handles nulls
                && this.email.equals(((ClientEmail) other).email)); // state check
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    public static boolean isValidClientEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the email of the client.
     * @return String representing email
     */
    public String uiRepresentation() {
        return "Email: " + this.email;
    }

    /**
     * Returns the String representation of the Client Email.
     * @return String representing the Client Email
     */
    @Override
    public String toString() {
        return this.email;
    }

}

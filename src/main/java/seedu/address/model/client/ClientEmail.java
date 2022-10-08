package seedu.address.model.client;

import seedu.address.model.person.Email;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the class for Client's Email address. This is modelled after Email in Person package of AB3,
 * particularly in checking the validity of emails.
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

    public String email;

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
     * Represents an Empty Email.
     */
    private static class EmptyClientEmail extends ClientEmail {
        private static final ClientEmail EMPTY_EMAIL = new EmptyClientEmail();
        private EmptyClientEmail() {
            super("");
        }
        /**
         * Checks if this Client Email is empty.
         * @return true if the Client Email is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
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
    public static boolean isValidClientEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the email of the client.
     * @return String representing email
     */
    public String getFullName() {
        return this.email;
    }

    /**
     * Returns the String representation of the Client Email.
     * @return String representing the Client Email
     */
    @Override
    public String toString() {
        return "Client Email: " + this.email;
    }

    /**
     * Checks if an object equals this.
     * @param other Object to be checked
     * @return boolean true if this is equal to other and false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClientEmail){
            ClientEmail otherEmail = (ClientEmail) other;
            return this.email.equals(otherEmail.email);
        } else {
            return false;
        }
    }

}


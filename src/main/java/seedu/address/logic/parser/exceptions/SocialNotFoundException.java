package seedu.address.logic.parser.exceptions;

/**
 * Signals that the operation is unable to find the specified social media platform.
 */
public class SocialNotFoundException extends ParseException {
    public SocialNotFoundException() {
        super("Social media handle format is incorrect.");
    }
}

package seedu.address.logic.parser.exceptions;

public class SocialNotFoundException extends ParseException {
    public SocialNotFoundException() {
        super("Social media handle format is incorrect.");
    }
}

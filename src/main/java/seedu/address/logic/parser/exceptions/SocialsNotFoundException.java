package seedu.address.logic.parser.exceptions;
import seedu.address.logic.parser.exceptions.ParseException;

public class SocialsNotFoundException extends ParseException {
    public SocialsNotFoundException() {
        super("Social media handle format is incorrect.");
    }
}

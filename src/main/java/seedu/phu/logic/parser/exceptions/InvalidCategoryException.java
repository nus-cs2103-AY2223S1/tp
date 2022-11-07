package seedu.phu.logic.parser.exceptions;

/**
 * Represents an exception when a user inputs an invalid category.
 */
public class InvalidCategoryException extends ParseException {

    public InvalidCategoryException(String message) {
        super(message);
    }
}

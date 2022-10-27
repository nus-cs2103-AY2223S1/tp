package seedu.clinkedin.model.person.exceptions;

/**
 * Signals that the operation is unable to find the specified tag type.
 */
public class TagTypeNotFoundException extends RuntimeException {
    public TagTypeNotFoundException() {
        super("Tag type not found!");
    }
}

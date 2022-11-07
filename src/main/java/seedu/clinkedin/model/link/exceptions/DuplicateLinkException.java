package seedu.clinkedin.model.link.exceptions;

/**
 * Signals that the operation will result in duplicate links for a person.
 */
public class DuplicateLinkException extends RuntimeException {
    public DuplicateLinkException(String link) {
        super("This link already exists: " + link);
    }
}

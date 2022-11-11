package seedu.address.model.team.exceptions;

/**
 * Signals that the operation will result in duplicate links.
 */
public class DuplicateLinkException extends RuntimeException {

    public DuplicateLinkException() {
        super("Operation would result in duplicate links with the same displayed name");
    }
}

package seedu.address.model.team.exceptions;

public class DuplicateLinkException extends RuntimeException {

    public DuplicateLinkException() {
        super("Operation would result in duplicate links with the same displayed name");
    }
}

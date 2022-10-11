package seedu.address.model.team.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTeamException extends RuntimeException {
    public DuplicateTeamException() {
        super("Operation would result in duplicate teams");
    }
}

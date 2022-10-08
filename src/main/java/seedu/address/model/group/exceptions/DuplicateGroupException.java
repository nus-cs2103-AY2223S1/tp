package seedu.address.model.group.exceptions;

/**
 * Signals that the operation will result in duplicate Groups (
 * Groups are considered the same if their name and set of
 * members are the same).
 */
public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Operation would result in duplicate groups");
    }
}

package seedu.address.model.profile.exceptions;

/**
 * Signals that the operation will result in duplicate Profiles (Profiles are considered duplicates if they have the same
 * identity).
 */
public class DuplicateProfileException extends RuntimeException {
    public DuplicateProfileException() {
        super("Operation would result in duplicate profiles");
    }
}

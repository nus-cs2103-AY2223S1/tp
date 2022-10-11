package seedu.address.model.profile.exceptions;

/**
 * Signals that the operation will result in duplicate Profiles (Profiles are duplicates if they have the same
 * identity).
 */
public class SimilarProfileException extends RuntimeException {
    public SimilarProfileException() {
        super("Operation would result in duplicate profiles");
    }
}

package seedu.address.model.profile.exceptions;

/**
 * Signals that the operation will result in similar Profiles (Profiles are similar if they have the same
 * email, phone or telegram).
 */
public class SimilarProfileException extends RuntimeException {
    public SimilarProfileException() {
        super("Operation would result in similar profiles");
    }
}

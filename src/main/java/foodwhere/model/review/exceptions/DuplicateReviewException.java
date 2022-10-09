package foodwhere.model.review.exceptions;

/**
 * Signals that the operation will result in duplicate Reviews (Reviews are considered duplicates if they have the same
 * identity).
 */
public class DuplicateReviewException extends RuntimeException {
    public DuplicateReviewException() {
        super("Operation would result in duplicate reviews");
    }
}

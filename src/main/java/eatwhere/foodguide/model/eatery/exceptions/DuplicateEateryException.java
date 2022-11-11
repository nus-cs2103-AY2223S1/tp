package eatwhere.foodguide.model.eatery.exceptions;

/**
 * Signals that the operation will result in duplicate Eateries (Eateries are considered duplicates if
 * they have the same identity).
 */
public class DuplicateEateryException extends RuntimeException {
    public DuplicateEateryException() {
        super("Operation would result in duplicate eateries");
    }
}

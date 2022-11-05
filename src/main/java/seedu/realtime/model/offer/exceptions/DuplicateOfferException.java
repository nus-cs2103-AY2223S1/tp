package seedu.realtime.model.offer.exceptions;

/**
 * Signals that the operation will result in duplicate Offers (Offers are considered duplicates if they have the same
 * identity).
 */
public class DuplicateOfferException extends RuntimeException {
    public DuplicateOfferException() {
        super("Operation would result in duplicate offers");
    }

}

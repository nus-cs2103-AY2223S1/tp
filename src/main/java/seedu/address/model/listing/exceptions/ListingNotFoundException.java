package seedu.address.model.listing.exceptions;

/**
 * Signals that the operation is unable to find the specified listing.
 */
public class ListingNotFoundException extends RuntimeException {
    public ListingNotFoundException(String message) {
        super(message);
    }
}

package seedu.address.model.listing.exceptions;

/**
 * Signals that the operation will result in duplicate Listings
 * (Listings are considered duplicates if they have the same address).
 */
public class DuplicateListingException extends RuntimeException {
    public DuplicateListingException() {
        super("Listing already exist in REal-Time.");
    }
}

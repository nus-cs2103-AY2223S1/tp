package seedu.realtime.model.listing;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.commons.util.AppUtil.checkArgument;

/**
 * Represents a ListingId of a specific listing in the address book.
 */
public class ListingId {
    public static final String MESSAGE_CONSTRAINTS =
        "ListingId should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@Code ListingId}
     *
     * @param id A valid listingID.
     */
    public ListingId(String id) {
        requireNonNull(id);
        checkArgument(isValidListingId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid listingID.
     */
    public static boolean isValidListingId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListingId // instanceof handles nulls
            && value.equals(((ListingId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

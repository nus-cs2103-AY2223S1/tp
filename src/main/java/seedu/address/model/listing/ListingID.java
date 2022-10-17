package seedu.address.model.listing;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ListingID of a specific listing in the address book.
 */
public class ListingID {
    public static final String MESSAGE_CONSTRAINTS =
        "ListingID should not be blank";
    public static final String VALIDATION_REGEX = "[\\S]*";
    public final String value;

    /**
     * Constructs a {@Code ListingID}
     *
     * @param id A valid listingID.
     */
    public ListingID(String id) {
        requireNonNull(id);
        checkArgument(isValidListingID(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid listingID.
     */
    public static boolean isValidListingID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListingID // instanceof handles nulls
            && value.equals(((ListingID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

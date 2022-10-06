package seedu.address.model.role;

import java.util.Optional;

/**
 * Placeholder
 */
public class PriceRange {
    public static final String MESSAGE_CONSTRAINTS =
            "Price range should contain exactly 2 numbers.";

    private Optional<? extends Float> low;
    private Optional<? extends Float> high;

    /**
     * Placeholder
     */
    public PriceRange(Optional<? extends Float> low, Optional<? extends Float> high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return low + "-" + high;
    }

    // Strings passed in will be non-null, so can check for validity assuming that there is an entry
    public static boolean isValidPriceRange(String test) {
        return true;
    }
}

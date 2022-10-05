package seedu.address.model.role;

import java.util.Optional;

public class PriceRange {
    private Optional<Float> low;
    private Optional<Float> high;

    public static final String MESSAGE_CONSTRAINTS =
            "Price range should contain exactly 2 numbers.";
    
    public PriceRange(Optional<Float> low, Optional<Float> high) {
        this.low = low;
        this.high = high;
    }
    
    @Override
    public String toString() {
        return low + "-" + high;
    }
}

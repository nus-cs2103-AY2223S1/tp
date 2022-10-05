package seedu.address.model.role;

public class PriceRange {
    private float low;
    private float high;

    public static final String MESSAGE_CONSTRAINTS =
            "Price range should contain exactly 2 numbers.";
    
    public PriceRange(float low, float high) {
        this.low = low;
        this.high = high;
    }
    
    @Override
    public String toString() {
        return low + "-" + high;
    }
}
